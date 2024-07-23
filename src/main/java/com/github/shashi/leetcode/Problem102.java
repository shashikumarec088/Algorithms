package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Problem102 {
    /*
    Binary Tree Level Order Traversal
    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right,
    level by level).

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]
    Example 2:
    Input: root = [1]
    Output: [[1]]
    Example 3:
    Input: root = []
    Output: []

    Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -1000 <= Node.val <= 1000

    Approach 1: BFS level order
    * intuition is to use the queue based level order bfs and visit the nodes at each level and add to result
    algo:
    * create a res of type of list of list of type integers, empty queue of type linked list and add root
    * iterate until queue is empty  at each level get the queue size, create new list
    * iterate over queue for size elements and add to new list and add left and right child to queue
    * add new list to res, repeat until queue is empty
    time & space:
    * n time and n space

    Approach 2: DFS recursion
    * we can do level order traversal using DFS, by maintaining lists for each level.
    algo:
    * create a res of type list of list of type integer, call rec with root,res,level=0
    * in rec if root is null then return
    * if res size is <= level then add new list to res and add element else add element by getting
    the list at level
    * return res at the end
    time & space:
    * n time and n space
     */
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
        List l = new LinkedList();
        l.add(0,1);
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
