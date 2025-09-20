package dev.shanelucy.proxy.impl;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.proxy.api.Proxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** An abstract ProxyFactory, concrete proxy factories should extend this factory. */
public abstract class ProxyFactory {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  /**
   * Creates a proxy which is responsible for distributing requests between clients and servers
   *
   * @param clientNode The {@link ClientNode} initiating the request
   * @param loadBalancer The {@link LoadBalancer} retrieves the correct server to distribute the
   *     request to
   * @param dataHandler The {@link DataHandler} handles any data sent in the request
   * @return A {@link Proxy} implementation
   */
  public Proxy create(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler) {
    final var proxy = createProxy(clientNode, loadBalancer, dataHandler);
    logger.atInfo().log("Proxy created");

    return proxy;
  }

  /**
   * Used by concrete proxy factories to create a {@link Proxy} implementation
   *
   * @param clientNode The {@link ClientNode} initiating the request
   * @param loadBalancer The {@link LoadBalancer} retrieves the correct server to distribute the
   *     request to
   * @param dataHandler The {@link DataHandler} handles any data sent in the request
   * @return A {@link Proxy} implementation
   */
  protected abstract Proxy createProxy(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler);
}
