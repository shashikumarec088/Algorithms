package com.github.shashi.leetcode;
import java.util.*;
public class Problem865 {
    class Result{
        TreeNode node;
        int dist;
        Result(TreeNode node, int dist){
            this.node = node;
            this.dist = dist;
        }
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode ans=null;
    private int deepest =0;
    private Map<TreeNode,Integer> map = new HashMap<>();
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return subtreeWithAllDeepestA3(root);
    }
    public TreeNode subtreeWithAllDeepestA4(TreeNode root){
        return recursion(root).node;
    }

    public Result recursion(TreeNode root){
        if(root==null)return new Result(null,0);
        Result l = recursion(root.left);
        Result r = recursion(root.right);
        if(l.dist>r.dist)return new Result(l.node,l.dist+1);
        if(r.dist>l.dist)return new Result(r.node,r.dist+1);
        return new Result(root,l.dist+1);
    }

    public TreeNode subtreeWithAllDeepestA2(TreeNode root){
        dfs(root,0);
        return ans;
    }

    public TreeNode subtreeWithAllDeepestA3(TreeNode root){
        map.put(null,-1);
        dfsA3(root,null);
        for(Integer value : map.values())
            deepest = Math.max(deepest,value);
        return dfsA3(root);
    }

    public void dfsA3(TreeNode root, TreeNode parent){
        if(root != null){
            map.put(root,map.get(parent)+1);
            dfsA3(root.left,root);
            dfsA3(root.right,root);
        }
    }

    public TreeNode dfsA3(TreeNode root){
        if(root==null || map.get(root)==deepest)
            return root;
        TreeNode l = dfsA3(root.left);
        TreeNode r = dfsA3(root.right);
        return l==null?r:r==null?l:root;
    }

    private int dfs(TreeNode root, int level){
        if(root==null)return level;
        int l=dfs(root.left,level+1);
        int r=dfs(root.right,level+1);
        int cur = Math.max(l,r);
        deepest = Math.max(deepest,cur);
        if(deepest==l && deepest==r)
            ans = root;
        return cur;
    }

    public TreeNode subtreeWithAllDeepestA1(TreeNode root){
        TreeNode start=null, end = null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            start = queue.peekFirst();
            end = queue.peekLast();
            int size = queue.size();
            while(size-->0){
                TreeNode node = queue.poll();
                if(node.left!=null)queue.add(node.left);
                if(node.right!=null)queue.add(node.right);
            }
        }
        return rec(root,start,end);
    }

    public TreeNode rec(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root==p || root==q)return root;
        TreeNode l = rec(root.left,p,q);
        TreeNode r = rec(root.right, p,q);
        return l==null?r:r==null?l:root;
    }
}
