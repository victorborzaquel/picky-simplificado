package com.picpay.simple.modules.transfer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpay.simple.exceptions.ConflictException;
import com.picpay.simple.modules.transfer.dto.CreateTransferDto;
import com.picpay.simple.modules.transfer.dto.ResponseTransferDto;
import com.picpay.simple.modules.transfer.dto.authorizeDto;
import com.picpay.simple.modules.user.UserEntity;
import com.picpay.simple.modules.user.UserService;
import com.picpay.simple.modules.user.UserTypeEnum;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

  private final TransferRepository repository;
  private final UserService userService;
  private final RestTemplate restTemplate;

  @Transactional
  public ResponseTransferDto create(CreateTransferDto dto) {
    UserEntity origin = userService.find(dto.payer());
    UserEntity destination = userService.find(dto.payee());

    if (origin.getType() == UserTypeEnum.SELLER) {
      throw new ConflictException("Seller user can't transfer");
    }

    origin.subtractMoney(dto.value());
    destination.addMoney(dto.value());

    TransferEntity transfer = TransferMapper.INSTANCE.toEntity(dto, origin, destination);

    userService.save(origin);
    userService.save(destination);
    repository.save(transfer);

    var isAutorize = restTemplate.postForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", dto, authorizeDto.class);

    if (!isAutorize.getBody().message().equals("Autorizado")) {
      throw new ConflictException("Transfer not authorized");
    }

    return TransferMapper.INSTANCE.toDto(transfer);
  }
}
