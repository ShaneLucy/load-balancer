package dev.shanelucy.loadbalancer.api;

import dev.shanelucy.node.api.ServerNode;

public interface LoadBalancer {

  ServerNode loadBalance();
}
