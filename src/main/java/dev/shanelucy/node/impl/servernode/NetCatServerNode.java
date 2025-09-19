package dev.shanelucy.node.impl.servernode;

import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public record NetCatServerNode(int port, String host, UUID id) implements ServerNode {

  @Override
  public Socket socket() throws IOException {
    return new Socket(host, port);
  }
}
