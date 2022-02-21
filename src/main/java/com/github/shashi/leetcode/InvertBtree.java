package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBtree {
    class TreeNode {
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

    public static void main(String[] args) {
        InvertBtree invertBtree = new InvertBtree();
        TreeNode root = invertBtree.constructTreeFromArray(new Integer[]{4,2,7,1,3,6,9});
        TreeNode out = invertBtree.invertTreeRec(root);
    }

    public TreeNode invertTreeRec(TreeNode root){
        if(root == null) return null;
        root.left = invertTreeRec(root.right);
        root.right = invertTreeRec(root.left);
        return root;
    }


    public TreeNode constructTreeFromArray(Integer[] data){
        if(data.length == 0 || (data.length == 1 && data[0] == null)) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(data[0]);
        queue.add(root);
        TreeNode current = null;
        int count = 0;
        for(int i=1; i<data.length;i++){
            Integer value = data[i];
            if(count == 0)
                current = queue.poll();
            TreeNode node = null;
            if(value != null)
                node = new TreeNode(value);
            if(count == 0){
                count++;
                current.left = node;
            }else{
                count =0;
                current.right = node;
            }
            if(node != null)
                queue.add(node);
        }
        return root;
    }
}


