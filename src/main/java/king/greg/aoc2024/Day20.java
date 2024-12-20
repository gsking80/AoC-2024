package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day20 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(1, 0),
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1));
  final List<Point> path = new ArrayList<>();
  private final char[][] map;
  private Point start;
  private Point end;

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
    calculatePath();
  }

  private void calculatePath() {
    Point current = new Point(start);
    while (true) {
      path.add(current);
      if (current.equals(end)) {
        return;
      }
      for (final Point direction : DIRECTIONS) {
        var nextPoint = new Point(current.x + direction.x, current.y + direction.y);
        if ((map[nextPoint.y][nextPoint.x] == '.') && !path.contains(nextPoint)) {
          current = nextPoint;
          break;
        }
      }
    }
  }

  public int findShortcuts(final int cheatTime, final int minimumTimeSaved) {
    int shortcutCount = 0;
    for (int shortcutStartDistance = 0; shortcutStartDistance < path.size() - minimumTimeSaved; shortcutStartDistance++) {
      final Point shortcutStart = path.get(shortcutStartDistance);
      for (int shortcutEndDistance = shortcutStartDistance + minimumTimeSaved; shortcutEndDistance < path.size(); shortcutEndDistance++) {
        final Point shortcutEnd = path.get(shortcutEndDistance);
        final int shortcutDistance =
            Math.abs(shortcutStart.x - shortcutEnd.x) + Math.abs(shortcutStart.y - shortcutEnd.y);
        if (shortcutDistance <= cheatTime) {
          final int timeSaved = (shortcutEndDistance - shortcutStartDistance) - shortcutDistance;
          if (timeSaved >= minimumTimeSaved) {
            shortcutCount++;
          }
        }
      }
    }
    return shortcutCount;
  }
}
