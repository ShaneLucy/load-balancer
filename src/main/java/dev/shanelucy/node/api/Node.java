package dev.shanelucy.node.api;

import java.util.UUID;

public sealed interface Node permits ClientNode, ServerNode {

  int port();

  String host();

  UUID id();
}
