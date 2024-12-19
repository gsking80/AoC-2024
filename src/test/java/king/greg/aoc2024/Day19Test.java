package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day19Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/sample1.txt"))
            .toURI()));
    final Day19 day19 = new Day19(lines);
    Assertions.assertThat(day19.possibleDesigns()).isEqualTo(6);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/input.txt"))
            .toURI()));
    final Day19 day19 = new Day19(lines);
    Assertions.assertThat(day19.possibleDesigns()).isEqualTo(247);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/sample1.txt"))
            .toURI()));
    final Day19 day19 = new Day19(lines);
    Assertions.assertThat(day19.possiblePermutations()).isEqualTo(16);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day19/input.txt"))
            .toURI()));
    final Day19 day19 = new Day19(lines);
    Assertions.assertThat(day19.possiblePermutations()).isEqualTo(692596560138745L);
  }
}