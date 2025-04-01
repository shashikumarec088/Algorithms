package com.github.shashi.leetcode;

import java.util.*;

public class Problem2215 {
    /*
    2215. Find the Difference of Two Arrays

    Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

    answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
    answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
    Note that the integers in the lists may be returned in any order.



    Example 1:

    Input: nums1 = [1,2,3], nums2 = [2,4,6]
    Output: [[1,3],[4,6]]
    Explanation:
    For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are
    not present in nums2. Therefore, answer[0] = [1,3].
    For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not
    present in nums2. Therefore, answer[1] = [4,6].
    Example 2:

    Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
    Output: [[3],[]]
    Explanation:
    For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is
    only included once and answer[0] = [3].
    Every integer in nums2 is present in nums1. Therefore, answer[1] = [].


    Constraints:

    1 <= nums1.length, nums2.length <= 1000
    -1000 <= nums1[i], nums2[i] <= 1000

    Approach 1: using hashset
    * intuition is to store the elements in set and add to result if not present in set
    algo:
    * create a method which takes list1, list2 and returns elements from list1 not in list2
    * init haset of type integer and add all elements of list2 to it
    * iterate over the list1
        * if element is not present in set then add it to result
    * return the result
    * call the method twice with list1 and list2 and list2 and list1
    * return the result
    time & space:
    * O(n) time and space
     */

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        return findDifferenceA1(nums1, nums2);
    }

    public List<List<Integer>> findDifferenceA1(int[] nums1, int[] nums2) {
        return Arrays.asList(findDifferenceBetween(nums1, nums2),
                findDifferenceBetween(nums2, nums1));
    }

    private List<Integer> findDifferenceBetween(int[] nums1, int[] nums2) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for(int num: nums2)
            set.add(num);
        for(int num: nums1){
            if(!set.contains(num))
                res.add(num);
        }
        return new ArrayList<>(res);
    }
}
