package dev.shanelucy.client.impl;

import dev.shanelucy.client.api.ClientSocket;
import java.io.IOException;

public abstract class ClientFactory {

  /**
   * Creates ClientSocket.
   *
   * @param queueSize client queue
   * @param port client port
   * @return Any ClientSocket implementation
   */
  public ClientSocket create(final int queueSize, final int port) throws IOException {
    final var client = createClient(queueSize, port);
    System.out.println(
        String.format(
            "Client: %s created with queue size: %s and port: %s", client.id(), queueSize, port));
    return client;
  }

  protected abstract ClientSocket createClient(int queueSize, int port) throws IOException;
}
