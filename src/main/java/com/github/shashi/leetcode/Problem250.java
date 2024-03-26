package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Problem250 {
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
    public int countUnivalSubtrees(TreeNode root) {
        return countUnivalSubtreesA2(root);
    }

    public int countUnivalSubtreesA2(TreeNode root) {
        if(root==null)return 0;
        int count=0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev=null, cur=root;
        Map<TreeNode,Boolean> map = new HashMap<>();
        while(cur!=null || !stack.isEmpty()){
            if(cur !=null){
                stack.push(cur);
                cur=cur.left;
            }else if(stack.peek().right != null && stack.peek().right!=prev)
                cur = stack.peek().right;
            else{
                TreeNode top = stack.pop();
                boolean left = top.left !=null ? map.get(top.left)&&top.val==top.left.val:true;
                boolean right = top.right!=null? map.get(top.right)&&top.val==top.right.val:true;
                if(left&&right){
                    count++;
                    map.put(top,true);
                }else map.put(top,false);
                prev = top;
            }
        }
        return count;
    }

    public int countUnivalSubtreesA1(TreeNode root) {
        int[] count = new int[1];
        rec(root,count);
        return count[0];
    }

    public boolean rec(TreeNode root, int[] cn){
        if(root==null)return true;
        boolean left = rec(root.left,cn);
        boolean right = rec(root.right,cn);
        left = left && (root.left != null? root.left.val == root.val:true);
        right = right && (root.right != null? root.right.val == root.val:true);
        if(left && right){
            cn[0]++;
            return true;
        }else return false;
    }
}