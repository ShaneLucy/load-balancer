package dev.shanelucy.node.impl.servernode;

import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import java.util.UUID;

/** Concrete factory for creating a {@link NetCatServerNode} */
public class NetCatServerNodeFactory extends ServerNodeFactory {
  /**
   * @return {@link NetCatServerNode} implementation.
   */
  @Override
  protected ServerNode createNode(int port, String host) throws IOException {
    return new NetCatServerNode(port, host, UUID.randomUUID());
  }
}
