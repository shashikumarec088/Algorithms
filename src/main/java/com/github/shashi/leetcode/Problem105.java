package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem105 {
    /*
    Construct Binary Tree from Preorder and Inorder Traversal
    Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
    inorder is the inorder traversal of the same tree, construct and return the binary tree.

    Example 1:
    Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    Output: [3,9,20,null,null,15,7]
    Example 2:
    Input: preorder = [-1], inorder = [-1]
    Output: [-1]
    Constraints:
    1 <= preorder.length <= 3000
    inorder.length == preorder.length
    -3000 <= preorder[i], inorder[i] <= 3000
    preorder and inorder consist of unique values.
    Each value of inorder also appears in preorder.
    preorder is guaranteed to be the preorder traversal of the tree.
    inorder is guaranteed to be the inorder traversal of the tree.

    Approach 1:recursion
    * intuition is that in preorder the traversal order is root,left, right and in inorder the traversal order is
    left,root,right.
    * if we observe the inorder the root node divides the tree into left and right subtree, we can construct the
    left subtree by using the left subarray and right subtree by using right subarray
    * we can start building the tree from first position of preorder since it contains the root element then we
    can proceed with building the left and right subtree recursively.
    algo:
    * init s=0, e=n-1,  n is number of elements
    * create a map to hold the position of elements in inorder which is used to get the position of root element in
    inorder array
    * start from 0th position of preorder, we need to maintain the position in object or array since we need the
    updated value between the recursion calls that is the reasion we are storing it in array
    * build the treenode for position value in preorder array,
    * get the index position for inorder array for current element
    * inc the position, to build the next element
    * build node.left by calling the recursion from start to i-1
    * build node.right by calling the recursion from i+1 to e
    * the base case for rec is s>e in which cas we return null
    time & space:
    * n time and n space
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeA1(preorder,inorder);
    }

    /*
     * the intuition behind the approach is in inorder -> left root right
     * in postorder -> left, right, root the last element in post will be
     * root we can start building from root, then traverse the right subtree
     * build it and then left.
     * the index of post element in the inorder devide the tree into 2 halfs
     * then we build the right subtree first then the left.
     * we build the right subtree first because the postorder array will have
     * root,right,left from the end
     * for position we are using the array because we want the state tobe global
     * which works only for objects in recursion else we need to have the global
     * variable
     * building from preorder also similar but we just build the left subtree
     * first then the right, even the array we traverse from begining
     */

    public TreeNode buildTreeA1(int[] preorder, int[] inorder) {
        int n = inorder.length;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<n;i++)
            map.put(inorder[i],i);
        int[] pos = {0};
        return rec(0,n-1,pos,map,inorder,preorder);
    }

    public TreeNode rec(int s, int e, int[] pos, Map<Integer,Integer> map, int[] in,int[] pre){
        if(s>e)return null;
        TreeNode node = new TreeNode(pre[pos[0]]);
        int index = map.get(pre[pos[0]]);
        pos[0]++;
        node.left = rec(s,index-1,pos,map,in,pre);
        node.right = rec(index+1,e,pos,map,in,pre);
        return node;
    }
}