import java.util.HashSet;
import java.util.Set;

public class PangramChecker {
  private static boolean isPangram(String s) {
    if (s == null) {
      return false;
    }

    s = s.toLowerCase();
    boolean[] allLetters = new boolean[26];
    
    for (char c : s.toCharArray()) {
      if (c >= 'a' && c <= 'z') {
        allLetters[c - 'a'] = true;
      }
    }

    for (boolean val : allLetters) {
      if (!val) {
        return false;
      }
    }

    return true;
  }

  private static String shortestPangram(String s) {
    return shortestPangram(s, null);
  }

  private static String shortestPangram(String s, Set<Character> alphabet) {
    if (s == null) {
      return null;
    }
    s = s.toLowerCase();

    if (alphabet == null) {
      alphabet = new HashSet<Character>();
      for (char c = 'a'; c <= 'z'; c++) {
        alphabet.add(c);
      }
    }

    int alphabetSize = alphabet.size();
    Set<Character> seenLetters = new HashSet<Character>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (alphabet.contains(c)) {
        seenLetters.add(c);
        if (seenLetters.size() == alphabetSize) {
          return s.substring(0, ++i);
        }
      }
    }

    return null;
  }
	
  public static void main(String[] args) {
    String pangram = "the quick brown fox jumps over lazy dog";
    String notPangram = "abcasdfasl asdlfjas azxcmv qoruwer this is not a pangram";
    System.out.println(pangram + ": " + isPangram(pangram));
    System.out.println(notPangram + ": " + isPangram(notPangram));

    System.out.println(pangram + ": " + shortestPangram(pangram));
    System.out.println(notPangram + ": " + shortestPangram(notPangram));

    String longPangram = "the d quick g brown fox jumps over lazy dog blaas dfsa";
    System.out.println(longPangram + ": " + shortestPangram(longPangram));
  }
}