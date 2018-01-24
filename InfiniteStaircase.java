

public class InfiniteStaircase {
  public static int getStepCombinations(int n) {
    if (n < 0) {
      return -1;
    }

    if (n < 3) {
      return n;
    }

    int last = 2;
    int secondLast = 1;

    for (int i = 3; i <= n; i++) {
      int aux = last;
      last += secondLast;
      secondLast = aux;
    }

    return last;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println("Combinations until " + i + ": " + getStepCombinations(i));
    }
  }
}