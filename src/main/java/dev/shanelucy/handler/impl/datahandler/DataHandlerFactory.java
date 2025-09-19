package dev.shanelucy.handler.impl.datahandler;

import dev.shanelucy.handler.api.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DataHandlerFactory {
  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates ClientSocket.
   *
   * @return Any ClientSocket implementation
   */
  public DataHandler create() {
    final var dataHandler = createDataHandler();
    logger.atInfo().log("Data handler created");

    return dataHandler;
  }

  protected abstract DataHandler createDataHandler();
}
