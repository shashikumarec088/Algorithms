package com.github.shashi.leetcode;
import java.util.*;
public class Problem1337 {
    public int[] kWeakestRows(int[][] mat, int k) {
        return kWeakestRowsA2(mat,k);
    }

    public int[] kWeakestRowsA3(int[][] mat, int k){
        int[] result = new int[k];
        int index=0, m=mat.length, n=mat[0].length;
        for(int c=0; c<n && index<k; c++)
            for(int r=0; r<m && index<k; r++)
                if(mat[r][c]==0 && (c==0 || mat[r][c-1]==1))
                    result[index++]=r;

        for(int r=0; r<m && index<k; r++)
            if(mat[r][n-1]==1)
                result[index++]=r;
        return result;
    }

    public int[] kWeakestRowsA2(int[][] mat, int k){
        Map<Integer,List<Integer>> map = new HashMap<>();
        int i=0;
        for(int[] row: mat){
            int freq = binarySearch(row);
            if(!map.containsKey(freq))
                map.put(freq, new ArrayList<>());
            map.get(freq).add(i++);
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        i=0;
        int[] result = new int[k];
        for(int j=0; j<keys.size(); j++){
            for(int item : map.get(keys.get(j)))
                if(i<k)result[i++]=item;
                else break;
            if(i>=k)break;
        }
        return result;
    }

    public int binarySearch(int[] row){
        int start=0, end=row.length;
        while(start<end){
            int mid = start+(end-start)/2;
            if(row[mid]==1)
                start=mid+1;
            else end=mid;
        }
        return start;
    }

    public int[] kWeakestRowsA1(int[][] mat, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[0]==b[0])return b[1]-a[1];
            else return b[0]-a[0];
        });
        int[] res = new int[k];
        for(int j=0; j<mat.length; j++){
            int[] row = mat[j];
            int sum=0;
            for(int i : row)
                if(i==1)sum++;
                else break;
            if(pq.size()>=k){
                if(sum< pq.peek()[0] || (sum ==pq.peek()[0] && j <pq.peek()[1])){
                    pq.poll();
                    pq.add(new int[]{sum,j});
                }
            }else pq.add(new int[]{sum,j});
        }
        for(int i=k-1; i>-1; i--)res[i] = pq.poll()[1];
        return res;
    }
}