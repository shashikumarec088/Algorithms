package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Problem145 {

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
    public List<Integer> postorderTraversal(TreeNode root) {
        return postorderTraversalA3(root);
    }

    /*
     * in post order traversal using the stack we need to have the previous
     * visited node to handle the infinite loop when already visited the right
     * child
     */
    public List<Integer> postorderTraversalA3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur=root,prev=null;
        while(cur !=null || !stack.isEmpty()){
            if(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }else{
                if(stack.peek().right!=null && stack.peek().right!=prev){
                    cur=stack.peek().right;
                }else{
                    prev = stack.pop();
                    list.add(prev.val);
                }
            }
        }
        return list;
    }

    /*
     * this is the modified version of preorder in preorder
     * we visit current, left, right , in post is is left,right,current
     * so what we can do is to to preorder with current, right, left and then
     * reverse the result
     */
    public List<Integer> postorderTraversalA2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.left != null)stack.push(node.left);
            if(node.right != null)stack.push(node.right);
        }
        Collections.reverse(list);
        return list;
    }

    public List<Integer> postorderTraversalA1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rec(root,res);
        return res;
    }

    public void rec(TreeNode root, List<Integer> list){
        if(root==null)return;
        rec(root.left,list);
        rec(root.right,list);
        list.add(root.val);
    }
}