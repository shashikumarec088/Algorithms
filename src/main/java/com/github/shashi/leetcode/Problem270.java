package com.github.shashi.leetcode;

public class Problem270 {

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
    public int closestValue(TreeNode root, double target) {
        return closestValueA1(root,target);
    }

    public int closestValueA1(TreeNode root, double target) {
        int closet = root.val;
        while(root != null){
            if(Math.abs(closet-target)>Math.abs(root.val-target) ||
                    (Math.abs(closet-target)==Math.abs(root.val-target) &&
                            root.val<closet))
                closet=root.val;
            if(target<root.val)root=root.left;
            else root = root.right;
        }
        return closet;
    }
}