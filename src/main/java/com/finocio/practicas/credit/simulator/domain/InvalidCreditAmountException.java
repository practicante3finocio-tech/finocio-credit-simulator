package com.finocio.practicas.credit.simulator.domain;

public class InvalidCreditAmountException extends RuntimeException {
  public InvalidCreditAmountException(String message) {
    super(message);
  }
}
