package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day12 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(-2, 0),
      new Point(0, -2),
      new Point(2, 0),
      new Point(0, 2));
  private final Map<Point, Character> map = new HashMap<>();
  private final int maxX;
  private final int maxY;

  Day12(final List<String> lines) {
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        map.put(new Point(x * 2, y * 2), lines.get(y).charAt(x));
      }
    }
    maxY = (lines.size() - 1) * 2;
    maxX = (lines.getFirst().length() - 1) * 2;
  }

  public int fenceCost() {
    return fenceCost(false);
  }

  public int fenceCost(final boolean bulk) {
    int price = 0;
    final Set<Point> fenced = new HashSet<>();
    for (int y = 0; y <= maxY; y += 2) {
      for (int x = 0; x <= maxX; x += 2) {
        var point = new Point(x, y);
        if (fenced.contains(point)) {
          continue;
        }
        var farmInfo = farm(point);
        fenced.addAll(farmInfo.getLeft());
        price += farmInfo.getLeft().size() *
            (bulk ? countSides(farmInfo.getRight())
                : farmInfo.getRight().size());
      }
    }
    return price;
  }

  private Pair<Set<Point>, Set<Pair<Point, Point>>> farm(final Point initialPoint) {
    final Character farmType = map.get(initialPoint);
    Queue<Pair<Point, Point>> queue = new ArrayDeque<>();
    final Set<Point> fenced = new HashSet<>();
    final Set<Pair<Point, Point>> fences = new HashSet<>();
    fenced.add(initialPoint);
    for (final var direction : DIRECTIONS) {
      queue.add(Pair.of(new Point(initialPoint.x + direction.x, initialPoint.y + direction.y),
          direction));
    }
    while (!queue.isEmpty()) {
      var entry = queue.poll();
      var point = entry.getLeft();
      var directionTravelled = entry.getRight();
      if (fenced.contains(point)) {
        continue;
      }
      if (farmType.equals(map.get(point))) {
        fenced.add(point);
        for (final var direction : DIRECTIONS) {
          queue.add(Pair.of(new Point(point.x + direction.x, point.y + direction.y), direction));
        }
      } else {
        fences.add(Pair.of(
            new Point(point.x - (directionTravelled.x / 2), point.y - (directionTravelled.y / 2)),
            directionTravelled));
      }
    }
    return Pair.of(fenced, fences);
  }

  private int countSides(final Set<Pair<Point, Point>> fences) {
    int sides = 0;
    final Set<Pair<Point, Point>> countedSides = new HashSet<>();
    for (final var fence : fences) {
      if (!countedSides.contains(fence)) {
        sides++;
        for (final var direction : DIRECTIONS) {
          int i = 1;
          while (fences.contains(
              Pair.of(new Point(fence.getLeft().x + (direction.x * i),
                  fence.getLeft().y + (direction.y * i)), fence.getRight()))) {
            countedSides.add(Pair.of(new Point(fence.getLeft().x + (direction.x * i),
                fence.getLeft().y + (direction.y * i)), fence.getRight()));
            i++;
          }
        }
      }
    }
    return sides;
  }
}
