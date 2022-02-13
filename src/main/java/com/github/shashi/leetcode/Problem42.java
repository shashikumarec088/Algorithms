package com.github.shashi.leetcode;
import java.util.*;
public class Problem42 {
    public int trap(int[] height) {
        return trapA3(height);
    }

    public int trapA3(int[] heights){
        Stack<Integer> stack = new Stack<>();
        int n = heights.length, ans =0;
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && heights[i] > heights[stack.peek()]){
                int topCount = heights[stack.pop()];
                if(stack.isEmpty()) break;
                int distance = i - stack.peek()-1;
                int height = Math.min(heights[i],heights[stack.peek()])
                        - topCount;
                ans += distance * height;
            }
            stack.push(i);
        }
        return ans;
    }

    public int trapA2(int[] heights){
        int ans = 0, n = heights.length;
        int maxLeft = 0, maxRight = 0, l=0, r=n-1;
        while(l<=r){
            if(maxLeft<=maxRight){
                int count = maxLeft - heights[l];
                ans += count >0 ?count:0;
                maxLeft = Math.max(heights[l],maxLeft);
                l++;

            }
            else{
                int count = maxRight - heights[r];
                ans += count>0?count:0;
                maxRight = Math.max(heights[r],maxRight);
                r--;
            }
        }
        return ans;
    }

    public int TrapA1(int[] heights){
        int n = heights.length;
        int[] maxLefts = new int[n];
        int sum = 0,max=0;
        maxLefts[0] = 0;
        for(int i=1; i<n; i++){
            max = Math.max(max,heights[i-1]);
            maxLefts[i]=Math.max(maxLefts[i-1],heights[i-1]);
        }
        max=0;
        for(int i=n-2; i>0; i--){
            max = Math.max(max,heights[i+1]);
            int trapLength = Math.min(maxLefts[i],max) - heights[i];
            sum += trapLength>0?trapLength:0;
        }
        return sum;
    }
}
