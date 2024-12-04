package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/sample1.txt"))
            .toURI()));
    final Day04 day04 = new Day04(lines);
    Assertions.assertThat(day04.xmasFinder()).isEqualTo(18);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/input.txt"))
            .toURI()));
    final Day04 day04 = new Day04(lines);
    Assertions.assertThat(day04.xmasFinder()).isEqualTo(2370);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/sample1.txt"))
            .toURI()));
    final Day04 day04 = new Day04(lines);
    Assertions.assertThat(day04.crossMasFinder()).isEqualTo(9);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day04/input.txt"))
            .toURI()));
    final Day04 day04 = new Day04(lines);
    Assertions.assertThat(day04.crossMasFinder()).isEqualTo(1908);
  }

}