package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day15Test {

  @Test
  void testSample1_1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/sample1.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.boxCoordinateSum()).isEqualTo(10092);
  }

  @Test
  void testSample1_2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/sample2.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.boxCoordinateSum()).isEqualTo(2028);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines);
    Assertions.assertThat(day15.boxCoordinateSum()).isEqualTo(1492518);
  }

  @Test
  void testSample2_1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/sample1.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines, true);
    Assertions.assertThat(day15.boxCoordinateSum()).isEqualTo(9021);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day15/input.txt"))
            .toURI()));
    final Day15 day15 = new Day15(lines, true);
    Assertions.assertThat(day15.boxCoordinateSum()).isEqualTo(1512860);
  }
}