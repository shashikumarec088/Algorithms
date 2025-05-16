package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem2542 {
    /*
    You are given two 0-indexed integer arrays nums1 and nums2 of equal length n and a positive integer k.
    You must choose a subsequence of indices from nums1 of length k.

    For chosen indices i0, i1, ..., ik - 1, your score is defined as:

    The sum of the selected elements from nums1 multiplied with the minimum of the selected elements from nums2.
    It can defined simply as: (nums1[i0] + nums1[i1] +...+ nums1[ik - 1]) * min(nums2[i0] , nums2[i1], ... ,nums2[ik - 1]).
    Return the maximum possible score.

    A subsequence of indices of an array is a set that can be derived from the set {0, 1, ..., n-1} by deleting
    some or no elements.



    Example 1:

    Input: nums1 = [1,3,3,2], nums2 = [2,1,3,4], k = 3
    Output: 12
    Explanation:
    The four possible subsequence scores are:
    - We choose the indices 0, 1, and 2 with score = (1+3+3) * min(2,1,3) = 7.
    - We choose the indices 0, 1, and 3 with score = (1+3+2) * min(2,1,4) = 6.
    - We choose the indices 0, 2, and 3 with score = (1+3+2) * min(2,3,4) = 12.
    - We choose the indices 1, 2, and 3 with score = (3+3+2) * min(1,3,4) = 8.
    Therefore, we return the max score, which is 12.
    Example 2:

    Input: nums1 = [4,2,3,1,1], nums2 = [7,5,10,9,6], k = 1
    Output: 30
    Explanation:
    Choosing index 2 is optimal: nums1[2] * nums2[2] = 3 * 10 = 30 is the maximum possible score.


    Constraints:

    n == nums1.length == nums2.length
    1 <= n <= 105
    0 <= nums1[i], nums2[j] <= 105
    1 <= k <= n

     Approach 1: intuition is based on the priorityQueue
    * intuition is problem asked to select k numbers from nums1
    such that their sum and product with min value among same positions
    from num2 is maximized
    * to get the minimum values from num2 the elements has to be sorted
    in decreasing order so that the min can be selected such that other
    elements are always greater
    * tomaximize the total score we need to select the maximum k elements
    from the nums1
    * this can be done by using the minheap, which always has k largest
    elements
    * we iterate over nums2 and at each step we calculate the cur score
    and update answer as maximum score we seen

    algo:
    *init 2 dimentional array of size n,2
    * insert nums1, nums2 into it and sort in dec based on nums2
    * create p1 of integer, total=0
    * iterate i=0 to <k then access the element from pairs
        * add it to total
    ans = total * pair[k-1][1]
    * to update the ans
    * iterate i=k to n
        * total += pairs[i][0] - pq.poll()
        * pq.offer(pairs[i][0])
        * ans = max(ans,total*pairs[i][1])
    * return ans

    time & space:
    * it takes n log n time and n space

     */

    public long maxScore(int[] nums1, int[] nums2, int k) {
        return maxScoreA1(nums1,nums2,k);
    }

    public long maxScoreA1(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] pairs = new int[n][2];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k,(a, b)->a-b);
        for(int i=0; i<n; i++){
            pairs[i] = new int[]{nums1[i],nums2[i]};
        }
        Arrays.sort(pairs,(a, b)->b[1]-a[1]);
        long total = 0;
        for(int i=0; i<k;i++){
            total += pairs[i][0];
            pq.offer(pairs[i][0]);
        }
        long ans = total * pairs[k-1][1];
        for(int i=k; i<n;i++){
            total += pairs[i][0] - pq.poll();
            pq.offer(pairs[i][0]);
            ans = Math.max(ans, total * pairs[i][1]);
        }
        return ans;
    }
}
