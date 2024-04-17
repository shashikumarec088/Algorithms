package com.github.shashi.leetcode;

public class Problem11 {
    public int maxArea(int[] height) {
        return maxAreaA2(height);
    }

    /*
        intuition is to have the maximum width
        possible and keep reducing the width based on
        the height of both the sides
    */
    public int maxAreaA2(int[] height) {
        int n = height.length, area=0;
        int i=0, j=n-1;
        while(i<j){
            area = Math.max(area, Math.min(height[i],height[j])*(j-i));
            if(height[i]<height[j])i++;
            else j--;
        }
        return area;
    }

    /*
        intuition is to check all the containers and
        then update the one with max value, we get
        TLE since we check all combinations
    */
    public int maxAreaA1(int[] height) {
        int n = height.length, area=0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                area = Math.max(area, Math.min(height[i],height[j])*(j-i));
            }
        }
        return area;
    }
}