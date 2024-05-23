package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem101 {
    /*
    Symmetric Tree
    Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

    Example 1:
    Input: root = [1,2,2,3,4,4,3]
    Output: true
    Example 2:
    Input: root = [1,2,2,null,3,null,3]
    Output: false
    Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    -100 <= Node.val <= 100

    Follow up: Could you solve it both recursively and iteratively?

    Approach 1: Recursion
    * intuition is to check the left and right subtree, if both are null or same then proceed to checking the
    remaining part else return false;
    algo:
    * call rec function with p=root.left q=root.right
    * in rec base case is to check if p & queue both are null if so return true, if either is null then return false
    * values are not same then return false
    * then rec call on p.left,q.right and p.right,q.left
    time & space:
    takes n time and n space

    Approach 2: iterative
    * intuition is to use the queue to immetate the same approach as in recursion.
    algo:
    * create a queue of TreeNode of type linkedList since it can hold nulls
    * add the root.left and root.right to queue then iterate until queue is empty
    * poll p and q, check if they are null if so continue, if either is null or different then return false
    * else add p.left,q.right,p.right,q.left to queue and repeat until queue is empty
    time & space:
    * takes n time and n space
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean isSymmetric(TreeNode root) {

        return isSymmetricA1(root);
    }

    public boolean isSymmetricA1(TreeNode root) {
        return rec(root,root);
    }

    public boolean isSymmetricA2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> ll = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while(!queue.isEmpty()){
            TreeNode p = queue.poll();
            TreeNode q = queue.poll();
            if(p==null && q==null) continue;
            if(p==null || q==null)return false;
            if(p.val != q.val)return false;
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }

    public boolean rec(TreeNode p, TreeNode q){
        if(p==null && q==null)return true;
        if(p==null || q==null)return false;
        if(p.val != q.val)return false;
        return rec(p.left,q.right) && rec(p.right,q.left);
    }
}