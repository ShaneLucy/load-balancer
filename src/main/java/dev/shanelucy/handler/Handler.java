package dev.shanelucy.handler;

import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Handler implements Runnable {

  private final ClientNode clientNode;
  private final LoadBalancer loadBalancer;

  public Handler(final ClientNode clientNode, final LoadBalancer loadBalancer) {
    this.clientNode = clientNode;
    this.loadBalancer = loadBalancer;
  }

  @Override
  public void run() {
    final ServerSocket clientServerSocket;
    try {
      clientServerSocket = clientNode.serverSocket();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    while (true) {
      try {
        final var clientSocket = clientServerSocket.accept();

        final var serverNode = loadBalancer.loadBalance();
        final var serverSocket = new Socket(serverNode.host(), serverNode.port());

        final var clientInputStream = clientSocket.getInputStream();
        final var clientOutputStream = clientSocket.getOutputStream();

        final var serverInputStream = serverSocket.getInputStream();
        final var serverOutputStream = serverSocket.getOutputStream();

        final Thread clientDataHandler =
            new Thread(() -> DataHandler.pipeData(clientInputStream, serverOutputStream));
        clientDataHandler.start();

        final Thread serverDataHandler =
            new Thread(() -> DataHandler.pipeData(serverInputStream, clientOutputStream));

        serverDataHandler.start();
      } catch (final Exception io) {
        System.out.println(io);
      }
    }
  }
}
