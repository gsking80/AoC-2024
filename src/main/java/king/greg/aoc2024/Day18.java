package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Day18 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(1, 0),
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1));
  private final List<Point> bytes = new ArrayList<>();
  private final Set<Point> boundary = new HashSet<>();
  private final Set<Point> map = new HashSet<>();
  private final int exit;

  Day18(final List<String> lines, final int exit) {
    for (final String line : lines) {
      final String[] split = line.split(",");
      bytes.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
    }
    this.exit = exit;
    for (int i = -1; i <= exit + 1; i++) {
      boundary.add(new Point(i, -1));
      boundary.add(new Point(i, exit + 1));
      boundary.add(new Point(-1, i));
      boundary.add(new Point(exit + 1, i));
    }
  }

  public int steps(final int numberOfBytes) {
    map.clear();
    for (int i = 0; i < numberOfBytes; i++) {
      map.add(bytes.get(i));
    }

    final var queue = initQueue();
    queue.add(new Node(new Point(0, 0), 0));
    final Set<Point> visited = new HashSet<>();
    while (!queue.isEmpty()) {
      var current = queue.poll();
      if (current.location.x == exit && current.location.y == exit) {
        return current.steps;
      }
      if (visited.contains(current.location)) {
        continue;
      }
      visited.add(current.location);
      queue.addAll(current.getNext());
    }
    return -1;
  }

  public Point blocked() {
    int min = 1;
    int max = bytes.size();
    while (max - min > 1) {
      var test = min + ((max - min) / 2);
      if (steps(test) == -1) {
        max = test;
      } else {
        min = test;
      }
    }
    return bytes.get(max - 1);
  }

  private PriorityQueue<Node> initQueue() {
    return new PriorityQueue<>(10,
        (arg0, arg1) -> Comparator.comparingInt(Node::getMaxSteps).compare(arg0, arg1));
  }

  class Node {

    private final Point location;
    private final int steps;
    private final int maxSteps;

    public Node(final Point location, final int steps) {
      this.location = location;
      this.steps = steps;
      this.maxSteps = steps + Math.abs(exit - location.x) + Math.abs(exit - location.y);
    }

    public int getMaxSteps() {
      return maxSteps;
    }

    public Set<Node> getNext() {
      final Set<Node> nextNodes = new HashSet<>();
      for (var direction : DIRECTIONS) {
        var nextLocation = new Point(location.x + direction.x, location.y + direction.y);
        if (!boundary.contains(nextLocation) && !map.contains(nextLocation)) {
          nextNodes.add(new Node(nextLocation, steps + 1));
        }
      }
      return nextNodes;
    }
  }
}
