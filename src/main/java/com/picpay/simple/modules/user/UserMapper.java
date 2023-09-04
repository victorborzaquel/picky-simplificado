package com.picpay.simple.modules.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.picpay.simple.modules.user.dto.CreateUserDto;
import com.picpay.simple.modules.user.dto.ResponseUserDto;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "id", ignore = true)
  UserEntity toEntity(CreateUserDto dto);

  ResponseUserDto toDto(UserEntity entity);
}
