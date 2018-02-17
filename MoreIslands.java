public class MoreIslands {
  private static class DsNode {
    private int x;
    private int y;
    private DsNode parent;
    private int rank;

    DsNode(int x, int y) {
      this.x = x;
      this.y = y;
      this.parent = this;
      this.rank = 0;
    }

    void incrementRank() { rank++; }
    int getRank() { return rank; }
    DsNode getParent() { return parent; }
    void setParent(DsNode parent) { this.parent = parent; } 

    @Override
    public boolean equals(Object o) {
      if (o instanceof DsNode) {
        DsNode other = (DsNode)o;
        return other.x == x && other.y == y;
      }
      return false;
    }
  }

  private static final int[][] DIRECTIONS = {
    {1, 0}, // DOWN
    {0, 1}, // RIGHT
    {-1, 0}, // UP
    {0, -1} // LEFT
  };

  private int[][] map;
  private int islands;

  private DsNode[][] dsMap;

  MoreIslands(int[][] map) {
    if (map == null || map.length == 0 || map[0].length == 0)  {
      throw new IllegalArgumentException("invalid map!");
    }

    this.map = map;
    this.islands = 0;
    this.dsMap = new DsNode[map.length][map[0].length];
  }
  
  private boolean isLand(int x, int y) {
    return this.map[x][y] == 1;
  }

  private boolean isValid(int x, int y) {
    return x >= 0 && x < map.length && y >= 0 && y < map[0].length;
  }

  private int addLand(int x, int y) {
    makeSet(x, y);
    this.map[x][y] = 1;

    for (int[] dir : DIRECTIONS) {
      int neighborX = x + dir[0];
      int neighborY = y + dir[1];

      if (isValid(neighborX, neighborY) && isLand(neighborX, neighborY)) {
        union(this.dsMap[x][y], this.dsMap[neighborX][neighborY]);
      }
    }

    return islands;
  }

  private void makeSet(int x, int y) {
    if (dsMap[x][y] != null) {
      return;
    }
    islands++;

    DsNode node = new DsNode(x, y);
    dsMap[x][y] = node;
  }

  private DsNode findSet(DsNode node) {
    DsNode parent = node.getParent();
    if (!parent.equals(node)) {
      parent = findSet(parent);
      node.setParent(parent);
    }
    
    return parent;
  }

  private void union(DsNode newNode, DsNode neighbor) {
    DsNode p1 = findSet(newNode);
    DsNode p2 = findSet(neighbor);

    if (!p1.equals(p2)) {
      if (p1.getRank() > p2.getRank()) {
        p2.setParent(p1);
      } else {
        p1.setParent(p2);
        if (p1.getRank() == p2.getRank()) {
          p1.incrementRank();
        }
      }

      islands--;
    }
  }

  public static void main(String[] args) {
    int[][] matrix = new int[][]{
      {0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0},
      {0, 0, 0, 0, 0}
    };

    MoreIslands a = new MoreIslands(matrix);

    int[] p = new int[]{0, 0};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{1, 1};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{0, 1};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{4, 1};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{3, 2};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{4, 4};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{4, 3};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));

    p = new int[]{4, 2};
    System.out.println("adding (" + p[0] + ", " + p[1] + "). islands: " + a.addLand(p[0], p[1]));
  }
}