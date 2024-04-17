package com.github.shashi.leetcode;

public class Problem238 {
    public int[] productExceptSelf(int[] nums) {
        return productExceptSelfA2(nums);
    }

    /*
       intuition is to calculate the left product and
       right product in an sperate arrays and
       compute the product using these 2 arrays
   */
    public int[] productExceptSelfA2(int[] nums) {
        int n = nums.length;
        int[] L = new int[n], R = new int[n];
        L[0]=1;
        for(int i=1; i<n;i++)
            L[i] = L[i-1]*nums[i-1];
        R[n-1] = 1;
        for(int i=n-2; i>=0; i--)
            R[i] = R[i+1]*nums[i+1];
        int[] ans = new int[n];
        for(int i=0; i<n; i++){
            ans[i] = L[i]*R[i];
        }
        return ans;
    }

    /*
        intuition is to first build the product
        from the end in the new array, then
        build the product from start and multiply
        with the end
    */
    public int[] productExceptSelfA1(int[] nums) {
        int n=nums.length, p=1;
        int[] nums1 = new int[n];
        for(int i=n-1; i>=0; i--){
            nums1[i]=p;
            p = p*nums[i];
        }
        p=1;
        for(int i=0; i<n; i++){
            nums1[i]=nums1[i]*p;
            p = p*nums[i];
        }
        return nums1;
    }
}