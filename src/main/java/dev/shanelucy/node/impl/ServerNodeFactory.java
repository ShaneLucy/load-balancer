package dev.shanelucy.node.impl;

import dev.shanelucy.node.model.ServerNode;
import java.util.UUID;

public class ServerNodeFactory extends NodeFactory {

  @Override
  protected ServerNode createNode(int port, String host) {
    return new ServerNode(port, host, UUID.randomUUID());
  }
}
