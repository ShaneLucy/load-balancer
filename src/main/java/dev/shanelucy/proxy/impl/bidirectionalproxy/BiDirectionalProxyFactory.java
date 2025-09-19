package dev.shanelucy.proxy.impl.bidirectionalproxy;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.proxy.impl.ProxyFactory;

public class BiDirectionalProxyFactory extends ProxyFactory {
  @Override
  protected BiDirectionalProxy createProxy(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler) {
    return new BiDirectionalProxy(clientNode, loadBalancer, dataHandler);
  }
}
