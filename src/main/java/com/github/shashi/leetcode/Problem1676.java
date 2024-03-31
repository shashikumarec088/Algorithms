package com.github.shashi.leetcode;
import java.util.*;
public class Problem1676 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(-1);
        System.out.println(sb.toString());
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        return lowestCommonAncestorA4(root,nodes);
    }

    public TreeNode lowestCommonAncestorA4(TreeNode root, TreeNode[] nodes){
        // since all the nodes are precent in the tree we can use
        // normal recursion same as in 236
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node: nodes)set.add(node);
        return traverse3(root,set);
    }

    public TreeNode lowestCommonAncestorA2(TreeNode root, TreeNode[] nodes) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        for(TreeNode node : nodes)queue.offer(node);
        while(queue.size()>1){
            queue.offer(lca(root,queue.poll(),queue.poll()));
        }
        return queue.poll();
    }

    public TreeNode lowestCommonAncestorA3(TreeNode root, TreeNode[] nodes) {
        TreeNode lc = nodes[0];
        for(int i=1; i<nodes.length;i++)
            lc = lca(root,lc,nodes[i]);
        return lc;
    }

    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root==p || root==q)return root;
        TreeNode l = lca(root.left,p,q);
        TreeNode r = lca(root.right,p,q);
        if(l!=null && r!=null)return root;
        if(l!=null)return l;
        else return r;
    }


    private TreeNode traverse3(TreeNode root, Set<TreeNode> set){
        if(root==null || set.contains(root) )return root;
        TreeNode l = traverse3(root.left,set);
        TreeNode r = traverse3(root.right,set);
        return l==null?r:r==null?l:root;
    }



    public TreeNode lowestCommonAncestorA1(TreeNode root, TreeNode[] nodes){
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node: nodes)set.add(node);
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, prev=null, lca = null;
        int count = 0;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(cur.right == null || cur.right == prev){
                    if(set.contains(cur)){
                        count++;
                        if(count==1 && set.size()==1)return cur;
                        // this is to handle the case when we found 1st element
                        if(count==1)lca = stack.peek();
                        if(count==set.size())return lca;
                    }
                    // if we are coming here then we traversed the complete subtree
                    // including this element so lca should always be the parent
                    if(cur == lca)lca = stack.peek();
                    prev = cur;
                    cur = null;
                }else{
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return null;
    }
}
