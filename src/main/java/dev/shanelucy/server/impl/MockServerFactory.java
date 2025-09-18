package dev.shanelucy.server.impl;

import dev.shanelucy.server.api.Server;
import dev.shanelucy.server.model.MockServer;
import java.util.UUID;

public final class MockServerFactory extends ServerFactory {

  @Override
  protected Server createServer(final String host, final int port) {
    return new MockServer(host, port, UUID.randomUUID());
  }
}
