package king.greg.aoc2024;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class Day24 {

  private final Map<String, String> gates = new HashMap<>();
  private Map<String, Integer> wires = new HashMap<>();

  public Day24(final List<String> lines) {
    boolean part1 = true;
    for (String line : lines) {
      if (part1) {
        if (line.isEmpty()) {
          part1 = false;
        } else {
          var parts = line.split(": ");
          wires.put(parts[0], Integer.parseInt(parts[1]));
        }
      } else {
        var parts = line.split(" -> ");
        gates.put(parts[1], parts[0]);
      }
    }
  }

  public long decimalZ() {
    long decimalZ = 0;
    int z = 0;
    while (true) {
      final long zWire = value(String.format("z%02d", z));
      if (zWire == -1) {
        break;
      }
      decimalZ += zWire << z;
      z++;
    }
    return decimalZ;
  }

  private int value(final String wire) {
    var wireValue = wires.get(wire);
    if (wireValue == null) {
      final var gate = gates.get(wire);
      if (gate == null) {
        return -1;
      }
      final var parts = StringUtils.split(gate, " ");
      wireValue = switch (parts[1]) {
        case "AND" -> value(parts[0]) & value(parts[2]);
        case "OR" -> value(parts[0]) | value(parts[2]);
        case "XOR" -> value(parts[0]) ^ value(parts[2]);
        default -> throw new UnsupportedOperationException("Unsupported gate: " + gate);
      };
      wires.put(wire, wireValue);
    }
    return wireValue;
  }

  public String wireSwaps() {
    // Corrected gates
    var wss = gates.get("wss");
    var wrm = gates.get("wrm");
    gates.put("wrm", wss);
    gates.put("wss", wrm);
    var z29 = gates.get("z29");
    var gbs = gates.get("gbs");
    gates.put("gbs", z29);
    gates.put("z29", gbs);
    var z08 = gates.get("z08");
    var thm = gates.get("thm");
    gates.put("thm", z08);
    gates.put("z08", thm);
    var z22 = gates.get("z22");
    var hwq = gates.get("hwq");
    gates.put("hwq", z22);
    gates.put("z22", hwq);
    // Pick interesting numbers to add.  The sample input only hit two errors.
    setWires("000000000000000000000000000000000000000000000", 'x');
    setWires("111111111111111111111111111111111111111111111", 'y');
    var wiresBackup = new HashMap<>(wires);
    final var mistakenZ = decimalZ();
    wires = wiresBackup;
    int i = 0;
    long x = 0;
    long y = 0;
    while (true) {
      final var xWire = wires.get(String.format("x%02d", i));
      final var yWire = wires.get(String.format("y%02d", i));
      if (xWire == null || yWire == null) {
        break;
      }
      x += ((long) xWire) << i;
      y += ((long) yWire) << i;
      i++;
    }

    final var actualSum = x + y;
    final var wrongZs = actualSum ^ mistakenZ;

    System.out.println(Long.toBinaryString(wrongZs));

    final var zValues = Long.toBinaryString(wrongZs).toCharArray();
    for (int z = 0; z < zValues.length; z++) {
      if (zValues[zValues.length - 1 - z] == '1') {
        // The pattern should take place for z values is:
        // zn = (xn XOR yn) XOR cn-1
        // cn = (xn AND yn) OR ((xn XOR yn) AND cn-1)
        // For any z value spit out, look through the listed gates and find where the pattern is broken, and the value that would fix it.
        // Start with the least-significant first, as those gates could screw up the more significant bits.
        System.out.printf("z%02d%n", z);
      }
    }
    var wiresToSwap = Arrays.asList("wss", "wrm", "z29", "gbs", "z08", "thm", "z22", "hwq");
    Collections.sort(wiresToSwap);
    StringBuilder sb = new StringBuilder();
    for (var wire : wiresToSwap) {
      sb.append(wire).append(',');
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  private void setWires(final String binaryString, final char wireLabel) {
    var binaryArray = binaryString.toCharArray();
    for (int i = 0; i < binaryString.length(); i++) {
      wires.put(String.format("%s%02d", wireLabel, i),
          binaryArray[binaryArray.length - 1 - i] - '0');
    }
  }
}
