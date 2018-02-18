import java.util.Arrays;

public class MatrixRotate {
  private static void rotate(int[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      throw new IllegalArgumentException("invalid matrix");
    }

    int n = matrix.length;
    for (int layer = 0; layer < n / 2; layer++) {
      int first = layer;
      int last = n - layer - 1;

      for (int i = first; i < last; i++) {
        int diff = i - first;

        int aux = matrix[first][i];

        matrix[first][i] = matrix[last - diff][first];
        matrix[last - diff][first] = matrix[last][last - diff];
        matrix[last][last - diff] = matrix[i][last];
        matrix[i][last] = aux;
      }
    }
  }

  private static void printMatrix(int[][] matrix) {
    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
  }
  
  public static void main(String[] args) {
    int[][] matrix = new int[][]{
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9},
    };

    printMatrix(matrix);
    rotate(matrix);
    System.out.println("After rotating:");
    printMatrix(matrix);
  }
}