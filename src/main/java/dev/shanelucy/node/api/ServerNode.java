package dev.shanelucy.node.api;

import java.io.IOException;
import java.net.Socket;

/**
 * A server receives requests from the load balancer. A ServerNode tracks information related to a
 * server.
 */
public non-sealed interface ServerNode extends Node {

  /**
   * Creates a {@link Socket} from the ServerNode host and port
   *
   * @return {@link Socket}
   * @throws IOException if there are errors opening the socket
   */
  Socket socket() throws IOException;
}
