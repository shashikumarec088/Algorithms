package com.github.shashi.leetcode;

public class Problem700 {
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
    public TreeNode searchBST(TreeNode root, int val) {
        return searchBSTA2(root,val);
    }

    public TreeNode searchBSTA2(TreeNode root, int val){
        while(root!=null && root.val != val){
            if(root.val<val)root=root.right;
            else root=root.left;
        }
        return root;
    }

    public TreeNode searchBSTA1(TreeNode root, int val) {
        if(root==null || root.val==val)return root;
        if(root.val<val)return searchBSTA1(root.right,val);
        else return searchBSTA1(root.left,val);
    }
}