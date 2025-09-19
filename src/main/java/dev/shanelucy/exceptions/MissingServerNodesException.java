package dev.shanelucy.exceptions;

public class MissingServerNodesException extends RuntimeException {
  public MissingServerNodesException(final String message) {
    super(message);
  }
}
