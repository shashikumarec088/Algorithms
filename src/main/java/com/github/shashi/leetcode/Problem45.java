package com.github.shashi.leetcode;

public class Problem45 {
    /*
    5. Jump Game II
    You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

    Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at
    nums[i], you can jump to any nums[i + j] where:
    0 <= j <= nums[i] and
    i + j < n
    Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach
    nums[n - 1].
    Example 1:
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3
    steps to the last index.
    Example 2:

    Input: nums = [2,3,0,1,4]
    Output: 2
    Constraints:
    1 <= nums.length <= 104
    0 <= nums[i] <= 1000
    It's guaranteed that you can reach nums[n - 1].

    Approach 1: Backtrack TLE
    * intuition is to consider all the possible paths that reach end and consider the min jumps path
    among those.
    algo:
    * call rec with nums, pos=0
    * base case is check if pos==n-1 is so return 0 indicating reached end
    * init min=integer.MAX_VALUE
    * iterate i from pos+1 to i<= min(pos+nums[pos],n-1)
    * call rec with nums and i if ans is not integer.MAX_VALUE
    * then update min with ans if ans<min
    * return min at the end
    time & space:
    * it takes 2^n time and n space

    Approach 2: recursion with memoization
    * intuition is same as approach 1, if we observe the first approach we see that there are many repeated
    calculations which can be avoided by storing the already computed results and reusing them.
    algo:
    * init global variable of type Integer array memo
    * base case is check if pos==n-1 is so return 0 indicating reached end
    * if memo[pos] is not null then return memo[i]
    * iterate i from pos+1 to i<= min(pos+nums[pos],n-1)
    * call rec with nums and i if ans is not integer.MAX_VALUE
    * then update min with ans if ans<min
    * make memo[pos]=min
    * return memo[pos]
    time & space:
    * it takes n^2 time and n space

    Approach 3: bottom up DP
    * intuition is same as approach 2, but using bottom up, we build the solution from start to end
    algo:
    * init n as number of elements , dp array of ints of size n
    * iterate from i=1 to i<n and init min=Integer.MAX_VALUE
    * iterate j=0 to j<i
    * check if dp[j] not Integer.MAX_VALUE and j+nums[j]>=i then update min
    * make dp[i]=min
    * return dp[n-1] at the end
    time & space:
    * it takes n^2 time and n space

    Approach 4: bottom up approach from end
    * intuition is same as approach 3 but instead of building the solution from start we build it
    from the end.
    algo:
    * init n as number of elements , dp array of ints of size n
    * iterate from i=n-2 to i>=0
    * init min=Integer.MAX_VALUE;
    * iterate j=i+1 to <=min(n-1,i+nums[i])
    * if dp[j]!=Integer.MAX_VALUE then update min with min of min,dp[j]+1
    * return dp[0]
    time & space:
    * it takes n^2 time and n space

    Approach 5: greedy approach
    * intuition is to consider the min jumps need to reach the current position, this we do it by periodically
    incrementing the jumps and the position we can reach
    * we start from 0th position and we take first jump, we traverse across the positions that can be reached
    and keep updating the next max position that can be reached, once we reach the end of the positions that
    can be reached with current jump, we increment the jumps and update the reachable positions to next max
    algo:
    * init n, curLimit=0, nextLimit=0, jumps=0
    * iterate i=0 to <n
    * update nextLimit with max of nextLimit and i+nums[i]
    * check if i== curLimit if so increment jumps and make curLimit=nextLimit
    * also check if curLimit>=n-1 if so return jumps
    * return jumps at the end
    time & space:
    * it takes n time and const space

     */
    public int jump(int[] nums) {
        return jumpA5(nums);
    }

    public int jumpA5(int[] nums){
        int n = nums.length,curLimit=0,nextLimit=0,jumps=0;
        if(n==1)return 0;
        for(int i=0; i<n;i++){
            nextLimit=Math.max(nextLimit,i+nums[i]);
            if(curLimit==i){
                jumps++;
                curLimit=nextLimit;
            }
            if(curLimit>=n-1)return jumps;
        }
        return jumps;
    }

    Integer[] memo;

    public int jumpA4(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        for(int i=n-2; i>=0;i--){
            int min=Integer.MAX_VALUE;
            for(int j=i+1; j<=Math.min(n-1,nums[i]+i); j++){
                if(dp[j]!=Integer.MAX_VALUE)
                    min=Math.min(min,dp[j]+1);
            }
            dp[i]=min;
        }
        return dp[0];
    }

    public int jumpA3(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        for(int i=1; i<n;i++){
            int min=Integer.MAX_VALUE;
            for(int j=0; j<i; j++){
                if(dp[j]!=Integer.MAX_VALUE && (j+nums[j])>=i)
                    min=Math.min(min,dp[j]+1);
            }
            dp[i]=min;
        }
        return dp[n-1];
    }

    public int jumapA2(int[] nums){
        memo = new Integer[nums.length];
        return rec2(nums,0);
    }

    public int rec2(int[] nums, int pos){
        if(pos==nums.length-1)return 0;
        if(memo[pos]!=null)return memo[pos];
        int min=Integer.MAX_VALUE;
        for(int i=pos+1; i<= Math.min(pos+nums[pos],nums.length-1);i++){
            int ans = rec2(nums,i);
            if(ans!=Integer.MAX_VALUE)
                min=Math.min(min,ans+1);
        }
        memo[pos]=min;
        return min;
    }

    public int jumapA1(int[] nums){
        return rec(nums,0);
    }

    public int rec(int[] nums, int pos){
        if(pos==nums.length-1)return 0;
        int min=Integer.MAX_VALUE;
        for(int i=pos+1; i<= Math.min(pos+nums[pos],nums.length-1);i++){
            int ans = rec(nums,i);
            if(ans!=Integer.MAX_VALUE)
                min=Math.min(min,ans+1);
        }
        return min;
    }
}