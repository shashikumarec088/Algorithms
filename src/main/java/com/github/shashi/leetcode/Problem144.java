package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem144 {
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
    public List<Integer> preorderTraversal(TreeNode root) {
        return preorderTraversalA3(root);
    }
    /*
     * most importent thing to remember in moris
     * preorder traversal is we visit the root node when we link
     * the rightmost child of left child to root but in moris
     * in order traversal we visit the root node when we unlik
     * rest remains the same when there is no left child of root
     * we visit the node and move on to right child
     */
    public List<Integer> preorderTraversalA3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null)return res;

        while(root != null){
            if(root.left==null){
                res.add(root.val);
                root= root.right;
            }else{
                TreeNode left = root.left;
                while(left.right !=null && left.right !=root)
                    left = left.right;
                if(left.right==null){
                    res.add(root.val);
                    left.right = root;
                    root = root.left;
                }else{
                    left.right = null;
                    root = root.right;
                }
            }
        }
        return res;
    }

    public List<Integer> preorderTraversalA2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null)return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right != null)stack.push(node.right);
            if(node.left != null)stack.push(node.left);
        }
        return res;
    }

    public List<Integer> preorderTraversalA1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rec(root,res);
        return res;
    }

    public void rec(TreeNode root, List<Integer> res){
        if(root==null)return;
        res.add(root.val);
        rec(root.left,res);
        rec(root.right,res);
    }
}