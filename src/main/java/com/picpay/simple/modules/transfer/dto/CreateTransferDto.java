package com.picpay.simple.modules.transfer.dto;

import java.math.BigDecimal;

public record CreateTransferDto(
    Long payer,
    Long payee,
    BigDecimal value) {

}
