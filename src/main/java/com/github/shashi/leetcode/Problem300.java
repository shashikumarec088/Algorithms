package com.github.shashi.leetcode;
import java.util.*;
public class Problem300 {
    /*
    Longest Increasing Subsequence
    Given an integer array nums, return the length of the longest strictly increasing
    subsequence
    Example 1:
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
    Example 2:
    Input: nums = [0,1,0,3,2,3]
    Output: 4
    Example 3:
    Input: nums = [7,7,7,7,7,7,7]
    Output: 1
    Constraints:
    1 <= nums.length <= 2500
    -104 <= nums[i] <= 104
    Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?

    Approach 1: dynamic programing with tabulation
    * intuition is here we are asked to find the longest increasing sequence ie maximum of something and the decision
    we need to take for current element depends on the previous decisions we made. this lets us know that we can solve
    this problem using the dp array.
    * here for any position the maximum sublength will be maximum among all the previous elements for which the current
    element is > previous element plus 1. ie dp[i] = max(dp[j]+1,dp[i]) for all values of j from 0 to < i such that
    nums[i] > nums[j]
    * at the end max value across the dp array is our answer
    algo:
    * n is number of elements, init dp array of size n of integer type and fill with 1
    * if n <=1 return n
    * set max=1;
    * iterate i=1 to <n and j=0 to < i, if nums[i] > nums[j] make dp[i] = max(dp[i],dp[j]+1)
    * update max after each value of i as max = max(max,dp[i])
    * return max
    time & space:
    * it takes n^2 time and n space

    Approach 2: intelligently building the subsequence
    * intuition is to build the subsequence from empty list and keep adding the elements from the array, consider
    ex 8,1,6,7,2,3,4,5 . initially since list is empty we add 8. when we consider 2nd element list had 8 which is
    > 1 so we need to replace it so that we make a way for considering numbers from 1 to 7
    * next element is 6 which is > 1 so we add it now sublist is 1,6 we do the same for 7 now list is 1,6,7
    * next element is 2 which is < 7 so we need to find the 1st element >= 2 and replace it so we make a way
    for elements betweeb 2 and 7, we replace 6 now the list is 1,2,7
    * next is 3 which is < 7 we will replace the 1st largest element in list with 3 now list becomes 1,2,3
    * next elemetn is 4 > largest element so we add now list is 1,2,3,4. next is 5 we add to list now
    the list is 1,2,3,4,5 so the largest subsequence length is 5.
    * hence the logic is we keep adding the elements to listuntil element is > largest element in list else
    we replace it with first largest element > current element. to find the 1st largest element we do linear scan
    * at the end the size of list is the largest sequence size.
    algo:
    * n is nums array size if <=1 return n, create array list of type integer, add 1st element
    * iterate i=1 to < n, let num = nums[i], check if num > last element in list if so add to list
    * else init j=0 iterate list until we find position where element at position is >= num
    * replace the element with num
    * return the list size at the end
    time & space:
    * it takes n^2 time and n space

    Approach 3: intelligently building the subsequence with binary search
    * intuition is same as approach 2 but instead of using the linear scan to find the position of num in list
    we can use binary search since sublist is sorted
    algo:
    * bost is same as approach 2
    * when num is not greater than last element in list to find the first element in list >= num we use
    binary search
    * init l=0, r=list.size()-1 itertate until l<r
    * mid=(l+r)2, if element at mid is < num make l=mid+1 else r = mid
    * replace element at r with num
    * return list size at the end
    time & space:
    * it takes n log n time and n space
     */
    public int lengthOfLIS(int[] nums) {
        return lengthOfLISA3(nums);
    }

    public int lengthOfLISA3(int[] nums) {
        int n=nums.length,max=0;
        if(n<=1)return n;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<n;i++){
            int num = nums[i];
            if(num>list.get(list.size()-1))
                list.add(num);
            else{
                int l=0,r=list.size()-1;
                while(l<r){
                    int mid = (l+r)/2;
                    if(list.get(mid)< num)l=mid+1;
                    else r=mid;
                }
                list.set(r,num);
            }
        }
        return list.size();
    }

    public int lengthOfLISA2(int[] nums) {
        int n=nums.length,max=0;
        if(n<=1)return n;
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for(int i=1; i<n;i++){
            int num = nums[i];
            if(num>list.get(list.size()-1))
                list.add(num);
            else{
                int j=0;
                while(list.get(j)<num)j++;
                list.set(j,num);
            }
        }
        return list.size();
    }

    public int lengthOfLISA1(int[] nums) {
        int n=nums.length,max=0;
        if(n<=1)return n;
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        for(int i=1; i<n;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
            max = Math.max(max,dp[i]);
        }
        return max;
    }
}
