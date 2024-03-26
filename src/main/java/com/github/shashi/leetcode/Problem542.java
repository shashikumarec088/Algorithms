package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Problem542 {
    public int[][] updateMatrix(int[][] mat) {
        return updateMatrixA3(mat);
    }

    public int[][] updateMatrixA3(int[][] mat){
        int r = mat.length, c = mat[0].length;
        int[][] dp = new int[r][c];
        for(int i=0;i<r; i++){
            for(int j=0; j<c; j++){
                dp[i][j] = mat[i][j];
            }
        }

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                int minVal = r*c;
                if(dp[i][j]==0)continue;
                if(j > 0){
                    minVal = Math.min(minVal, dp[i][j-1]);
                }
                if(i > 0){
                    minVal = Math.min(minVal, dp[i-1][j]);
                }
                dp[i][j] = minVal+1;
            }
        }

        for(int i=r-1; i>-1; i--){
            for(int j=c-1; j>-1; j--){
                int minVal = r*c;
                if(dp[i][j]==0)continue;
                if(j < c-1){
                    minVal = Math.min(minVal, dp[i][j+1]);
                }
                if(i < r-1){
                    minVal = Math.min(minVal, dp[i+1][j]);
                }
                dp[i][j] = Math.min(minVal+1, dp[i][j]);
            }
        }
        return dp;
    }

    public int[][] updateMatrixA2(int[][] mat) {
        int r = mat.length, c = mat[0].length;
        int[][] res = new int[r][c];
        boolean[][] visited = new boolean[r][c];
        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                res[i][j] = mat[i][j];
                if(mat[i][j]==0){
                    visited[i][j] = true;
                    queue.add(new int[]{i,j,0});
                }
            }
        }

        while(!queue.isEmpty()){
            int[] state = queue.poll();
            int sr = state[0], sc = state[1], steps = state[2];
            for(int[] dir : dirs){
                int nr = sr+dir[0], nc = sc+dir[1];
                if(nr>=0 && nr < r && nc >= 0 && nc < c && !visited[nr][nc]){
                    res[nr][nc]=steps+1;
                    visited[nr][nc]=true;
                    queue.add(new int[]{nr,nc,steps+1});
                }
            }
        }
        return res;
    }


    public int[][] updateMatrixA1(int[][] mat) {
        int r = mat.length, c = mat[0].length;
        int[][] res = new int[r][c];
        int[][] cache = new int[r][c];
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(mat[i][j]==1){
                    if(cache[i][j]!=0){
                        res[i][j] = cache[i][j];
                    }
                    else {
                        cache[i][j] = bfs(mat,i,j);
                        res[i][j] = cache[i][j];
                    }
                }
            }
        }
        return res;
    }

    public int bfs(int[][] mat, int i, int j){
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int nr = mat.length, nc = mat[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(new int[]{i,j});
        int dist = 1;
        while(! queue.isEmpty()){
            int size = queue.size();
            for(int m=0; m<size; m++){
                int[] pos = queue.poll();
                if(!visited.contains(nr*pos[0]+pos[1])){
                    visited.add(nr*pos[0]+pos[1]);
                    for(int[] dir: dirs){
                        int sr = dir[0]+pos[0], sc = dir[1]+pos[1];
                        if(sr>= nr || sr < 0 || sc >= nc || sc < 0)
                            continue;
                        if(mat[sr][sc]==0){
                            return dist;
                        }

                        queue.add(new int[]{sr,sc});
                    }
                }
            }
            dist++;
        }
        return -1;
    }
}