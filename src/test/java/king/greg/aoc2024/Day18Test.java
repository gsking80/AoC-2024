package king.greg.aoc2024;

import java.awt.Point;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day18Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/sample1.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines, 6);
    Assertions.assertThat(day18.steps(12)).isEqualTo(22);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines, 70);
    Assertions.assertThat(day18.steps(1024)).isEqualTo(336);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/sample1.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines, 6);
    Assertions.assertThat(day18.blocked()).isEqualTo(new Point(6, 1));
  }

  @Test
  void testSoluution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day18/input.txt"))
            .toURI()));
    final Day18 day18 = new Day18(lines, 70);
    Assertions.assertThat(day18.blocked()).isEqualTo(new Point(24, 30));
  }
}