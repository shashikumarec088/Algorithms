package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem637 {
    /*
    Average of Levels in Binary Tree
    Given the root of a binary tree, return the average value of the nodes on each level in the form of an array.
    Answers within 10-5 of the actual answer will be accepted.

    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [3.00000,14.50000,11.00000]
    Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
    Hence return [3, 14.5, 11].
    Example 2:
    Input: root = [3,9,20,15,7]
    Output: [3.00000,14.50000,11.00000]
    Constraints:

    The number of nodes in the tree is in the range [1, 104].
    -231 <= Node.val <= 231 - 1

    Approach 1: BFS
    * intuition is to do the level order traversal using bfs and at each level get the size sum values and get the
    average and add it to result
    algo:
    * create list of doubles to store the result, queue of type treeNode add root to queue
    * iterate until queue is empty, at each iteration get the queue size n
    * create sum=0 of type double else we end up having overflow for big values, iterate for n nodes
    over queue and get the node from queue add its value to sum
    * add left and right child to queue if exists
    * find avg = sum/(double)n and add to result, repeat until queue is empty and return result
    time & space:
    * n space and n time

    Approach 2: DFS recursion
    * intuition is to calculate the count and sum for each level and then compute the average for each level.
    algo:
    * define sums of type list of double and counts of type list of integers
    * call recursion with root, sums, count, 0
    * if root is null then return
    * if level is less than the counts size, then set counts of i as 1 and sums of i to root.val
    else get the current values at the index i and add cur val to sums of i and 1 to counts of i
    * call rec on root.left and root.right by increasing the level
    * after rec call
    * iterate over the counts array and replace the value at each index of sums with sums[i]/counts[i]
    * return counts
     */

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

    public List<Double> averageOfLevels(TreeNode root) {
        return averageOfLevelsA2(root);
    }

    public List<Double> averageOfLevelsA2(TreeNode root) {
        List<Double> sums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        rec(root,sums,counts,0);
        for(int i=0; i<sums.size(); i++)
            sums.set(i,sums.get(i)/(double)counts.get(i));
        return sums;
    }

    public void rec(TreeNode root, List<Double> sums, List<Integer> counts, int level){
        if(root==null)return;
        if(sums.size()<=level){
            sums.add((double)root.val);
            counts.add(1);
        }else{
            sums.set(level,sums.get(level)+root.val);
            counts.set(level,counts.get(level)+1);
        }
        rec(root.left,sums,counts,level+1);
        rec(root.right,sums,counts,level+1);
    }

    public List<Double> averageOfLevelsA1(TreeNode root) {
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            double sum=0;
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                sum += (double)node.val;
                if(node.left!=null)queue.offer(node.left);
                if(node.right!=null)queue.offer(node.right);
            }
            res.add(sum/(double)size);
        }
        return res;
    }
}
