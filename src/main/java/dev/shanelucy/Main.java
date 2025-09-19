package dev.shanelucy;

import dev.shanelucy.handler.impl.datahandler.BasicDataHandlerFactory;
import dev.shanelucy.loadbalancer.impl.roundrobin.RoundRobinLoadBalancerFactory;
import dev.shanelucy.node.api.ServerNode;
import dev.shanelucy.node.impl.clientnode.NetCatClientNodeFactory;
import dev.shanelucy.node.impl.servernode.NetCatServerNodeFactory;
import dev.shanelucy.proxy.impl.bidirectionalproxy.BiDirectionalProxyFactory;
import java.io.IOException;
import java.util.List;

public final class Main {

  public static void main(final String[] args) throws IOException {
    final var clientNodeFactory = new NetCatClientNodeFactory();
    final var serverNodeFactory = new NetCatServerNodeFactory();
    final var dataHandlerFactory = new BasicDataHandlerFactory();
    final var loadBalancerFactory = new RoundRobinLoadBalancerFactory();
    final var proxyFactory = new BiDirectionalProxyFactory();

    final var clientNode = clientNodeFactory.create(3000, "localhost");
    final List<ServerNode> serverNodes =
        List.of(
            serverNodeFactory.create(8080, "localhost"),
            serverNodeFactory.create(8081, "localhost"),
            serverNodeFactory.create(8082, "localhost"));
    final var dataHandler = dataHandlerFactory.create();

    final var roundRobinLoadBalancer = loadBalancerFactory.create(serverNodes);

    final var proxy = proxyFactory.create(clientNode, roundRobinLoadBalancer, dataHandler);
    while (true) {
      proxy.proxyRequest();
    }
  }
}
