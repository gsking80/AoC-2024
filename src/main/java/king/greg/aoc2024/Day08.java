package king.greg.aoc2024;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 {

  private final List<String> lines;

  Day08(final List<String> lines) {
    this.lines = lines;
  }

  public int antinodes() {
    return antinodes(false);
  }

  public int antinodes(final boolean harmonics) {
    int maxX = lines.getFirst().length() - 1;
    int maxY = lines.size() - 1;

    final HashMap<Character, List<Point>> antennas = antennaMap();

    final Set<Point> antinodes = new HashSet<>();
    for (final var wavelength : antennas.values()) {
      for (int i = 0; i < wavelength.size() - 1; i++) {
        for (int j = i + 1; j < wavelength.size(); j++) {
          var dX = wavelength.get(j).x - wavelength.get(i).x;
          var dY = wavelength.get(j).y - wavelength.get(i).y;
          var inBounds = true;
          var k = harmonics ? 0 : 1;
          while (inBounds) {
            inBounds = false;
            var pointA = new Point(wavelength.get(i).x - (dX * k), wavelength.get(i).y - (dY * k));
            var pointB = new Point(wavelength.get(j).x + (dX * k), wavelength.get(j).y + (dY * k));
            if (pointA.x >= 0 && pointA.x <= maxX && pointA.y >= 0 && pointA.y <= maxY) {
              antinodes.add(pointA);
              inBounds = harmonics;
            }
            if (pointB.x >= 0 && pointB.x <= maxX && pointB.y >= 0 && pointB.y <= maxY) {
              antinodes.add(pointB);
              inBounds = harmonics;
            }
            k++;
          }
        }
      }
    }
    return antinodes.size();
  }

  private HashMap<Character, List<Point>> antennaMap() {
    final HashMap<Character, List<Point>> antennas = new HashMap<>();
    for (int y = 0; y < lines.size(); y++) {
      for (int x = 0; x < lines.get(y).length(); x++) {
        var node = lines.get(y).charAt(x);
        if (node != '.') {
          var map = antennas.computeIfAbsent(node, k -> new ArrayList<>());
          map.add(new Point(x, y));
        }
      }
    }
    return antennas;
  }
}
