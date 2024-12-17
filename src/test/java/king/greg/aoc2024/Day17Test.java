package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day17Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("4,6,3,5,6,3,5,2,1,0");
  }

  @Test
  void testSample1a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1a.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("1");
  }

  @Test
  void testSample1b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1b.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("0,1,2");
  }

  @Test
  void testSample1c() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1c.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("4,2,5,6,7,7,7,7,3,1,0,0");
  }

  @Test
  void testSample1d() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/sample1d.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("2");
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/input.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.output()).isEqualTo("1,3,7,4,6,4,2,3,5");
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day17/input.txt"))
            .toURI()));
    final Day17 day17 = new Day17(lines);
    Assertions.assertThat(day17.initializedValue()).isEqualTo(202367025818154L);
  }
}