package com.example.solid.controller;

import lombok.Data;

/**
 * WithdrawDto
 */
@Data
public class WithdrawDto {

    private Long userId;
    private String password;
    private Long value;

}