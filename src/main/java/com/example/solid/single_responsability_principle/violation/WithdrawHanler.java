package com.example.solid.single_responsability_principle.violation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import com.example.solid.controller.WithdrawDto;
import com.example.solid.repository.banknote.Banknote;
import com.example.solid.repository.banknote.BanknoteRepository;
import com.example.solid.repository.banknote.BanknoteValue;
import com.example.solid.repository.user.User;
import com.example.solid.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * WithdrawHanler
 */
@Component
public class WithdrawHanler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BanknoteRepository banknoteRepository;

    public Banknote[] handle(final WithdrawDto dto) {
        Long remainingValue = dto.getValue();
        List<BanknoteValue> banknoteValues = Arrays.asList(BanknoteValue.values());
        banknoteValues.sort((v1, v2) -> v2.getValue() - v1.getValue());
        Map<BanknoteValue, Long> notesMap = new HashMap<>();
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(dto.getPassword())) {
                for (BanknoteValue banknoteValue : banknoteValues) {
                    Long availableAmout = banknoteRepository.countByValue(banknoteValue);
                    Long amount = remainingValue / banknoteValue.getValue();
                    Long result = Math.min(amount, availableAmout);
                    notesMap.put(banknoteValue, result);
                    remainingValue -= result * banknoteValue.getValue();
                }
            } else {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        if (!remainingValue.equals(0l)) {
            throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE);
        }

        List<Banknote> banknotes = null;
        for (Entry<BanknoteValue, Long> entry : notesMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                Pageable page = PageRequest.of(0, entry.getValue().intValue());
                Page<Banknote> banknotesPage = banknoteRepository.findAllByValue(entry.getKey(), page);
                banknotes = banknotesPage.getContent();
            }
        }
        return banknotes.toArray(new Banknote[banknotes.size()]);
    }
}
