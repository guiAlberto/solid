package com.example.solid.controller;

import com.example.solid.handler.WithdrawHanler;
import com.example.solid.repository.banknote.Banknote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * WithdrawController
 */
@RestController
public class WithdrawController {

    @Autowired
    private WithdrawHanler handler;

    @GetMapping(value="/withdraw")
    public Banknote[] withdraw(@RequestParam WithdrawDTO dto) {
        return handler.handle(dto);
    }
    
}