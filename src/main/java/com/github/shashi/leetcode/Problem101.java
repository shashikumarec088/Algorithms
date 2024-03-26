package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Problem101 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean isSymmetric(TreeNode root) {

        return isSymmetricA4(root);
    }

    public boolean isSymmetricA3(TreeNode root) {
        return rec(root,root);
    }

    public boolean rec(TreeNode left, TreeNode right){
        if(left==null && right==null)return true;
        else if(left == null || right ==null || left.val!= right.val)return false;
        else return rec(left.right,right.left) && rec(left.left,right.right);
    }

    /*
     * for queue implementation use the LinkedList, if we
     * use array dequeue we will get NP when adding null
     * but Linkedlist lets to add null
     */
    public boolean isSymmetricA4(TreeNode root) {
        if(root.left==null && root.right==null)return true;
        if(root.left==null || root.right==null)return false;
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.offer(root);
        q1.offer(root);
        while(!q1.isEmpty()){
            TreeNode c1 = q1.poll();
            TreeNode c2 = q1.poll();
            if(c1==null && c2==null)continue;
            if(c1==null || c2==null || c1.val != c2.val)return false;
            q1.offer(c1.right);
            q1.offer(c2.left);
            q1.offer(c1.left);
            q1.offer(c2.right);
        }
        return true;

    }

    public boolean isSymmetricA2(TreeNode root) {
        if(root.left==null && root.right==null)return true;
        if(root.left==null || root.right==null)return false;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        s2.push(root);
        while(!s1.isEmpty()){
            TreeNode c1 = s1.pop();
            TreeNode c2 = s2.pop();
            if(c1==null && c2==null)continue;
            if(c1==null || c2==null || c1.val != c2.val)return false;
            if(c1 != null){
                s1.push(c1.left);
                s1.push(c1.right);
            }
            if(c2 != null){
                s2.push(c2.right);
                s2.push(c2.left);
            }
        }
        return true;

    }


    public boolean isSymmetricA1(TreeNode root) {
        if(root.left==null && root.right==null)return true;
        if(root.left==null || root.right==null)return false;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root.left);
        s2.push(root.right);
        while(!s1.isEmpty()){
            TreeNode c1 = s1.pop();
            TreeNode c2 = s2.pop();
            if(c1==null && c2==null)continue;
            if(c1==null || c2==null || c1.val != c2.val)return false;
            if(c1 != null){
                s1.push(c1.left);
                s1.push(c1.right);
            }
            if(c2 != null){
                s2.push(c2.right);
                s2.push(c2.left);
            }
        }
        return true;

    }
}