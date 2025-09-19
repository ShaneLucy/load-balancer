package dev.shanelucy.exceptions;

public class NoServersAvailableException extends RuntimeException {
  public NoServersAvailableException(final String message) {
    super(message);
  }
}
