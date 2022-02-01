package com.github.shashi.leetcode;

public class Problem48 {
    public void rotate(int[][] matrix) {
        rotateSol(matrix);
    }

    public void rotate2(int[][] matrix){
        int n = matrix.length;
        for(int i=0; i<n; i++){
            for(int j=i; j<n;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i=0; i<n; i++){
            for(int j = 0; j<n/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-1-j];
                matrix[i][n-1-j] = temp;
            }
        }
    }

    public void rotateSol(int[][] matrix){
        int n = matrix.length;
        int l =0, r = n-1;
        while(l<r){
            for(int i=0; i<r-l; i++){
                int t = l, b=r;
                int temp = matrix[t][l+i];
                matrix[t][l+i]= matrix[b-i][l];
                matrix[b-i][l] = matrix[b][r-i];
                matrix[b][r-i] = matrix[t+i][r];
                matrix[t+i][r] = temp;
            }
            l++;
            r--;
        }
    }
}
