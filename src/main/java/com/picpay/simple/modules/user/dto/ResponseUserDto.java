package com.picpay.simple.modules.user.dto;

import java.math.BigDecimal;

public record ResponseUserDto(
    Long id,
    String name,
    String document,
    String email,
    String type,
    BigDecimal money) {
}
