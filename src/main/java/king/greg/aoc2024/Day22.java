package king.greg.aoc2024;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day22 {

  private final List<String> lines;

  Day22(final List<String> lines) {
    this.lines = lines;
  }

  private static long secretNumber(final long initialNumber, final long generations) {
    long secretNumber = initialNumber;
    for (int i = 0; i < generations; i++) {
      secretNumber = nextSecretNumber(secretNumber);
    }
    return secretNumber;
  }

  private static long nextSecretNumber(final long initialNumber) {
    long secretNumber = initialNumber;
    var mixer = secretNumber << 6;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber % 16777216;

    mixer = secretNumber >> 5;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber % 16777216;

    mixer = secretNumber << 11;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber % 16777216;
    return secretNumber;
  }

  public long secretNumberSums(final long generations) {
    long result = 0;
    for (final String line : lines) {
      result += secretNumber(Long.parseLong(line), generations);
    }
    return result;
  }

  public long mostBananas(final int generations) {
    final int[][] prices = new int[lines.size()][generations + 1];
    final int[][] changes = new int[lines.size()][generations + 1];
    final Map<RecentChanges, Long> bestPrices = new HashMap<>();
    for (int i = 0; i < lines.size(); i++) {
      long secretNumber = Long.parseLong(lines.get(i));
      prices[i][0] = (int) secretNumber % 10;
      final Set<RecentChanges> seen = new HashSet<>();
      for (int j = 1; j <= generations; j++) {
        prices[i][j] = (int) secretNumber % 10;
        changes[i][j] = prices[i][j] - prices[i][j - 1];
        if (j >= 4) {
          final RecentChanges recentChanges = new RecentChanges(changes[i][j - 3], changes[i][j - 2],
              changes[i][j - 1], changes[i][j]);
          if (!seen.contains(recentChanges)) {
            seen.add(recentChanges);
            bestPrices.put(recentChanges,
                bestPrices.getOrDefault(recentChanges, 0L) + prices[i][j]);
          }
        }
        secretNumber = nextSecretNumber(secretNumber);
      }
    }
    return Collections.max(bestPrices.values());
  }

  private record RecentChanges(int a, int b, int c, int d) {

  }
}
