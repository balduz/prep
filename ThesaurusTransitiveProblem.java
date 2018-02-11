import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ThesaurusTransitiveProblem {
  private static class Thesaurus {
    private Map<String, Node> disjointSets;

    private static class Node {
      private int rank;
      private Node parent;
      private String value;

      Node(String val) {
        this.value = val;
      }

      Node getParent() { return this.parent; }
      int getRank() { return this.rank; }
      String getValue() { return this.value; }

      void setParent(Node p) { this.parent = p; }
      void incrementRank() { this.rank++; }

      public boolean equals(Object o) {
        if (o instanceof Node) {
          Node n2 = (Node)o;
          return value.equals(n2.getValue());
        }
        return false;
      }
    }

    private void makeSet(String s) {
      Node n = new Node(s);
      n.setParent(n);
      disjointSets.put(s, n);
    }

    private Node findSet(Node n) {
      if (!n.equals(n.getParent())) {
        n.setParent(n.getParent());
      }
      return n.getParent();
    }

    private void union(Node n1, Node n2) {
      Node parent1 = findSet(n1);
      Node parent2 = findSet(n2);
      if (parent1.equals(parent2)) {
        return;
      }
      if (parent1.getRank() > parent2.getRank()) {
        parent2.setParent(parent1);
      } else {
        parent1.setParent(parent2);
        if (parent1.getRank() == parent2.getRank()) {
          parent2.incrementRank();
        }
      }
    }

    void populateThesaurus(List<String[]> entries) {
      if (entries == null) {
        throw new IllegalArgumentException("null entries");
      }
      disjointSets = new HashMap<String, Node>();
      for (String[] entry : entries) {
        makeSet(entry[0]);
        makeSet(entry[1]);
      }

      for (String[] entry : entries) {
        union(disjointSets.get(entry[0]), disjointSets.get(entry[1]));
      }
    }

    private boolean areSynonyms(String s1, String s2) {
      if (s1.equals(s2)) {
        return true;
      }
      if (!disjointSets.containsKey(s1) || !disjointSets.containsKey(s2)) {
        return false;
      }
      Node n1 = disjointSets.get(s1);
      Node n2 = disjointSets.get(s2);
      return findSet(n1).equals(findSet(n2));
    }

    boolean areStringsSame(String s1, String s2) {
      if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
        return false;
      }

      String[] words1 = s1.split(" ");
      String[] words2 = s2.split(" ");
      if (words1.length != words2.length) {
        return false;
      }

      for (int i = 0; i < words1.length; i++) {
        if (!areSynonyms(words1[i], words2[i])) {
          return false;
        }
      }

      return true;
    }
  }

  public static void main(String[] args) {
    List<String[]> entries = new ArrayList<>();
    entries.add(new String[]{"rating", "score"});
    entries.add(new String[]{"pet", "dog"});
    entries.add(new String[]{"dog", "cat"});
    entries.add(new String[]{"cute", "nice"});

    Thesaurus t = new Thesaurus();
    t.populateThesaurus(entries);

    String s1 = "this restaurant has a good rating";
    String s2 = "this restaurant has a good score";
    System.out.println(t.areStringsSame(s1, s2));

    s1 = "your dog is cute";
    s2 = "your pet is nice";
    System.out.println(t.areStringsSame(s1, s2));
  }
}
