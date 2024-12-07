package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.calibrate(false)).isEqualTo(3749);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.calibrate(false)).isEqualTo(14711933466277L);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/sample1.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.calibrate(true)).isEqualTo(11387);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day07/input.txt"))
            .toURI()));
    final Day07 day07 = new Day07(lines);
    Assertions.assertThat(day07.calibrate(true)).isEqualTo(286580387663654L);
  }
}