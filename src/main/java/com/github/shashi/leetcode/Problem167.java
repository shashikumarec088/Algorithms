package com.github.shashi.leetcode;

public class Problem167 {
    /*
            167. Two Sum II - Input Array Is Sorted

            Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order
            , find two numbers such that they add up to a specific target number. Let these two numbers
             be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

            Return the indices of the two numbers, index1 and index2, added by one as an integer
             array [index1, index2] of length 2.

            The tests are generated such that there is exactly one solution. You may not use the same
             element twice.

            Your solution must use only constant extra space.

            Example 1:

            Input: numbers = [2,7,11,15], target = 9
            Output: [1,2]
            Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

            Constraints:

            2 <= numbers.length <= 3 * 104
            -1000 <= numbers[i] <= 1000
            numbers is sorted in non-decreasing order.
            -1000 <= target <= 1000
            The tests are generated such that there is exactly one solution.


            approach 1:
            * intuition is to use the 2 pointer technique
            * have i=0 and j=n-1 and check the sum if greater then dec j, if lesser inc i else return
            the target

            approach 2:
            * intuition is since the array is sorted we can use the modified bs to reduce
            the search space when possible
            * compute the mid = (l+r)/2 , sum = l+r   if sum is target then return, else
            we can optimize the search space, when sum < target -> if mid+r < target then l=mid+1 else l+1
            * when sum > target -> if mid + l > target then r = mid-1 else r-1
            * note we should use l+1 and r-1 in the code using l++ and r-- inside assignment is
            not right
     */
    public int[] twoSum(int[] numbers, int target) {
        return twoSumA2(numbers, target);
    }

    public int[] twoSumA1(int[] nums, int target) {
        int i=0, j=nums.length-1;
        while(i<j){
            int sum = nums[i]+nums[j];
            if(sum==target)
                return new int[]{i+1,j+1};
            else if(sum<target)i++;
            else j--;
        }
        return new int[]{-1,-1};
    }

    public int[] twoSumA2(int[] nums, int target) {
        int i=0, j=nums.length-1;
        while(i<j){
            int sum = nums[i]+nums[j];
            int mid = (j-i)/2+i;
            if(sum==target)
                return new int[]{i+1,j+1};
            else if(sum<target)
                i = nums[mid]+nums[j]<target?mid+1:i+1;
            else j = nums[mid]+nums[i]>target?mid-1:j-1;
        }
        return new int[]{-1,-1};
    }
}
