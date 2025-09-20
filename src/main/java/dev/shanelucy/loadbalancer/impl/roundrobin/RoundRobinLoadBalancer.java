package dev.shanelucy.loadbalancer.impl.roundrobin;

import dev.shanelucy.exceptions.MissingServerNodesException;
import dev.shanelucy.exceptions.NoServersAvailableException;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link LoadBalancer} implementation which sequentially selects a server from the list of
 * available servers, if a server health status check fails the failing server will be removed from
 * the list of available servers and a new server will be returned.
 */
public final class RoundRobinLoadBalancer implements LoadBalancer {

  private static final Logger LOGGER = LoggerFactory.getLogger(RoundRobinLoadBalancer.class);
  private final List<ServerNode> serverNodes;
  private int requestCount = 0;

  public RoundRobinLoadBalancer(final List<ServerNode> serverNodes) {
    if (serverNodes.isEmpty()) {
      throw new MissingServerNodesException("Empty list of server nodes supplied to load balancer");
    }
    this.serverNodes = new ArrayList<>(serverNodes);
  }

  @Override
  public ServerNode getServer() {
    LOGGER.atInfo().log("Determining server to distribute next request to");
    if (serverNodes.isEmpty()) {
      LOGGER.atError().log("Unable to distribute load as no servers are online");
      throw new NoServersAvailableException("No servers available");
    }

    var serverNode = serverNodes.get(requestCount % serverNodes.size());
    final var newRequestCount = requestCount += 1;
    setRequestCount(newRequestCount);

    LOGGER.atInfo().log(
        "Checking health status of server: {}:{} with ID: {}",
        serverNode.host(),
        serverNode.port(),
        serverNode.id());

    try (Socket socket = serverNode.socket()) {
      socket.close();
    } catch (final IOException e) {
      LOGGER
          .atWarn()
          .log(
              "Server: {}:{}  with ID: {} not alive, removing from list of available servers - {}",
              serverNode.host(),
              serverNode.port(),
              serverNode.id(),
              e.getMessage(),
              e);
      serverNodes.remove(serverNode);
      serverNode = getServer();
    }

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
