package com.github.shashi.leetcode;

import java.util.LinkedList;

public class Provinces {
    public static void main(String[] args) {
        Provinces mat = new Provinces();
        int[][] matrix = new int[][]{
                {1, 1, 1}, {1, 1, 1}, {1, 1, 1}
        };
        System.out.println(mat.findProvincesDfs(matrix));
    }

    public int findProvincesDfs(int[][] grid) {
        int count = 0;
        int rc = grid.length, cc = grid[0].length;
        int[] visited = new int[rc];
        for (int r = 0; r < rc; r++) {
            if (visited[r] == 0) {
                count++;
                dfs(grid, visited, r);
            }
        }
        return count;
    }

    public void dfs(int[][] grid, int[] visited, int r) {
        for (int c = 0; c < visited.length; c++) {
            if (grid[r][c] == 1 && visited[c] == 0) {
                visited[c] = 1;
                dfs(grid, visited, c);
            }
        }
    }
}
