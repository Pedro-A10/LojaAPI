package com.pedro_a10.LojaAPI.exceptions;

public class UserCpfNotFoundException extends RuntimeException {
  public UserCpfNotFoundException(String message) {
    super(message);
  }
}
