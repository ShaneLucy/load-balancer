package dev.shanelucy.loadbalancer.impl.roundrobin;

import dev.shanelucy.loadbalancer.impl.LoadBalancerFactory;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;

public class RoundRobinLoadBalancerFactory extends LoadBalancerFactory {
  @Override
  protected RoundRobinLoadBalancer createLoadBalancer(final List<ServerNode> serverNodes) {
    return new RoundRobinLoadBalancer(serverNodes);
  }
}
