package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem2326 {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        return spiralMatrixA2(m, n, head);
    }

    /*
        intuition is similar to problem 54 but here we use
        the directions to populate the metrics and stop
        once the head is null, we fill the array with -1
        initially, we only change the direction when we
        meet the bounds or we have already visited the position
    */
    public int[][] spiralMatrixA2(int m, int n, ListNode head) {
        int[][] res = new int[m][n];
        for(int i=0; i<m ;i++)
            Arrays.fill(res[i],-1);
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        int i=0, j=0, d=0, r=0,c=0;
        while(head !=null){
            res[r][c] = head.val;
            int nr = r+dir[d][0];
            int nc = c +dir[d][1];
            if(nr>= m || nr < 0 || nc>=n || nc < 0
                    || res[nr][nc] !=-1)
                d = (d+1)%4;
            r = r+dir[d][0];
            c = c +dir[d][1];
            head = head.next;
        }
        return res;
    }

    /*
        intuition is very similar to problem 54
    */
    public int[][] spiralMatrixA1(int m, int n, ListNode head) {
        int rs =0, re =m-1, cs=0, ce=n-1;
        int[][] res = new int[m][n];
        while(rs <=re && cs <=ce){
            for(int i=cs; i<= ce; i++){
                res[rs][i] = head ==null?-1:head.val;
                head = head==null?null : head.next;
            }
            for(int i=rs+1; i<= re; i++){
                res[i][ce] = head ==null?-1:head.val;
                head = head==null?null : head.next;
            }

            if(rs!=re){
                for(int i=ce-1; i >= cs; i--){
                    res[re][i] = head ==null?-1:head.val;
                    head = head==null?null : head.next;
                }
            }

            if(cs != ce){
                for(int i=re-1; i > rs; i--){
                    res[i][cs] = head ==null?-1:head.val;
                    head = head==null?null : head.next;
                }
            }
            rs++;
            cs++;
            re--;
            ce--;
        }
        return res;
    }
}