package com.example.solid.controller;

import lombok.Data;

/**
 * WithdrawDTO
 */
@Data
public class WithdrawDTO {

    private Long userId;
    private String password;
    private Integer value;

}