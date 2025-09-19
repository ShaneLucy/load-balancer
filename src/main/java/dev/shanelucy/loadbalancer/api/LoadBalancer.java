package dev.shanelucy.loadbalancer.api;

import dev.shanelucy.node.api.ServerNode;

@FunctionalInterface
public interface LoadBalancer {

  ServerNode getServer();
}
