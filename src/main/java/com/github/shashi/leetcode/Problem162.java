package com.github.shashi.leetcode;

public class Problem162 {
    /*
    Find Peak Element
    A peak element is an element that is strictly greater than its neighbors.
    Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple
    peaks, return the index to any of the peaks.
    You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly
    greater than a neighbor that is outside the array.
    You must write an algorithm that runs in O(log n) time.
    Example 1:
    Input: nums = [1,2,3,1]
    Output: 2
    Explanation: 3 is a peak element and your function should return the index number 2.
    Example 2:
    Input: nums = [1,2,1,3,5,6,4]
    Output: 5
    Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where
    the peak element is 6.
    Constraints:
    1 <= nums.length <= 1000
    -231 <= nums[i] <= 231 - 1
    nums[i] != nums[i + 1] for all valid i.

    Approach 1: linear scan.
    * intuition is to be aware of the fact given in the problem that nums[i] != nums[i+1] and also for i<0 or >= n
    default value tobe considered is -inf which means element at 0th and n-1th index is considered to higher than
    boundary elements.
    * we will have 3 cases, case 1: when numbers are increasing till end ex: 1,2,3,4,5 in this case our peak will
    always be the last element since numbers are in increasing order and no element satisfies the criteria of cur
    element > prev and next.
    * case 2 when the numbers are decreasing ex: 3,2,1,0 in this case the first element itself will satisfy the criteria
    so we can check with condition nums[i]>nums[i+1] since two neighbors will not be the same any element which
    satisfies this criteria will be the answer
    * case 3 then there is slope in the middle ex: 1,2,3,1 in this case 3rd element satisfies criteria of i>i+1 and
    i > i-1, this also satisfied with the criteria of nums[i] > nums[i+1] because two neighbors will not be same
    hence nums[i] should be greater than nums[i-1], if it was lesser than i-1 then i-1th element should have been
    the answer in the previous iteration of i
    * over wall with the condition nums[i]>nums[i+1] will help us get the right element in case 2 and 3 to cover
    case 1 we will check if we reached end of array if so then end index is the answer
    algo:
    * init n number of elements, iterate i=0 to n-1
    * for each iteration check if element at i is > i+1 then return i
    * return n-1 at the end as case 1
    time & space:
    * it takes n time and const space

    Approach 2: Binary search iteration
    * intuition is to be aware of the fact given in the problem that nums[i] != nums[i+1] and also for i<0 or >= n
    default value tobe considered is -inf which means element at 0th and n-1th index is considered to higher than
    boundary elements, we can consider binary seach with modification in the condition and boundaries, we find
    the middle element and check if it is > than mid+1 if so then our target should be on left side including mid
    else on the right side excluding mid, below explaination tells how it covers 3 cases.
    * if we consider the 3 cases as mentioned in approach 1, case 1, when the seq is increasing then last element
    in the list is the answer, we do binary search since always mid+1 > mid we end with n-1th position and we
    come out of the loop since condition l<h becomes false.
    * in case 2 when sequence is decreasing, then mid condition is always satisfied and h becomes 0 so we come out
    of loop with l=h=0
    *in case 3, we will have 3 sub cases sub 1: when peak lies in left half ex: 1,2,1,2,3,4 in this case mid < mid+1
    but our slope is on the left side, this will get skipped but if we look at the problem statement if there are
    many answer then we can return any of the ans, if we keenly look at the ex last position also the answer because
    it satifies all the conditions, wo we can be sure that if next element of mid is > mid then their will always
    be atleast 1 element on right that satisfies the given constraints hence even if we mis some of the answers
    that are present on the left side we will end up getting one of the answer present in the right part of the
    array.
    * sub case 2: when mid element is > mid+1 then one of the answer should be on the left side of the mid including
    mid ex: 1,2,1,3 here mid=1 and and 2 > 1 so we consider then left part of the array with elements 1,2 then if
    we do the binary search then we end up with l=1,h=1 which is the answer
    * sub case 3:when slope is on the right, when mid < mid+1 this covers this case and explanation is same as
    sub case 1.
    algo:
    * we init l=0, h=n-1 where n is number of elements
    * we iterate until l<r (remember we consider l<=r when no element is left at the end here since we will be
    left with 1 element at the end we need to consider l<r, how will we know which of this case to consider is
    derived by how we are updating the boundaries of l, h if we are making either l or h as mid then we will
    generally consider l<r , if we update l as mid+1 and h as mid-1 then we use l<=h)
    * we find the mid=(l+h)/2,  if nums[mid]>nums[mid+1] we make h=mid else l=mid+1
    * we return l at the end.
    time & space:
    * it takes log n time and const space

    Approach 3: binary searach with recursion
    * intuition is same as approach 2 we implement using recursion
    algo:
    * we call rec with l=0, h=n-1
    * base case is to check if l>=h if so we return l
    * compute mid=(l+h)/2;
    * if nums[mid]>nums[mid+1] then we return rec(nums,l,mid)
    else we return rec(nums,mid+1,h)
    time & space:
    * it takes log n time and log n space for recursion stack
     */
    public int findPeakElement(int[] nums) {
        return findPeakElementA3(nums);
    }

    public int findPeakElementA1(int[] nums) {
        int n=nums.length;
        for(int i=0; i<n-1;i++){
            if( nums[i]>nums[i+1])return i;
        }
        return n-1;
    }

    public int findPeakElementA2(int[] nums) {
        int n=nums.length;
        int l=0,h=n-1;
        while(l<h){
            int mid = (l+h)/2;
            if(nums[mid]>nums[mid+1])h=mid;
            else l=mid+1;
        }
        return l;
    }

    public int findPeakElementA3(int[] nums) {
        return rec(nums,0,nums.length-1);
    }

    public int rec(int[] nums, int l, int r){
        if(l>=r)return l;
        int mid = (l+r)/2;
        if(nums[mid]>nums[mid+1])return rec(nums,l,mid);
        else return rec(nums,mid+1,r);
    }
}