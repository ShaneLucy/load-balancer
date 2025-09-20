package dev.shanelucy.proxy.impl.bidirectionalproxy;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.proxy.impl.ProxyFactory;

/** Concrete factory for creating a {@link BiDirectionalProxy} */
public class BiDirectionalProxyFactory extends ProxyFactory {

  /**
   * @return {@link BiDirectionalProxy} implementation.
   */
  @Override
  protected BiDirectionalProxy createProxy(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler) {
    return new BiDirectionalProxy(clientNode, loadBalancer, dataHandler);
  }
}
