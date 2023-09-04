package com.picpay.simple.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpay.simple.exceptions.AppException;
import com.picpay.simple.exceptions.ResponseExceptionDto;

@RestControllerAdvice
public class ExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(AppException.class)
  public ResponseEntity<ResponseExceptionDto> handle(AppException e) {
    var response = ResponseExceptionDto.builder()
        .code(e.getCode())
        .reason(e.getReason())
        .message(e.getMessage())
        .build();

    return ResponseEntity.status(e.getCode()).body(response);
  }
}
