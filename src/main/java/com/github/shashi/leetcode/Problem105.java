package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem105 {
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