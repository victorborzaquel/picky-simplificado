package com.picpay.simple.modules.transfer.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration.DslContextConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.picpay.simple.modules.transfer.QTransferEntity;
import com.picpay.simple.modules.transfer.TransferEntity;
import com.querydsl.core.types.dsl.DslExpression;
import com.querydsl.core.types.dsl.DslOperation;
import com.querydsl.jpa.impl.JPAQuery;
// QuerydslRepositorySupport implements ProductRepositoryCustom 
@Repository
public class TransferDslRepository extends QuerydslRepositorySupport {
  public TransferDslRepository() {
    super(TransferEntity.class);
  }

  Optional<TransferEntity> findAllByUser(Long id) {
    JPAQuery query = new JPAQuery(entityManager);
    QTransferEntity transfer = QTransferEntity.transferEntity;
    query.
    return Optional.empty();
  }
}
