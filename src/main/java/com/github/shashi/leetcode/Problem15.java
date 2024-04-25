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
            * we can use the window technique to find the other two elements since the array is sorted
            * when we found the other two elements  add those elements since duplicates are not allowed
             update the bounds and check if the next element is same as current if so skip that element

       Approach 2:
            * intuition is that we can solve this problem without sorting the array
            * to avoid the duplication at the first level we can use the set to track the duplicates and
            ignore if they are already seen.
            * then while finding the remaining 2 elements we can solve using the another set, here
            we need to find i,j,k such that there sum is 0, hence we need to check if -sum is
            present in the set where sum = nums[i] + nums[j]
            * to avoid the duplicate triplets we can use the hashSet to store the results and add the
            triplets after sorting so that duplicates are removed
            * then create the list at the end since the expectation is to return the list
     */

    public static void main(String[] args) {
        System.out.println("shashi");
    }
    public List<List<Integer>> threeSum(int[] nums) {
        return threeSumA1(nums);
    }

    public List<List<Integer>> threeSumA2(int[] nums){
        Set<Integer> dup = new HashSet<>();
        int n= nums.length;
        Set<List<Integer>> result = new HashSet<>();
        for(int i=0; i<n; i++){
            if(dup.add(nums[i])){
                Set<Integer> seen = new HashSet<>();
                for(int j=i+1; j<n; j++){
                    int sum = nums[i]+nums[j];
                    if(seen.contains(-sum)){
                        List<Integer> list = Arrays.asList(nums[i],nums[j],-sum);
                        Collections.sort(list);
                        result.add(list);
                    }
                    seen.add(nums[j]);
                }
            }
        }
        return new ArrayList<>(result);
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
