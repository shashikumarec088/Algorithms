package com.github.shashi.leetcode;

import java.util.*;

public class Problem104 {
    public static void main(String[] args) {
        Problem104 problem119 = new Problem104();
        System.out.println(problem119.maxDepth(null));
    }

    public int maxDepth(TreeNode root) {
        return maxDepthBfs(root);
    }

    public int maxDepthRec(TreeNode root){
        return rec(root,0);
    }

    public int rec(TreeNode root,int depth){
        if(root==null)return depth;
        return Math.max(rec(root.left,depth+1),rec(root.right,depth+1));
    }

    public int maxDepthBfs(TreeNode root){
        if(root==null)return 0;
        Queue<TreeNode> q = new LinkedList<>();
        int depth=0;
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            depth++;
            for(int i=0; i<size; i++){
                TreeNode node = q.poll();
                if(node.left!= null)q.add(node.left);
                if(node.right!=null)q.add(node.right);
            }
        }
        return depth;
    }

    public int maxDepthDfs(TreeNode root){
        if(root==null) return 0;
        int depth = 1;
        Stack<TreeNode> st1 = new Stack<>();
        Stack<Integer> dp1 = new Stack<>();
        st1.push(root);
        dp1.push(depth);
        depth=0;
        while(!st1.isEmpty()){
            TreeNode node = st1.pop();
            int cDepth = dp1.pop();
            depth = Math.max(depth,cDepth);
            if(node.left != null){
                st1.push(node.left);
                dp1.push(cDepth+1);
            }
            if(node.right != null){
                st1.push(node.right);
                dp1.push(cDepth+1);
            }
        }
        return depth;
    }
}
