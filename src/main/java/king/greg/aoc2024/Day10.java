package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(-1, 0),
      new Point(0, -1),
      new Point(1, 0),
      new Point(0, 1));
  final Map<Point, Integer> trailMap = new HashMap<>();
  final Map<Point, Set<Point>> trailEnds = new HashMap<>();
  final Map<Point, Integer> paths = new HashMap<>();
  final List<Set<Point>> heightLocations = new ArrayList<>();

  Day10(final List<String> lines) {
    for (var i = 0; i < 10; i++) {
      heightLocations.add(new HashSet<>());
    }
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        var height = lines.get(y).charAt(x) - '0';
        var point = new Point(x, y);
        trailMap.put(point, height);
        trailEnds.put(point, new HashSet<>());
        heightLocations.get(height).add(point);
      }
    }
  }

  public long trailheadScore() {
    for (var height = 9; height >= 0; height--) {
      for (var point : heightLocations.get(height)) {
        calculateEnds(point, height);
      }
    }
    var score = 0;
    for (var point : heightLocations.getFirst()) {
      score += trailEnds.get(point).size();
    }
    return score;
  }

  private void calculateEnds(Point point, int height) {
    if (height == 9) {
      trailEnds.get(point).add(point);
    } else {
      for (final var direction : DIRECTIONS) {
        final var testPoint = new Point(point.x + direction.x, point.y + direction.y);
        if (trailMap.getOrDefault(testPoint, 20) == height + 1) {
          trailEnds.get(point).addAll(trailEnds.get(testPoint));
        }
      }
    }
  }

  public long trailheadRating() {
    for (var height = 9; height >= 0; height--) {
      for (var point : heightLocations.get(height)) {
        paths.put(point, getPathsToEnd(point, height));
      }
    }
    var score = 0;
    for (var point : heightLocations.getFirst()) {
      score += paths.get(point);
    }
    return score;
  }

  private int getPathsToEnd(final Point point, final int height) {
    int pathsToEnd = 0;
    if (height == 9) {
      pathsToEnd++;
    } else {
      for (final var direction : DIRECTIONS) {
        final var testPoint = new Point(point.x + direction.x, point.y + direction.y);
        if (trailMap.getOrDefault(testPoint, 20) == height + 1) {
          pathsToEnd += paths.getOrDefault(testPoint, 0);
        }
      }
    }
    return pathsToEnd;
  }
}
