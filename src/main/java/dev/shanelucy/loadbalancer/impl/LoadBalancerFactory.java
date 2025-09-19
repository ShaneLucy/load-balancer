package dev.shanelucy.loadbalancer.impl;

import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class LoadBalancerFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates ClientSocket.
   *
   * @return Any ClientSocket implementation
   */
  public LoadBalancer create(final List<ServerNode> serverNodes) {
    final var loadBalancer = createLoadBalancer(serverNodes);
    logger.atInfo().log("Load balancer created");

    return loadBalancer;
  }

  protected abstract LoadBalancer createLoadBalancer(List<ServerNode> serverNodes);
}
