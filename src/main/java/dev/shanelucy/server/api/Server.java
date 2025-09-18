package dev.shanelucy.server.api;

import java.util.UUID;

public interface Server {

  String host();

  int port();

  UUID id();
}
