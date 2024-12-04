package king.greg.aoc2024;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 {

  private static final String XMAS = "XMAS";
  private static final List<Point> DIRECTIONS = Arrays.asList(new Point(-1, 0), new Point(-1, -1),
      new Point(0, -1), new Point(1, -1), new Point(1, 0),
      new Point(1, 1), new Point(0, 1), new Point(-1, 1));
  private static final Set<String> CROSSES = new HashSet<>(Arrays.asList("MS", "SM"));

  private final List<String> lines;
  private final int xMax;
  private final int yMax;

  Day04(final List<String> lines) {
    this.lines = lines;
    xMax = lines.getFirst().length() - 1;
    yMax = lines.size() - 1;
  }

  public int xmasFinder() {
    int count = 0;

    for (int y = 0; y <= yMax; y++) {
      for (int x = 0; x <= xMax; x++) {
        if ('X' == lines.get(y).charAt(x)) {
          for (final Point direction : DIRECTIONS) {
            count += xmasCheck(x, y, direction);
          }
        }
      }
    }
    return count;
  }

  public int crossMasFinder() {
    int count = 0;

    for (int y = 1; y < yMax; y++) {
      for (int x = 1; x < xMax; x++) {
        if ('A' == lines.get(y).charAt(x)) {
          count += crossMasCheck(x, y);
        }
      }
    }
    return count;
  }

  private int xmasCheck(final int x, final int y, final Point direction) {
    if ((x + (direction.x * 3) > xMax) || (y + (direction.y * 3) > yMax) || (
        x + (direction.x * 3) < 0) || (y + (direction.y * 3) < 0)) {
      return 0;
    }
    for (int i = 1; i <= 3; i++) {
      if (XMAS.charAt(i) != lines.get(y + (direction.y * i)).charAt(x + (direction.x * i))) {
        return 0;
      }
    }
    return 1;
  }

  private int crossMasCheck(final int x, final int y) {
    var diagonal = "" + lines.get(y - 1).charAt(x - 1) + lines.get(y + 1).charAt(x + 1);
    if (!CROSSES.contains(diagonal)) {
      return 0;
    }
    diagonal = "" + lines.get(y - 1).charAt(x + 1) + lines.get(y + 1).charAt(x - 1);
    if (!CROSSES.contains(diagonal)) {
      return 0;
    }
    return 1;
  }
}
