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

    /*
    * this moris traversal in pre order traversal we visit the node
    * when creating the link, but in inorder we visit the node when removing link
     */
    public List<Integer> inorderTraversalA3(TreeNode root){
        List<Integer> list = new ArrayList<>();
        while(root!=null){
            if(root.left==null){
                list.add(root.val);
                root= root.right;
            }else{
                TreeNode left = root.left;
                while(left.right !=null && left.right !=root)
                    left = left.right;
                if(left.right==null){
                    left.right=root;
                    root=root.left;
                }else{
                    left.right=null;
                    list.add(root.val);
                    root=root.right;
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
