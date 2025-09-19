package dev.shanelucy.node.api;

import java.io.IOException;
import java.net.Socket;

public non-sealed interface ServerNode extends Node {

  Socket socket() throws IOException;
}
