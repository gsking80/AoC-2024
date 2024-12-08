package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {

  @Test
  void testSample1a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample2.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes()).isEqualTo(2);
  }

  @Test
  void testSample1b() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample3.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes()).isEqualTo(4);
  }

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample1.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes()).isEqualTo(14);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes()).isEqualTo(276);
  }

  @Test
  void testSample2a() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample4.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes(true)).isEqualTo(9);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/sample1.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes(true)).isEqualTo(34);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day08/input.txt"))
            .toURI()));
    final Day08 day08 = new Day08(lines);
    Assertions.assertThat(day08.antinodes(true)).isEqualTo(991);
  }
}