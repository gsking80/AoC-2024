package king.greg.aoc2024;

import java.util.List;

public class Day22 {

  private final int[] secrets;

  Day22(final List<String> lines) {
    secrets = new int[lines.size()];
    for (int i = 0; i < lines.size(); i++) {
      secrets[i] = Integer.parseInt(lines.get(i));
    }
  }

  private static int secretNumber(final int initialNumber, final int generations) {
    int secretNumber = initialNumber;
    for (int i = 0; i < generations; i++) {
      secretNumber = nextSecretNumber(secretNumber);
    }
    return secretNumber;
  }

  private static int nextSecretNumber(final int initialNumber) {
    int secretNumber = initialNumber;
    var mixer = secretNumber << 6;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber & 16777215;

    mixer = secretNumber >> 5;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber & 16777215;

    mixer = secretNumber << 11;
    secretNumber = secretNumber ^ mixer;
    secretNumber = secretNumber & 16777215;
    return secretNumber;
  }

  public long secretNumberSums(final int generations) {
    long result = 0;
    for (final int secret : secrets) {
      result += secretNumber(secret, generations);
    }
    return result;
  }

  public int mostBananas(final int generations) {
    final int[] bananas = new int[1048576];
    for (final int secret : secrets) {
      int secretNumber = secret;
      int bananaFilter = 0;
      int price = secretNumber % 10;
      final boolean[] filtersSeen = new boolean[1048576];
      for (int j = 1; j <= generations; j++) {
        int lastPrice = price;
        secretNumber = nextSecretNumber(secretNumber);
        price = secretNumber % 10;
        if (j >= 4) {
          bananaFilter = ((bananaFilter & 32767) << 5) + price + 9 - lastPrice;
          if (!filtersSeen[bananaFilter]) {
            filtersSeen[bananaFilter] = true;
            bananas[bananaFilter] += price;
          }
        }
      }
    }
    int maxBananas = 0;
    for (final int banana : bananas) {
      if (banana > maxBananas) {
        maxBananas = banana;
      }
    }
    return maxBananas;
  }
}
