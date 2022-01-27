package com.github.shashi.leetcode;
import java.util.*;
public class Problem1091 {
    private Integer dist = null;
    private int[][] dirs = new int[][]{
            {-1,0},{-1,1},{0,1},{1,1},
            {1,0},{1,-1},{0,-1},{-1,-1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        return shortestPathBinaryMatrixBfs(grid);
    }

    public int shortestPathBinaryMatrixBfs(int[][] grid) {
        int  m = grid.length, n = grid[0].length,count=0;
        if(grid[0][0]!=0 || grid[m-1][n-1] !=0) return -1;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        visited[0][0]=true;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] cell = queue.poll();
                if(cell[0] == m-1 && cell[1] == n-1)
                    return count+1;
                for(int[] dir : dirs){
                    int nr = cell[0]+dir[0];
                    int nc = cell[1]+dir[1];

                    if(nr <0 ||nc <0 || nr >= m || nc >= n || visited[nr][nc]||
                            grid[nr][nc] != 0) continue;
                    visited[nr][nc]=true;
                    queue.add(new int[]{nr,nc});
                }
            }
            count++;
        }
        return -1;
    }

    public int shortestPathBinaryMatrixdfs(int[][] grid) {
        if(grid[0][0]!=0) return -1;
        grid[0][0]=2;
        shortestPathDfs(grid,0,0,new int[]{0});
        grid[0][0]=0;
        if(dist == null) return -1;
        return dist+1;
    }

    public void shortestPathDfs(int[][] grid, int r, int c, int[] count){
        if(r == grid.length-1 && c == grid.length-1){
            if(dist == null) dist = count[0];
            else dist = Math.min(dist,count[0]);
            return;
        }
        for(int[] path : getValidPaths(grid,r,c)){
            if(grid[path[0]][path[1]] == 0){
                grid[path[0]][path[1]] = 2;
                count[0]++;
                shortestPathDfs(grid,path[0],path[1],count);
                grid[path[0]][path[1]] = 0;
                count[0]--;
            }
        }
    }

    public List<int[]> getValidPaths(int[][] grid, int r, int c){
        List<int[]> list = new ArrayList<>();
        int n = grid.length;
        for(int[] dir : dirs){
            int nr = r +dir[0];
            int nc = c +dir[1];
            if(nr < 0 || nr >= n || nc <0 || nc >= n || grid[nr][nc] != 0)
                continue;
            list.add(new int[]{nr,nc});
        }
        return list;
    }
}
