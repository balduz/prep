import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestIncreasingSubsequence {
  private static int getLis(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int[] lis = new int[arr.length];
    lis[0] = 1;
    for (int i = 1; i < arr.length; i++) {
      lis[i] = 1;
      for (int j = 0; j < i; j++) {
        if (arr[j] < arr[i] && lis[j] >= lis[i]) {
          lis[i] = lis[j] + 1;
        }
      }
    }

    int max = 0;
    for (int i : lis) {
      if (i > max) {
        max = i;
      }
    }
    return max;
  }

  private static int getLisOptimized(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    List<Integer> lis = new ArrayList<>();
    for (int i : arr) {
      int pos = Collections.binarySearch(lis, i);
      if (pos < 0) {
        pos = Math.abs(++pos);
        if (pos < lis.size()) {
          lis.set(pos, i);
        } else {
          lis.add(i);
        }
      }
    }

    return lis.size();
  }

  public static void main(String[] args) {
    int[] arr = new int[]{2, 3, 1, 5, 9, 7, 10, 8};
    System.out.println(getLis(arr));
    System.out.println(getLisOptimized(arr));
  }
}