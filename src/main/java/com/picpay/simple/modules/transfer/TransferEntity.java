package com.picpay.simple.modules.transfer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.picpay.simple.modules.user.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transfers")
@EqualsAndHashCode(of = "id")
public class TransferEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreationTimestamp
  @Column(nullable = false, updatable = false)
  private LocalDateTime date;

  @ManyToOne(optional = false)
  @JoinColumn(name = "payer_id", nullable = false)
  private UserEntity payer;

  @ManyToOne(optional = false)
  @JoinColumn(name = "payee_id", nullable = false)
  private UserEntity payee;

  @Column(nullable = false)
  private BigDecimal value;

}
