package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/sample1.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.pagePrinter()).isEqualTo(143);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.pagePrinter()).isEqualTo(5108);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/sample1.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.sortedPrinter()).isEqualTo(123);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day05/input.txt"))
            .toURI()));
    final Day05 day05 = new Day05(lines);
    Assertions.assertThat(day05.sortedPrinter()).isEqualTo(7380);
  }
}