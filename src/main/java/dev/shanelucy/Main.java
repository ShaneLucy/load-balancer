package dev.shanelucy;

import dev.shanelucy.handler.impl.datahandler.BasicDataHandlerFactory;
import dev.shanelucy.loadbalancer.impl.roundrobin.RoundRobinLoadBalancerFactory;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.node.api.ServerNode;
import dev.shanelucy.node.impl.clientnode.NetCatClientNodeFactory;
import dev.shanelucy.node.impl.servernode.NetCatServerNodeFactory;
import dev.shanelucy.proxy.api.Proxy;
import dev.shanelucy.proxy.impl.bidirectionalproxy.BiDirectionalProxyFactory;
import java.io.IOException;
import java.util.List;

public final class Main {

  private static final String LOCAL_HOST = "localhost";

  public static void main(final String[] args) throws IOException {
    final var clientNodeFactory = new NetCatClientNodeFactory();
    final var serverNodeFactory = new NetCatServerNodeFactory();
    final var dataHandlerFactory = new BasicDataHandlerFactory();
    final var loadBalancerFactory = new RoundRobinLoadBalancerFactory();
    final var proxyFactory = new BiDirectionalProxyFactory();

    final List<ClientNode> clientNodes =
        List.of(
            clientNodeFactory.create(3000, LOCAL_HOST),
            clientNodeFactory.create(3001, LOCAL_HOST),
            clientNodeFactory.create(3002, LOCAL_HOST));
    final List<ServerNode> serverNodes =
        List.of(
            serverNodeFactory.create(8080, LOCAL_HOST),
            serverNodeFactory.create(8081, LOCAL_HOST),
            serverNodeFactory.create(8082, LOCAL_HOST));
    final var dataHandler = dataHandlerFactory.create();

    final var roundRobinLoadBalancer = loadBalancerFactory.create(serverNodes);

    final List<Proxy> proxies =
        clientNodes.stream()
            .map(clientNode -> proxyFactory.create(clientNode, roundRobinLoadBalancer, dataHandler))
            .toList();

    while (true) {
      proxies.forEach(Proxy::proxyRequest);
    }
  }
}
