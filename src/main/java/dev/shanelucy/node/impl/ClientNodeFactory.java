package dev.shanelucy.node.impl;

import dev.shanelucy.node.api.ClientNode;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ClientNodeFactory {

  private final Logger logger = LoggerFactory.getLogger(ClientNodeFactory.class);

  /**
   * Creates ClientSocket.
   *
   * @param port client port
   * @param host client host
   * @return Any ClientSocket implementation
   */
  public ClientNode create(final int port, final String host) throws IOException {
    final var clientNode = createNode(port, host);
    logger.atInfo().log(
        "Client node: {} crated with host: {} and port: {}",
        clientNode.id(),
        clientNode.host(),
        clientNode.port());

    return clientNode;
  }

  protected abstract ClientNode createNode(int queueSize, String host) throws IOException;
}
