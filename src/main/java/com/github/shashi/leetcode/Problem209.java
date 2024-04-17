package com.github.shashi.leetcode;

public class Problem209 {

    public static void main(String[] args) {
        Problem209 problem209 = new Problem209();
        int[] nums = {2,3,1,2,4,3};
        problem209.minSubArrayLen( 7,nums);
    }
    public int minSubArrayLen(int target, int[] nums) {
        return minSubArrayLenA2(target, nums);
    }


    /*
      bf optimized further by keeping the sum in the extra array and
      using binary search for finding the end index so o(nlog n )
      is achieved
       */
    public int minSubArrayLenA4(int target, int[] nums) {
        int n = nums.length;
        int sum =0, minLen = Integer.MAX_VALUE;
        int[] sumAll = new int[n];
        sumAll[0] = nums[0];
        for(int i=1; i<n; i++)sumAll[i] = sumAll[i-1]+nums[i];
        for(int i=0; i<n; i++){
            int  s= i, e = n-1;
            while(s<e){
                int mid = s +(e-s)/2;
                int sumCur = sumAll[mid] - sumAll[i]+nums[i];
                if(sumCur < target){
                    s = mid+1;
                }else e = mid;
            }
            if((sumAll[e] - sumAll[i]+nums[i]) >= target)
                minLen = Math.min(minLen, s-i+1);
        }
        return minLen==Integer.MAX_VALUE?0:minLen ;
    }

    /*
   bf optimized by keeping the sum in the extra array so o(n2)
   is achieved
    */
    public int minSubArrayLenA3(int target, int[] nums) {
        int n = nums.length;
        int sum =0, minLen = Integer.MAX_VALUE;
        int[] sumAll = new int[n];
        sumAll[0] = nums[0];
        for(int i=1; i<n; i++)sumAll[i] = sumAll[i-1]+nums[i];
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                sum = sumAll[j] - sumAll[i]+nums[i];
                if(sum >= target){
                    minLen = Math.min(minLen, j-i+1);
                    break;
                }
            }
        }
        return minLen==Integer.MAX_VALUE?0:minLen ;
    }

    /*
    bf approach start from 0 and calculate sub array lengths
    for all lengths till end and update once the target is reached
    repeat it for all values of i
     */
    public int minSubArrayLenA2(int target, int[] nums) {
        int n = nums.length;
        int sum =0, minLen = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                sum = 0;
                for(int k=i; k<= j; k++)
                    sum += nums[k];
                if(sum >= target){
                    minLen = Math.min(minLen, j-i+1);
                    break;
                }
            }
        }
        return minLen==Integer.MAX_VALUE?0:minLen ;
    }

    /*
    intuition is to start from 0 keep incresing j until target is reached,
    then update the ans and inc i until the target is satisfied
     */
    public int minSubArrayLenA1(int target, int[] nums) {
        int i=0, j=0, sum=0,l=0;
        while(i<=j && j< nums.length){
            sum += nums[j];
            while( sum >= target){
                l = l==0?j-i+1:Math.min(l, j-i+1);
                sum -= nums[i];
                i++;
            }
            j++;
        }
        return l;
    }
}