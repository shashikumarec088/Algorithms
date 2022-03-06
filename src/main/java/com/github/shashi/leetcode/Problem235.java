package com.github.shashi.leetcode;

public class Problem235 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorA2(root,p,q);
    }

    public TreeNode lowestCommonAncestorA2(TreeNode root, TreeNode p, TreeNode q){
        TreeNode node = root;
        int nv = node.val, pv = p.val, qv = q.val;
        while(node!=null){
            nv = node.val;
            if(pv<nv && qv<nv)
                node = node.left;
            else if(pv>nv && qv>nv)
                node = node.right;
            else return node;
        }
        return node;
    }

    public TreeNode lowestCommonAncestorA1(TreeNode root, TreeNode p, TreeNode q){
        if(root == null)return root;
        if(p.val<root.val && q.val<root.val)
            return lowestCommonAncestorA1(root.left,p,q);
        else if(p.val>root.val && q.val>root.val)
            return lowestCommonAncestorA1(root.right,p,q);
        else return root;
    }
}