package com.picpay.simple.modules.transfer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.simple.modules.transfer.dto.CreateTransferDto;
import com.picpay.simple.modules.transfer.dto.ResponseTransferDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transfers")
@RequiredArgsConstructor
public class TransferController {
  
  private final TransferService service;

  @PostMapping
  public ResponseTransferDto create(@Valid @RequestBody CreateTransferDto dto) {
    return service.create(dto);
  }
}
