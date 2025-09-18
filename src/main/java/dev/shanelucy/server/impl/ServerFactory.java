package dev.shanelucy.server.impl;

import dev.shanelucy.server.api.Server;

public abstract class ServerFactory {

  /**
   * Creates servers.
   *
   * @param host server host
   * @param port server port
   * @return Any Server implementation
   */
  public Server create(final String host, final int port) {
    final var server = createServer(host, port);
    System.out.println(
        String.format("Server: %s created with host: %s and port: %s", server.id(), host, port));
    return server;
  }

  protected abstract Server createServer(String host, int port);
}
