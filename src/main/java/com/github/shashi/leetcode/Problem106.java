package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem106 {

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