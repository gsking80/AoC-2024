package king.greg.aoc2024;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class Day16 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(1, 0),
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1));
  private final char[][] map;
  private Point start;
  private Point end;

  Day16(final List<String> lines) {
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
  }

  public int lowestScore() {
    Queue<Node> priorityQueue = initQueue();
    final Set<Pair<Point, Integer>> visited = new HashSet<>();
    Node current;
    priorityQueue.add(new Node(new Point(start), 0, 0));
    while (!priorityQueue.isEmpty()) {
      current = priorityQueue.remove();
      final var state = Pair.of(current.location, current.direction);
      if (visited.contains(state)) {
        continue;
      }
      visited.add(state);
      if (current.location.equals(end)) {
        return current.score;
      }
      priorityQueue.addAll(current.getNext());
    }
    return -1;
  }

  public int bestTiles() {
    Queue<Node> priorityQueue = initQueue();
    int bestScore = Integer.MAX_VALUE;
    final Map<Pair<Point, Integer>, Integer> visited = new HashMap<>();
    final Map<Point, Set<Point>> paths = new HashMap<>();
    Node current;
    priorityQueue.add(new Node(new Point(start), 0, 0));
    while (!priorityQueue.isEmpty()) {
      current = priorityQueue.remove();
      if (current.score > bestScore) {
        return paths.get(end).size();
      }
      final var state = Pair.of(current.location, current.direction);
      final Integer previousScore = visited.get(state);
      if (previousScore != null && previousScore < current.score) {
        continue;
      }
      visited.put(state, current.score);
      var bestPaths = paths.get(current.location);
      if (bestPaths == null) {
        bestPaths = new HashSet<>();
      }
      bestPaths.addAll(current.visitedNodes);
      paths.put(current.location, bestPaths);
      if (current.location.equals(end)) {
        bestScore = current.score;
      }
      priorityQueue.addAll(current.getNext());
    }
    return -1;
  }

  private PriorityQueue<Node> initQueue() {
    return new PriorityQueue<>(10,
        (arg0, arg1) -> Comparator.comparingInt(Node::getMinimumFinalScore).compare(arg0, arg1));
  }

  class Node {

    final Point location;
    final int direction;
    final int score;
    final Set<Point> visitedNodes;
    final int minimumFinalScore;

    Node(Point location, int direction, int score) {
      this(location, direction, score, new HashSet<>());
    }

    Node(Point location, int direction, int score, final Set<Point> visitedNodes) {
      this.location = location;
      this.direction = direction;
      this.score = score;
      this.visitedNodes = new HashSet<>();
      this.visitedNodes.addAll(visitedNodes);
      this.visitedNodes.add(location);
      this.minimumFinalScore = calcMinimumFinalScore();
    }

    public int getMinimumFinalScore() {
      return minimumFinalScore;
    }

    private int calcMinimumFinalScore() {
      int deltaX = end.x - location.x;
      int deltaY = end.y - location.y;
      int minTurns;
      switch (direction) {
        case 0:
          if (deltaX < 0) {
            minTurns = 2;
          } else {
            minTurns = deltaY == 0 ? 0 : 1;
          }
          break;
        case 1:
          if (deltaY > 0) {
            minTurns = 2;
          } else {
            minTurns = deltaX == 0 ? 0 : 1;
          }
          break;
        case 2:
          if (deltaX > 0) {
            minTurns = 2;
          } else {
            minTurns = deltaY == 0 ? 0 : 1;
          }
          break;
        case 3:
          if (deltaY < 0) {
            minTurns = 2;
          } else {
            minTurns = deltaX == 0 ? 0 : 1;
          }
          break;
        default:
          throw new IllegalStateException("Unexpected direction: " + direction);
      }
      return score + Math.abs(deltaX) + Math.abs(deltaY) + 1000 * minTurns;
    }

    public Set<Node> getNext() {
      final Set<Node> nextNodes = new HashSet<>();
      nextNodes.add(new Node(location, (direction + 1) % 4, score + 1000, visitedNodes));
      nextNodes.add(new Node(location, (direction + 3) % 4, score + 1000, visitedNodes));
      final int nextX = location.x + DIRECTIONS.get(direction).x;
      final int nextY = location.y + DIRECTIONS.get(direction).y;
      if (map[nextY][nextX] == '.') {
        nextNodes.add(new Node(new Point(nextX, nextY), direction, score + 1, visitedNodes));
      }
      return nextNodes;
    }

    public String toString() {
      return location.toString() + " - " + direction + " - " + score + " - heuristic: "
          + getMinimumFinalScore();
    }
  }
}
