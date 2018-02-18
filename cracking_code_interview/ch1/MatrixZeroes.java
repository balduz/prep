import java.util.Arrays;

public class MatrixZeroes {
  private static void setZeroes(int[][] m) {
    boolean[] rows = new boolean[m.length];
    boolean[] cols = new boolean[m[0].length];

    for (int i = 0; i < m.length; i++) {
      for  (int j = 0; j < m[0].length; j++) {
        if (m[i][j] == 0) {
          rows[i] = true;
          cols[j] = true;
        }
      }
    }

    for (int i = 0; i < m.length; i++) {
      for  (int j = 0; j < m[0].length; j++) {
        if (rows[i] || cols[j]) {
          m[i][j] = 0;
        }
      }
    }
  }

  private static void printMatrix(int[][] m) {
    for (int[] row : m) {
      System.out.println(Arrays.toString(row));
    }
  }
  
  public static void main(String[] args) {
    int[][] m = new int[][]{
      {3, 2, 0, 1},
      {4, 8, 7, 6},
      {0, 1, 4, 5},
      {3, 2, 0, 1},
    };

    printMatrix(m);
    System.out.println("after");
    setZeroes(m);
    printMatrix(m);
  }
}