package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Problem112 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSumA3(root,targetSum);
    }

    public boolean hasPathSumA3(TreeNode root, int targetSum) {
        if(root==null)return false;
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        q1.offer(root);
        q2.offer(root.val);
        while(!q1.isEmpty()){
            int size = q1.size();
            for(int i=0; i<size; i++){
                int sum = q2.poll();
                TreeNode node = q1.poll();
                if(sum==targetSum && node.left==null && node.right==null)return true;
                if(node.left!=null){
                    q2.offer(node.left.val+sum);
                    q1.offer(node.left);
                }
                if(node.right!=null){
                    q2.offer(node.right.val+sum);
                    q1.offer(node.right);
                }
            }
        }
        return false;
    }

    public boolean hasPathSumA2(TreeNode root, int targetSum) {
        if(root==null)return false;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        s1.push(root);
        s2.push(root.val);
        while(!s1.isEmpty()){
            TreeNode node = s1.pop();
            int sum = s2.pop();
            if(sum==targetSum && node.left==null && node.right==null)return true;
            if(node.left !=null){
                s1.push(node.left);
                s2.push(node.left.val+sum);
            }
            if(node.right!=null){
                s1.push(node.right);
                s2.push(node.right.val+sum);
            }
        }
        return false;
    }

    public boolean hasPathSumA1(TreeNode root, int targetSum) {
        if(root==null)return false;
        return rec(root,targetSum,0);
    }

    public boolean rec(TreeNode root, int target, int sum){
        if(root==null) return false;
        if(root.left == null && root.right==null && root.val+sum==target) return true;
            // if we add below case we mis the path where middle sum = target but at the
            // end we might have sum = target due to having 2 opposite value nodes
            // so we should not add this
            // else if (sum+root.val == target && (root.left!=null || root.right!=null)) return false;
        else return rec(root.left,target,sum+root.val) ||
                rec(root.right,target,sum+root.val);
    }
}