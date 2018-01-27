public class ArraysMedian {
  private static double getMedian(int[] arr1, int[] arr2) {
    if (arr1 == null || arr2 == null) {
      throw new IllegalArgumentException("Both arrays need to be initialized");
    }

    int p1 = 0, p2 = 0;
    int combinedLength = arr1.length + arr2.length;
    if (combinedLength == 0) {
      throw new IllegalArgumentException("Both arrays are empty");
    }

    int medianPos = combinedLength / 2;
    double median = 0;
    double prev = 0;

    for(int i = 0; i <= medianPos; i++) {
      if (p1 >= arr1.length || (p2 < arr2.length && arr1[p1] > arr2[p2])) {
        prev = median;
        median = arr2[p2];
        p2++;
      } else {
        prev = median;
        median = arr1[p1];
        p1++;
      }
    }

    if (combinedLength % 2 == 0) {
      return (median + prev) / 2;
    }
    return median;
  }

  private static double getMedianOptimized(int[] arr1, int[] arr2) {
    if (arr1 == null || arr2 == null || arr1.length + arr2.length == 0) {
      throw new IllegalArgumentException("Invalid input");
    }

    if (arr1.length > arr2.length) {
      return getMedianOptimized(arr2, arr1);
    }

    int combinedLength = arr1.length + arr2.length;
    int medianPoint = (combinedLength + 1) / 2;
    int left = 0;
    int right = arr1.length;

    while (left <= right) {
      int pos1 = (left + right) / 2;
      int pos2 = medianPoint - pos1;

      int leftX = pos1 == 0 ? -1000 : arr1[pos1-1];
      int rightX = pos1 >= arr1.length ? 1000 : arr1[pos1];

      int leftY = pos2 == 0 ? -1000 : arr2[pos2-1];
      int rightY = pos2 >= arr2.length ? 1000 : arr2[pos2];

      if (leftX < rightY && leftY < rightX) {
        if (combinedLength % 2 == 0) {
          return ((double)Math.max(leftX, leftY) + Math.min(rightX, rightY)) / 2;
        }
        return Math.max(leftX, leftY);
      }
      if (leftX > rightY) {
        right = pos1-1;
      } else {
        left = pos1+1;
      }
    }

    return -1;
  }
  
  public static void main(String[] args) {
    int[] arr1 = new int[]{2,4,6,8,10,12};
    int[] arr2 = new int[]{1,3,5,7,9,11};
    System.out.println(getMedian(arr1, arr2));

    arr1 = new int[]{1,2,3,4,5};
    arr2 = new int[]{6,7,8,9,10,11,12,13};
    System.out.println(getMedian(arr1, arr2));

    arr1 = new int[]{2,4,6,8,10,12};
    arr2 = new int[]{1,3,5,7,9,11};
    System.out.println(getMedianOptimized(arr1, arr2));

    arr1 = new int[]{1,2,3,4,5};
    arr2 = new int[]{6,7,8,9,10,11,12,13};
    System.out.println(getMedianOptimized(arr1, arr2));
  }
}