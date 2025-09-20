package dev.shanelucy.handler.impl.basicdatahandler;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.handler.impl.DataHandlerFactory;

/** Concrete factory for creating a {@link BasicDataHandler} */
public class BasicDataHandlerFactory extends DataHandlerFactory {

  /**
   * @return {@link BasicDataHandler} implementation.
   */
  @Override
  protected DataHandler createDataHandler() {
    return new BasicDataHandler();
  }
}
