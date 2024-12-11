package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day11Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/sample1.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.stoneCount(25)).isEqualTo(55312);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.stoneCount(25)).isEqualTo(183620);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day11/input.txt"))
            .toURI()));
    final Day11 day11 = new Day11(lines);
    Assertions.assertThat(day11.stoneCount(75)).isEqualTo(220377651399268L);
  }
}