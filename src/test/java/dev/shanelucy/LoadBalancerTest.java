package dev.shanelucy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoadBalancerTest {
  private Main loadBalancer;

  @BeforeEach
  void setUp() {
    loadBalancer = new Main();
  }

  @Test
  void itAddsCorrectly() {
    final var result = loadBalancer.add(1, 2);

    Assertions.assertEquals(3, result);
  }
}
