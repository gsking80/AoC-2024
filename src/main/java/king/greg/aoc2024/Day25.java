package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.List;

public class Day25 {

  private final List<Long> locks;
  private final List<Long> keys;

  public Day25(List<String> lines) {
    locks = new ArrayList<>();
    keys = new ArrayList<>();
    for (int i = 0; i < lines.size(); i += 8) {
      long value = 0L;
      for (int j = i; j < i + 7; j++) {
        for (char c : lines.get(j).toCharArray()) {
          value = (value << 1) + (c == '#' ? 1L : 0L);
        }
      }
      if (lines.get(i).equals("#####")) {
        locks.add(value);
      } else {
        keys.add(value);
      }
    }
  }

  public int fits() {
    int count = 0;
    for (long key : keys) {
      for (long lock : locks) {
        if ((key & lock) == 0) {
          count++;
        }
      }
    }
    return count;
  }
}
