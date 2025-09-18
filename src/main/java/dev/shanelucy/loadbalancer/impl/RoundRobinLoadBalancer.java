package dev.shanelucy.loadbalancer.impl;

import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.Node;
import java.util.ArrayList;
import java.util.List;

public final class RoundRobinLoadBalancer implements LoadBalancer {
  private final List<Node> serverNodes;
  private int requestCount = 0;

  public RoundRobinLoadBalancer(final List<Node> serverNodes) {
    this.serverNodes = new ArrayList<>(serverNodes);
  }

  @Override
  public Node loadBalance() {
    final var serverNode = serverNodes.get(requestCount % serverNodes.size());
    System.out.println(serverNode.id());
    requestCount += 1;
    return serverNode;
  }
}
