package dev.shanelucy.handler.impl.datahandler;

import dev.shanelucy.handler.api.DataHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BasicDataHandler implements DataHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(BasicDataHandler.class);

  @Override
  public void pipeData(final InputStream inputStream, final OutputStream outputStream) {
    if (Objects.isNull(inputStream) || Objects.isNull(outputStream)) {
      LOGGER.warn("Unable to transfer data due to null input or output stream");
      return;
    }

    try {
      int data;
      while ((data = inputStream.read()) != -1) {
        outputStream.write(data);
      }
    } catch (final IOException e) {
      LOGGER.atError().log("IO Exception encountered during data transfer: {}", e.getMessage(), e);
    }
  }
}
