package com.github.shashi.leetcode;
import java.util.*;
public class Problem733  {
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        return floodFillA1(image, sr, sc, newColor);
    }

    public static void main(String[] args) {
        Problem733 problem733 = new Problem733();
        int[][] input = {{1,1,1},{1,1,0},{1,0,1}};
        problem733.floodFillA3(input,1,1,2);
    }

    public int[][] floodFillA3(int[][] image, int sr, int sc, int color) {
        int prev = image[sr][sc];
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{sr,sc});
        while(!stack.isEmpty()){
            int[] pos = stack.pop();
            if(image[pos[0]][pos[0]]==prev){
                image[pos[0]][pos[1]] = color;
                for(int[] dir: dirs){
                    int nr = pos[0]+dir[0], nc = pos[1]+dir[1];
                    if(nr >= image.length || nr <0 ||
                            nc >= image[0].length || nc<0 ||
                            image[nr][nc] != prev || image[nr][nc] == color)
                        continue;
                    stack.push(new int[]{nr,nc});
                }
            }
        }
        return image;
    }

    public int[][] floodFillA2(int[][] image, int sr, int sc, int nc){
        int color = image[sr][sc];
        if(color != nc)dfs(image,sr, sc, color,nc);
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int color, int nc){
        if(image[sr][sc]==color){
            image[sr][sc]=nc;
            if(sr>=1)dfs(image,sr-1,sc,color,nc);
            if(sc>=1)dfs(image,sr,sc-1,color,nc);
            if(sr+1 < image.length)dfs(image,sr+1,sc,color,nc);
            if(sc+1 < image[0].length)dfs(image,sr,sc+1,color,nc);
        }
    }

    public int[][] floodFillA1(int[][] image, int sr, int sc, int newColor){
        int oldColor = image[sr][sc], m = image.length, n = image[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr,sc});
        boolean[][] visited = new boolean[m][n];
        if(oldColor==newColor)return image;
        image[sr][sc] = newColor;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                int[] pos = queue.poll();
                for(int[] dir : dirs){
                    int nr = dir[0]+pos[0];
                    int nc = dir[1]+pos[1];
                    if(nr>=m || nr<0 || nc >= n || nc < 0 || image[nr][nc] != oldColor) continue;
                    image[nr][nc] = newColor;
                    queue.add(new int[]{nr,nc});
                }
                size--;
            }
        }
        return image;
    }
}