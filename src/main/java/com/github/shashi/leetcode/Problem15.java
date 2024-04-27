package com.github.shashi.leetcode;
import java.util.*;
public class Problem15 {

    /*
        15. 3Sum
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.

        Example 1:

        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation:
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.
        Example 2:

        Input: nums = [0,1,1]
        Output: []
        Explanation: The only possible triplet does not sum up to 0.

        Constraints:

            3 <= nums.length <= 3000
            -105 <= nums[i] <= 105

       Approach 1:
            * intuition is to sort the array and use the two sum approach to get the remaining elements
            * we need to remember that the triplets should not be same, since the array is sorted, same
            elements will appear one after the another so we should skip if element is same as previous
            and also when element > 0 since sum can not be 0 after that
            * we can use the window technique to find the other two elements since the array is sorted
            * when we found the other two elements  add those elements since duplicates are not allowed
             update the bounds and check if the next element is same as current if so skip that element
            algo:
             * sort the input array, create empty list to hold the result
             * iterate from i=0 to n until nums[i]<=0 since when nums[i]>0 then total sum can not become 0
             as the array is sorted
             * call the 2 sum method which takes i, nums and result list
             * in 2 sum method we have j=i+1 and k=n-1
             * iterate until j<k , get the sum for i,j,k
             * if sum is zero then add the triplet nums[i] nums[j] nums[k] to list, inc j and dec k
             * after adding check if j-1 and jth elements are same if so keep inc j
             * if sum > 0 then dec k else inc j
            time & space:
            time is n log n for sort amd we use two sum for all values of n hence it is n2 overall,
            space is n or nlogn based on sorting

       Approach 2:
            * intuition is that we can solve this problem without sorting the array
            * to avoid the duplication at the first level we can use the set to track the duplicates and
            ignore if they are already seen.
            * then while finding the remaining 2 elements we can solve using the twosum with hashset
            algo:
            * have a set to hold the result we are using set to avoid duplicates, seen set to capture
            processed elements
            * iterate from i=0 to n , when nums[i] not in seen call two sum with result set, i and nums
            * in two sum have set to track the visited elements,
            * iterate from j=i+1 to end and if we find  -(nums[i]+nums[j]) in set that means these
            are the triplets and create a list with these and sort it(to avoid duplicates) and add to result.
            * add the visited element to set and repeat for all values
            * at the end we need to return the list as answer
     */

    public static void main(String[] args) {
        System.out.println("shashi");
    }
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumA1(nums);
    }

    public List<List<Integer>> threeSumA2(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> list = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            if(!set.contains(nums[i]))twoSumA2(list,nums,i);
        }
        return new ArrayList<>(list);
    }

    public void twoSumA2(Set<List<Integer>> list,int[] nums,int i){
        Set<Integer> set = new HashSet<>();
        for(int j=i+1; j<nums.length;j++){
            int sum = nums[i]+nums[j];
            if(set.contains(-sum)){
                List<Integer> res = Arrays.asList(nums[i],nums[j],-sum);
                Collections.sort(res);
                list.add(res);
            }
            set.add(nums[j]);
        }
    }


    public List<List<Integer>> threeSumA1(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<nums.length && nums[i]<=0; i++){
            if(i>0 && nums[i-1] == nums[i])continue;
            twoSum(nums,i,result);
        }
        return result;
    }

    public void twoSum(int[] nums, int i, List<List<Integer>> result){
        int l=i+1,h=nums.length-1;
        while(l<h){
            int sum = nums[i]+nums[l]+nums[h];
            if(sum==0) {
                result.add(Arrays.asList(nums[i],nums[l++],nums[h--]));
                while(l<h& nums[l]==nums[l-1])l++;
            }else if(sum>0)h--;
            else l++;
        }
    }
}
