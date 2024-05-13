package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem106 {
    /*
    Construct Binary Tree from Inorder and Postorder Traversal
    Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
    is the postorder traversal of the same tree, construct and return the binary tree.

    Example 1:
    Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    Output: [3,9,20,null,null,15,7]
    Example 2:
    Input: inorder = [-1], postorder = [-1]
    Output: [-1]
    Constraints:
    1 <= inorder.length <= 3000
    postorder.length == inorder.length
    -3000 <= inorder[i], postorder[i] <= 3000
    inorder and postorder consist of unique values.
    Each value of postorder also appears in inorder.
    inorder is guaranteed to be the inorder traversal of the tree.
    postorder is guaranteed to be the postorder traversal of the tree.

    Approach 1: recursion
    * intuition is that in post the traversal order is left, right, root and in inorder the traversal order is
    left,root,right.
    * if we observe the inorder the root node divides the tree into left and right subtree, we can construct the
    left subtree by using the left subarray and right subtree by using right subarray
    * we can start building the tree from last position of postorder since it contains the root element then we
    can proceed with building the right and left subtree recursively.
    algo:
    * init s=0, e=n-1,  n is number of elements
    * create a map to hold the position of elements in inorder which is used to get the position of root element in
    inorder array
    * start from n-1 th position of postorder, we need to maintain the position in object or array since we need the
    updated value between the recursion calls that is the reason we are storing it in array
    * build the treenode for position value in postorder array,
    * get the index position for inorder array for current element
    * dec the position, to build the next element
    * build node.right by calling the recursion from i+1 to e
    * build node.left by calling the recursion from s to i-1
    * the base case for rec is s>e in which cas we return null
    time & space:
    * takes n time and n space
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeA1(inorder,postorder);
    }

    public TreeNode buildTreeA1(int[] inorder, int[] postorder) {
        Map<Integer,Integer> map = new HashMap<>();
        int n = inorder.length;
        for(int i=0; i<n;i++){
            map.put(inorder[i],i);
        }
        int[] post={n-1};
        return rec(0,n-1,post,map,inorder,postorder);
    }

    public TreeNode rec(int s, int e,int[] pos,Map<Integer,Integer> map,
                        int[] in, int[] post){
        if(s>e)return null;
        int index = map.get(post[pos[0]]);
        TreeNode root = new TreeNode(post[pos[0]]);
        pos[0]--;
        root.right = rec(index+1,e,pos,map,in,post);
        root.left = rec(s,index-1,pos,map,in,post);
        return root;
    }
}