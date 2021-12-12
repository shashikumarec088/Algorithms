package com.github.shashi.leetcode;

public class Problem98 {

    public static void main(String[] args) {
        Problem98 problem119 = new Problem98();
        System.out.println(problem119.isValidBST(null));
    }

    public boolean isValidBST(TreeNode root) {
        Integer[] prev = new Integer[]{null};
        return  isValidIn(root,prev);
    }

    public boolean isValidTree(TreeNode root){
        return isValidRec(root, null, null);
    }

    public boolean isValidRec(TreeNode root, Integer lb, Integer rb){
        if(root==null)return true;
        return (lb==null || root.val>lb) && (rb==null || root.val<rb) &&
                isValidRec(root.left,lb,root.val) &&
                isValidRec(root.right,root.val,rb);
    }

    public boolean isValidIn(TreeNode root, Integer[] prev){
        if(root == null) return true;
        if(!isValidIn(root.left,prev)) return false;
        if(!(prev[0] == null || root.val > prev[0])) return false;
        prev[0]=root.val;
        return isValidIn(root.right,prev);
    }
}
