package com.github.shashi.leetcode;

public class Problem605 {
    /*
    605. Can Place Flowers
    You have a long flowerbed in which some of the plots are planted, and some are not. However, flowers cannot be
    planted in adjacent plots.

    Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty, and an integer
    n, return true if n new flowers can be planted in the flowerbed without violating the no-adjacent-flowers rule
    and false otherwise.



    Example 1:

    Input: flowerbed = [1,0,0,0,1], n = 1
    Output: true
    Example 2:

    Input: flowerbed = [1,0,0,0,1], n = 2
    Output: false


    Constraints:

    1 <= flowerbed.length <= 2 * 104
    flowerbed[i] is 0 or 1.
    There are no two adjacent flowers in flowerbed.
    0 <= n <= flowerbed.length

    Approach 1: 1 pass by checking the adjacent elements
    * intuition is to iterate over the array and check if the current element is 0 and previous and next element is 0
    if so count is as valid position and decrement n and update the current element to 1 to avoid planting in adjacent
    * first and last position we need to only check only 1 adjacent element
    algo:
    * iterate over the array from 0 to n-1
    * left = i is 0 or nums[i-1] is 0
    * right = i is n-1 or nums[i+1] is 0
    * if left and right are 0 then decrement n and update nums[i] to 1
    * if n <=0 then return true
    * return n <=0 at the end
    time & space:
    * n time and 1 space
     */

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        return canPlaceFlowersA1(flowerbed,n);
    }

    public boolean canPlaceFlowersA1(int[] nums, int n) {
        for(int i=0; i<nums.length && n>0;i++){
            if(nums[i]==1)continue;
            boolean left = (i==0) || nums[i-1] == 0;
            boolean right = (i== nums.length-1) || (nums[i+1]==0);
            if(left && right){
                n--;
                nums[i]=1;
            }
        }
        return n==0;
    }


}
