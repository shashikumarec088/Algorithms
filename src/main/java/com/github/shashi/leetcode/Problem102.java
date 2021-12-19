package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Problem102 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        return levelOrderRec(root);
    }

    public List<List<Integer>> levelOrderItr(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root== null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> llist = new ArrayList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                llist.add(node.val);
                if(node.left != null)queue.add(node.left);
                if(node.right != null)queue.add(node.right);
            }
            list.add(llist);
        }
        return list;
    }

    public List<List<Integer>> levelOrderRec(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        helper(root,0,result);
        return result;
    }

    public void helper(TreeNode root, int level, List<List<Integer>> result){
        if(root==null) return;
        if(result.size()== level)
            result.add(new ArrayList<>());
        result.get(level).add(root.val);
        helper(root.left,level+1,result);
        helper(root.right,level+1,result);
    }
}
