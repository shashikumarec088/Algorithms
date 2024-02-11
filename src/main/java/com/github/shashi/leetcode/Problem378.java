package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem378 {

    public static void main(String[] args) {
        int [][] mat = {{1,5,9},{10,11,13},{12,13,15}};
        int k = 8;
        Problem378 ps = new Problem378();
        int ans = ps.kthSmallest(mat,k);
        System.out.println(ans);
        Arrays.sort(mat,(a,b)-> a[1]-b[1]);
    }

    public int kthSmallest(int[][] matrix, int k) {
        return  kthSmallestA3(matrix,k);
    }

    public int kthSmallestA3(int[][] mat, int k){
        int n= mat.length;
        int s = mat[0][0], e = mat[n-1][n-1];
        while(s<e){
            int m = s +(e-s)/2;
            int[] sl = new int[]{mat[0][0],mat[n-1][n-1]};
            int cn = getCount(mat,m,sl);
            if(cn==k) return sl[0];
            else if (cn < k) s = sl[1];
            else e = sl[0];
        }
        return s;
    }

    public int getCount(int[][] mat, int m, int[] sl){
        int n = mat.length;
        int r = n-1, c = 0, cn = 0;
        while(r >=0 && c < n){
            if(mat[r][c] > m){
                sl[1] = Math.min(sl[1],mat[r][c]);
                r--;
            }else{
                sl[0] = Math.max(sl[0],mat[r][c]);
                cn += r+1;
                c++;
            }
        }
        return cn;
    }

    public int kthSmallestA2(int[][] mat, int k){
        int n = mat.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        // since r, c are sorted if k< n then we need only k rows

        for(int r=0; r < Math.min(n,k); r++){
            pq.add(new int[]{mat[r][0],r,0});
        }
        int[] elem = pq.peek();
        while(k-- > 0){
            elem = pq.poll();
            int r = elem[1], c = elem[2];
            if(c < n-1){
                pq.add(new int[]{mat[r][c+1],r,c+1});
            }
        }
        return elem[0];
    }

    public int kthSmallestA1(int[][] mat, int k) {
        int n = mat.length;
        int ln = n*n - (k-1);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        if(k == n*n) return mat[n-1][n-1];
        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                if(pq.size() < ln)
                    pq.add(mat[r][c]);
                else if(pq.peek()<mat[r][c]){
                    pq.poll();
                    pq.add(mat[r][c]);
                }
            }
        }
        return pq.poll();
    }
}
