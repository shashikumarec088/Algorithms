package com.github.shashi.leetcode;
import java.util.*;

public class Problem103 {
    /*
    Binary Tree Zigzag Level Order Traversal
    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to
    right, then right to left for the next level and alternate between).

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[20,9],[15,7]]
    Example 2:
    Input: root = [1]
    Output: [[1]]
    Example 3:
    Input: root = []
    Output: []
    Constraints:
    The number of nodes in the tree is in the range [0, 2000].
    -100 <= Node.val <= 100

    Approach 1: bfs
    * intuition is to use the normal bfs and at each level add to the start or end of the list based or direction
    algo:
    * create a list of list of integers and queue of type treeNode add root to queue
    * have flag left set to true
    * iterate until queue is empty, get the queue size, create new linkedlist of integers to store the level
    * iterate over the queue for size elements and if left then add to end of list else to start of list
    * add left and right child to queue
    * add list to result and invert the left flag
    * iterate until queue is empty
    * return result
    time & space
    * n time and n space

    Approach 2: dfs recursion
    * intuition is to do the dfs by passing the level and result, very similar to inorder dfs but we check if
    level%2 ==0  if so then add at end else at first
    algo:
    * create list of list of integers and call rec with root, res,0 level
    * in recursion base case is if root is null then return
    * if res size is <= level then we add new list
    * if level%2==0 then we add element at end of list else at start
    * call recursion on root.left and right with level+1
    time & space:
    * n time and n space

     */

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return zigzagLevelOrderA1(root);
    }

    public List<List<Integer>> zigzagLevelOrderA2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        rec(root,res,0);
        return res;
    }

    public void rec(TreeNode root, List<List<Integer>> res, int level){
        if(root==null)return;
        if(res.size()<=level)
            res.add(new LinkedList<>());
        if(level%2 ==0)res.get(level).add(root.val);
        else res.get(level).add(0,root.val);
        rec(root.left,res,level+1);
        rec(root.right,res,level+1);
    }

    public List<List<Integer>> zigzagLevelOrderA1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root==null)return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean left=true;
        while(!queue.isEmpty()){
            int size=queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(left)list.add(node.val);
                else list.addFirst(node.val);
                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            res.add(list);
            left = !left;
        }
        return res;
    }
}
