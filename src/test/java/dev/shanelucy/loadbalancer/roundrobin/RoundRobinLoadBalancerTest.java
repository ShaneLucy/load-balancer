package dev.shanelucy.loadbalancer.roundrobin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import dev.shanelucy.exceptions.MissingServerNodesException;
import dev.shanelucy.exceptions.NoServersAvailableException;
import dev.shanelucy.loadbalancer.api.LoadBalancer;
import dev.shanelucy.loadbalancer.impl.roundrobin.RoundRobinLoadBalancerFactory;
import dev.shanelucy.node.api.ServerNode;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoundRobinLoadBalancerTest {

  private static final String SERVER1_HOST = "localhost";
  private static final int SERVER1_PORT = 8080;
  private static final UUID SERVER1_ID = UUID.randomUUID();

  private static final String SERVER2_HOST = "a-different-host";
  private static final int SERVER2_PORT = 8081;
  private static final UUID SERVER2_ID = UUID.randomUUID();

  private static final String SERVER3_HOST = "something-else";
  private static final int SERVER3_PORT = 8082;
  private static final UUID SERVER3_ID = UUID.randomUUID();

  private final RoundRobinLoadBalancerFactory roundRobinLoadBalancerFactory =
      new RoundRobinLoadBalancerFactory();
  private LoadBalancer roundRobinLoadBalancer;
  @Mock private ServerNode serverNode1;
  @Mock private ServerNode serverNode2;
  @Mock private ServerNode serverNode3;
  @Mock private Socket socket1;
  @Mock private Socket socket2;
  @Mock private Socket socket3;

  @BeforeEach
  void setUp() {
    reset(serverNode1, serverNode2, serverNode3, socket1, socket2, socket3);
    roundRobinLoadBalancer =
        roundRobinLoadBalancerFactory.create(List.of(serverNode1, serverNode2, serverNode3));
  }

  @Test
  void itReturnsAServerNodeWhenCalled() throws IOException {
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenReturn(socket1);

    final var result = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode1, result);

    verifyNoInteractions(serverNode2);
    verifyNoInteractions(serverNode3);
  }

  @Test
  void whenCalledRepeatedlyItNeverReturnsTheSameServerNodeTwiceInARowWhenMoreThan1ServerPresent()
      throws IOException {
    // request 1
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenReturn(socket1);

    final var result1 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode1, result1);

    verifyNoInteractions(serverNode2);
    verifyNoInteractions(serverNode3);

    // request 2
    when(serverNode2.host()).thenReturn(SERVER2_HOST);
    when(serverNode2.port()).thenReturn(SERVER2_PORT);
    when(serverNode2.id()).thenReturn(SERVER2_ID);
    when(serverNode2.socket()).thenReturn(socket2);

    final var result2 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode2, result2);

    verifyNoMoreInteractions(serverNode1);
    verifyNoInteractions(serverNode3);

    // request 3
    when(serverNode3.host()).thenReturn(SERVER3_HOST);
    when(serverNode3.port()).thenReturn(SERVER3_PORT);
    when(serverNode3.id()).thenReturn(SERVER3_ID);
    when(serverNode3.socket()).thenReturn(socket3);

    final var result3 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode3, result3);

    verifyNoMoreInteractions(serverNode1);
    verifyNoMoreInteractions(serverNode2);
  }

  @Test
  void whenItExhaustsAllNodesInTheArrayItReturnsTheFirstItem() throws IOException {
    // request 1
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenReturn(socket1);

    final var result1 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode1, result1);

    verifyNoInteractions(serverNode2);
    verifyNoInteractions(serverNode3);

    // request 2
    when(serverNode2.host()).thenReturn(SERVER2_HOST);
    when(serverNode2.port()).thenReturn(SERVER2_PORT);
    when(serverNode2.id()).thenReturn(SERVER2_ID);
    when(serverNode2.socket()).thenReturn(socket2);

    final var result2 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode2, result2);

    verifyNoMoreInteractions(serverNode1);
    verifyNoInteractions(serverNode3);

    // request 3
    when(serverNode3.host()).thenReturn(SERVER3_HOST);
    when(serverNode3.port()).thenReturn(SERVER3_PORT);
    when(serverNode3.id()).thenReturn(SERVER3_ID);
    when(serverNode3.socket()).thenReturn(socket3);

    final var result3 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode3, result3);

    verifyNoMoreInteractions(serverNode1);
    verifyNoMoreInteractions(serverNode2);

    // request 4
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenReturn(socket1);

    final var result4 = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode1, result4);

    verifyNoMoreInteractions(serverNode2);
    verifyNoMoreInteractions(serverNode3);
  }

  @Test
  void ifAServerIsNotAvailableItReturnsTheNextServer() throws IOException {
    // request 1, server failure
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenThrow(new IOException("error message", new Throwable()));

    when(serverNode3.host()).thenReturn(SERVER3_HOST);
    when(serverNode3.port()).thenReturn(SERVER3_PORT);
    when(serverNode3.id()).thenReturn(SERVER3_ID);
    when(serverNode3.socket()).thenReturn(socket3);

    final var result = roundRobinLoadBalancer.getServer();
    assertEquals(serverNode3, result);

    verifyNoInteractions(serverNode2);
  }

  @Test
  void ifAllServersAreDownItThrowsANoServersAvailableException() throws IOException {
    final var ioException = new IOException("error message", new Throwable());
    // request 1, server failure
    when(serverNode1.host()).thenReturn(SERVER1_HOST);
    when(serverNode1.port()).thenReturn(SERVER1_PORT);
    when(serverNode1.id()).thenReturn(SERVER1_ID);
    when(serverNode1.socket()).thenThrow(ioException);

    // request 2, server failure
    when(serverNode2.host()).thenReturn(SERVER2_HOST);
    when(serverNode2.port()).thenReturn(SERVER2_PORT);
    when(serverNode2.id()).thenReturn(SERVER2_ID);
    when(serverNode2.socket()).thenThrow(ioException);

    // request 3, server failure
    when(serverNode3.host()).thenReturn(SERVER3_HOST);
    when(serverNode3.port()).thenReturn(SERVER3_PORT);
    when(serverNode3.id()).thenReturn(SERVER3_ID);
    when(serverNode3.socket()).thenThrow(ioException);

    assertThrows(NoServersAvailableException.class, () -> roundRobinLoadBalancer.getServer());
  }

  @Test
  void itThrowsAMissingServerNodesExceptionWhenServerNodesAreEmpty() {
    assertThrows(
        MissingServerNodesException.class,
        () -> roundRobinLoadBalancerFactory.create(new ArrayList<>()));
  }
}
