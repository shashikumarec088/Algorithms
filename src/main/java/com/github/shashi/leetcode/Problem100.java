package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem100 {
    /*
    Same Tree
    Given the roots of two binary trees p and q, write a function to check if they are the same or not.

    Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.

    Example 1:
    Input: p = [1,2,3], q = [1,2,3]
    Output: true
    Example 2:
    Input: p = [1,2], q = [1,null,2]
    Output: false
    Example 3:
    Input: p = [1,2,1], q = [1,1,2]
    Output: false
    Constraints:

    The number of nodes in both trees is in the range [0, 100].
    -104 <= Node.val <= 104

    approach 1: recursion
    * intuition is to check if p, q both null if so return true else either is null return false else value is not
    equal return false then check on p.left, q.left and on p.rigth,q.right
    algo:
    * check if p & q are null then return true, check if either is null then return false
    * check if p.val != q.val then return false;
    * then call method on p.left. q.left then with p.right,q.right, then return the and of both results
    time & space:
    * n time and n space

    approach 2: iterative
    * intuition is we can immetate the same behavior using the queue
    algo:
    * create a queue of type linkedlist since it can hold nulls but arraydeque does not hold nulls
    * add p,q and iterate untill queue is empty
    * take 2 elements from queue t1,t2
    * if both are null then continue,if either is null or not same return false
    * else add the p.left,q.left,p.right,q.right to queue and repeat
    * return true at the end
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        return isSameTreeA2(p,q);
    }

    public boolean isSameTreeA2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while(!queue.isEmpty()){
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if(t1==null && t2 == null)continue;
            if(t1==null || t2==null)return false;
            if(t1.val != t2.val)return false;
            queue.offer(t1.left);
            queue.offer(t2.left);
            queue.offer(t1.right);
            queue.offer(t2.right);
        }
        return true;
    }

    public boolean isSameTreeA1(TreeNode p, TreeNode q) {
        if(p==null && q==null)return true;
        if(p==null || q==null)return false;
        if(p.val != q.val) return false;
        return isSameTreeA1(p.left,q.left) && isSameTreeA1(p.right,q.right);
    }

}
