package king.greg.aoc2024;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

  private static final Pattern pattern = Pattern.compile("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)");
  private final int[][] bots;

  Day14(final List<String> lines) {
    bots = new int[lines.size()][4];
    for (int i = 0; i < lines.size(); i++) {
      final Matcher matcher = pattern.matcher(lines.get(i));
      while (matcher.find()) {
        bots[i][0] = Integer.parseInt(matcher.group(1));
        bots[i][1] = Integer.parseInt(matcher.group(2));
        bots[i][2] = Integer.parseInt(matcher.group(3));
        bots[i][3] = Integer.parseInt(matcher.group(4));
      }
    }
  }

  public long safetyScore(final int width, final int height, final int seconds) {
    final int[][] quadrantBots = safetyQuadrants(width, height, seconds);
    long safetyScore = 1;
    for (int x = 0; x < 2; x++) {
      for (int y = 0; y < 2; y++) {
        safetyScore *= quadrantBots[x][y];
      }
    }
    return safetyScore;
  }

  private int[][] safetyQuadrants(final int width, final int height, final int seconds) {
    final int[][] quadrantBots = new int[2][2];
    int xDivider = width / 2;
    int yDivider = height / 2;
    for (int[] bot : bots) {
      var location = botLocation(width, height, seconds, bot);
      if (location[0] == xDivider || location[1] == yDivider) {
        continue;
      }
      quadrantBots[location[0] < xDivider ? 0 : 1][location[1] < yDivider ? 0 : 1]++;
    }
    return quadrantBots;
  }

  private int[] botLocation(final int width, final int height, final int seconds, final int[] bot) {
    int finalX = (bot[0] + (bot[2] * seconds)) % width;
    if (finalX < 0) {
      finalX += width;
    }
    int finalY = (bot[1] + (bot[3] * seconds)) % height;
    if (finalY < 0) {
      finalY += height;
    }
    return new int[]{finalX, finalY};
  }

  public int firstTree(final int width, final int height) {
    for (int t = 0; t < 10000; t++) {
      final Set<Point> botLocations = new HashSet<>();
      for (int[] bot : bots) {
        var location = botLocation(width, height, t, bot);
        final Point botLocation = new Point(location[0], location[1]);
        if (botLocations.contains(botLocation)) {
          break;
        }
        botLocations.add(botLocation);
      }
      if (botLocations.size() == bots.length) {
        System.out.println("Maybe " + t);
        print(width, height, botLocations);
      }
    }
    return 6587;
  }

  private void print(final int width, final int height, final Set<Point> bots) {
    for (int y = 0; y < height; y++) {
      StringBuilder sb = new StringBuilder();
      for (int x = 0; x < width; x++) {
        sb.append(bots.contains(new Point(x, y)) ? "X" : ".");
      }
      System.out.println(sb);
    }
    System.out.println();
  }
}
