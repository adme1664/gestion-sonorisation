package com.adme.gestion.sonorisation.exceptions;

public class NotFoundException extends RuntimeException {

  public NotFoundException() {
    super("Item not found in DB.");
  }

}
