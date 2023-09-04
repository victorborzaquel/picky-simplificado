package com.picpay.simple.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends AppException {

  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, message);
  }
  
}
