import java.util.HashSet;
import java.util.Set;

public class Substrings {
  
  public static Set<String> getSubstrings(String s) {
    Set<String> substrings = new HashSet<>();
    boolean[] lettersUsed = new boolean[s.length()];
    populateSubstrings(s, lettersUsed, "", substrings);
    return substrings;
  }

  public static void populateSubstrings(String s, boolean[] lettersUsed, String substring, Set<String> substrings) {
    for (int i = 0; i < s.length(); i++) {
      if (lettersUsed[i]) {
        continue;
      }
      String current = substring + s.charAt(i);
      substrings.add(current);
      lettersUsed[i] = true;
      populateSubstrings(s, lettersUsed, current, substrings);
      lettersUsed[i] = false;
    }
  }

  public static void main(String[] args) {
    System.out.println("All substrings for " + args[0] + ": " + getSubstrings(args[0]));
  }
}
