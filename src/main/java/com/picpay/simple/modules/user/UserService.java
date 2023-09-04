package com.picpay.simple.modules.user;

import org.springframework.stereotype.Service;

import com.picpay.simple.exceptions.NotFoundException;
import com.picpay.simple.modules.user.dto.CreateUserDto;
import com.picpay.simple.modules.user.dto.ResponseUserDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository repository;
  
  public UserEntity find(Long id) {
    return repository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

  public void save(UserEntity entity) {
    repository.save(entity);
  }

  public ResponseUserDto create(CreateUserDto dto) {
    UserEntity entity = UserMapper.INSTANCE.toEntity(dto);
    UserEntity response = repository.save(entity);
    return UserMapper.INSTANCE.toDto(response);
  }
  
}
