package com.picpay.simple.exceptions;

import lombok.Builder;

@Builder
public record ResponseExceptionDto(
    Integer code,
    String reason,
    String message) {

}
