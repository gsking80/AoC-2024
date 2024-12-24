package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day24Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/sample1.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.decimalZ()).isEqualTo(4);
  }

  @Test
  void testSample1a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/sample1a.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.decimalZ()).isEqualTo(2024);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.decimalZ()).isEqualTo(53258032898766L);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day24/input.txt"))
            .toURI()));
    final Day24 day24 = new Day24(lines);
    Assertions.assertThat(day24.wireSwaps()).isEqualTo("gbs,hwq,thm,wrm,wss,z08,z22,z29");
  }
}