package com.picpay.simple.modules.transfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.picpay.simple.modules.transfer.TransferEntity;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

}
