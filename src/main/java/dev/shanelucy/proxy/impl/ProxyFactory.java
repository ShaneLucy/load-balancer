package dev.shanelucy.proxy.impl;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.proxy.api.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ProxyFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates ClientSocket.
   *
   * @return Any ClientSocket implementation
   */
  public Proxy create(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler) {
    final var proxy = createProxy(clientNode, loadBalancer, dataHandler);
    logger.atInfo().log("Proxy created");

    return proxy;
  }

  protected abstract Proxy createProxy(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler);
}
