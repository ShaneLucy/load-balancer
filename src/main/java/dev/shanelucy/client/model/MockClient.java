package dev.shanelucy.client.model;

import dev.shanelucy.client.api.ClientSocket;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public record MockClient(int queueSize, int port, UUID id) implements ClientSocket {
  @Override
  public ServerSocket serverSocket() throws IOException {
    return new ServerSocket(port, queueSize);
  }
}
