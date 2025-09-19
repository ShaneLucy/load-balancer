package dev.shanelucy.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DataHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DataHandler.class);

  public static void pipeData(final InputStream inputStream, final OutputStream outputStream) {
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
