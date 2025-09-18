package dev.shanelucy.client.impl;

import dev.shanelucy.client.api.ClientSocket;
import dev.shanelucy.client.model.MockClient;
import java.io.IOException;
import java.util.UUID;

public final class MockClientFactory extends ClientFactory {

  @Override
  protected ClientSocket createClient(final int queueSize, final int port) throws IOException {
    return new MockClient(queueSize, port, UUID.randomUUID());
  }
}
