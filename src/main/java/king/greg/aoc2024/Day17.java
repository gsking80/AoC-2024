package king.greg.aoc2024;

import java.util.Arrays;
import java.util.List;

public class Day17 {

  final int[] program;
  long registerA;
  long registerB;
  long registerC;

  Day17(final List<String> lines) {
    this.registerA = Long.parseLong(lines.get(0).split(": ")[1]);
    this.registerB = Long.parseLong(lines.get(1).split(": ")[1]);
    this.registerC = Long.parseLong(lines.get(2).split(": ")[1]);
    this.program = Arrays.stream(lines.get(4).split("Program: |,")).skip(1)
        .mapToInt(Integer::parseInt).toArray();
  }

  public String output() {
    int pointer = 0;
    final StringBuilder output = new StringBuilder();
    while (pointer < program.length) {
      int opcode = program[pointer];
      int operand = program[pointer + 1];
      switch (opcode) {
        case 0: //adv
          registerA = registerA >> combo(operand);
          pointer += 2;
          break;
        case 1: //bxl
          registerB = registerB ^ operand;
          pointer += 2;
          break;
        case 2: //bst
          registerB = combo(operand) % 8;
          pointer += 2;
          break;
        case 3: //jnz
          if (registerA == 0) {
            pointer += 2;
          } else {
            pointer = operand;
          }
          break;
        case 4: // bxc
          registerB = registerB ^ registerC;
          pointer += 2;
          break;
        case 5: // out
          if (!output.isEmpty()) {
            output.append(',');
          }
          output.append(combo(operand) % 8);
          pointer += 2;
          break;
        case 6: // bdv
          registerB = registerA >> combo(operand);
          pointer += 2;
          break;
        case 7: //cdv
          registerC = registerA >> combo(operand);
          pointer += 2;
          break;
        default:
          throw new IllegalArgumentException(
              "Invalid opcode: " + opcode + " - pointer: " + pointer);
      }
    }
    return output.toString();
  }

  private long combo(final int operand) {
    return switch (operand) {
      case 0, 1, 2, 3 -> operand;
      case 4 -> registerA;
      case 5 -> registerB;
      case 6 -> registerC;
      default -> throw new IllegalArgumentException("Illegal combo operand: " + operand);
    };
  }

  public long initializedValue() {
    return calculateOctets(0, program.length - 1);
  }

  public long calculateOctets(final long a, final int octet) {
    if (octet < 0) {
      return a;
    }
    // B = Last 3 bits of A
    // B = B ^ 001
    // C = A >> B (0-7 times)
    // B = B ^ C (only last 3 bits matter)
    // B = B ^ 100
    // Last 3 bits of B is program[i]
    // A >> 3
    for (var x = 0; x < 8; x++) {
      var testA = (a << 3) + x;
      var b = testA % 8;
      b = b ^ 1;
      var c = testA >> b;
      b = b ^ c;
      b = b ^ 4;
      if (b % 8 == program[octet]) {
        testA = calculateOctets(testA, octet - 1);
        if (testA != -1) {
          return testA;
        }
      }
    }
    return -1;
  }
}
