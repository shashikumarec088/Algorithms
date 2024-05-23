package com.github.shashi.leetcode;
import java.util.*;
public class Problem200 {
    /*
        Number of Islands
        Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number
        of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
        You may assume all four edges of the grid are all surrounded by water.

        Example 1:
        Input: grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
        Output: 1
        Example 2:
        Input: grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
        Output: 3
        Constraints:
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 300
        grid[i][j] is '0' or '1'.

        approach 1: dfs recursion
        * it is the undirected graph dfs problem where we need to find the subgraphs which are isolated, here
        we do dfs when element value is 1 and keep exploring in all possible direction when neighbor is 1
        algo:
        * initialize m= rows, n=cols, count=0 iterate over the grid, check if element is 1 if so inc count and
        call dfs, return count at the end
        * in dfs base case is to check if i <0 or >= rows or j <0 or > cols, grid[i][j] is not 1 then return
        * else reset grid[i][j]='0' and iterate in all directions
        * possible directions are 0,1 1,0 0,-1 -1,0 call the dfs with updated value of i, j
        time & space:
        * take m*n time and m*n space for recursion stack in worst case

        approach 2: bfs
        * inuition is same as approach 1 but instead of dfs we use bfs to explore the grid
        algo:
        * initialize m,n,count, 2d boolean visit array
        * iterate i=0,j=0 to m, n do bfs if grid has 1 and not visited
        * increment count and call bfs
        * return count
        * in bfs create queue if type int array and add i,j
        * iterate until queue is empty
        * consider the size of the queue and iterate from i=0 to size
        * poll the position and check if it is visited or not
        * if not visited  mark it visited, iterate over all directions
        * if ni,nj within the bound and not visited and has 1 then add to queue
        * iterate until queue is empty
        time & space:
        * takes mn time and min(m,n) space when all the nodes are 1s

        approach 3: union find with rank and path compression
        * intuition is to use the union find ds initialize with grid size and with islands as all ones count in
        grid and dec islands when we union 2 adjacent cells
        algo:
        * create the union find ds which takes size and number if islands
        * count the number if islands, define the unionfind with size m*n and numIslands
        * iterate over the grid and when it is 1, iterate over the neighbors and when neighbor
        is 1 then call union with i*n+j, nei*n+nej
        * return isLands at the end


     */
    private char[][] grid;
    private boolean[][] visited;
    private int m,n;
    private int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};


    class UnionFind{
        private int[] root,rank;
        private int isLands;
        UnionFind(int n, int isLands){
            root = new int[n];
            rank = new int[n];
            this.isLands = isLands;
            for(int i=0; i<n; i++){
                root[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x){
            if(x== root[x])
                return x;
            return root[x]=find(root[x]);
        }

        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if(rx!= ry){
                if(rank[rx]>rank[ry])
                    root[ry]=rx;
                else if(rank[ry]>rank[rx])
                    root[rx]=ry;
                else{
                    root[ry]=rx;
                    rank[rx]++;
                }
                isLands--;
            }
        }
        public int numIslands(){
            return isLands;
        }

    }

    public int numIslandsA3(char[][] grid) {
        int m=grid.length,n=grid[0].length,count=0;
        for(int i=0; i<m;i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1')
                    count++;
        UnionFind uf = new UnionFind(m*n,count);
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int i=0; i<m;i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1'){
                    for(int[] dir:dirs){
                        int nei = i+dir[0], nej = j+dir[1];
                        if(nei<0||nei>=m || nej<0 || nej>=n ||
                                grid[nei][nej]!='1')continue;
                        uf.union(n*i+j,n*nei+nej);
                    }
                }
        return uf.numIslands();
    }

    public int numIslandsA2(char[][] grid) {
        int m=grid.length,n=grid[0].length,count=0;
        boolean[][] visit = new boolean[m][n];
        for(int i=0; i<m;i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1' && !visit[i][j]){
                    count++;
                    bfs(i,j,grid,visit);
                }
        return count;
    }

    public void bfs(int i1,int j, char[][] grid, boolean[][] visit){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i1,j});
        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size;i++){
                int[] pos = queue.poll();
                if(!visit[pos[0]][pos[1]]){
                    visit[pos[0]][pos[1]]=true;
                    for(int[] dir:dirs){
                        int ni = pos[0]+dir[0], nj = pos[1]+dir[1];
                        if(ni<0 || ni>=grid.length || nj<0 || nj>=grid[0].length
                                || visit[ni][nj] || grid[ni][nj]!='1')continue;
                        queue.offer(new int[]{ni,nj});
                    }
                }
            }
        }
    }
    public int numIslandsA1(char[][] grid) {
        int m=grid.length,n=grid[0].length,count=0;
        for(int i=0; i<m;i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1'){
                    count++;
                    dfs(i,j,grid);
                }
        return count;
    }

    public void dfs(int i, int j, char[][] grid){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length
                || grid[i][j]!='1')return;
        grid[i][j]='0';
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] dir : dirs)
            dfs(i+dir[0],j+dir[1],grid);

    }
}