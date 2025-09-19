package dev.shanelucy.handler.impl.datahandler;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.handler.impl.DataHandlerFactory;

public class BasicDataHandlerFactory extends DataHandlerFactory {
  @Override
  protected DataHandler createDataHandler() {
    return new BasicDataHandler();
  }
}
