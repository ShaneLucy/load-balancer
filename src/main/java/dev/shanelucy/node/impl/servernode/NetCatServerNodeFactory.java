package dev.shanelucy.node.impl.servernode;

import dev.shanelucy.node.api.ServerNode;
import dev.shanelucy.node.model.NetCatServerNode;
import java.io.IOException;
import java.util.UUID;

public class NetCatServerNodeFactory extends ServerNodeFactory {
  @Override
  protected ServerNode createNode(int port, String host) throws IOException {
    return new NetCatServerNode(port, host, UUID.randomUUID());
  }
}
