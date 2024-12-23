package king.greg.aoc2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class Day23 {

  final Map<String, Set<String>> network = new HashMap<>();
  private final Graph<String, DefaultEdge> graph;

  Day23(final List<String> lines) {
    graph = new DefaultUndirectedGraph<>(DefaultEdge.class);
    for (String line : lines) {
      String[] parts = line.split("-");
      graph.addVertex(parts[0]);
      graph.addVertex(parts[1]);
      graph.addEdge(parts[0], parts[1]);
      var left = network.getOrDefault(parts[0], new HashSet<>());
      left.add(parts[1]);
      network.put(parts[0], left);
      var right = network.getOrDefault(parts[1], new HashSet<>());
      right.add(parts[0]);
      network.put(parts[1], right);
    }
  }

  public int sets(final String start) {
    final Set<String> polygons = new HashSet<>();
    for (final var entry : network.entrySet()) {
      if (entry.getKey().startsWith(start)) {
        for (final var nodeA : entry.getValue()) {
          for (final var nodeB : entry.getValue()) {
            if (network.get(nodeA).contains(nodeB)) {
              var polygon = Arrays.asList(entry.getKey(), nodeA, nodeB);
              Collections.sort(polygon);
              polygons.add(polygon.get(0) + polygon.get(1) + polygon.get(2));
            }
          }
        }
      }
    }
    return polygons.size();
  }

  final String password() {
    final BronKerboschCliqueFinder<String, DefaultEdge> finder = new BronKerboschCliqueFinder<>(
        graph);
    final var cliques = finder.maximumIterator();
    final var maximalSubnet = new ArrayList<>(cliques.next());
    Collections.sort(maximalSubnet);
    final var sb = new StringBuilder();
    for (final String computer : maximalSubnet) {
      sb.append(computer).append(',');
    }
    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }
}
