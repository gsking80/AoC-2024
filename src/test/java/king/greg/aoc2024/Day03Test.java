package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day03Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/sample1.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.mulSum()).isEqualTo(161);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.mulSum()).isEqualTo(185797128);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/sample2.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.conditionalMulSum()).isEqualTo(48);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day03/input.txt"))
            .toURI()));
    final Day03 day03 = new Day03(lines);
    Assertions.assertThat(day03.conditionalMulSum()).isEqualTo(89798695);
  }
}