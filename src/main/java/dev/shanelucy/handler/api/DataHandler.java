package dev.shanelucy.handler.api;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * A {@link FunctionalInterface} which consumers should implement to pipe data from an input stream
 * to an output stream.
 */
@FunctionalInterface
public interface DataHandler {

  /** This method handles piping data between input and output streams. */
  void pipeData(final InputStream inputStream, final OutputStream outputStream);
}
