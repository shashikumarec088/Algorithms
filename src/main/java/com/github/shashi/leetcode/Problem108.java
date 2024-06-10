package com.github.shashi.leetcode;

import java.util.Random;

public class Problem108 {
    /*
    Convert Sorted Array to Binary Search Tree
    Given an integer array nums where the elements are sorted in ascending order, convert it to a
    height-balanced
    binary search tree.
    Example 1:
    Input: nums = [-10,-3,0,5,9]
    Output: [0,-3,9,-10,null,5]
    Explanation: [0,-10,5,null,-3,null,9] is also accepted:
    Example 2:
    Input: nums = [1,3]
    Output: [3,1]
    Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
    Constraints:
    1 <= nums.length <= 104
    -104 <= nums[i] <= 104
    nums is sorted in a strictly increasing order.

    approach 1:
    * intuition is to use the fact that if the array is sorted then it represents the inorder traversal of
    the BST, we can consider the mid as root and recursively build the left and right subtree
    algo:
    * call the rec function with nums, left=0. right=n-1 where n is length of nums
    * calculate mid = (l+r)/2 and create root with value at mid, call rec with l,mid-1 to form the left subtree
    * and rec with mid+1 right to form right subtree, then return root at the end.
    time & space:
    * takes n time and log n space

    Approach 2:
    * same as approach 1 but instead of choosing the left for even length nums we can choose right element using
    the condition (l+r)%2==1 then mid++

    Approach 3:
    * same as approach 1 but instead of choosing the left we can choose randomly with condition (l+r)%2==1 then
    mid+= rand.nextInt(2).
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTA3(nums);
    }

    public TreeNode sortedArrayToBSTA3(int[] nums) {
        return rec3(nums,0,nums.length-1);
    }

    Random rand = new Random();
    public TreeNode rec3(int[] nums, int l, int r){
        if(l>r)return null;
        int mid = (l+r)/2;
        if((l+r)%2==1)mid+=rand.nextInt(2);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = rec3(nums,l,mid-1);
        root.right = rec3(nums,mid+1,r);
        return root;
    }

    public TreeNode sortedArrayToBSTA2(int[] nums) {
        return rec2(nums,0,nums.length-1);
    }


    public TreeNode rec2(int[] nums, int l, int r){
        if(l>r)return null;
        int mid = (l+r)/2;
        if((l+r)%2==1)mid++;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = rec2(nums,l,mid-1);
        root.right = rec2(nums,mid+1,r);
        return root;
    }

    public TreeNode sortedArrayToBSTA1(int[] nums) {
        return rec1(nums,0,nums.length-1);
    }


    public TreeNode rec1(int[] nums, int l, int r){
        if(l>r)return null;
        int mid = (l+r)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = rec1(nums,l,mid-1);
        root.right = rec1(nums,mid+1,r);
        return root;
    }
}
