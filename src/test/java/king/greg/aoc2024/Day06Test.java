package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.guardPositions()).isEqualTo(41);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.guardPositions()).isEqualTo(4982);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/sample1.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.obstructions()).isEqualTo(6);
  }

  @Test
  void testBorder2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/borderTest.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.obstructions()).isZero();
  }

  @Test
  void testStartLocation2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource(
                "Day06/startLocationTest.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.obstructions()).isZero();
  }

  @Test
  void testBacktrack2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource(
                "Day06/backtrack.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.obstructions()).isZero();
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day06/input.txt"))
            .toURI()));
    final Day06 day06 = new Day06(lines);
    Assertions.assertThat(day06.obstructions()).isEqualTo(
        1663);
  }
}