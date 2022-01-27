package com.github.shashi.leetcode;

public class Problem11 {
    public int maxArea(int[] height) {
        return maxAreaSolPointer(height);
    }

    public int maxAreaSol(int[] heights){
        int n = heights.length, area =0;
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                int h = Math.min(heights[i],heights[j]);
                area = Math.max(area,(i-j)*h);
            }
        }
        return area;
    }

    public int maxAreaSolPointer(int[] heights){
        int i=0,j=heights.length-1,area=0;
        while(i<j){
            int height = Math.min(heights[i],heights[j]);
            area = Math.max(area,height*(j-i));
            if(heights[i]>heights[j])j--;
            else i++;
        }
        return area;
    }
}
