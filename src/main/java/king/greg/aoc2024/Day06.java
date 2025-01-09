package king.greg.aoc2024;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 {

  static final Point[] directions = {new Point(0, -1), new Point(1, 0), new Point(0, 1),
      new Point(-1, 0)};
  private final char[][] map;
  private int startX;
  private int startY;

  Day06(final List<String> lines) {
    map = new char[lines.getFirst().length() + 2][lines.size() + 2];
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        char current = lines.get(y).charAt(x);
        map[x + 1][y + 1] = current;
        if (current == '^') {
          startX = x + 1;
          startY = y + 1;
        }
      }
    }
  }

  public int guardPositions() {
    Point guardLocation = new Point(startX, startY);
    int guardDirection = 0;

    final Set<Point> positions = new HashSet<>();

    positions.add(new Point(guardLocation.x, guardLocation.y));
    while (map[guardLocation.x][guardLocation.y] != 0) {
      positions.add(new Point(guardLocation.x, guardLocation.y));
      var nextStep = new Point(guardLocation.x + directions[guardDirection].x,
          guardLocation.y + directions[guardDirection].y);
      if (map[nextStep.x][nextStep.y] == '#') {
        guardDirection = (guardDirection + 1) % 4;
      } else {
        guardLocation = nextStep;
      }
    }
    return positions.size();
  }

  public int obstructions() {
    int obstructions = 0;
    int guardX = startX;
    int guardY = startY;
    int guardDirection = 0;

    final Set<Integer> path = new HashSet<>();
    final Set<Integer> corners = new HashSet<>();
    final Set<Integer> potentialCorners = new HashSet<>();

    while (map[guardX][guardY] != 0) {
      int pathKey = (guardX << 9) + guardY;
      path.add(pathKey);
      var nextX = guardX + directions[guardDirection].x;
      var nextY = guardY + directions[guardDirection].y;
      if (map[nextX][nextY] == '#') {
        guardDirection = (guardDirection + 1) % 4;
        corners.add((((guardX << 9) + guardY) << 2) + guardDirection);
      } else if (path.contains((nextX << 9) + nextY) || map[nextX][nextY] == 0) {
        guardX = nextX;
        guardY = nextY;
      } else {
        var potentialDirection = (guardDirection + 1) % 4;
        var potentialX = guardX;
        var potentialY = guardY;
        potentialCorners.clear();
        map[nextX][nextY] = '#';
        while (map[potentialX][potentialY] != 0) {
          var nextPotentialX = potentialX + directions[potentialDirection].x;
          var nextPotentialY = potentialY + directions[potentialDirection].y;
          if (map[nextPotentialX][nextPotentialY] == '#') {
            potentialDirection = (potentialDirection + 1) % 4;
            var newCorner = (((potentialX << 9) + potentialY) << 2) + potentialDirection;
            if (corners.contains(newCorner) || potentialCorners.contains(newCorner)) {
              obstructions++;
              break;
            }
            potentialCorners.add(newCorner);
          } else {
            potentialX = nextPotentialX;
            potentialY = nextPotentialY;
          }
        }
        map[nextX][nextY] = '.';
        guardX = nextX;
        guardY = nextY;
      }
    }
    return obstructions;
  }
}
