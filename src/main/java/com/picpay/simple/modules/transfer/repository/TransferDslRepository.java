package com.picpay.simple.modules.transfer.repository;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.picpay.simple.modules.transfer.QTransferEntity;
import com.picpay.simple.modules.transfer.TransferEntity;
import com.querydsl.jpa.impl.JPAQuery;

@Repository
public class TransferDslRepository extends QuerydslRepositorySupport {

  private final EntityManager entityManager;

  public TransferDslRepository(EntityManager entityManager) {
    super(TransferEntity.class);
    this.setEntityManager(entityManager);
    this.entityManager = entityManager;
  }

  public List<TransferEntity> findAllByUser(Long id) {
    JPAQuery<TransferEntity> query = new JPAQuery<>(entityManager);
    QTransferEntity transfer = QTransferEntity.transferEntity;

    BooleanExpression findPayeeId = transfer.payee.id.eq(id);
    BooleanExpression findPayerId = transfer.payer.id.eq(id);

    BooleanExpression findPayeeOrPayer = findPayeeId.or(findPayerId);

    return query.from(transfer)
        .where(findPayeeOrPayer)
        .orderBy(transfer.date.desc())
        .stream().toList();
  }
}
