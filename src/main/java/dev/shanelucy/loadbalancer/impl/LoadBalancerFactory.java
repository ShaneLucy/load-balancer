package dev.shanelucy.loadbalancer.impl;

import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract LoadBalancerFactory, concrete load balancer factories should extend this factory. */
public abstract class LoadBalancerFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates a load balancer which is responsible for determining which server to use next.
   *
   * @param serverNodes A List of all {@link ServerNode} to load balance.
   * @return A {@link LoadBalancer} implementation.
   */
  public LoadBalancer create(final List<ServerNode> serverNodes) {
    final var loadBalancer = createLoadBalancer(serverNodes);
    logger.atInfo().log("Load balancer created");

    return loadBalancer;
  }

  /**
   * Used by concrete load balancer factories to create a {@link LoadBalancer} implementation
   *
   * @param serverNodes A List of all {@link ServerNode} to load balance.
   * @return A {@link LoadBalancer} implementation.
   */
  protected abstract LoadBalancer createLoadBalancer(List<ServerNode> serverNodes);
}
