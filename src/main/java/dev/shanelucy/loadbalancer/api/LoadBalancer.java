package dev.shanelucy.loadbalancer.api;

import dev.shanelucy.node.api.ServerNode;

/** A {@link FunctionalInterface} which consumers should implement to perform load balancing. */
@FunctionalInterface
public interface LoadBalancer {

  /** Determines which server should receive a request. */
  ServerNode getServer();
}
