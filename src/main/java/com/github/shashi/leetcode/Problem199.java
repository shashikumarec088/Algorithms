package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem199 {
    /*
    Binary Tree Right Side View
    Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes
    you can see ordered from top to bottom.

    Example 1:
    Input: root = [1,2,3,null,5,null,4]
    Output: [1,3,4]
    Example 2:
    Input: root = [1,null,3]
    Output: [1,3]
    Example 3:
    Input: root = []
    Output: []
    Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100

    Approach 1: iterative bfs
    * intuition is to do the iterative bfs and at each level add the last element
    algo:
    * create a list of integers res for storing results, and list of TreeNodes to store the cur level tree nodes list
    * add root to list and iterate until list is empty
    * get the last element value from list and add it to res, create new list of tree nodes
    * iterate all elements in list, add left and right child to new list if exists
    * make new list as list at the end
    * return the res
    time & space:
    * n time and n space

    Approach 2: reverse preorder recursion (root,right,left)
    * intuition is to do reverse pre order recursion with level and add to result if level is same as size of list
    algo:
    * create a empty list res of integers, call recursion with res, root and level 0
    * if root is null then return
    * if size of res and level are same then add root.val to res
    * call rec with root.right,level+1,res and then call rec with root.left,level+1,res
    * once recursion completes return the res
    time & space:
    * n time & n space
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

    public List<Integer> rightSideView(TreeNode root) {
        return rightSideViewA2(root);
    }

    public List<Integer> rightSideViewA2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rec(root,res,0);
        return res;
    }

    public void rec(TreeNode root, List<Integer> list, int level){
        if(root==null)return;
        if(list.size()==level)
            list.add(root.val);
        rec(root.right,list,level+1);
        rec(root.left,list,level+1);
    }

    public List<Integer> rightSideViewA1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null)return res;
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        while(!list.isEmpty()){
            res.add(list.get(list.size()-1).val);
            List<TreeNode> next = new ArrayList<>();
            for(TreeNode node : list){
                if(node.left !=null)next.add(node.left);
                if(node.right !=null)next.add(node.right);
            }
            list = next;
        }
        return res;
    }
}
