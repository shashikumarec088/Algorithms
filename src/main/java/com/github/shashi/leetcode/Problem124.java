package com.github.shashi.leetcode;

public class Problem124 {
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
    private int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        return maxPathSumA1(root);
    }

    public int maxPathSumA1(TreeNode root){
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root){
        if(root==null)return 0;
        int leftMax = Math.max(dfs(root.left),0);
        int rightMax = Math.max(dfs(root.right),0);
        int newSum = root.val+leftMax+rightMax;
        max = Math.max(max,newSum);
        return Math.max(leftMax+root.val,rightMax+root.val);
    }
}
