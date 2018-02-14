import java.util.LinkedList;
import java.util.Queue;

public class Islands {
  private static final int[][] DIRECTIONS = new int[][]{
    {1, 0}, // DOWN
    {0, 1}, // RIGHT
    {-1, 0}, // UP
    {0, -1}, // LEFT
  };

  private static boolean isValidPoint(int mapHeight, int mapWidth, int x, int y) {
    return x >= 0 && x < mapHeight && y >= 0 && y < mapWidth;
  }

  private static boolean isSea(int[][] map, int x, int y) {
    return map[x][y] == 0;
  }

  private static void visitIsland(int x, int y, int[][] map, boolean[][] visited) {
    Queue<int[]> toVisit = new LinkedList<>();
    toVisit.add(new int[]{x, y});
    visited[x][y] = true;
    while (!toVisit.isEmpty()) {
      int[] current = toVisit.poll();
      x = current[0];
      y = current[1];

      for (int[] direction : DIRECTIONS) {
        int newX = x + direction[0];
        int newY = y + direction[1];

        if (!isValidPoint(map.length, map[0].length, newX, newY)) {
          continue;
        }
        
        if (!isSea(map, newX, newY) && !visited[newX][newY]) {
          visited[newX][newY] = true;
          toVisit.add(new int[]{newX, newY});
        }
      }
    }
  }

  private static int countIslands(int[][] map) {
    if (map == null) {
      return -1;
    }

    int numIslands = 0;
    boolean[][] visited = new boolean[map.length][map[0].length];
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (!isSea(map, i, j) && !visited[i][j]) {
          numIslands++;
          visitIsland(i, j, map, visited);
        }
      }
    }

    return numIslands;
  }
  
  public static void main(String[] args) {
    int[][] map = new int[][]{
      {0, 1, 0, 0, 0, 1},
      {0, 0, 1, 0, 1, 1},
      {1, 1, 1, 0, 0, 1},
    };
    System.out.println(countIslands(map));

    map = new int[][]{
      {0, 1, 0, 0, 0, 1},
      {0, 1, 1, 0, 1, 1},
      {1, 1, 1, 0, 0, 1},
    };
    System.out.println(countIslands(map));

    map = new int[][]{
      {0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0, 0},
    };
    System.out.println(countIslands(map));

    map = new int[][]{
      {1, 0, 1, 0, 1, 0},
      {0, 1, 0, 1, 0, 1},
      {1, 0, 1, 0, 1, 0},
    };
    System.out.println(countIslands(map));
  }
}