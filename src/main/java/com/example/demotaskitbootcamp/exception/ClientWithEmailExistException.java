package com.example.demotaskitbootcamp.exception;

public class ClientWithEmailExistException extends ApplicationException {
  public ClientWithEmailExistException(String message) {
    super(message);
  }
}
