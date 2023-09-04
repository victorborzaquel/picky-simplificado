package com.picpay.simple.modules.transfer.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.picpay.simple.modules.user.dto.ResponseUserDto;

public record ResponseTransferDto(
    Long id,
    LocalDateTime date,
    ResponseUserDto payer,
    ResponseUserDto payee,
    BigDecimal value) {

}
