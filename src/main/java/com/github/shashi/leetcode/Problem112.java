package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Problem112 {
    /*
    Path Sum
    Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that
    adding up all the values along the path equals targetSum.

    A leaf is a node with no children.
    Example 1:
    Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
    Output: true
    Explanation: The root-to-leaf path with the target sum is shown.
    Example 2:
    Input: root = [1,2,3], targetSum = 5
    Output: false
    Explanation: There two root-to-leaf paths in the tree:
    (1 --> 2): The sum is 3.
    (1 --> 3): The sum is 4.
    There is no root-to-leaf path with sum = 5.
    Example 3:
    Input: root = [], targetSum = 0
    Output: false
    Explanation: Since the tree is empty, there are no root-to-leaf paths.
    Constraints:
    The number of nodes in the tree is in the range [0, 5000].
    -1000 <= Node.val <= 1000
    -1000 <= targetSum <= 1000

    Approach 1: recursion
    * intuition is to check if node has no child and tg==root.val if so return true, else traverse on left and right
    tree recursively
    algo:
    * if root is null return false
    * check if root has no children and root.val==target then return true
    * else cal rec function on root.left with updated value as target-root.val or
    * call rec on root.right with updated value as target - root.val
    time & space:
    * n time and n space

    Approach 2: iterative approach using 2 stacks
    * intuition is to have 2 stacks one for nodes and 1 for values and immetate the approach 1
    algo:
    * create stack1 of type nodes and stack2 of type integers
    * push root, target-root.val to stacks respectively
    * iterate until stack is empty
    * pop the top node and value check if node has no children and value is 0 if so return true
    * if node has left then add node.left to stack1 and value-node.left.val to stack2
    * if node has right then add node.right to stack1 and value-node.right.val to stack2
    * return false at the end
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSumA2(root,targetSum);
    }

    public boolean hasPathSumA2(TreeNode root, int tg) {
        if(root==null)return false;
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackVal = new Stack<>();
        stackNode.push(root);
        stackVal.push(tg-root.val);
        while(!stackNode.isEmpty()){
            TreeNode node = stackNode.pop();
            int val = stackVal.pop();
            if(node.left==null && node.right==null && val==0)return true;
            if(node.left!=null){
                stackNode.push(node.left);
                stackVal.push(val-node.left.val);
            }
            if(node.right!=null){
                stackNode.push(node.right);
                stackVal.push(val-node.right.val);
            }
        }
        return false;
    }


    public boolean hasPathSumA1(TreeNode root, int tg) {
        if(root==null)return false;
        if(root.left==null && root.right==null) return root.val==tg;
        return hasPathSumA1(root.left,tg-root.val) ||
                hasPathSumA1(root.right,tg-root.val);
    }
}