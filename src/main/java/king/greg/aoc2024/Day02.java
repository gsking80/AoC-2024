package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Day02 {

  private final List<String> lines;

  Day02(final List<String> lines) {
    this.lines = lines;
  }

  public long safetyCount() {
    long count = 0;
      for (String line : lines) {
        var reports = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).boxed().toList();
        if (safety(reports))  {
          count++;
        }
      }
    return count;
  }

  public long dampenerSafetyCount() {
    long count = 0;
    for (String line : lines) {
      var reports = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).boxed().toList();

      if (safety(reports)) {
        count++;
      } else {
        var safe = false;
        for (var i = 0; i < reports.size() && !safe; i++) {
          var testReport = new ArrayList<>(reports);
          testReport.remove(i);
          safe = safety(testReport);
        }
        if (safe) {
          count++;
        }
      }
    }
    return count;
  }

  private boolean safety(final List<Long> reports) {
    var reportValue = reports.get(0);
    var nextValue = reports.get(1);
    if (Objects.equals(reportValue, nextValue)) {
      return false;
    }
    var ascending = reportValue < nextValue;
    for (var i = 1; i < reports.size(); i++) {
      nextValue = reports.get(i);
      var diff = ascending ? nextValue - reportValue : reportValue - nextValue;
      if ((diff < 1) || (diff > 3)) {
        return false;
      }
      reportValue = nextValue;
    }
    return true;
  }
}
