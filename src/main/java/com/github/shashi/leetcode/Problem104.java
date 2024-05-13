package com.github.shashi.leetcode;

import java.util.*;

public class Problem104 {
    /*
    Maximum Depth of Binary Tree
    Given the root of a binary tree, return its maximum depth.

    A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest
    leaf node.
    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: 3
    Example 2:
    Input: root = [1,null,2]
    Output: 2
    Constraints:
    The number of nodes in the tree is in the range [0, 104].
    -100 <= Node.val <= 100

    Approach 1: dfs recursion
    * intuition is to check the depth on left and right subtree and add 1 and return the max depth
    algo:
    * call rec with root, 0 , base case is if root is hull return depth
    * return the max of the rec (root.left,depth)+1 and rec (root.right,depth)+1
    time & space:
    * n time and n space for rec stack

    Approach 2: bfs
    * do the bfs and update the depth after each layer
    algo:
    keep depth =0, initialize queue, add the root, iterate until queue is empty
    * take the size of queue, iterate until size and take the node and check if left exists then push to queue
    * if right exists then push to queue
    * inc depth by 1
    * return the depth
    time & space:
    * n time and n space

    Approach 3: dfs iteration
    * intuition is to do the iterative dfs using 2 stacks 1 for nodes and 1 for depths
    algo:
    * if root is null return 0
    * create stack of TreeNodes and stack of ints
    * push the root to nodes stack and push 1 to depths stack
    * initialize maxDepth = 0
    * iterate until nodes stack is empty
    * pop the node and depth update the maxDepth to max of depth and maxDepth
    * if node has left add to nodes stack and add depth+1 to depths stack
    * if node has right add to nodes stack and add depth+1 to depths stack
    * return maxDepth at the end
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
    public static void main(String[] args) {
        Problem104 problem119 = new Problem104();
        System.out.println(problem119.maxDepth(null));
    }

    public int maxDepth(TreeNode root) {
        return maxDepthA3(root);
    }

    public int maxDepthA3(TreeNode root) {
        if(root==null)return 0;
        Stack<TreeNode> nodes = new Stack<>();
        Stack<Integer> depths = new Stack<>();
        nodes.push(root);
        depths.push(1);
        int max=0;
        while(!nodes.isEmpty()){
            TreeNode node = nodes.pop();
            int depth = depths.pop();
            max =Math.max(max,depth);
            if(node.left !=null){
                nodes.push(node.left);
                depths.push(depth+1);
            }
            if(node.right!=null){
                nodes.push(node.right);
                depths.push(depth+1);
            }
        }
        return max;
    }
    public int maxDepthA2(TreeNode root) {
        if(root==null)return 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            depth++;
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(node.left != null)queue.offer(node.left);
                if(node.right != null)queue.offer(node.right);
            }
        }
        return depth;
    }

    public int maxDepthA1(TreeNode root) {
        return rec(root,0);
    }

    public int rec(TreeNode root, int depth){
        if(root==null) return depth;
        return Math.max(rec(root.left,depth)+1,rec(root.right,depth)+1);
    }
}
