package com.github.shashi.leetcode;

public class Problem80 {
    /*
    Remove Duplicates from Sorted Array II
    Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique
    element appears at most twice. The relative order of the elements should be kept the same.

    Since it is impossible to change the length of the array in some languages, you must instead have the result be
    placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates,
    then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first
    k elements.

    Return k after placing the final result in the first k slots of nums.

    Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1)
    extra memory.

    Example 1:

    Input: nums = [1,1,1,2,2,3]
    Output: 5, nums = [1,1,2,2,3,_]
    Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores).
    Example 2:

    Input: nums = [0,0,1,1,1,1,2,3,3]
    Output: 7, nums = [0,0,1,1,2,3,3,_,_]
    Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3
    respectively.
    It does not matter what you leave beyond the returned k (hence they are underscores).

    Constraints:
    1 <= nums.length <= 3 * 104
    -104 <= nums[i] <= 104
    nums is sorted in non-decreasing order.

    Approach 1:
    * intuition is to keep copying when they are different, when they are same and duplicates count is within allowed
    limit then copy element and update the count, else keep incrementing the count and move to next element
    algo:
    * initialize i=0, j=1, count=0;
    * if nums[i] != nums[j] then copy nums[++i] = nums[j++] and reset count as we are copying new element
    * if nums[i] == nums[j] and count == 0 then copy and inc count
    * else inc count and j
    time & space:
    * n time and const space
    Approach 2:
    * intuition is to start from second element and copy the current element and check if cur element is different
    from prev second element if so increment i else continue
    algo:
    * initialize i=2 iterate j from 2 to n
    * copy value at j to i
    * check if value at nums[i] is different from nums[j] is so inc i else continue
    * return i at the end
    time & space:
    * n time and constant space
     */
    public int removeDuplicates(int[] nums) {
        return removeDuplicatesA2(nums);
    }

    /*
    inspired from leetcode comments, idia is to
    start from 2nd index if curent element is same
    compared to previous end element then this should
    be replaced else we need to increment i and proceed
    */
    public int removeDuplicatesA2(int[] nums){
        int i=2;
        for(int j=2; j< nums.length;j++){
            nums[i]=nums[j];
            if(nums[i]!=nums[i-2])i++;
        }
        return i;
    }
    public int removeDuplicatesA1(int[] nums) {
        int i=0, j=1, count=0,n=nums.length;
        while(j<n){
            if(nums[i]!=nums[j]){nums[++i]=nums[j++];
                count=0;
            }
            else if(count<1){
                nums[++i]=nums[j++];
                count++;
            }else{
                count++;
                j++;
            }
        }
        return i+1;
    }
}