package dev.shanelucy.loadbalancer.impl.roundrobin;

import dev.shanelucy.exceptions.MissingServerNodesException;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RoundRobinLoadBalancer implements LoadBalancer {

  private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinLoadBalancer.class);
  private final List<ServerNode> serverNodes;
  private int requestCount = 0;

  public RoundRobinLoadBalancer(final List<ServerNode> serverNodes) {
    if (serverNodes.isEmpty()) {
      throw new MissingServerNodesException("Empty list of server nodes supplied to load balancer");
    }
    this.serverNodes = List.of(serverNodes.toArray(new ServerNode[0]));
  }

  @Override
  public ServerNode loadBalance() {
    LOGGER.atInfo().log("Determining server to distribute next request to");
    final var serverNode = serverNodes.get(requestCount % serverNodes.size());

    setRequestCount(requestCount += 1);
    LOGGER.atInfo().log(
        "Picked server: {}:{} with ID: {}", serverNode.host(), serverNode.port(), serverNode.id());
    return serverNode;
  }

  private void setRequestCount(final int requestCount) {
    if (this.requestCount == Integer.MAX_VALUE) {
      this.requestCount = 0;
      return;
    }

    this.requestCount = requestCount;
  }
}
