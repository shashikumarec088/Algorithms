package com.github.shashi.leetcode;
import java.util.*;
public class Problem543 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private int maxDia=0;
    public int diameterOfBinaryTree(TreeNode root) {
        return diameterOfBinaryTreeA1(root);
    }

    public int diameterOfBinaryTreeA2(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode prev=null;
        ;
        Map<TreeNode,Integer> map = new HashMap<>();
        int d=0;
        while(cur != null || stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                TreeNode top = stack.peek();
                if(top.right ==null || top.right==prev){
                    cur = stack.pop();
                    int l = cur.left !=null?map.remove(cur.left):0;
                    int r = cur.right != null?map.remove(cur.right):0;
                    d = Math.max(d, l+r);
                    map.put(cur,Math.max(l,r)+1);
                    prev = cur;
                    cur=null;
                }
                else cur=top.right;
            }
        }
        return d;
    }

    public int diameterOfBinaryTreeA1(TreeNode root){
        recursion(root);
        return maxDia;
    }

    public int recursion(TreeNode root){
        if(root==null)return 0;
        int l = recursion(root.left);
        int r = recursion(root.right);
        maxDia = Math.max(l+r,maxDia);
        return Math.max(l,r)+1;
    }
}
