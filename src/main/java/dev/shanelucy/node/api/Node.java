package dev.shanelucy.node.api;

import java.util.UUID;

/**
 * Contains methods shared between various types of nodes, consumers need to implement {@link
 * ClientNode}, {@link ServerNode} or create a new type of node.
 */
public sealed interface Node permits ClientNode, ServerNode {
  /**
   * The node's port
   *
   * @return port
   */
  int port();

  /**
   * The node's host
   *
   * @return host
   */
  String host();

  /**
   * The node's unique identifier
   *
   * @return id
   */
  UUID id();
}
