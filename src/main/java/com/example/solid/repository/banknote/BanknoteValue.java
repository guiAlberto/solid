package com.example.solid.repository.banknote;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Value
 */
@AllArgsConstructor
@Getter
public enum BanknoteValue {

    ONE(1), TWO(2), FIVE(5), TEN(10), TWENTY(20), FIFTY(50), ONE_HUNDRED(100);

    private int value;
}
