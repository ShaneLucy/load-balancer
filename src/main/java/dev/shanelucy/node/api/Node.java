package dev.shanelucy.node.api;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public interface Node {

  int port();

  String host();

  UUID id();

  Socket socket() throws IOException;
}
