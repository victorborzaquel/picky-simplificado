package com.picpay.simple.modules.user;

import java.math.BigDecimal;
import java.util.List;

import com.picpay.simple.exceptions.ConflictException;
import com.picpay.simple.modules.transfer.TransferEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
@EqualsAndHashCode(of = "id")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private UserTypeEnum type;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String document;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private BigDecimal money;

  @OneToMany(mappedBy = "payer")
  private List<TransferEntity> transfersAsPayer;

  @OneToMany(mappedBy = "payee")
  private List<TransferEntity> transfersAsPayee;

  public BigDecimal addMoney(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0){
      throw new ConflictException("Value must be greater than zero");
    }

    this.money = this.money.add(value);

    return this.money;
  }

  public BigDecimal subtractMoney(BigDecimal value) {
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new ConflictException("Value must be greater than zero");
    }
    if (this.money.compareTo(value) < 0) {
      throw new ConflictException("Insufficient funds");
    }

    this.money = this.money.subtract(value);
    
    return this.money;
  }
}
