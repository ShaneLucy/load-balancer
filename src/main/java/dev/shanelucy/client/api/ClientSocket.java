package dev.shanelucy.client.api;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.UUID;

public interface ClientSocket {
  int port();

  int queueSize();

  UUID id();

  ServerSocket serverSocket() throws IOException;
}
