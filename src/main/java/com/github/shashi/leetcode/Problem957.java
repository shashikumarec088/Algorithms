package com.github.shashi.leetcode;

public class Problem957 {
    public static void main(String[] args) {
        Problem957 problem957 = new Problem957();
        int [] cells = {0,1,0,1,1,0,0,1};
        problem957.prisonAfterNDays(cells,7);
        for(int cell : cells) System.out.println(cell);
    }
    public int[] prisonAfterNDays(int[] cells, int n) {
        return prisonAfterNDaysA1(cells, n);
    }

    public int[] prisonAfterNDaysA1(int[] cells, int n){
        int m = cells.length;
        for(int i=0; i<n; i++){
            for(int j=1; j<m-1; j++){
                if(cells[j-1]==cells[j+1])
                    cells[j]=1;
                else cells[j]=0;
            }
            cells[0]=0;
            cells[m-1]=0;
        }
        return cells;
    }
}