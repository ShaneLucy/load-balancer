package dev.shanelucy.node.impl.clientnode;

import java.util.UUID;

public class NetCatClientNodeFactory extends ClientNodeFactory {

  @Override
  protected NetCatClientNode createNode(int port, String host) {
    return new NetCatClientNode(port, host, UUID.randomUUID());
  }
}
