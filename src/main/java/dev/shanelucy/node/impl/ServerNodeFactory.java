package dev.shanelucy.node.impl;

import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;

public abstract class ServerNodeFactory {

  /**
   * Creates ClientSocket.
   *
   * @param port client port
   * @param host client host
   * @return Any ClientSocket implementation
   */
  public ServerNode create(final int port, final String host) throws IOException {
    final var serverNode = createNode(port, host);
    System.out.println(
        String.format(
            "server node: %s created with host: %s and port: %s",
            serverNode.id(), serverNode.host(), serverNode.port()));
    return serverNode;
  }

  protected abstract ServerNode createNode(int port, String host) throws IOException;
}
