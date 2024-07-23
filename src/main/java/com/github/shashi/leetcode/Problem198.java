package com.github.shashi.leetcode;
import java.util.*;
public class Problem198 {
    /*
    House Robber
    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
    the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected
    and it will automatically contact the police if two adjacent houses were broken into on the same night.

    Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
    can rob tonight without alerting the police.

    Example 1:
    Input: nums = [1,2,3,1]
    Output: 4
    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
    Total amount you can rob = 1 + 3 = 4.
    Example 2:
    Input: nums = [2,7,9,3,1]
    Output: 12
    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
    Total amount you can rob = 2 + 9 + 1 = 12.
    Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 400

    Approach 1: recursion with memoization
    * intuition it to try all possible combinations and then use the combinations and then use the combination that
    gives the maximum amount of money to the robber, we can not use the greedy approach because we wont be able
    to decide on weather to consider the current house or not until we try all combinations.
    * at any moment the choice we make is to rob the current house or not, if we choose to rob the current house,
    then we need to skip the next house. otherwise we can evaluate the same choice on the next house it to rob or
    not rob
    * so the maximum amount that can be robbed till the current house will be max mount robbed till i+1 or sum of
    current house amount plus the max amount till i+2. it rob[i] = max(rob[i+1], rob[i+2]+rob[i])
    * if we look at the above recurrence relation there are duplicate calculations  for example if there are 5 houses
    from 0 choices are 1, 2. from 1 choices are 2,3 from 2 choices are 3,4. for coice 2 there is repeatation, this
    can be avoiding by using the cache which stores the maxAmount till that houses.
    * in recursion if i >= n then we return 0 indicating that we reached last, if i is in map then we return value
    in map.
    * we get the max amount at i by calling rec for i+1 & i+2. we put the value into map and return value this
    makes sure that for same value of i from different branches of recursion we return from map instead of recomputing
    algo:
    * init n = nums.length and map of type integer,integer to store the max amount at each index.
    * call rec with index 0 and nums array
    * we check if i >= n if so we return 0
    * we check if i in map if so we return value from map
    * we compute max = max of rec(i+1), rec(i+2)+nums[i]
    * we put the max to map at index i
    * return max
    time & space:
    * it takes n time since we make max n rec calls and repetitive calls are returned from map.
    * it takes n space since we use cache and also from recursion stack

    Approach 2: Dynamic programming ie recursion with tabulation
    * intuition is same as above approach but instead of using the recursion we can use the tabulation, consider
    if we have no houses then ammount is 0 and if we have 1 house then amount if the money at that house, if
    we have 2 houses then max is sum of amoubt at current house plus the max at next to next of current house and
    the max at next house ie rob[i] = MAX(rob[i+1], rob[i+2]+nums[i])
    * we use array to represent the table of size n+1 and value at n will be 0 and at n-1 will be nums[n-1]
    * we iterate from i=n-2 to 0 and at each index value will be MAX(rob[i+1], rob[i+2]+nums[i])
    algo:
    * init n = nums.length, rob is an array of length n+1.
    * init rob[n] = 0, rob[n-1] = nums[n-1]
    * iterate from i=n-2 to i>=0, for each value of i  make rob[i]= MAX(rob[i+1], rob[i+2]+nums[i])
    * return rob[0] at the end
    time & space:
    * it takes n time since we iterate over all values of nums and takes n space since we are using
    array of size n+1

    Approach 3: Dynamic programing with space optimization
    * intuition is same as above approach instead of using the complete array if we can track the next and
    nextnext values then that is enough to compute the cur max and then we can use the same variable to
    compute.
    algo:
    * init nextNext = 0, next = nums[n-1]
    *iterate from i=n-2 to i>=0 cur = max(next,nextNext+nums[i])
    * make nextNext = next, next=cur
    * return next
    time & space:
    * it takes n time and const space
     */
    public int rob(int[] nums) {
        return robA3(nums);
    }

    public int robA3(int[] nums) {
        int n = nums.length;
        int next = nums[n-1], nextNext = 0;
        for(int i=n-2; i>=0; i--){
            int cur=Math.max(next, nextNext+nums[i]);
            nextNext = next;
            next = cur;
        }
        return next;
    }

    public int robA2(int[] nums) {
        int n = nums.length;
        int[] maxRob = new int[n+1];
        maxRob[n] = 0;
        maxRob[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--)
            maxRob[i] = Math.max(nums[i]+maxRob[i+2],maxRob[i+1]);
        return maxRob[0];
    }

    public int robA1(int[] nums) {
        if(nums.length==1)return nums[0];
        return rec(nums,0);
    }
    Map<Integer,Integer> map = new HashMap<>();
    public int rec(int[] nums, int i){
        if(i>=nums.length)return 0;
        if(map.containsKey(i))return map.get(i);
        int ans = Math.max(rec(nums,i+1),rec(nums,i+2)+nums[i]);
        map.put(i,ans);
        return ans;
    }
}
