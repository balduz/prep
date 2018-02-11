import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ThesaurusProblem {
  private static class Thesaurus {
    private Map<String, Set<String>> thesaurus;

    Thesaurus() {
      thesaurus = new HashMap<>();
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
        if (thesaurus.containsKey(words1[i])) {
          if (!thesaurus.get(words1[i]).contains(words2[i])) {
            return false;
          }
        } else if (!words1[i].equals(words2[i])) {
          return false;
        }
      }

      return true;
    }

    void populateThesaurus(List<String[]> entries) {
      if (entries == null) {
        throw new IllegalArgumentException("null entries");
      }
      for (String[] entry : entries) {
        addSynonymPair(entry[0], entry[1]);
        addSynonymPair(entry[1], entry[0]);
      }
    }

    private void addSynonymPair(String s1, String s2) {
      Set<String> synonyms = thesaurus.get(s1);
      if (synonyms == null) {
        synonyms = new HashSet<String>();
        thesaurus.put(s1, synonyms);
      }
      synonyms.add(s1);
      synonyms.add(s2);
    }
  }

  public static void main(String[] args) {
    List<String[]> entries = new ArrayList<>();
    entries.add(new String[]{"rating", "score"});
    entries.add(new String[]{"pet", "dog"});
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