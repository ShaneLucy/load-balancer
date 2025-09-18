package dev.shanelucy.loadbalancer.api;

import dev.shanelucy.node.api.Node;

public interface LoadBalancer {

  Node loadBalance();
}
