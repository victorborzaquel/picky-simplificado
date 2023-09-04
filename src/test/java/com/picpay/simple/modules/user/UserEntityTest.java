package com.picpay.simple.modules.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserEntityTest {

  private UserEntity userEntity;

  @BeforeEach
  void setUp() {
    userEntity = createUserEntity();
  }

  private UserEntity createUserEntity() {
    return UserEntity.builder()
        .id(1L)
        .name("User")
        .document("12345678901")
        .email("victor@mail.com")
        .password("123456")
        .type(UserTypeEnum.COMUM)
        .money(BigDecimal.valueOf(10))
        .build();
  }

  @Test
  void shouldUserEntityCreate() {
    assertEquals(1L, userEntity.getId());
    assertEquals("User", userEntity.getName());
    assertEquals("12345678901", userEntity.getDocument());
    assertEquals("victor@mail.com", userEntity.getEmail());
    assertEquals("123456", userEntity.getPassword());
    assertEquals(UserTypeEnum.COMUM, userEntity.getType());
    assertEquals(BigDecimal.valueOf(10), userEntity.getMoney());
  }

  @Test
  void shouldAddMoney() {
    userEntity.addMoney(BigDecimal.valueOf(10));

    assertEquals(BigDecimal.valueOf(20), userEntity.getMoney());
  }

  @Test
  void shouldSubtractMoney() {
    userEntity.subtractMoney(BigDecimal.valueOf(5));

    assertEquals(BigDecimal.valueOf(5), userEntity.getMoney());
  }

  @Test
  void shouldThrowExceptionWhenAddMoneyWithNegativeValue() {
    assertThrows(IllegalArgumentException.class, () -> userEntity.addMoney(BigDecimal.valueOf(-10)),
        "Value must be greater than zero");
  }

  @Test
  void shouldThrowExceptionWhenSubtractMoneyWithNegativeValue() {
    assertThrows(IllegalArgumentException.class, () -> userEntity.subtractMoney(BigDecimal.valueOf(-10)),
        "Value must be greater than zero");
  }
}