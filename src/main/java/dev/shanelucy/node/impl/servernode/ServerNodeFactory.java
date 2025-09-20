package dev.shanelucy.node.impl.servernode;

import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract ServerNodeFactory, concrete server node factories should extend this factory. */
public abstract class ServerNodeFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates a server node which stores data relating to a server.
   *
   * @param port The port used by the server
   * @param host The host used by the server
   * @return {@link ServerNode} implementation
   * @throws IOException if there is an error creating a {@link Socket}
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

  /**
   * Used by concrete server node factories to create a {@link ServerNode} implementation.
   *
   * @param port The port used by the server
   * @param host The host used by the server
   * @return {@link ServerNode} implementation
   * @throws IOException if there is an error creating a {@link Socket}
   */
  protected abstract ServerNode createNode(int port, String host) throws IOException;
}
