package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem114 {
    /*
    Flatten Binary Tree to Linked List
    Given the root of a binary tree, flatten the tree into a "linked list":

    The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list
    and the left child pointer is always null.
    The "linked list" should be in the same order as a pre-order traversal of the binary tree.

    Example 1:
    Input: root = [1,2,5,3,4,null,6]
    Output: [1,null,2,null,3,null,4,null,5,null,6]
    Example 2:
    Input: root = []
    Output: []
    Example 3:
    Input: root = [0]
    Output: [0]
    Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100
    Follow up: Can you flatten the tree in-place (with O(1) extra space)?

    Approach 1: recursion
    * intuition is to flatten the left and right subtree and then if the left subtree exists then connect the left tail
    to right subtree and make the left subtree as root right subtree and return right tail if exists else the left tail
    algo:
    * recursive function which returns the tree node check if root is null then returns null
    * if root has no left and right then return root
    * get the left tail by recursively calling the rec on left subtree
    * get the right tail by recursively calling the rec on right subtree
    * if left tail is not null, then connect tail.right = root.right
    * make root.right = root.left and make root.left = null
    * return right tail if not null else left tail
    time & space:
    * takes n time and n space

    Approach 2: iterative implementation using moris traversal
    * intuition is to use the moris traversal when the there is no left node then we make root as right
    * for each left node we find the rightmost node and connect it to root.right and make root.right
    as root.left and then make root.left as null then move towards right
    algo:
    * iterate until root is null, if root has no left then make root = root.right
    * else take the left = root.left and move until left.right is null
    * then connect the left.right to root.right, then make root.right =root.left
    * make root.left = null and mave root to root.right
    time & space:
    * n time and 1 space

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

    public void flatten(TreeNode root) {
        flattenA2(root);
    }

    public void flattenA1(TreeNode root) {
        rec(root);
    }

    public void flattenA2(TreeNode root) {
        while(root !=null){
            if(root.left==null)
                root = root.right;
            else{
                TreeNode rightMost = root.left;
                while(rightMost.right !=null)
                    rightMost = rightMost.right;
                rightMost.right = root.right;
                root.right = root.left;
                root.left=null;
                root = root.right;
            }
        }
    }



    public TreeNode rec(TreeNode root){
        if(root==null)return null;
        if(root.left==null && root.right==null)return root;
        TreeNode lt = rec(root.left);
        TreeNode rt = rec(root.right);
        if(lt!=null){
            lt.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rt==null?lt:rt;
    }
}
