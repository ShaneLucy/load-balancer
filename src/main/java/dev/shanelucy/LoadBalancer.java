package dev.shanelucy;

import dev.shanelucy.client.api.ClientSocket;
import dev.shanelucy.client.impl.MockClientFactory;
import dev.shanelucy.server.api.Server;
import dev.shanelucy.server.impl.MockServerFactory;
import java.io.IOException;

public final class LoadBalancer {

  public static void main(final String[] args) throws IOException {
    final var mockClientFactory = new MockClientFactory();
    final ClientSocket mockClient1 = mockClientFactory.create(100, 3000);
    System.out.println(String.format("mock client 1: %s", mockClient1.port()));
    final var mockServerFactory = new MockServerFactory();
    final Server mockServer1 = mockServerFactory.create("http://localhost", 8080);
    System.out.println(String.format("mock server 1: %s", mockServer1.host()));
  }

  public int add(final int a, final int b) {
    return a + b;
  }
}
