package com.picpay.simple.modules.transfer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.picpay.simple.modules.transfer.dto.CreateTransferDto;
import com.picpay.simple.modules.transfer.dto.ResponseTransferDto;
import com.picpay.simple.modules.user.UserEntity;
import com.picpay.simple.modules.user.UserMapper;

@Mapper
public interface TransferMapper {

  TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "date", ignore = true)
  @Mapping(target = "payer", source = "payer")
  @Mapping(target = "payee", source = "payee")
  TransferEntity toEntity(CreateTransferDto dto, UserEntity payer, UserEntity payee);

  ResponseTransferDto toDto(TransferEntity entity);
}
