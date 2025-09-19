package dev.shanelucy.node.api;

import java.io.IOException;
import java.net.ServerSocket;

public non-sealed interface ClientNode extends Node {

  ServerSocket serverSocket() throws IOException;
}
