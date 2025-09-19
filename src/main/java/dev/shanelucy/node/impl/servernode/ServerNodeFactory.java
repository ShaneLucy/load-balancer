package dev.shanelucy.node.impl.servernode;

import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerNodeFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates ClientSocket.
   *
   * @param port client port
   * @param host client host
   * @return Any ClientSocket implementation
   */
  public ServerNode create(final int port, final String host) throws IOException {
    final var serverNode = createNode(port, host);

    logger.atInfo().log(
        "Server node: {} crated with host: {} and port: {}",
        serverNode.id(),
        serverNode.host(),
        serverNode.port());
    return serverNode;
  }

  protected abstract ServerNode createNode(int port, String host) throws IOException;
}
