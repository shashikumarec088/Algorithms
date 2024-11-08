package com.github.shashi.leetcode;
import java.util.*;
public class Problem42 {
    /*
    Given n non-negative integers representing an elevation map where the width of each bar is 1,
    compute how much water it can trap after raining.

    Example 1:

    Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6
    Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
    In this case, 6 units of rain water (blue section) are being trapped.
    Example 2:
    Input: height = [4,2,0,3,2,5]
    Output: 9

    Constraints:
    n == height.length
    1 <= n <= 2 * 104
    0 <= height[i] <= 105

    Approach 1: Brute force
    * intuition is to calculate the water trapped at each index by finding the max height on left and right side
    algo:
    * init sum=0, n=height.length
    * iterate from i=0 to n-1
    * find maxLeft=0, maxRight=0
    * iterate from j=i to 0 find maxLeft=max(maxLeft,height[j])
    * iterate from j=i to n find maxRight=max(maxRight,height[j])
    * sum+=min(maxLeft,maxRight)-height[i]
    * return sum
    time & space:
    * it takes O(n^2) time and O(1) space

    Approach 2: DP
    * intuition is to store the max height on left and right side of the current index, rest of the logic
    remains same
    algo:
    * init sum=0, n=height.length, lmax=0
    * init rmaxArr of size n and rmaxArr[n-1]=height[n-1]
    * iterate from i=n-2 to 0 rmaxArr[i]=max(height[i],rmaxArr[i+1])
    * iterate from i=0 to n
    * lmax=max(lmax,height[i])
    * rmax=rmaxArr[i]
    * sum+=min(rmax,lmax)-height[i]
    * return sum
    time & space:
    * it takes O(n) time and O(n) space

    Approach 3: Stack bases approach
    * intuition is to use the stack to store the indices of the elements in decreasing order of height
    * if we find the element greater than the top of the stack then we can calculate the water trapped
    * by finding the distance between the current element and the top of the stack and the height will be
    * min of the current element and the top of the stack minus the height of the top of the stack
    algo:
    * init sum=0, n=height.length, cur=0
    * init stack of integers to store the indices
    * iterate until cur<n
    * iterate until stack is not empty and height[stack.peek()]<height[cur]
    * pop the top of the stack and calculate the distance and height and add to the sum
    * push the current index to the stack
    * return sum
    time & space:
    * it takes O(n) time and O(n) space

    Approach 4: Two pointer
    * intuition is to use the two pointer approach to find the water trapped at each index
    * we will have two pointers l=0, r=n-1, lmax=0, rmax=0
    * if height[l]<height[r] then we will calculate the water trapped at l and move l to right
    * else we will calculate the water trapped at r and move r to left
    algo:
    * init sum=0, n=height.length, l=0, r=n-1, lmax=0, rmax=0
    * iterate until l<r
    * if height[l]<height[r] then
    * lmax=max(lmax,height[l])
    * sum+=lmax-height[l++]
    * else
    * rmax=max(rmax,height[r])
    * sum+=rmax-height[r--]
    * return sum
    time & space:
    * it takes O(n) time and O(1) space

     */
    public int trap(int[] height) {
        return trapA3(height);
    }

    public int trapA3(int[] height) {
        int sum=0,n=height.length,cur=0;
        Stack<Integer> stack = new Stack<>();
        while(cur<n){
            while(!stack.isEmpty() && height[stack.peek()]<height[cur]){
                int top = stack.pop();
                if(stack.isEmpty())break;
                int dist = cur - stack.peek()-1;
                sum+= dist * (Math.min(height[stack.peek()],height[cur])-height[top]);
            }
            stack.push(cur++);
        }
        return sum;
    }

    public int trapA4(int[] height) {
        int sum=0,n=height.length,l=0,r=height.length-1,lmax=0,rmax=0;
        while(l<r){
            if(height[l]<height[r]){
                lmax=Math.max(lmax,height[l]);
                sum+= lmax-height[l++];
            }else{
                rmax=Math.max(rmax,height[r]);
                sum+=rmax-height[r--];
            }
        }
        return sum;
    }

    public int trapA2(int[] height) {
        int sum=0,n=height.length,lmax=0;
        int[] rmaxArr = new int[n];
        rmaxArr[n-1]=height[n-1];
        for(int i=n-2;i>=0;i--)
            rmaxArr[i]=Math.max(height[i],rmaxArr[i+1]);
        for(int i=0; i<n;i++){
            lmax=Math.max(height[i],lmax);
            int rmax=rmaxArr[i];
            sum+= Math.min(rmax,lmax)-height[i];
        }
        return sum;
    }

    public int trapA1(int[] height) {
        int sum = 0, n = height.length;
        for (int i = 0; i < n; i++) {
            int lmax = 0, rmax = 0;
            for (int j = i; j >= 0; j--)
                lmax = Math.max(lmax, height[j]);
            for (int j = i; j < n; j++)
                rmax = Math.max(rmax, height[j]);
            sum += Math.min(rmax, lmax) - height[i];
        }
        return sum;
    }
}
