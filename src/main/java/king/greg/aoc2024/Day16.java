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
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.tuple.Pair;

public class Day16 {

  private static final List<Point> DIRECTIONS = Arrays.asList(
      new Point(1, 0),
      new Point(0, -1),
      new Point(-1, 0),
      new Point(0, 1));
  final Map<Pair<Point, Integer>, Set<Pair<Point, Integer>>> paths = new HashMap<>();
  final Map<String, Set<Point>> seatMemo = new ConcurrentSkipListMap<>();
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

    Node current;
    priorityQueue.add(new Node(new Point(start), 0, 0));
    while (!priorityQueue.isEmpty()) {
      current = priorityQueue.remove();
      if (current.score > bestScore) {
        return bestSeats(end).size();
      }
      final var state = Pair.of(current.location, current.direction);
      final Integer previousScore = visited.get(state);
      if (previousScore != null && previousScore < current.score) {
        continue;
      }
      visited.put(state, current.score);
      var pathsIn = paths.get(state);
      if (pathsIn == null) {
        pathsIn = new HashSet<>();
      }
      pathsIn.add(current.previousState);
      paths.put(state, pathsIn);
      if (current.location.equals(end)) {
        bestScore = current.score;
      }
      if (previousScore == null) {
        priorityQueue.addAll(current.getNext());
      }
    }
    return -1;
  }

  private Set<Point> bestSeats(final Point location) {
    final Set<Point> seats = new HashSet<>();
    for (var direction = 0; direction < DIRECTIONS.size(); direction++) {
      seats.addAll(bestSeats(Pair.of(location, direction)));
    }
    return seats;
  }

  private Set<Point> bestSeats(final Pair<Point, Integer> state) {
    return seatMemo.computeIfAbsent(state.toString(), set -> {
      final Set<Point> previousSeats = new HashSet<>();
      var pathsIn = paths.get(state);
      if (pathsIn != null) {
        for (var pathIn : pathsIn) {
          if (pathIn != null) {
            previousSeats.addAll(bestSeats(pathIn));
          }
        }
      }
      previousSeats.add(state.getLeft());
      return previousSeats;
    });
  }

  private PriorityQueue<Node> initQueue() {
    return new PriorityQueue<>(10,
        (arg0, arg1) -> Comparator.comparingInt(Node::getMinimumFinalScore).compare(arg0, arg1));
  }

  class Node {

    final Point location;
    final int direction;
    final int score;
    final Pair<Point, Integer> previousState;
    final int minimumFinalScore;

    Node(Point location, int direction, int score) {
      this(location, direction, score, null);
    }

    Node(Point location, int direction, int score, final Pair<Point, Integer> previousState) {
      this.location = location;
      this.direction = direction;
      this.score = score;
      this.previousState = previousState;
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
      nextNodes.add(
          new Node(location, (direction + 1) % 4, score + 1000, Pair.of(location, direction)));
      nextNodes.add(
          new Node(location, (direction + 3) % 4, score + 1000, Pair.of(location, direction)));
      final int nextX = location.x + DIRECTIONS.get(direction).x;
      final int nextY = location.y + DIRECTIONS.get(direction).y;
      final Point nextLocation = new Point(nextX, nextY);
      if (map[nextY][nextX] == '.' && !location.equals(nextLocation)) {
        nextNodes.add(
            new Node(new Point(nextX, nextY), direction, score + 1, Pair.of(location, direction)));
      }
      return nextNodes;
    }

    public String toString() {
      return location.toString() + " - " + direction + " - " + score + " - heuristic: "
          + getMinimumFinalScore();
    }
  }
}
