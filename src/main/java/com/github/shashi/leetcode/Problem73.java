package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem73 {
    /*

        Set Matrix Zeroes
        Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's.
        You must do it in place.

        Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
        Output: [[1,0,1],[0,0,0],[1,0,1]]

        Constraints:

        m == matrix.length
        n == matrix[0].length
        1 <= m, n <= 200
        -231 <= matrix[i][j] <= 231 - 1

        Follow up:

        A straightforward solution using O(mn) space is probably a bad idea.
        A simple improvement uses O(m + n) space, but still not the best solution.
        Could you devise a constant space solution?

        Approach 1:

        * intuition is to capture all the rows with zeros and colums in seperate array
        add traverse across the matrix and set it to zero
        algo:
        * have a boolean array of size m and n set to true if any element in row is 0 or col is 0
        * iterate matrix once again and set element to 0 if row flag is col flag is true

        Approach 2:
        * intuition is in approach 1 we used extra m+n space instead we can use the first row and
        col of input mat itself to store the 0 element rows and cols and reduce the space req to 0;
        * use the first row to store the columns with 0s and first column to store the rows with zeros
        * since the rows and cols overlap for 0th index use the extra variable to capture 0th col and
        use the matrix 0th position for storing row status
        algo:
        * iterate over the mat from i =0 to m and j=0 to n, if mat[i][j]==0 then check if j is 0 if so set the 0th col
        flag else set mat[0][j] =0 and for rows set mat[i][0]=0,
        * then iterate from i=1 j=1 if either mat[i][0] or mat[0][j] is 0 then we set mat[i][j]=0
        * then if mat[0][0] is 0 then we set 0th row values to 0, if col flag is set
        then we set 0th col values to 0


     */
    public void setZeroes(int[][] matrix) {
        setZeroesA2(matrix);
    }

    public void setZeroesA2(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        boolean col = false;
        for(int r=0; r<m; r++){
            for(int c=0; c<n; c++){
                if(mat[r][c]==0){
                    if(c==0)col=true;
                    else mat[0][c]=0;
                    mat[r][0]=0;
                }
            }
        }

        for(int r=1; r<m; r++){
            for(int c=1; c<n; c++){
                if(mat[r][0]== 0 || mat[0][c]== 0){
                    mat[r][c]=0;
                }
            }
        }
        if(mat[0][0]==0)
            for(int i=1; i<n;i++)
                mat[0][i]=0;
        if(col)
            for(int i=0; i<m;i++)
                mat[i][0]=0;

    }

    public void setZeroesA1(int[][] mat){
        int m=mat.length, n = mat[0].length;
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j]==0){
                    rows[i]=true;
                    cols[j]=true;
                }
            }
        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(rows[i]|| cols[j]){
                    mat[i][j]=0;
                }
            }
        }
    }
}