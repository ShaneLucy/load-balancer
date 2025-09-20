package dev.shanelucy.loadbalancer.impl.roundrobin;

import dev.shanelucy.loadbalancer.impl.LoadBalancerFactory;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;

/** Concrete factory for creating a {@link RoundRobinLoadBalancer} */
public class RoundRobinLoadBalancerFactory extends LoadBalancerFactory {

  /**
   * @return {@link RoundRobinLoadBalancer} implementation.
   */
  @Override
  protected RoundRobinLoadBalancer createLoadBalancer(final List<ServerNode> serverNodes) {
    return new RoundRobinLoadBalancer(serverNodes);
  }
}
