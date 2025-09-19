package dev.shanelucy.node.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public record NetCatClientNode(int port, String host, UUID id)
    implements dev.shanelucy.node.api.ClientNode {

  @Override
  public ServerSocket serverSocket() throws IOException {
    return new ServerSocket(port);
  }
}
