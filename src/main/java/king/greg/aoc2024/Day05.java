package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day05 {

  private final List<String> updatesList = new ArrayList<>();
  private final HashMap<String, Set<String>> rules = new HashMap<>();

  Day05(final List<String> lines) {
    var updates = false;
    for (String line : lines) {
      if (line.isBlank()) {
        updates = true;
      } else if (updates) {
        updatesList.add(line);
      } else {
        var parts = line.split("\\|");
        var rule = rules.getOrDefault(parts[0], new HashSet<>());
        rule.add(parts[1]);
        rules.put(parts[0], rule);
      }
    }
  }

  public int pagePrinter() {
    var middleSum = 0;
    for (String updates : updatesList) {
      var pages = updates.split(",");
      if (pagesSorted(pages)) {
        middleSum += Integer.parseInt(pages[pages.length / 2]);
      }
    }
    return middleSum;
  }

  public int sortedPrinter() {
    var middleSum = 0;
    for (String updates : updatesList) {
      var pages = updates.split(",");
      if (!pagesSorted(pages)) {
        var sortedPages = sort(pages);
        middleSum += Integer.parseInt(sortedPages.get(sortedPages.size() / 2));
      }
    }
    return middleSum;
  }

  private boolean pagesSorted(final String[] pages) {
    var previousPages = new HashSet<String>();
    for (String page : pages) {
      if (!Collections.disjoint(rules.getOrDefault(page, new HashSet<>()), previousPages)) {
        return false;
      }
      previousPages.add(page);
    }
    return true;
  }

  private List<String> sort(final String[] pages) {
    final List<String> sortedPages = new ArrayList<>(pages.length);
    for (String page : pages) {
      var rule = rules.getOrDefault(page, new HashSet<>());
      var minIndex = sortedPages.size();
      for (String rulePage : rule) {
        var index = sortedPages.indexOf(rulePage);
        if (index >= 0 && index < minIndex) {
          minIndex = index;
        }
      }
      sortedPages.add(minIndex, page);
    }
    return sortedPages;
  }
}
