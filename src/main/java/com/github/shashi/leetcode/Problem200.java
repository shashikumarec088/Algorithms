package com.github.shashi.leetcode;
import java.util.*;
public class Problem200 {
    private char[][] grid;
    private boolean[][] visited;
    private int m,n;
    private int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    public int numIslands(char[][] grid) {
        return numIslandsA3(grid);
    }

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

    public int numIslandsA3(char[][] grid1){
        grid = grid1;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int islands = 0;
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1')
                    islands++;
        UnionFind uf = new UnionFind(m*n,islands);
        for(int i=0; i<m; i++)
            for(int j=0; j<n; j++)
                if(grid[i][j]=='1'){
                    for(int[] nei : getNeighbors(i,j))
                        uf.union(i*n+j,nei[0]*n+nei[1]);
                }

        return uf.numIslands();
    }

    public int numIslandsA1(char[][] grid1){
        grid = grid1;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int islands = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    dfs(i,j);
                    islands++;
                }
            }
        }
        return islands;
    }

    public int numIslandsA2(char[][] grid1){
        grid = grid1;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int islands = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]=='1' && !visited[i][j]){
                    bfs(i,j);
                    islands++;
                }
            }
        }
        return islands;
    }

    public void bfs(int i, int j){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n*i+j);
        visited[i][j]=true;
        while(!queue.isEmpty()){
            int id = queue.poll();
            int r = id/n;
            int c = id%n;
            for(int[] nei : getNeighbors(r,c)){
                visited[nei[0]][nei[1]] = true;
                queue.add(nei[0]*n+nei[1]);
            }
        }
    }

    public int numIslandsA4(char[][] grid) {
        int n=0, r=grid.length, c = grid[0].length;
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(grid[i][j]=='1'){
                    n++;
                    dfs2(i,j,grid);
                }

            }
        }
        return n;
    }

    public void dfs2(int i, int j, char[][] grid){
        int r = grid.length, c = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        if(grid[i][j]=='0') return;
        grid[i][j]='0';
        for(int[] dir: dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >= r || nr<0 || nc>= c || nc <0
                    || grid[nr][nc]=='0')
                continue;
            dfs2(nr,nc,grid);
        }
    }

    public void dfs(int i, int j, char[][] grid){
        int r = grid.length, c = grid[0].length;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        Stack<int[]>  stack = new Stack<>();
        stack.push(new int[]{i,j});
        grid[i][j]='0';
        while(!stack.isEmpty()){
            int[] pos = stack.pop();
            for(int[] dir: dirs){
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];
                if(nr >= r || nr<0 || nc>= c || nc <0
                        || grid[nr][nc]=='0')
                    continue;
                grid[nr][nc]='0';
                stack.push(new int[]{nr,nc});
            }
        }
    }

    public void dfs(int i, int j){
        if(visited[i][j]) return;
        visited[i][j]=true;
        for(int[] nei : getNeighbors(i,j))
            dfs(nei[0],nei[1]);
    }

    public List<int[]> getNeighbors(int i, int j){
        List<int[]> result = new ArrayList<>();
        for(int[] dir : dirs){
            int x = i+dir[0];
            int y = j+dir[1];
            if(x<0 || y<0 || x>= m || y>= n ||grid[x][y]=='0'|| visited[x][y])
                continue;
            result.add(new int[]{x,y});
        }
        return result;
    }
}