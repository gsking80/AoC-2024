package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day21Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/sample1.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.complexitySum(2)).isEqualTo(126384);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/input.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.complexitySum(2)).isEqualTo(215374);
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day21/input.txt"))
            .toURI()));
    final Day21 day21 = new Day21(lines);
    Assertions.assertThat(day21.complexitySum(25)).isEqualTo(260586897262600L);
  }
}