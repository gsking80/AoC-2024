package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import org.apache.commons.lang3.tuple.Pair;

public class Day21 {

  private static final Map<Character, Point> numericPad = Map.ofEntries(
      Map.entry('7', new Point(0, 0)),
      Map.entry('8', new Point(1, 0)),
      Map.entry('9', new Point(2, 0)),
      Map.entry('4', new Point(0, 1)),
      Map.entry('5', new Point(1, 1)),
      Map.entry('6', new Point(2, 1)),
      Map.entry('1', new Point(0, 2)),
      Map.entry('2', new Point(1, 2)),
      Map.entry('3', new Point(2, 2)),
      Map.entry('0', new Point(1, 3)),
      Map.entry('A', new Point(2, 3))
  );
  private static final Point numericTaboo = new Point(0, 3);
  private static final Map<Character, Point> directionalPad = Map.ofEntries(
      Map.entry('^', new Point(1, 0)),
      Map.entry('A', new Point(2, 0)),
      Map.entry('<', new Point(0, 1)),
      Map.entry('v', new Point(1, 1)),
      Map.entry('>', new Point(2, 1))
  );
  private static final Point directionTaboo = new Point(0, 0);
  private static final Map<Point, Character> DIRECTIONS = Map.ofEntries(
      Map.entry(new Point(1, 0), '>'),
      Map.entry(new Point(0, -1), '^'),
      Map.entry(new Point(-1, 0), '<'),
      Map.entry(new Point(0, 1), 'v'));
  private final List<String> lines;
  private final Map<Pair<Point, Point>, Set<String>> numericMemo = new HashMap<>();
  private final Map<Pair<Point, Point>, Set<String>> directionMemo = new HashMap<>();
  private final Map<Pair<String, Integer>, Long> sequenceLengthMemo = new ConcurrentSkipListMap<>();

  Day21(final List<String> lines) {
    this.lines = lines;
  }

  public long complexitySum(final int directionBots) {
    long complexitySum = 0;
    for (String line : lines) {
      complexitySum += complexity(line, directionBots);
    }
    return complexitySum;
  }

  private long complexity(final String code, final int directionBots) {
    return manualPresses(code, directionBots) * Long.parseLong(
        code.substring(0, code.length() - 1));
  }

  private long manualPresses(final String code, final int directionBots) {
    var sequenceSets = subSequences(code, false);
    long presses = 0;
    for (var sequenceSet : sequenceSets) {
      presses += sequenceSet.stream().mapToLong(s -> minLength(s, directionBots)).min()
          .orElseThrow();
    }
    return presses;
  }

  private long minLength(final String sequence, final int directionBots) {
    return sequenceLengthMemo.computeIfAbsent(Pair.of(sequence, directionBots), k ->
    {
      if (directionBots == 0) {
        return (long) sequence.length();
      }
      long sequenceLength = 0;
      for (var subSequences : subSequences(sequence, true)) {
        sequenceLength += subSequences.stream().mapToLong(s -> minLength(s, directionBots - 1))
            .min()
            .orElseThrow();
      }
      return sequenceLength;
    });
  }

  private List<Set<String>> subSequences(final String directionPadSequence, final boolean dPad) {
    final var pad = dPad ? directionalPad : numericPad;
    final var buttons = directionPadSequence.toCharArray();
    final List<Set<String>> paths = new ArrayList<>();
    var currentPosition = pad.get('A');
    for (char button : buttons) {
      var nextPosition = pad.get(button);
      paths.add(keyPadPaths(currentPosition, nextPosition, dPad));
      currentPosition = nextPosition;
    }
    return paths;
  }

  private Set<String> keyPadPaths(final Point current, final Point next, final boolean dPad) {
    final var memo = dPad ? directionMemo : numericMemo;
    return memo.computeIfAbsent(Pair.of(current, next), k ->
    {
      if (current.equals(next)) {
        return Set.of("A");
      }
      final var tabooSpace = dPad ? directionTaboo : numericTaboo;
      var possiblePresses = new HashSet<String>();
      final var queue = new ArrayDeque<Pair<Point, String>>();
      queue.add(Pair.of(current, ""));
      while (!queue.isEmpty()) {
        var step = queue.removeFirst();
        var xDelta = next.x - step.getLeft().x;
        var yDelta = next.y - step.getLeft().y;
        for (final var direction : DIRECTIONS.entrySet()) {
          if (xDelta * direction.getKey().x >= 0 && yDelta * direction.getKey().y >= 0
              && ((xDelta != 0 && direction.getKey().x != 0)
              || (yDelta != 0 && direction.getKey().y != 0))) {
            var nextStep = new Point(step.getLeft().x + direction.getKey().x,
                step.getLeft().y + direction.getKey().y);
            if (nextStep.equals(next)) {
              possiblePresses.add(step.getRight() + direction.getValue() + 'A');
            } else if (nextStep.equals(tabooSpace)) {
              continue;
            }
            queue.add(Pair.of(nextStep, step.getRight() + direction.getValue()));
          }
        }
      }

      return possiblePresses;
    });
  }
}
