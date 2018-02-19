public class Metatheses {
  private static boolean areMetatheses(String s1, String s2) {
    if (s1 == null || s2 == null || s1.length() != s2.length()) {
      return false;
    }

    int diff = -1;
    int swaps = 0;
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) != s2.charAt(i)) {
        if (diff >= 0) {
          if (s1.charAt(diff) != s2.charAt(i) || s2.charAt(diff) != s1.charAt(i)) {
            return false;
          }
        }
        diff = i;
        swaps++;
      }
    }

    return swaps == 0 || swaps == 2;
  }

  public static void main(String[] args) {
    String s1 = "converve";
    String s2 = "conserve";
    System.out.println(areMetatheses(s1, s2));

    s1 = "canverse";
    s2 = "censerve";
    System.out.println(areMetatheses(s1, s2));
  }
}