import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MarsBases {
  private static final int EMPTY = 0;
  private static final int MOUNTAIN = 1;
  private static final int BASE = 2;

  private static class Point {
    private int x;
    private int y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    int getX() {
      return x;
    }

    int getY() {
      return y;
    }

    public String toString() {
      return "(" + x + ", " + y+ ")";
    }
  }

  private static final Point UP = new Point(-1, 0);
  private static final Point RIGHT = new Point(0, 1);
  private static final Point DOWN = new Point(1, 0);
  private static final Point LEFT = new Point(0, -1);

  private static final Point[] DIRECTIONS = new Point[]{UP, RIGHT, DOWN, LEFT};

  private static Point addPoints(Point p1, Point p2) {
    return new Point(p1.getX() + p2.getX(), p1.getY() + p2.getY());
  }

  private static boolean isPointValid(Point point, int rows, int cols) {
    return point.getX() >= 0 && point.getX() < rows && point.getY() >= 0 && point.getY() < cols;
  }

  private static void populateDistancesFromPoint(Point point, int[][] marsMap, int[][] distances) {
    int rows = marsMap.length;
    int cols = marsMap[0].length;

    Queue<Point> adjacents = new LinkedList<>();
    adjacents.add(point);
    distances[point.getX()][point.getY()] = 1;

    while (adjacents.size() > 0) {
      Point current = adjacents.poll();

      for (Point direction : DIRECTIONS) {
        Point next = addPoints(current, direction);
        if (isPointValid(next, rows, cols)) {
          if (distances[next.getX()][next.getY()] == 0) {
            if (marsMap[next.getX()][next.getY()] != MOUNTAIN) {
              distances[next.getX()][next.getY()] = distances[current.getX()][current.getY()] + 1;
              adjacents.add(next);
            }
          }
        }
      }
    }
  }

  private static void addDistanceMetrics(int[][] distances, int[][] newDistances) {
    for (int i = 0; i < distances.length; i++) {
      for (int j = 0; j < distances[0].length; j++) {
        distances[i][j] += newDistances[i][j];
      }
    }
  }

  private static Point getBestPoint(int[][] marsMap) {
    if (marsMap == null || marsMap.length == 0 || marsMap[0].length == 0) {
      throw new IllegalArgumentException("Invalid input map");
    }

    int rows = marsMap.length;
    int cols = marsMap[0].length;
    List<Point> bases = new ArrayList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (marsMap[i][j] == BASE) {
          bases.add(new Point(i, j));
        }
      }
    }

    int[][] distances = new int[rows][cols];
    for (Point base : bases) {
      int[][] newDistances = new int[rows][cols];
      populateDistancesFromPoint(base, marsMap, newDistances);
      addDistanceMetrics(distances, newDistances);
    }

    int minimumDistance = Integer.MAX_VALUE;
    Point bestLocation = null;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (distances[i][j] > 0 && distances[i][j] < minimumDistance) {
          bestLocation = new Point(i, j);
          minimumDistance = distances[i][j];
        }
      }
    }

    return bestLocation;
  }

  public static void main(String[] args) {
    int[][] marsMap = new int[][]{
      {0,0,0,0,0,1,1,1,2,0,1},
      {0,0,0,0,0,1,1,0,0,1,1},
      {0,0,2,0,0,0,0,0,0,1,1},
      {0,0,0,0,0,0,0,0,0,1,0},
      {0,0,0,0,0,0,0,0,0,0,0},
      {0,0,1,1,1,0,0,0,2,0,0},
      {0,0,0,1,1,1,1,0,0,0,0},
      {0,0,0,1,0,1,1,0,0,0,0},
      {0,2,0,1,0,1,0,0,0,0,0},
    };

    System.out.println(getBestPoint(marsMap));
  }
}
