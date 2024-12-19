package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Day19 {

  private final List<String> towels = new ArrayList<>();
  private final List<String> designs = new ArrayList<>();
  private final Map<String, Long> memo = new ConcurrentSkipListMap<>();

  Day19(final List<String> lines) {
    boolean secondPart = false;
    for (String line : lines) {
      if (secondPart) {
        designs.add(line);
      } else if (line.isEmpty()) {
        secondPart = true;
      } else {
        towels.addAll(Arrays.stream(line.split(", ")).toList());
      }
    }
  }

  public long possibleDesigns() {
    long possibleDesigns = 0;
    for (String design : designs) {
      if (designPermutations(design) > 0) {
        possibleDesigns++;
      }
    }
    return possibleDesigns;
  }

  public long possiblePermutations() {
    long possiblePermutations = 0;
    for (String design : designs) {
      possiblePermutations += designPermutations(design);
    }
    return possiblePermutations;
  }

  private long designPermutations(String design) {
    return memo.computeIfAbsent(design, k ->
    {
      long permutations = 0;
      for (String towel : towels) {
        if (design.equals(towel)) {
          permutations++;
        } else if (design.startsWith(towel)) {
          permutations += designPermutations(design.substring(towel.length()));
        }
      }
      return permutations;
    });
  }
}
