package dev.shanelucy;

import dev.shanelucy.handler.Handler;
import dev.shanelucy.loadbalancer.impl.RoundRobinLoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import dev.shanelucy.node.impl.NetCatClientNodeFactory;
import dev.shanelucy.node.impl.NetCatServerNodeFactory;
import java.io.IOException;
import java.util.List;

public final class Main {

  public static void main(final String[] args) throws IOException {
    final var clientNodeFactory = new NetCatClientNodeFactory();
    final var serverNodeFactory = new NetCatServerNodeFactory();
    final var client = clientNodeFactory.create(3000, "localhost");

    final List<ServerNode> serverNodes =
        List.of(
            serverNodeFactory.create(8080, "localhost"),
            serverNodeFactory.create(8081, "localhost"),
            serverNodeFactory.create(8082, "localhost"));

    final var roundRobinLoadBalancer = new RoundRobinLoadBalancer(serverNodes);

    final var handler = new Handler(client, roundRobinLoadBalancer);
    handler.run();
  }

  public int add(final int a, final int b) {
    return a + b;
  }
}
