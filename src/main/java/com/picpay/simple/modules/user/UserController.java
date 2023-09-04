package com.picpay.simple.modules.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.simple.modules.user.dto.CreateUserDto;
import com.picpay.simple.modules.user.dto.ResponseUserDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
  
  private final UserService service;

  @PostMapping
  public ResponseUserDto create(@Valid @RequestBody CreateUserDto dto) {
    return service.create(dto);
  }
}
