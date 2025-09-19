package dev.shanelucy.handler.impl.datahandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.shanelucy.handler.api.DataHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDataHandlerTest {

  private static final byte[] INPUT_DATA = {11, 22, 33, 44, 55};

  private DataHandler basicDataHandler;
  private final BasicDataHandlerFactory basicDataHandlerFactory = new BasicDataHandlerFactory();

  @BeforeEach
  void setUp() throws IOException {
    basicDataHandler = basicDataHandlerFactory.create();
  }

  @Test
  void itPipesDataFromInputStreamToOutputStream() {
    final var inputStream = new ByteArrayInputStream(INPUT_DATA);
    final var outputStream = new ByteArrayOutputStream();
    basicDataHandler.pipeData(inputStream, outputStream);

    final var outputData = outputStream.toByteArray();
    for (int i = 0; i < INPUT_DATA.length; i++) {
      assertEquals(INPUT_DATA[i], outputData[i]);
    }
  }

  @Test
  void inputDataAndOutputDataAreTheSameLength() {
    final var inputStream = new ByteArrayInputStream(INPUT_DATA);
    final var outputStream = new ByteArrayOutputStream();
    basicDataHandler.pipeData(inputStream, outputStream);

    assertEquals(INPUT_DATA.length, outputStream.toByteArray().length);
  }

  @Test
  void whenGivenNoDataItDoesNotThrowAnError() {
    final var inputStream = new ByteArrayInputStream(INPUT_DATA);
    final var outputStream = new ByteArrayOutputStream();
    final var readDataBeforePiping = inputStream.readAllBytes();

    assertDoesNotThrow(() -> basicDataHandler.pipeData(inputStream, outputStream));
  }

  @Test
  void whenGivenNoDataItDoesNotAddDataToOutputStream() {
    final var inputStream = new ByteArrayInputStream(INPUT_DATA);
    final var outputStream = new ByteArrayOutputStream();
    final var readDataBeforePiping = inputStream.readAllBytes();

    basicDataHandler.pipeData(inputStream, outputStream);

    assertEquals(0, outputStream.toByteArray().length);
  }

  @Test
  void itHandlesNullInputStreamGracefully() {
    assertDoesNotThrow(() -> basicDataHandler.pipeData(null, new ByteArrayOutputStream()));
  }

  @Test
  void itHandlesNullOutputStreamGracefully() {
    assertDoesNotThrow(() -> basicDataHandler.pipeData(new ByteArrayInputStream(INPUT_DATA), null));
  }

  @Test
  void itHandlesNullOutputStreamAndNullInputGracefully() {
    assertDoesNotThrow(() -> basicDataHandler.pipeData(null, null));
  }
}
