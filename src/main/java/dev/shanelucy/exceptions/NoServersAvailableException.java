package dev.shanelucy.exceptions;

/** Signals that all previously available servers are no longer available. */
public class NoServersAvailableException extends RuntimeException {
  public NoServersAvailableException(final String message) {
    super(message);
  }
}
