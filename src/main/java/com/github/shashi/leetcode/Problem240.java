package com.github.shashi.leetcode;

public class Problem240 {

    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0) return false;
        int rs = 0, cs =0,re = matrix.length-1,ce=matrix[0].length-1;
        return searchSpaceRed(matrix,target);
    }

    public boolean findM(int[][] m, int t){
        int rs=0,re=m.length-1,cs=0,ce=m[0].length-1;
        return rec(m,rs,re,cs,ce,t);
    }

    public boolean rec(int[][] m, int rs, int re, int cs, int ce, int t){
        if(rs>re|| cs>ce) return false;
        int mc = cs+(ce-cs)/2;
        int mr = rs;
        while(mr <=re && m[mr][mc] <=t){
            if(m[mr][mc]==t)return true;
            mr++;
        }
        return rec(m,rs,mr-1,mc+1,ce,t) ||rec(m,mr,re,cs,mc-1,t);
    }

    public boolean bruteForce(int[][]m, int t){
        if(m==null || m.length==0)return false;
        for(int i=0; i<m.length;i++){
            for(int j=0; j<m[0].length;j++){
                if(m[i][j]==t)return true;
            }
        }
        return false;
    }

    /*
    intuition is since the mat is sorted we can do binary search diagonally downwards

     */
    public boolean binSearch2(int[][]m, int t){
        int n = Math.min(m.length,m[0].length);
        for(int i=0; i<n; i++){
            if(search(m,i,t,true)||
                    search(m,i,t,false))return true;
        }
        return false;
    }
    public boolean search(int[][]m, int start, int t, boolean vertical){
        int low = start;
        int high = vertical?m[0].length-1:m.length-1;
        while(high>= low){
            int mid = low+(high-low)/2;
            if(vertical){
                if(m[start][mid]<t)
                    low = mid+1;
                else if(m[start][mid]>t)
                    high = mid-1;
                else return true;
            }else{
                if(m[mid][start]<t)
                    low = mid+1;
                else if(m[mid][start]>t)
                    high = mid-1;
                else return true;
            }
        }
        return false;
    }

    public boolean devideSearch(int[][] m, int t){
        int rs=0,re=m.length-1,cs=0,ce=m[0].length-1;
        return devide(m,rs,re,cs,ce,t);
    }

    /*
    intuition is that since the matrix is sorted if we find the mid element
    such that the elements below mid are lower and above are grater than target
    then we can ignore the top left and bottom right part. one more optimization
    is if the target is < start and end of matrix then it is not in that matrix
     */
    public boolean devide(int[][] m, int rs,int re, int cs, int ce, int t){
        if(rs>re||cs>ce)return false;
        if(t> m[re][ce] || t < m[rs][cs])return false;
        int cm = cs+(ce-cs)/2;
        int rm =rs;
        while(rm <= re && m[rm][cm]<= t){
            if(m[rm][cm]==t)return true;
            rm++;
        }
        // while searching in top right we no need to consider the mid row
        // since all the elements in the that row after cm will be > target
        // similar in bottom left we no need to consider the mid column since
        // all the elements in the column will be greater than target
        return devide(m,rs,rm-1,cm+1,ce,t)||
                devide(m,rm,re,cs,cm-1,t);
    }

    public boolean searchSpaceRed(int[][] m, int t){
        int r=m.length-1, c = m[0].length-1;
        int i= r, j = 0;
        while(i > -1 && j <= c){
            if(m[i][j]<t)j++;
            else if(m[i][j]>t)i--;
            else return true;
        }
        return false;
    }
}
