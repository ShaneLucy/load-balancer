package dev.shanelucy.node.impl;

import dev.shanelucy.node.api.ClientNode;
import java.io.IOException;

public abstract class ClientNodeFactory {

  /**
   * Creates ClientSocket.
   *
   * @param port client port
   * @param host client host
   * @return Any ClientSocket implementation
   */
  public ClientNode create(final int port, final String host) throws IOException {
    final var clientNode = createNode(port, host);
    System.out.println(
        String.format(
            "client node: %s created with host: %s and port: %s",
            clientNode.id(), clientNode.host(), clientNode.port()));
    return clientNode;
  }

  protected abstract ClientNode createNode(int queueSize, String host) throws IOException;
}
