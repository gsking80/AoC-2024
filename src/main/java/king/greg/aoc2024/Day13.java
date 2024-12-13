package king.greg.aoc2024;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

  private final List<String> lines;
  Pattern aPattern = Pattern.compile("Button A: X\\+(\\d+), Y\\+(\\d+)");
  Pattern bPattern = Pattern.compile("Button B: X\\+(\\d+), Y\\+(\\d+)");
  Pattern prizePattern = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");

  Day13(final List<String> lines) {
    this.lines = lines;
  }

  public long minTokens() {
    return minTokens(0);
  }

  public long minTokens(final long prizeOffset) {
    long tokens = 0;

    int index = 0;
    while (index < lines.size() - 2) {
      final var buttonA = point(lines.get(index++), aPattern);
      final var buttonB = point(lines.get(index++), bPattern);
      final var prize = point(lines.get(index++), prizePattern, prizeOffset);
      index++;
      tokens += minTokens(buttonA, buttonB, prize);
    }

    return tokens;
  }

  private double[] point(final String line, final Pattern pattern) {
    return point(line, pattern, 0);
  }

  private double[] point(final String line, final Pattern pattern, final long offset) {
    final Matcher matcher = pattern.matcher(line);
    if (matcher.find()) {
      return new double[]{Double.parseDouble(matcher.group(1)) + offset,
          Double.parseDouble(matcher.group(2)) + offset};
    }
    return new double[2];
  }

  private long minTokens(final double[] buttonA, final double[] buttonB, final double[] prize) {
    double x = ((buttonB[1] * prize[0]) - (buttonB[0] * prize[1])) / ((buttonB[1] * buttonA[0]) - (
        buttonB[0] * buttonA[1]));
    double y = (prize[0] - buttonA[0] * x) / buttonB[0];

    long xPresses = (long) x;
    long yPresses = (long) y;

    if (xPresses * buttonA[0] + yPresses * buttonB[0] == prize[0]
        && xPresses * buttonA[1] + yPresses * buttonB[1] == prize[1]) {
      return 3 * xPresses + yPresses;
    }
    return 0;
  }
}
