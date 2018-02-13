import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

    Map<Character, Integer> charFreq = new HashMap<>();
    for (char c : alphabet) {
      charFreq.put(c, 0);
    }

    String maxPangram = null;
    int lettersCovered = 0;
    int start = 0;
    int alphabetSize = alphabet.size();

    for (int end = 0; end < s.length(); end++) {
      char cEnd = s.charAt(end);
      if (!alphabet.contains(cEnd)) {
        continue;
      }

      int freq = charFreq.get(cEnd);
      if (freq == 0) {
        lettersCovered++;
        charFreq.put(cEnd, 1);
      } else {
        charFreq.put(cEnd, ++freq);
      }

      if (lettersCovered < alphabetSize) {
        continue;
      }
      while (!alphabet.contains(s.charAt(start)) || charFreq.get(s.charAt(start)) > 1) {
        if (alphabet.contains(s.charAt(start))) {
          int startFreq = charFreq.get(s.charAt(start));
          charFreq.put(s.charAt(start), --startFreq);
        }

        start++;
      }

      if (maxPangram == null || maxPangram.length() > end - start) {
        maxPangram = s.substring(start, end+1);
      }
    }

    return maxPangram;
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

    longPangram = "the d quick g brown fox jumps over lazy dog abcdefghijklmnopqrstuvwxyz blaas dfsa";
    System.out.println(longPangram + ": " + shortestPangram(longPangram));
  }
}