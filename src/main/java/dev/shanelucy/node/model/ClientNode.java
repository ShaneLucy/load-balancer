package dev.shanelucy.node.model;

import dev.shanelucy.node.api.Node;
import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public record ClientNode(int port, String host, UUID id) implements Node {

  @Override
  public Socket socket() throws IOException {
    return new Socket(host, port);
  }
}
