package dev.shanelucy.node.impl;

import dev.shanelucy.node.api.Node;
import java.io.IOException;

public abstract class NodeFactory {

  /**
   * Creates ClientSocket.
   *
   * @param port client port
   * @param host client host
   * @return Any ClientSocket implementation
   */
  public Node create(final int port, final String host) throws IOException {
    final var node = createNode(port, host);
    System.out.println(
        String.format(
            "node: %s created with host: %s and port: %s", node.id(), node.host(), node.port()));
    return node;
  }

  protected abstract Node createNode(int queueSize, String host) throws IOException;
}
