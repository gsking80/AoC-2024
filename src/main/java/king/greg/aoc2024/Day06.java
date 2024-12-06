package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day06 {

  static final Point[] directions = {new Point(0, -1), new Point(1, 0), new Point(0, 1),
      new Point(-1, 0)};
  private final Set<Point> obstacles = new HashSet<>();
  private final int maxX;
  private final int maxY;
  private Point startLocation;

  Day06(final List<String> lines) {
    maxX = lines.getFirst().length() - 1;
    maxY = lines.size() - 1;
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        switch (lines.get(y).charAt(x)) {
          case '#':
            obstacles.add(new Point(x, y));
            break;
          case '^':
            startLocation = new Point(x, y);
            break;
          default:
        }
      }
    }
  }

  public int guardPositions() {
    Point guardLocation = new Point(startLocation);
    int guardDirection = 0;

    final Set<Point> positions = new HashSet<>();

    positions.add(new Point(guardLocation.x, guardLocation.y));
    while (guardLocation.x <= maxX && guardLocation.y <= maxY && guardLocation.x >= 0
        && guardLocation.y >= 0) {
      positions.add(new Point(guardLocation.x, guardLocation.y));
      var nextStep = new Point(guardLocation.x + directions[guardDirection].x,
          guardLocation.y + directions[guardDirection].y);
      if (obstacles.contains(nextStep)) {
        guardDirection = (guardDirection + 1) % 4;
      } else {
        guardLocation = nextStep;
      }
    }
    return positions.size();
  }

  public int obstructions() {
    Point guardLocation = new Point(startLocation);
    int guardDirection = 0;
    final List<Pair<Point, Integer>> path = new ArrayList<>();
    final Set<Point> positions = new HashSet<>();
    final Set<Point> obstacleOptions = new HashSet<>();
    while (guardLocation.x <= maxX && guardLocation.y <= maxY && guardLocation.x >= 0
        && guardLocation.y >= 0) {
      path.add(Pair.of(new Point(guardLocation), guardDirection));
      positions.add(new Point(guardLocation));
      var nextStep = new Point(guardLocation.x + directions[guardDirection].x,
          guardLocation.y + directions[guardDirection].y);
      if (obstacles.contains(nextStep)) {
        guardDirection = (guardDirection + 1) % 4;
      } else if (obstacleOptions.contains(nextStep) ||
          positions.contains(nextStep) ||
          !(nextStep.x <= maxX && nextStep.y <= maxY && nextStep.x >= 0 && nextStep.y >= 0)) {
        guardLocation = new Point(nextStep);
      } else {
        var potentialDirection = (guardDirection + 1) % 4;
        var potentialX = guardLocation.x;
        var potentialY = guardLocation.y;
        List<Pair<Point, Integer>> potentialPath = new ArrayList<>();
        while (potentialX <= maxX && potentialY <= maxY && potentialX >= 0 && potentialY >= 0) {
          var potentialPathStep = Pair.of(new Point(potentialX, potentialY), potentialDirection);
          if (potentialPath.contains(potentialPathStep) || path.contains(potentialPathStep)) {
            obstacleOptions.add(nextStep);
            break;
          }
          potentialPath.add(potentialPathStep);
          var nextPotentialX = potentialX + directions[potentialDirection].x;
          var nextPotentialY = potentialY + directions[potentialDirection].y;
          if (obstacles.contains(new Point(nextPotentialX, nextPotentialY)) || nextStep.equals(
              new Point(nextPotentialX, nextPotentialY))) {
            potentialDirection = (potentialDirection + 1) % 4;
          } else {
            potentialX = nextPotentialX;
            potentialY = nextPotentialY;
          }
        }
        guardLocation = new Point(nextStep);
      }
    }
    return obstacleOptions.size();
  }
}
