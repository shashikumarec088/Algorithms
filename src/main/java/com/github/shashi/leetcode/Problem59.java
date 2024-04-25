package com.github.shashi.leetcode;

public class Problem59 {
    public int[][] generateMatrix(int n) {
        return generateMatrixA2(n);
    }

    /*
        intuition is very similar to problem 54
        where we traverse and change direction when
        we already visited the matrics
    */
    public int[][] generateMatrixA2(int n) {
        int m = n, val=1, dir=0, chdir=0, r=0,c=0;
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int[][] mat = new int[m][n];
        mat[r][c] = val++;
        while(chdir <2){
            while(r+ dirs[dir][0] >= 0 && r+ dirs[dir][0] < m &&
                    c+ dirs[dir][1] >= 0 && c+ dirs[dir][1] < n &&
                    mat[r+ dirs[dir][0]][c+ dirs[dir][1]] ==0){
                r = r+ dirs[dir][0];
                c = c+ dirs[dir][1];
                mat[r][c] = val++;
                chdir=0;
            }
            chdir++;
            dir = (dir+1)%4;
        }
        return mat;
    }

    /*
        intuition is to traverse the metrics
        spirally and keep updating the value
        very similar to spiral metrics
    */
    public int[][] generateMatrixA1(int n) {
        int m = n, rs=0, cs =0, val=1;
        int re = m-1, ce = n-1;
        int[][] mat = new int[m][n];
        while(rs <= re && cs <= ce){
            for(int i=cs; i<= ce; i++)
                mat[rs][i] = val++;
            for(int i=rs+1; i<= re; i++)
                mat[i][ce] = val++;
            if(rs != re)
                for(int i=ce-1; i>= cs; i--)
                    mat[re][i] = val++;
            if(cs != ce)
                for(int i=re-1; i> rs; i--)
                    mat[i][cs] = val++;
            rs++;
            cs++;
            re--;
            ce--;

        }
        return mat;
    }
}