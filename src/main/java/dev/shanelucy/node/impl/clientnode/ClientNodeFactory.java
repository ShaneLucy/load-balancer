package dev.shanelucy.node.impl.clientnode;

import dev.shanelucy.node.api.ClientNode;
import java.io.IOException;
import java.net.ServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract ClientNodeFactory, concrete client node factories should extend this factory. */
public abstract class ClientNodeFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates a client node which stores data relating to a client.
   *
   * @param port The port used by the server
   * @param host The host used by the server - TODO remove
   * @return {@link ClientNode} implementation
   * @throws IOException if there is an error creating a {@link ServerSocket}
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

  /**
   * Used by concrete client node factories to create a {@link ClientNode} implementation.
   *
   * @param port The port used by the server
   * @param host The host used by the server - TODO remove
   * @return {@link ClientNode} implementation
   * @throws IOException if there is an error creating a {@link ServerSocket}
   */
  protected abstract ClientNode createNode(int port, String host) throws IOException;
}
