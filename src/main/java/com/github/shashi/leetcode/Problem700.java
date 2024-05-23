package com.github.shashi.leetcode;

public class Problem700 {
    /*
    Search in a Binary Search Tree
    You are given the root of a binary search tree (BST) and an integer val.
    Find the node in the BST that the node's value equals val and return the subtree rooted with that node.
    If such a node does not exist, return null.

    Example 1:
    Input: root = [4,2,7,1,3], val = 2
    Output: [2,1,3]
    Example 2:
    Input: root = [4,2,7,1,3], val = 5
    Output: []
    Constraints:
    The number of nodes in the tree is in the range [1, 5000].
    1 <= Node.val <= 107
    root is a binary search tree.
    1 <= val <= 107

    Approach 1: recursion
    * intuition is to check if root is null then we return null if it is equal to target then return root
    if it is less than target then we move right side else left side of recursion
    algo:
    * call recursion with root and target, if root is null return null
    * if root == target then return root;
    * if root.val < target.val then call rec with root.right else with root.left
    time & space:
    * log n time and space

    Approach 2: iterative
    * intuition is to start with root and keep search on left side if root.val > target else on right side
    algo:
    * initialize node=root iterate until node is null
    * if node == target then return, if node.val > target make node = node.left else noode =node.right
    * iterate until node is null, return root at the end
    time & space:
    * takes log n time and const space
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
    public TreeNode searchBST(TreeNode root, int val) {
        return searchBSTA2(root,val);
    }

    public TreeNode searchBSTA2(TreeNode root, int val) {
        while(root!=null){
            if(root.val==val)return root;
            if(root.val<val)root=root.right;
            else root=root.left;
        }
        return root;
    }

    public TreeNode searchBSTA1(TreeNode root, int val) {
        if(root==null)return null;
        if(root.val==val)return root;
        if(root.val<val)return searchBSTA1(root.right,val);
        else return searchBSTA1(root.left,val);
    }
}