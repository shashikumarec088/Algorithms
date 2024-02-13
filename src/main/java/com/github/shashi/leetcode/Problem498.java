package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class Problem498 {

    public static void main(String[] args) {
        int[][] mat = {{2,3}};
        Problem498 problem498 = new Problem498();
        int[] res = problem498.findDiagonalOrder(mat);
    }

    public int[] findDiagonalOrderA2(int[][] mat){
        int m = mat.length, n = mat[0].length;
        boolean dir = true;
        int r=0,c=0, i=0;
        int[] result = new int[m*n];
        while(r<m && c<n){
            result[i++] = mat[r][c];
            int newR=dir?r-1:r+1;
            int newC=dir?c+1:c-1;

            if(newR <0 || newR >=m || newC <0 || newC>=n){
                if(dir){
                    r+= newC<n? 0 : 1;
                    c+= newC<n? 1: 0;
                }else{
                    r+= newR <m?1:0;
                    c+= newR<m?0:1;
                }
                dir = !dir;
            }else{
                r = newR;
                c = newC;
            }
        }
        return result;

    }

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        ArrayList<Integer> inter = new ArrayList<>();
        for(int i=0; i<m+n-1;i++){
            int r = i<m? 0: i-m+1;
            int c = i<n? i : n-1;
            ArrayList<Integer> diag = new ArrayList<>();
            while (r<m && c>-1){
                diag.add(mat[r][c]);
                r++;
                c--;
            }
            if(i%2==0) Collections.reverse(diag);
            inter.addAll(diag);
            diag.clear();
        }
        for(int i=0; i<result.length; i++){
            result[i] = inter.get(i);
        }
        return result;
    }
}
