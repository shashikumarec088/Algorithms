package com.github.shashi.leetcode;

public class Problem55 {
    /*
    55. Jump Game
    You are given an integer array nums. You are initially positioned at the array's first index, and each element
    in the array represents your maximum jump length at that position.
    Return true if you can reach the last index, or false otherwise.

    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
    Example 2:
    Input: nums = [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
    impossible to reach the last index.

    Constraints:
    1 <= nums.length <= 104
    0 <= nums[i] <= 105

    Approach 1: backtracking
    * intuition is to consider all the possible positions starting from 0 to reach the last position.
    algo:
    * call recursion with nums, pos=0
    * base case is check if pos==n-1 if so we reached end return true
    * iterate from i=pos+1 to <= min(pos+nums[pos],n-1)
    * check if rec(nums,i) is true if so return true
    * return false at the end
    time & space:
    * it takes 2^n time and takes n space

    Approach 2: recursion with memoization
    * intuition is same as approach 1, we use memo array to reduce the repeatitive calculations.
    algo:
    * init global array of tye Boolean and size n, call rec with nums, 0
    * base case is check if pos==n-1 if so we reached end return true
    * check if memo[i] is not null if so return memo[i]
    * init found = false, iterate from i=pos+1 to <= min(pos+nums[pos],n-1)
    * check if rec(nums,i) is true if so make found=true and break
    * make memo[pos]=found;
    * return memo[pos]
    time & space:
    * it takes n^2 time and n space

    Approach 3: bottom up DP
    * intuition is same as approach 2 but we build the solution from bottom where we build the solution
    by considering the empty array and building step by step
    algo:
    * init n is size of array, init array of size n, make memo[0]=true
    * we iterate from i=1 to i < n
    * we iterate j=i to j>=0 dec j here we do from i towards zero for optimization
    * we can break once we find the first reachable position
    * check if memo[j] is true and (j+nums[j]) >=i if so set memo[i]=true and break
    * return memo[n-1] at the end
    time & space:
    * it takes n^2 time and n space

    Approach 4: bottom up DP from end to start
    * intuition is similar to approach 3 but we build the solution from end to start, this will help to
    come up with next no extra space solution.
    algo:
    * init n is size of the array, create boolean memo array of size n
    * init memo[n-1]=true
    * iterate i=n-2 to i>=0
    * iterate j=i to <= min(n-1,i+nums[i])
    * if memo[j] is true then we make memo[i]=true and break
    * return memo[0] at the end
    time & space:
    * it takes n^2 time and n space

    Approach 5: no space n time one pass solution
    * intuition is same as approach 4 but if we closely observe the approach 1 for reach iteration of i we
    only depend on the leftmost value of already processed values. we can have one variable to track that
    value and get rid of the extra array
    algo:
    * int n number of elements last=n-1 stores last position
    * iterate from i=n-2 to i>=0
    * if nums[i]+i >= last then make last=i
    * return last==0 at the end
    time & space:
    * n time and const space
     */
    public boolean canJump(int[] nums) {
        return canJumpA5(nums);
    }

    Boolean[] memo;

    public boolean canJumpA5(int[] nums) {
        int n=nums.length;
        int last=n-1;
        for(int i=n-2; i>=0;i--){
            if((nums[i]+i)>=last)last=i;
        }
        return last==0;
    }

    public boolean canJumpA4(int[] nums) {
        int n=nums.length;
        boolean[] memo = new boolean[nums.length];
        memo[n-1]=true;
        for(int i=n-2; i>=0;i--){
            for(int j=i+1; j<=Math.min(n-1,i+nums[i]);j++){
                if(memo[j]){
                    memo[i]=true;
                    break;
                }
            }
        }
        return memo[0];
    }

    public boolean canJumpA3(int[] nums) {
        int n=nums.length;
        boolean[] memo = new boolean[nums.length];
        memo[0]=true;
        for(int i=1; i<n;i++){
            for(int j=i; j>=0;j--){
                if(memo[j] && (j+nums[j])>=i){
                    memo[i]=true;
                    break;
                }
            }
        }
        return memo[n-1];
    }

    public boolean canJumpA2(int[] nums) {
        memo = new Boolean[nums.length];
        return rec2(nums,0);
    }

    public boolean rec2(int[] nums, int pos){
        if(pos>= nums.length-1)return true;
        if(memo[pos]!=null)return memo[pos];
        boolean found=false;
        for(int i=pos+1; i<=Math.min(nums.length-1,pos+nums[pos]);i++)
            if(rec2(nums,i)){
                found=true;
                break;
            }
        memo[pos]=found;
        return memo[pos];
    }

    public boolean canJumpA1(int[] nums) {
        return rec(nums,0);
    }

    public boolean rec(int[] nums, int pos){
        if(pos>= nums.length-1)return true;
        for(int i=pos+1; i<=Math.min(nums.length-1,pos+nums[pos]);i++)
            if(rec(nums,i))return true;
        return false;
    }
}