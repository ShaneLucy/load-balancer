package dev.shanelucy.node.impl.clientnode;

import dev.shanelucy.node.api.ClientNode;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public record NetCatClientNode(int port, String host, UUID id) implements ClientNode {

  @Override
  public ServerSocket serverSocket() throws IOException {
    return new ServerSocket(port);
  }
}
