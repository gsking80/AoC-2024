package king.greg.aoc2024;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.tuple.Pair;

public class Day11 {

  private final List<String> lines;
  private final Map<Pair<String, Long>, Long> memo = new ConcurrentSkipListMap<>();

  Day11(final List<String> lines) {
    this.lines = lines;
  }

  public long stoneCount(final long blinks) {
    final var stones = lines.getFirst().split(" ");
    long count = 0;
    for (String stone : stones) {
      count += stoneCount(stone, blinks);
    }
    return count;
  }

  private long stoneCount(final String stone, final long blinks) {
    return memo.computeIfAbsent(Pair.of(stone, blinks),
        s -> {
          var nextStones = blink(stone);
          if (blinks == 1) {
            return (long) nextStones.length;
          }
          long calcCount = 0;
          for (var nextStone : nextStones) {
            calcCount += stoneCount(nextStone, blinks - 1);
          }
          return calcCount;
        });
  }

  private String[] blink(final String stone) {
    if ("0".equals(stone)) {
      return new String[]{"1"};
    } else if (stone.length() % 2 == 0) {
      return new String[]{stone.substring(0, stone.length() / 2),
          stone.substring(stone.length() / 2).replaceFirst("^0+(?!$)", "")};
    }
    return new String[]{String.valueOf(Long.parseLong(stone) * 2024)};
  }
}
