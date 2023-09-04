package com.picpay.simple.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
  private final Integer code;
  private final String reason;
  private final String message;

  public AppException(HttpStatus status, String message) {
    super(message);

    this.message = message;
    this.code = status.value();
    this.reason = status.getReasonPhrase();
  }
}
