import java.util.ArrayList;
import java.util.List;

public class PermutationsOfKUntilN {
  public static List<List<Integer>> generateCombinations(int k, int n) {
    if (k > n) {
      throw new IllegalArgumentException("k cannot be higher than n");
    }
    return generateCombinations(0, n, k, new ArrayList<Integer>());
  }

  private static List<List<Integer>> generateCombinations(int start, int end, int size, List<Integer> current) {
    List<List<Integer>> combinations = new ArrayList<>();

    if (current.size() >= size) {
      combinations.add(current);
      return combinations;
    }

    for (int i = start; i < end; i++) {
      List<Integer> currentCopy = new ArrayList<>(current);
      currentCopy.add(i);
      combinations.addAll(generateCombinations(i+1, end, size, currentCopy));
    }

    return combinations;
  }

  public static void main(String[] args) {
    System.out.println(generateCombinations(2, 3));
    System.out.println(generateCombinations(1, 6));
    System.out.println(generateCombinations(3, 4));
    System.out.println(generateCombinations(5, 5));
  }
}