import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphDiameter {
  static class Node {
    private int value;
    private List<Node> neighbors;

    Node(int value) {
      this.value = value;
      this.neighbors = new ArrayList<Node>();
    }

    int getValue() {
      return this.value;
    }

    List<Node> getNeighbors() {
      return this.neighbors;
    }

    void addNeighbor(Node node) {
      this.neighbors.add(node);
    }
  }

  static class Pair {
    private int distance;
    private Node node;

    Pair(int distance, Node node) {
      this.distance = distance;
      this.node = node;
    }

    int getDistance() {
      return this.distance;
    }

    Node getNode() {
      return this.node;
    }
  }

  public static void main(String args[]) {
    Node a = new Node(1);
    Node b = new Node(2);
    Node c = new Node(3);
    Node d = new Node(4);
    Node e = new Node(5);
    Node f = new Node(6);
    Node g = new Node(7);
    Node h = new Node(8);
    
    a.addNeighbor(b);

    b.addNeighbor(a);
    b.addNeighbor(c);
    b.addNeighbor(e);

    c.addNeighbor(b);
    c.addNeighbor(d);

    d.addNeighbor(c);

    e.addNeighbor(b);
    e.addNeighbor(f);

    f.addNeighbor(e);
    f.addNeighbor(g);
    f.addNeighbor(h);

    g.addNeighbor(f);

    h.addNeighbor(f);

    System.out.println("Diameter: " + getDiameter(a));
    System.out.println("Diameter: " + getDiameter(c));
    System.out.println("Diameter: " + getDiameter(e));
    System.out.println("Diameter: " + getDiameter(g));
  }

  public static int getDiameter(Node n) {
    Pair extreme = getMaximumDistanceNode(n);
    System.out.println("Extreme: " + extreme.getNode().getValue() + ", distance: " + extreme.getDistance());
    Pair opposite = getMaximumDistanceNode(extreme.getNode());
    return opposite.getDistance();
  }

  public static Pair getMaximumDistanceNode(Node n) {
    Map<Node, Integer> distances = new HashMap<>();
    distances.put(n, 0);
    populateMaximumDistances(distances, n);

    Node extreme = null;
    int maxDistance = -1;
    for (Map.Entry<Node, Integer> entry : distances.entrySet()) {
      if (entry.getValue() > maxDistance) {
        maxDistance = entry.getValue();
        extreme = entry.getKey();
      }
    }

    return new Pair(maxDistance, extreme);
  }

  public static void populateMaximumDistances(Map<Node, Integer> distances, Node n) {
    for (Node neighbor : n.getNeighbors()) {
      if (distances.get(neighbor) != null) {
        continue;
      }

      distances.put(neighbor, distances.get(n) + 1);
      populateMaximumDistances(distances, neighbor);
    }
  }
}
