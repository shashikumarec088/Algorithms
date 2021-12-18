package com.github.shashi.leetcode;

import java.util.*;
import java.util.List;

public class Problem94 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List<Integer> inorderTraversal(TreeNode root) {
        return inOrderRec(root, new ArrayList<Integer>());
    }

    public List<Integer> morisTrav(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null)return list;
        TreeNode current = root;
        while(current != null){
            if(current.left == null){
                list.add(current.val);
                current = current.right;
            }else{
                TreeNode prev = current.left;
                while(prev.right != null && prev.right != current)
                    prev = prev.right;
                if(prev.right == null){
                    prev.right = current;
                    current = current.left;
                }else{
                    prev.right= null;
                    list.add(current.val);
                    current = current.right;
                }
            }
        }
        return list;
    }

    public List<Integer> inOrderRec(TreeNode root, List<Integer> list){
        if(root == null)return list;
        else{
            inOrderRec(root.left,list);
            list.add(root.val);
            inOrderRec(root.right,list);
            return list;
        }
    }

    public List<Integer> inOrderItr(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root==null)return list;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while(current != null || !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.val);
            current = current.right;
        }
        return list;
    }
}
