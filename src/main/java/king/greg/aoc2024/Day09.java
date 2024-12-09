package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;

public class Day09 {

  private final List<String> lines;

  Day09(final List<String> lines) {
    this.lines = lines;
  }

  public long compactedChecksum() {
    var diskMap = lines.getFirst().toCharArray();
    boolean space = false;
    long id = 0;
    long address = 0;
    long checksum = 0;
    var endId = diskMap.length / 2 + 1;
    var endIndex = diskMap.length + 1;
    var remainder = 0;
    for (var i = 0; i < diskMap.length; i++) {
      if (space) {
        for (var j = 0; j < diskMap[i] - '0'; j++) {
          while (remainder == 0) {
            endIndex -= 2;
            endId -= 1;
            remainder = diskMap[endIndex] - '0';
            if (endIndex <= i) {
              return checksum;
            }
          }
          checksum += (address * endId);
          address++;
          remainder--;
        }
        space = false;
      } else {
        var size = diskMap[i] - '0';
        if (id == endId) {
          for (var j = 0; j < remainder; j++) {
            checksum += (address * id);
            address++;
          }
          return checksum;
        }
        for (var j = 0; j < size; j++) {
          checksum += (address * id);
          address++;
        }
        id++;
        space = true;
      }
    }
    return checksum;
  }

  public long defragmentedChecksum() {
    long checksum = 0;
    var diskMap = lines.getFirst().toCharArray();
    List<List<Long>> emptySpaceAddresses = new ArrayList<>();
    for (var i = 0; i <= 9; i++) {
      emptySpaceAddresses.add(new ArrayList<>());
    }
    Map<Long, Pair<Integer, Long>> files = new HashMap<>();
    long id = 0;
    long address = 0;
    boolean space = false;
    for (char c : diskMap) {
      var size = c - '0';
      if (space) {
        emptySpaceAddresses.get(size).add(address);
        space = false;
      } else {
        files.put(id, Pair.of(size, address));
        id++;
        space = true;
      }
      address += size;
    }
    var endId = diskMap.length / 2;
    for (id = endId; id >= 0; id--) {
      var fileInfo = files.get(id);
      var size = fileInfo.getLeft();
      address = fileInfo.getRight();
      var newAddressFound = false;
      var newAddressSize = 0;
      var newAddress = Long.MAX_VALUE;
      for (var spaceSize = size; spaceSize <= 9; spaceSize++) {
        if (!emptySpaceAddresses.get(spaceSize).isEmpty()) {
          if (emptySpaceAddresses.get(spaceSize).getFirst() <= address
              && emptySpaceAddresses.get(spaceSize).getFirst() < newAddress) {
            newAddressFound = true;
            newAddressSize = spaceSize;
            newAddress = emptySpaceAddresses.get(spaceSize).getFirst();
          }
        } else {
          emptySpaceAddresses.get(spaceSize).clear();
        }
      }
      if (newAddressFound) {
        address = newAddress;
        emptySpaceAddresses.get(newAddressSize).removeFirst();
        if (newAddressSize != size) {
          newAddress = address + size;
          newAddressSize -= size;
          emptySpaceAddresses.get(newAddressSize).add(newAddress);
          emptySpaceAddresses.get(newAddressSize).sort(Long::compareTo);
        }
      }
      for (int i = 0; i < size; i++) {
        checksum += ((address + i) * id);
      }
    }
    return checksum;
  }
}
