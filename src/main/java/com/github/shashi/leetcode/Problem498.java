package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem498 {

    public static void main(String[] args) {
        int[][] mat = {{2,3}};
        Problem498 problem498 = new Problem498();
        //int[] res = problem498.findDiagonalOrder(mat);
    }

    /*
        intuition behind this approach is that
        all the elements within the diagonal will
        have same sum of indices, starting from 0
        to m+n-1, we can first form these diagonal elements
        and form the final result from those lists
    */
    public int[] findDiagonalOrderA3(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int d = m+n-1;
        List<Integer>[] list = new List[d];
        for(int i=0; i <d; i++)
            list[i] = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                list[i+j].add(mat[i][j]);
            }
        }
        int[] res = new int[m*n];
        int i=0;
        for(int r = 0; r<d; r++){
            if(r%2==0)Collections.reverse(list[r]);
            for(int elem : list[r])
                res[i++] = elem;
        }
        return res;
    }

    /*
        intuition is to simulate the actual
        traversal, we start in upward direction
        in upward direction we dec r and inc c
        in downward we inc r and dec c, when bounds
        are reached we adjust r & c based on direction

    */
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

    /*
        intuition is that there will be m+n-1
        diagonals in the matrix and start position
        will be from r = 0 and c=0 and it keeps
        changing based on diagonal number,
        traverse downwards for each diagonal
    */
    public int[] findDiagonalOrderA1(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int d = m+n-1;
        int[] res = new int[m*n];
        int k = 0;
        for(int i=0; i <d; i++){
            List<Integer> list = new ArrayList<>();
            int r = i < n ? 0 : i-n+1;
            int c = i< n? i : n-1;
            while(r < m && c > -1){
                list.add(mat[r][c]);
                r++;
                c--;
            }
            if(i%2==0)Collections.reverse(list);
            for(int elem : list)
                res[k++] = elem;
        }

        return res;
    }
}
