package dev.shanelucy.handler.api;

import java.io.InputStream;
import java.io.OutputStream;

@FunctionalInterface
public interface DataHandler {

  void pipeData(InputStream inputStream, OutputStream outputStream);
}
