// package dev.shanelucy.node.servernode;
//
// import static org.mockito.Mockito.reset;
// import static org.mockito.Mockito.when;
//
// import dev.shanelucy.node.impl.servernode.NetCatServerNode;
// import java.io.IOException;
// import java.net.Socket;
// import java.util.UUID;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
//
// @ExtendWith(MockitoExtension.class)
// class NetCatServerNodeTest {
//
//  private NetCatServerNode netCatServerNode;
//
//  @Mock private Socket socket;
//
//  @BeforeEach
//  void setUp() {
//    reset(socket);
//    netCatServerNode = new NetCatServerNode(12, "localhost", UUID.randomUUID());
//  }
//
//  @Test
//  void ifServerIsAvailableItReturnsTrue() throws IOException {
//    when(netCatServerNode.socket()).thenReturn(socket);
//    Assertions.assertTrue(netCatServerNode.isServerAlive());
//  }
//
//  @Test
//  void ifServerIsNotAvailableItReturnsFalse() {
//    Assertions.assertFalse(netCatServerNode.isServerAlive());
//  }
// }
