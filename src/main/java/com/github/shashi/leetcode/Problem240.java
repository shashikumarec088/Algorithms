package com.github.shashi.leetcode;

public class Problem240 {

    public static void main(String[] args) {
        Problem240 problem119 = new Problem240();
        System.out.println(problem119.searchMatrix(null,0));
    }
    public boolean searchM(int[][] m, int target){
        int r = m.length-1,c=0;
        while(r >= 0 && c <m[0].length){
            if(m[r][c]==target)return true;
            if(m[r][c]>target)r--;
            else c++;
        }
        return false;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0) return false;
        int rs = 0, cs =0,re = matrix.length-1,ce=matrix[0].length-1;
        return searchMatrics(matrix,cs,ce,rs,re,target);
    }

    public boolean searchMatrics(int[][] m, int cs, int ce,
                                 int rs, int re, int target){
        if(rs > re || cs >ce) return false;
        int cm = cs +(ce-cs)/2;
        int rm = rs;
        while(rm <=re && m[rm][cm] <= target){
            if(m[rm][cm]==target) return true;
            rm++;
        }
        return searchMatrics(m,cs,cm-1,rm,re,target) ||
                searchMatrics(m,cm+1,ce,rs,rm-1,target);
    }
}
