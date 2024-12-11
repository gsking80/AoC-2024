package king.greg.aoc2024;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.tuple.Pair;

public class Day11 {

  private final List<String> lines;
  private final Map<Pair<Long, Long>, Long> memoCount = new ConcurrentSkipListMap<>();

  Day11(final List<String> lines) {
    this.lines = lines;
  }

  public long stoneCount(final long blinks) {
    final var stones = lines.getFirst().split(" ");
    long count = 0;
    for (String stone : stones) {
      count += stoneCount(Long.parseLong(stone), blinks);
    }
    return count;
  }

  private long stoneCount(final long stone, final long blinks) {
    return memoCount.computeIfAbsent(Pair.of(stone, blinks),
        s -> {

          if (blinks == 0) {
            return 1L;
          }
          int digits = (int) (Math.log10(stone) + 1);
          long calcCount;
          if (stone == 0) {
            calcCount = stoneCount(1, blinks - 1);
          } else if (digits % 2 == 0) {
            long halfDigits = digits / 2;
            long threshold = ((long) Math.pow(10, halfDigits));
            calcCount = stoneCount(stone / threshold, blinks - 1)
                + stoneCount(stone % threshold, blinks - 1);
          } else {
            calcCount = stoneCount(stone * 2024, blinks - 1);
          }
          return calcCount;
        }
    );
  }
}
