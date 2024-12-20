package king.greg.aoc2024;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(1, 0),
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1));
  private final char[][] map;
  private final Map<Point, Integer> distancesToEnd = new HashMap<>();
  private Point start;
  private Point end;
  private int maxTime;

  Day20(final List<String> lines) {
    map = new char[lines.size()][lines.getFirst().length()];
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        final char space = lines.get(y).charAt(x);
        switch (space) {
          case 'S':
            start = new Point(x, y);
            map[y][x] = '.';
            break;
          case 'E':
            end = new Point(x, y);
            map[y][x] = '.';
            break;
          default:
            map[y][x] = space;
        }
      }
    }
    populateDistanceMap();
  }

  private void populateDistanceMap() {
    Point current = new Point(end);
    int distanceLeft = 0;
    while (true) {
      distancesToEnd.put(current, distanceLeft);
      if (current.equals(start)) {
        maxTime = distanceLeft;
        return;
      }
      for (final Point direction : DIRECTIONS) {
        var nextPoint = new Point(current.x + direction.x, current.y + direction.y);
        if (!distancesToEnd.containsKey(nextPoint) && map[nextPoint.y][nextPoint.x] == '.') {
          current = nextPoint;
          distanceLeft++;
          break;
        }
      }
    }
  }

  public int calculateShortcuts(final int minimumTimeSaved) {
    final int[] shortcuts = new int[maxTime];
    for (final var entry : distancesToEnd.entrySet()) {
      final Point mapPoint = entry.getKey();
      final int distanceToEnd = entry.getValue();
      for (final Point direction1 : DIRECTIONS) {
        final Point testPoint = new Point(mapPoint.x + direction1.x * 2,
            mapPoint.y + direction1.y * 2);
        final Integer nextDistanceToEnd = distancesToEnd.get(testPoint);
        if (nextDistanceToEnd != null) {
          final int savings = (distanceToEnd - nextDistanceToEnd) - 2;
          if (savings > 0) {
            shortcuts[savings]++;
          }
        }
      }
    }
    int shortcutCount = 0;
    for (int i = minimumTimeSaved; i < shortcuts.length; i++) {
      shortcutCount += shortcuts[i];
    }
    return shortcutCount;
  }

  public int calculateShortcuts2(final int cheatTime, final int minimumTimeSaved) {
    final int[] shortcuts = new int[maxTime];
    for (final var entry : distancesToEnd.entrySet()) {
      final Point mapPoint = entry.getKey();
      final int distanceToEnd = entry.getValue();
      for (final var entry2 : distancesToEnd.entrySet()) {
        final Point mapPoint2 = entry2.getKey();
        final int distanceToEnd2 = entry2.getValue();
        final int shortcutDistance =
            Math.abs(mapPoint.x - mapPoint2.x) + Math.abs(mapPoint.y - mapPoint2.y);
        if (shortcutDistance <= cheatTime) {
          final int timeSaved = (distanceToEnd2 - distanceToEnd) - shortcutDistance;
          if (timeSaved > 0) {
            shortcuts[timeSaved]++;
          }
        }
      }
    }
    int shortcutCount = 0;
    for (int i = minimumTimeSaved; i < shortcuts.length; i++) {
      shortcutCount += shortcuts[i];
    }
    return shortcutCount;
  }
}
