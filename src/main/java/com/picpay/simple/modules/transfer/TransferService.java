package com.picpay.simple.modules.transfer;

import java.util.Collections;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.picpay.simple.exceptions.ConflictException;
import com.picpay.simple.modules.transfer.dto.CreateTransferDto;
import com.picpay.simple.modules.transfer.dto.ResponseTransferDto;
import com.picpay.simple.modules.transfer.dto.authorizeDto;
import com.picpay.simple.modules.transfer.repository.TransferRepository;
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

 
    WebClient client = WebClient.create("https://run.mocky.io");
    var isAutorize = client.post().uri("/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6").bodyValue(dto).retrieve().bodyToMono(authorizeDto.class).block();

    if (!isAutorize.message().equals("Autorizado")) {
      throw new ConflictException("Transfer not authorized");
    }

    // var notification = restTemplate.postForEntity("http://o4d9z.mocklab.io/notify", dto, authorizeDto.class);

    return TransferMapper.INSTANCE.toDto(transfer);
  }
}
