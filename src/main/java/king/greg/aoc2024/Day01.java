package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Day01 {

  private final List<String> lines;

  Day01(final List<String> lines) {
    this.lines = lines;
  }

  public long distance() {
    var left = new ArrayList<Long>();
    var right = new ArrayList<Long>();
    for (var line : lines) {
      var temp = line.split("\\s+");
      left.add(Long.parseLong(temp[0]));
      right.add(Long.parseLong(temp[1]));
    }
    left.sort(Long::compareTo);
    right.sort(Long::compareTo);
    long distance = 0;
    for (var i = 0; i < left.size(); i++) {
      distance += Math.abs(left.get(i) - right.get(i));
    }
    return distance;
  }

  public long similarity() {
    var left = new ArrayList<Long>();
    var right = new HashMap<Long, Long>();
    for (var line : lines) {
      var temp = line.split("\\s+");
      left.add(Long.parseLong(temp[0]));
      var num = Long.parseLong(temp[1]);
      right.put(num, right.getOrDefault(num, 0L) + 1);
    }
    long similarity = 0;
    for (var num : left) {
      similarity += num * right.getOrDefault(num, 0L);
    }
    return similarity;
  }
}
