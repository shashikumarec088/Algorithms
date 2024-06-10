package com.github.shashi.leetcode;
import java.util.Arrays;
import java.util.PriorityQueue;
public class Problem215 {
    /*
    Kth Largest Element in an Array
    Given an integer array nums and an integer k, return the kth largest element in the array.
    Note that it is the kth largest element in the sorted order, not the kth distinct element.
    Can you solve it without sorting?
    Example 1:
    Input: nums = [3,2,1,5,6,4], k = 2
    Output: 5
    Example 2:
    Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
    Output: 4
    Constraints:
    1 <= k <= nums.length <= 105
    -104 <= nums[i] <= 104

    Approach 1: sorting the array
    * intuition is to sort the array in asc order and return the n-kth element from array
    algo:
    * sort array using arrays.sort method and return nums[n-k]
    time & space:
    * nlogn time and n space depending on sorting algorithm

    Approach 2: using heap
    * intuition is to use heap which always gives min element in const time and inserts in log n time where n is
    the number of elements in heap. keep adding elements to heap when heap has < k elements or element at top of
     the heap is < current element and remove when size goes beyond k.
     algo:
     * create priorityQueue of type Integer, iterate over all elements in nums
     * for each element check if heap size is < k or top element is < num if so add to heap
     * if heap size is > k then remove top element from heap
     * return top element from heap
     time & space:
     * nlog k time and k space

     Approach 3: counting sort
     * intuition is to use the counts array to capture the elements freq and iterate from end with initial value
     as k and keep subtracking freq from k at each element from counts array when k <=0 then that is the target.
     since problem states it can have -ve numbers to handle this we need to find the min and max in array and create
     array of size max-min+1.
     algo:
     * init min with Integer.MAX_VALUE and max with Integer.MIN_VALUE
     * iterate over the input array and update min if we find lesser value and max if we find greater value
     * create counts array of size max-min+1
     * iterate over input and inc counts[num-min]
     * iterate over counts from end sub k with counts[i] if k <=0 then return i+min
     * return -1 at the end default case
     time & space:
     * n time and n space
     */
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        Problem215 problem215 = new Problem215();
        System.out.println(problem215.findKthLargest(nums,2));
    }
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestA1(nums,k);
    }

    public int findKthLargestA1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length-k];
    }

    public int findKthLargestA2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int num : nums){
            if(queue.size()<k || queue.peek()<num)queue.offer(num);
            if(queue.size()>k)queue.poll();
        }
        return queue.peek();
    }

    public int findKthLargestA3(int[] nums, int k) {
        int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(int num: nums){
            min = Math.min(num,min);
            max = Math.max(num,max);
        }
        int[] counts = new int[max-min+1];
        for(int num:nums)
            counts[num-min]++;
        for(int i=counts.length-1; i>=0;i--){
            k-= counts[i];
            if(k<=0)return i+min;
        }
        return -1;
    }
}