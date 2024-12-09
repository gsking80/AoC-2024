package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day09Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.compactedChecksum()).isEqualTo(1928);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.compactedChecksum()).isEqualTo(6291146824486L);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/sample1.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.defragmentedChecksum()).isEqualTo(2858);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day09/input.txt"))
            .toURI()));
    final Day09 day09 = new Day09(lines);
    Assertions.assertThat(day09.defragmentedChecksum()).isEqualTo(6307279963620L);
  }
}