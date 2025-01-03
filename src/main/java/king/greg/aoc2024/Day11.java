package king.greg.aoc2024;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 {

  private final List<String> lines;

  Day11(final List<String> lines) {
    this.lines = lines;
  }

  public long stoneCount(final long blinks) {
    final var stones = lines.getFirst().split(" ");
    Map<Long, Long> stoneCounts = new HashMap<>();
    for (String stone : stones) {
      stoneCounts.put(Long.parseLong(stone),
          stoneCounts.getOrDefault(Long.parseLong(stone), 0L) + 1);
    }
    for (int i = 0; i < blinks; i++) {
      Map<Long, Long> nextStoneCounts = new HashMap<>();
      for (var stoneCount : stoneCounts.entrySet()) {
        var stone = stoneCount.getKey();
        var count = stoneCount.getValue();
        int digits = (int) (Math.log10(stone) + 1);
        if (stone == 0) {
          nextStoneCounts.put(1L, nextStoneCounts.getOrDefault(1L, 0L) + count);
        } else if (digits % 2 == 0) {
          long halfDigits = digits / 2;
          long threshold = ((long) Math.pow(10, halfDigits));
          nextStoneCounts.put(stone / threshold,
              nextStoneCounts.getOrDefault(stone / threshold, 0L) + count);
          nextStoneCounts.put(stone % threshold,
              nextStoneCounts.getOrDefault(stone % threshold, 0L) + count);
        } else {
          nextStoneCounts.put(stone * 2024, nextStoneCounts.getOrDefault(stone * 2024, 0L) + count);
        }
      }
      stoneCounts = nextStoneCounts;
    }
    long count = 0;
    for (var value : stoneCounts.values()) {
      count += value;
    }
    return count;
  }
}
