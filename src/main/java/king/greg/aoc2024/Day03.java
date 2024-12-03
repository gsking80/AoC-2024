package king.greg.aoc2024;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 {

  private final List<String> lines;

  Day03(final List<String> lines) {
    this.lines = lines;
  }

  public long mulSum() {
    Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
    long sum = 0;
    for (final String line : lines) {
      Matcher matcher = pattern.matcher(line);
      while (matcher.find()) {
        sum += (Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2)));
      }
    }
    return sum;
  }

  public long conditionalMulSum() {
    Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(do\\(\\))|(don't\\(\\))");
    var enabled = true;
    long sum = 0;
    for (final String line : lines) {
      Matcher matcher = pattern.matcher(line);
      while (matcher.find()) {
        switch (matcher.group()) {
          case "do()":
            enabled = true;
            break;
          case "don't()":
            enabled = false;
            break;
          default:
            if (enabled) {
              sum += (Long.parseLong(matcher.group(1)) * Long.parseLong(matcher.group(2)));
            }
        }
      }
    }
    return sum;
  }
}
