package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem226 {
    /*
    Invert Binary Tree
    Given the root of a binary tree, invert the tree, and return its root.

    Example 1:
    Input: root = [4,2,7,1,3,6,9]
    Output: [4,7,2,9,6,3,1]
    Example 2:
    Input: root = [2,1,3]
    Output: [2,3,1]
    Example 3:
    Input: root = []
    Output: []
    Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

    Approach 1: recursion
    * intuition is to swap the child nodes of root and call the recursion on left and right child
    algo:
    * base case is to check if root is null and return the root
    * have temp = root.left, make root.left = root.right and root.right = temp
    * call rec(root.left) and rec(root.right)
    * return root;
    time & space:
    * n time and n space

    Approach 2: iteration
    * intuition is to use the queue add root to queue, visit each node swap the child nodes and add the child nodes
    to queue
    algo:
    * create a queue of type tree node, if root is null return it else add to queue
    * iterate until queue is empty
    * poll the node, connect left child to right and right to left, add the child nodes if they are not null
    * return root at the end
    time & space:
    * n time and n space
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

    public TreeNode invertTree(TreeNode root) {
        return invertTreeA2(root);
    }

    public TreeNode invertTreeA1(TreeNode root) {
        if(root==null)return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTreeA1(root.left);
        invertTreeA1(root.right);
        return root;
    }

    public TreeNode invertTreeA2(TreeNode root) {
        if(root==null)return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if(node.left!=null)queue.offer(node.left);
            if(node.right!=null)queue.offer(node.right);
        }
        return root;
    }
}
