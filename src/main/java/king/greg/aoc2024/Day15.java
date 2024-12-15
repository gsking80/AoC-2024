package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day15 {

  private static final Map<Character, Point> DIRECTION_MAP = Map.of(
      '<', new Point(-1, 0),
      '^', new Point(0, -1),
      '>', new Point(1, 0),
      'v', new Point(0, 1));
  private final Set<Point> walls = new HashSet<>();
  private final Set<Point> boxes = new HashSet<>();
  private final List<String> movement = new ArrayList<>();
  private final boolean wide;
  private Point botPosition;

  Day15(final List<String> lines) {
    this(lines, false);
  }

  Day15(final List<String> lines, final boolean wide) {
    this.wide = wide;
    int i = 0;
    boolean movements = false;
    for (String line : lines) {
      if (line.isEmpty()) {
        movements = true;
      } else if (movements) {
        movement.add(line);
      } else {
        for (int index = 0; index < line.length(); index++) {
          int x = wide ? index * 2 : index;
          switch (line.charAt(index)) {
            case '#':
              walls.add(new Point(x, i));
              break;
            case 'O':
              boxes.add(new Point(x, i));
              break;
            case '@':
              botPosition = new Point(x, i);
              break;
            default:
              //do nothing
          }
        }
        i++;
      }
    }
  }

  public int boxCoordinateSum() {
    for (var motions : movement) {
      for (var motion : motions.toCharArray()) {
        moveBot(DIRECTION_MAP.get(motion), wide);
      }
    }
    int boxCoordinateSum = 0;
    for (var box : boxes) {
      boxCoordinateSum += box.x + 100 * box.y;
    }
    return boxCoordinateSum;
  }

  private void moveBot(final Point direction, final boolean wide) {
    final var canMove = canMove(Set.of(botPosition), direction, wide);
    if (Boolean.TRUE.equals(canMove.getLeft())) {
      final var boxesToRemove = new HashSet<Point>();
      final var boxesToAdd = new HashSet<Point>();
      for (final var boxToMove : canMove.getRight()) {
        if (boxes.contains(boxToMove)) {
          boxesToRemove.add(boxToMove);
          boxesToAdd.add(new Point(boxToMove.x + direction.x, boxToMove.y + direction.y));
        }
      }
      boxes.removeAll(boxesToRemove);
      boxes.addAll(boxesToAdd);
      botPosition = new Point(botPosition.x + direction.x, botPosition.y + direction.y);
    }
  }

  private Pair<Boolean, Set<Point>> canMove(final Set<Point> boxesToMove,
      final Point direction, final boolean wide) {
    final Set<Point> boxesToCheck = new HashSet<>();
    for (var boxLocation : boxesToMove) {
      var nextLocation = new Point(boxLocation.x + direction.x,
          boxLocation.y + direction.y);
      if (!boxesToMove.contains(nextLocation)) {
        var nextLeftLocation = new Point(nextLocation.x - 1, nextLocation.y);
        if (walls.contains(nextLocation) || (wide && walls.contains(nextLeftLocation))) {
          return Pair.of(false, null);
        }
        if (boxes.contains(nextLocation)) {
          boxesToCheck.add(nextLocation);
          if (wide) {
            boxesToCheck.add(new Point(nextLocation.x + 1, nextLocation.y));
          }
        } else if (wide && boxes.contains(nextLeftLocation)) {
          boxesToCheck.add(nextLeftLocation);
          boxesToCheck.add(nextLocation);
        }
      }
    }
    if (boxesToCheck.isEmpty()) {
      return Pair.of(true, boxesToMove);
    }
    final Set<Point> totalBoxes = new HashSet<>();
    totalBoxes.addAll(boxesToCheck);
    totalBoxes.addAll(boxesToMove);
    return canMove(totalBoxes, direction, wide);
  }
}
