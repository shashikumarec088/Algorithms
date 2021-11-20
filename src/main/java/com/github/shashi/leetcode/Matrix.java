package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix {

    public static void main(String[] args) {
        Matrix mat = new Matrix();
        int[][] matrix = new int[][]{
                {0,0,0},{0,1,0},{0,0,0}
        };
        mat.updateMat(matrix);
    }
    public int[][] updateMat(int[][] mat){
        int rc = mat.length,cc = mat[0].length;
        int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<Integer> queue = new LinkedList<>();

        for(int i =0; i < rc; i++){
            for(int j=0; j<cc; j++){
                if(mat[i][j] ==  0)
                    queue.add(cc*i+j);
                else mat[i][j] = -1;
            }
        }

        while(!queue.isEmpty()){
            int pos = queue.poll();
            int row = pos/cc;
            int col = pos%cc;
            for(int[] dir : dirs){
                int r = row+dir[0];
                int c = row+dir[1];
                if(r <0||c<0|| r>=rc || c>= cc || mat[r][c]!= -1)
                    continue;
                mat[r][c] = mat[row][col]+1;
                queue.add(r*cc+c);
            }
        }
        return mat;
    }
}
