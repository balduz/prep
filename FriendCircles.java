public class FriendCircles {
    private static class DisjointSet {
        private int[] parents;
        private int[] ranks;
        private int sets;
        
        DisjointSet(int len) {
            this.parents = new int[len];
            this.ranks = new int[len];
        }
        
        void makeSet(int x) {
            this.parents[x] = x;
            this.sets++;
        }
        
        void union(int x, int y) {
            int pX = find(x);
            int pY = find(y);
            if (pX == pY) {
                return;
            }
            if (this.ranks[pX] > this.ranks[pY]) {
                this.parents[pY] = this.parents[pX];
            } else {
                this.parents[pX] = this.parents[pY];
                if (this.ranks[pX] == this.ranks[pY]) {
                    this.ranks[pY]++;
                }
            }
            this.sets--;
        }
        
        int find(int x) {
            if (this.parents[x] != x)
                this.parents[x] = find(this.parents[x]);
            return this.parents[x];
        }
        
        int getNumberOfSets() {
            return this.sets;
        }
    }
    
    public static int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        
        int numStudents = M.length;
        DisjointSet ds = new DisjointSet(numStudents);
        for (int i = 0; i < numStudents; i++) {
            ds.makeSet(i);
        }
        for (int i = 0; i < numStudents; i++) {
            for (int j = i+1; j < numStudents; j++) {
                if (M[i][j] == 1) {
                    ds.union(i, j);
                }
            }
        }
        return ds.getNumberOfSets();
    }

    public static void main(String[] args) {
        int[][] friends = new int[][]{
            {1, 1, 0},
            {1, 1, 0},
            {0, 0, 1}
        };
        System.out.println(findCircleNum(friends));

        friends = new int[][]{
            {1, 1, 0},
            {1, 1, 1},
            {0, 1, 1}
        };
        System.out.println(findCircleNum(friends));

        friends = new int[][]{
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println(findCircleNum(friends));
    }
}
