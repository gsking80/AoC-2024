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
      if (canCalibrate(pieces, concatenate)) {
        sum += pieces[0];
      }
    }
    return sum;
  }

  private boolean canCalibrate(final long[] pieces, final boolean concatenate) {
    return canCalibrate(pieces, pieces[1] + pieces[2], 3, concatenate)
        || canCalibrate(pieces, pieces[1] * pieces[2], 3, concatenate)
        || (concatenate && canCalibrate(pieces,
        Long.parseLong(String.valueOf(pieces[1]) + pieces[2]), 3, true));
  }

  private boolean canCalibrate(final long[] pieces, final long value, final int index,
      final boolean concatenate) {
    if (index >= pieces.length) {
      return value == pieces[0];
    }
    if (value > pieces[0]) {
      return false;
    }
    return canCalibrate(pieces, value + pieces[index], index + 1, concatenate)
        || canCalibrate(pieces, value * pieces[index], index + 1, concatenate)
        || (concatenate && canCalibrate(pieces,
        Long.parseLong(String.valueOf(value) + pieces[index]), index + 1, true));
  }
}
