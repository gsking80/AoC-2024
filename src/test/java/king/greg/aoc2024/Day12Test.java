package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day12Test {

  @Test
  void testSample1_1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample1.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost()).isEqualTo(140);
  }

  @Test
  void testSample1_2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample2.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost()).isEqualTo(772);
  }

  @Test
  void testSample1_3() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample3.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost()).isEqualTo(1930);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/input.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost()).isEqualTo(1450422);
  }

  @Test
  void testSample2_1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample1.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost(true)).isEqualTo(80);
  }

  @Test
  void testSample2_4() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample4.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost(true)).isEqualTo(236);
  }

  @Test
  void testSample2_5() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/sample5.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost(true)).isEqualTo(368);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day12/input.txt"))
            .toURI()));
    final Day12 day12 = new Day12(lines);
    Assertions.assertThat(day12.fenceCost(true)).isEqualTo(906606);
  }
}