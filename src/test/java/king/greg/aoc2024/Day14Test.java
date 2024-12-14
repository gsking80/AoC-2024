package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day14Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/sample1.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.safetyScore(11, 7, 100)).isEqualTo(12);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.safetyScore(101, 103, 100)).isEqualTo(211692000);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day14/input.txt"))
            .toURI()));
    final Day14 day14 = new Day14(lines);
    Assertions.assertThat(day14.firstTree(101, 103)).isEqualTo(6587);
  }
}