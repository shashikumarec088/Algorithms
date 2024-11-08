package com.github.shashi.leetcode;

public class    Problem27 {
    /*
    Remove Element
    Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
    The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.

    Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the
    following things:

    Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
    The remaining elements of nums are not important as well as the size of nums.
    Return k.

    Example 1:
    Input: nums = [3,2,2,3], val = 3
    Output: 2, nums = [2,2,_,_]
    Explanation: Your function should return k = 2, with the first two elements of nums being 2.
    It does not matter what you leave beyond the returned k (hence they are underscores).
    Example 2:
    Input: nums = [0,1,2,2,3,0,4,2], val = 2
    Output: 5, nums = [0,1,4,0,3,_,_,_]
    Explanation: Your function should return k = 5, with the first five elements of nums containing 0, 0, 1, 3, and 4.
    Note that the five elements can be returned in any order.
    It does not matter what you leave beyond the returned k (hence they are underscores).

    Constraints:
    0 <= nums.length <= 100
    0 <= nums[i] <= 50
    0 <= val <= 100

    Approach 1:
    * intuition is to have 2 pointers i=0, j=n-1 and iterate until i<=j and swap when nums[i] == val, this
    will have less number of swaps
    algo:
    * initialize i=0, j=n-1, iterate until i<=j
    * at each step check if nums[i] == val if so swap with j and dec j
    * else inc i
    * return j+1 at the end
    time & space:
    * takes n time and const extra space

    Approach 2:
    * intuition is to keep shifting the elements to left when the cur element is val, it involves more swaps
    algo:
    * initialize i=0,j=0 iterater until j<n
    * if nums[j] is not val then swap with i and inc i, then inc j
    * return i at the end
    time & space:
    * takes n time and const space
     */
    public int removeElement(int[] nums, int val) {
        return removeElementA2(nums,val);
    }

    public int removeElementA2(int[] nums, int val) {
        int i=0, j=0;
        while(j<nums.length){
            if(nums[j]!=val)
                nums[i++] = nums[j];
            j++;
        }
        return i;
    }

    public int removeElementA1(int[] nums, int val) {
        int i=0, j=nums.length-1;
        while(i<=j){
            if(nums[i]==val){
                nums[i] = nums[j];
                j--;
            }else i++;
        }
        return j+1;
    }
}
