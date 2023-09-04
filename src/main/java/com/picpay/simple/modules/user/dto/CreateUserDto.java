package com.picpay.simple.modules.user.dto;

import java.math.BigDecimal;

import com.picpay.simple.modules.user.UserTypeEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
    UserTypeEnum type,
    @NotEmpty String name,
    @NotEmpty String document,
    @NotEmpty @Email String email,
    @NotNull BigDecimal money,
    @NotEmpty @Min(4) String password) {
}
