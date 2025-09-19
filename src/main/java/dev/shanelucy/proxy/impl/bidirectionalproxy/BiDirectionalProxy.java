package dev.shanelucy.proxy.impl.bidirectionalproxy;

import dev.shanelucy.handler.api.DataHandler;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.node.api.ClientNode;
import dev.shanelucy.proxy.api.Proxy;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BiDirectionalProxy implements Proxy {

  private static final Logger LOGGER = LoggerFactory.getLogger(BiDirectionalProxy.class);
  private final LoadBalancer loadBalancer;
  private final DataHandler dataHandler;
  private final ClientNode clientNode;

  public BiDirectionalProxy(
      final ClientNode clientNode, final LoadBalancer loadBalancer, final DataHandler dataHandler) {
    this.clientNode = clientNode;
    this.loadBalancer = loadBalancer;
    this.dataHandler = dataHandler;
  }

  @Override
  public void proxyRequest() {
    final var serverNode = loadBalancer.loadBalance();
    final InputStream clientInputStream;
    final OutputStream clientOutputStream;
    final InputStream serverInputStream;
    final OutputStream serverOutputStream;
    final ExecutorService executorService;

    try (final var serverSocket = serverNode.socket();
        final var clientServerSocket = clientNode.serverSocket();
        final var clientSocket = clientServerSocket.accept()) {
      clientInputStream = clientSocket.getInputStream();
      clientOutputStream = clientSocket.getOutputStream();
      serverInputStream = serverSocket.getInputStream();
      serverOutputStream = serverSocket.getOutputStream();
      executorService = Executors.newFixedThreadPool(2);

      final var clientDataFuture =
          createFuture(
              executorService,
              () -> dataHandler.pipeData(clientInputStream, serverOutputStream),
              serverSocket);
      final var serverDataFuture =
          createFuture(
              executorService,
              () -> dataHandler.pipeData(serverInputStream, clientOutputStream),
              clientSocket);

      clientDataFuture.get();
      serverDataFuture.get();
    } catch (final IOException e) {
      LOGGER
          .atError()
          .log("IO exception encountered during request proxying: {}", e.getMessage(), e);
    } catch (final ExecutionException e) {
      LOGGER
          .atError()
          .log("Execution exception encountered during request proxying: {}", e.getMessage(), e);
    } catch (final InterruptedException e) {
      LOGGER
          .atError()
          .log("Interrupted exception encountered during request proxying: {}", e.getMessage(), e);
      Thread.currentThread().interrupt();
    }
  }

  private Future<?> createFuture(
      final ExecutorService executorService, Runnable task, final Socket socket) {
    return executorService.submit(
        () -> {
          task.run();
          try {
            socket.shutdownInput();
          } catch (final IOException e) {
            LOGGER
                .atError()
                .log(
                    "Error encountered trying to shutdown input to socket with host: {} - {}",
                    socket.getPort(),
                    e.getMessage(),
                    e);
            throw new RuntimeException(e);
          }
        });
  }
}
