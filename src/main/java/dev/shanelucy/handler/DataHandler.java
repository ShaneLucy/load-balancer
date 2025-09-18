package dev.shanelucy.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class DataHandler {

  public static void pipeData(final InputStream inputStream, final OutputStream outputStream) {
    try {
      int data;
      while ((data = inputStream.read()) != -1) {
        outputStream.write(data);
      }
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }
}
