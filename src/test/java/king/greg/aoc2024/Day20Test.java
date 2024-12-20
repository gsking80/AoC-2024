package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day20Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/sample1.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.findShortcuts(2, 2)).isEqualTo(44);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.findShortcuts(2, 100)).isEqualTo(1448);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/sample1.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.findShortcuts(20, 76)).isEqualTo(3);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day20/input.txt"))
            .toURI()));
    final Day20 day20 = new Day20(lines);
    Assertions.assertThat(day20.findShortcuts(20, 100)).isEqualTo(1017615);
  }
}