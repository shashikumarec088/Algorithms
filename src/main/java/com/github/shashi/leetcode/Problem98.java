package com.github.shashi.leetcode;
import java.util.*;
public class Problem98 {
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
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        return isValidBSTA4(root);
    }

    public boolean isValidBSTA4(TreeNode root){
        if(root == null)return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root,prev1=null;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(prev1!=null && cur.val<=prev1.val)
                return false;
            prev1 = cur;
            cur = cur.right;
        }
        return true;
    }

    public boolean isValidBSTA3(TreeNode root){
        if(root == null) return true;
        if(!isValidBSTA3(root.left))return false;
        if(prev!= null && root.val<=prev.val) return false;
        prev = root;
        return isValidBSTA3(root.right);
    }


    class Record{
        Integer left;
        Integer right;
        TreeNode root;
        Record(Integer left,TreeNode root, Integer right){
            this.left = left;
            this.right = right;
            this.root = root;
        }
    }

    public boolean isValidBSTA2(TreeNode root){
        if(root==null) return true;
        Stack<Record> stack = new Stack<>();

        stack.push(new Record(null,root,null));
        while(!stack.isEmpty()){
            Record rec = stack.pop();
            Integer left = rec.left;
            Integer right = rec.right;
            TreeNode node = rec.root;
            boolean lc = left == null?true: node.val>left;
            boolean rc = right == null?true: node.val<right;
            if(lc&&rc){
                if(node.left != null)
                    stack.push(new Record(left,node.left,node.val));
                if(node.right != null)
                    stack.push(new Record(node.val,node.right,right));
            }
            else return false;
        }
        return true;
    }

    public boolean isValidBSTA1(TreeNode root){
        return rec(null,root,null);
    }

    public boolean rec(Integer left, TreeNode root, Integer right){
        if(root == null) return true;
        boolean lc = left == null?true: root.val>left;
        boolean rc = right == null?true: root.val<right;
        return lc && rc && rec(left,root.left,root.val)
                && rec(root.val, root.right, right);
    }
}
