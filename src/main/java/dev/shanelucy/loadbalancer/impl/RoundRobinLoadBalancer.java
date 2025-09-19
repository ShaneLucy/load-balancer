package dev.shanelucy.loadbalancer.impl;

import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;

public final class RoundRobinLoadBalancer implements LoadBalancer {
  private final List<ServerNode> serverNodes;
  private int requestCount = 0;

  public RoundRobinLoadBalancer(final List<ServerNode> serverNodes) {
    this.serverNodes = List.of(serverNodes.toArray(new ServerNode[0]));
  }

  @Override
  public ServerNode loadBalance() {
    final var serverNode = serverNodes.get(requestCount % serverNodes.size());
    System.out.println(serverNode.id());
    requestCount += 1;
    return serverNode;
  }
}
