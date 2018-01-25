import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class TripletsSum {
  static class Triplet {
    private int val1;
    private int val2;
    private int val3;

    Triplet(int val1, int val2, int val3) {
      this.val1 = val1;
      this.val2 = val2;
      this.val3 = val3;
    }

    int getVal1() {
      return val1;
    }
    int getVal2() {
      return val2;
    }
    int getVal3() {
      return val3;
    }

    public String toString() {
      return "(" + val1 + ", " + val2 + ", " + val3 + ")";
    }
  }

  public static List<Triplet> getTriplets(int[] arr, int sum) {
    List<Triplet> triplets = new ArrayList<>();
    if (arr == null || arr.length == 0) {
      return triplets;
    }

    Arrays.sort(arr);
    int left = 0, mid = 0, right = 0;
    int currentSum = 0;

    right = arr.length - 1;

    while (right > left) {
      mid = left + 1;
      currentSum = arr[left] + arr[mid] + arr[right];
      while (currentSum > sum && right > mid) {
        currentSum = arr[left] + arr[mid] + arr[right];
        right--;
      }
      while (mid < right) {
        currentSum = arr[left] + arr[mid] + arr[right];
        if (currentSum == sum) {
          triplets.add(new Triplet(arr[left], arr[mid], arr[right]));
        }
        mid++;
      }
      left++;
    }

    return triplets;
  }

  public static void main (String[] args) {
    System.out.println(getTriplets(new int[]{0, 1, 2, 3, 4, -1, -1, -1}, 2));
  }
}