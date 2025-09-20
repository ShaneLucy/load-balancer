package dev.shanelucy.handler.impl;

import dev.shanelucy.handler.api.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract DataHandler, concrete data handler factories should extend this factory. */
public abstract class DataHandlerFactory {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates a data handler which is responsible handling data passed through the load balancer
   *
   * @return A {@link DataHandler} implementation
   */
  public DataHandler create() {
    final var dataHandler = createDataHandler();
    logger.atInfo().log("Data handler created");

    return dataHandler;
  }

  /**
   * Used by concrete data handler factories to create a {@link DataHandler} implementation
   *
   * @return A {@link DataHandler} implementation
   */
  protected abstract DataHandler createDataHandler();
}
