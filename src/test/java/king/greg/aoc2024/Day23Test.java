package king.greg.aoc2024;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Day23Test {

  @Test
  void testSample1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/sample1.txt"))
            .toURI()));
    final Day23 day23 = new Day23(lines);
    Assertions.assertThat(day23.sets("t")).isEqualTo(7);
  }

  @Test
  void testSolution1() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/input.txt"))
            .toURI()));
    final Day23 day23 = new Day23(lines);
    Assertions.assertThat(day23.sets("t")).isEqualTo(1437);
  }

  @Test
  void testSample2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/sample1.txt"))
            .toURI()));
    final Day23 day23 = new Day23(lines);
    Assertions.assertThat(day23.password()).isEqualTo("co,de,ka,ta");
  }

  @Test
  void testSolution2() throws URISyntaxException, IOException {
    final var lines = Files.readAllLines(Paths.get(
        Objects.requireNonNull(getClass().getClassLoader().getResource("Day23/input.txt"))
            .toURI()));
    final Day23 day23 = new Day23(lines);
    Assertions.assertThat(day23.password()).isEqualTo("da,do,gx,ly,mb,ns,nt,pz,sc,si,tp,ul,vl");
  }
}