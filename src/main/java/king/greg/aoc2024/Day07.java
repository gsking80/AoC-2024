package king.greg.aoc2024;

import java.util.Arrays;
import java.util.List;

public class Day07 {

  private final List<String> lines;

  Day07(final List<String> lines) {
    this.lines = lines;
  }

  public long calibrate(final boolean concatenate) {
    long sum = 0;
    for (String line : lines) {
      var pieces = Arrays.stream(line.split(" |: ")).mapToLong(Long::parseLong).toArray();
      if (canCalibrateBackwards(pieces, pieces[0], pieces.length - 1, concatenate)) {
        sum += pieces[0];
      }
    }
    return sum;
  }

  private boolean canCalibrateBackwards(final long[] pieces, final long value, final int index,
      final boolean concatenate) {
    if (index < 1) {
      return value == 0;
    }
    if (value < 1) {
      return false;
    }
    return canAdd(pieces, value, index, concatenate)
        || canMultiply(pieces, value, index, concatenate)
        || (concatenate && canConcatenate(pieces, value, index));
  }

  private boolean canAdd(final long[] pieces, final long value, final int index,
      final boolean concatenate) {
    return canCalibrateBackwards(pieces, value - pieces[index], index - 1, concatenate);
  }

  private boolean canMultiply(final long[] pieces, final long value, final int index,
      final boolean concatenate) {
    if (value % pieces[index] == 0) {
      return canCalibrateBackwards(pieces, value / pieces[index], index - 1, concatenate);
    }
    return false;
  }

  private boolean canConcatenate(final long[] pieces, final long value, final int index) {
    long length = (long) Math.pow(10, String.valueOf(pieces[index]).length());
    if ((value % length) == pieces[index]) {
      return canCalibrateBackwards(pieces, value / length, index - 1, true);
    }
    return false;
  }
}
