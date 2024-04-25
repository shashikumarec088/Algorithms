package com.github.shashi.leetcode;

public class Problem11 {
    /*

        11. Container With Most Water

        You are given an integer array height of length n. There are n vertical lines drawn such
         that the two endpoints of the ith line are (i, 0) and (i, height[i]).

        Find two lines that together with the x-axis form a container, such that the container
        contains the most water.

        Return the maximum amount of water a container can store.

        Notice that you may not slant the container.

        Input: height = [1,8,6,2,5,4,8,3,7]
        Output: 49
        Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
         In this case, the max area of water (blue section) the container can contain is 49.

         Constraints:
            n == height.length
            2 <= n <= 105
            0 <= height[i] <= 104

        Approach 1:
         * intuition is to consider all the possible containers and calculate the amount of water
         in each container and update the most water whenever we see more water
         * iterate the outer loop from 0 to n and inner loop from i+1 to n and calculate water
         as j-i * min(nums[i],nums[j])
         * this is n2 solution but this is the bf approach


       Approach 2:
       * intuition is to use the sliding window technique where u have big window covering entire
       length and reduce the side where the height is lesser since with less height u wont be able
       to capture more volume than you already captured
       * have pointers i=0 and i=n-1, iterate untill i<j and keep updating the volume
         which is area = max(area, (j-i)*min(nums[i],nums[j]))


        */
    public int maxArea(int[] height) {
        return maxAreaA2(height);
    }

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