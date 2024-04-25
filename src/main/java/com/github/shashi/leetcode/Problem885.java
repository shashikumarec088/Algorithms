package com.github.shashi.leetcode;

public class Problem885 {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        return spiralMatrixIIIA1(rows,cols, rStart, cStart);
    }

    /*
        intuition is to move in 4 direction from the given position,
        when we are start to move in east or west we increase the
        path length by 1, keep adding when the position is within bounds
    */
    public int[][] spiralMatrixIIIA1(int rows, int cols, int rs, int cs) {
        int j=0, len=0, d=0;
        int[][] res = new int[rows*cols][2];
        res[j++] = new int[]{rs, cs};
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        while(j < rows * cols){
            if(d==0 || d==2)len++;
            for(int i=0; i<len;i++){
                rs = rs + dirs[d][0];
                cs = cs + dirs[d][1];
                if(rs >=0 && rs < rows &&
                        cs >= 0 && cs < cols)
                    res[j++] = new int[]{rs, cs};
            }
            d = (d+1)%4;
        }
        return res;
    }
}