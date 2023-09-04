package com.picpay.simple.modules.transfer;

import org.springframework.web.bind.annotation.*;

import com.picpay.simple.modules.transfer.dto.CreateTransferDto;
import com.picpay.simple.modules.transfer.dto.ResponseTransferDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransferController {
  
  private final TransferService service;

  @PostMapping
  public ResponseTransferDto create(@Valid @RequestBody CreateTransferDto dto) {
    return service.create(dto);
  }

  @GetMapping("user/{id}")
  public List<ResponseTransferDto> findAllByUser(@PathVariable Long id) {
    return service.findAllByUser(id);
  }
}
