package dev.shanelucy.node.api;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * A client initiates requests into the load balancer. A ClientNode tracks information related to a
 * client.
 */
public non-sealed interface ClientNode extends Node {

  /**
   * Creates a {@link ServerSocket} from the ClientNode port
   *
   * @return {@link ServerSocket}
   * @throws IOException if there are errors opening the socket
   */
  ServerSocket serverSocket() throws IOException;
}
