package dev.shanelucy.node.impl.clientnode;

import java.util.UUID;

/** Concrete factory for creating a {@link NetCatClientNode} */
public class NetCatClientNodeFactory extends ClientNodeFactory {

  /**
   * @return {@link NetCatClientNode} implementation.
   */
  @Override
  protected NetCatClientNode createNode(int port, String host) {
    return new NetCatClientNode(port, host, UUID.randomUUID());
  }
}
