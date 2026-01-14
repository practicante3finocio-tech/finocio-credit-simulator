package com.finocio.practicas.credit.simulator.domain;

public class CreditLimitExceededException extends RuntimeException {
  public CreditLimitExceededException(String message) {
    super(message);
  }
}

