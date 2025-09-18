package dev.shanelucy.node.impl;

import dev.shanelucy.node.model.ClientNode;
import java.util.UUID;

public class ClientNodeFactory extends NodeFactory {
  @Override
  protected ClientNode createNode(int port, String host) {
    return new ClientNode(port, host, UUID.randomUUID());
  }
}
