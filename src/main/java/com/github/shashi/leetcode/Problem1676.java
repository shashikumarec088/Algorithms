package com.github.shashi.leetcode;
import java.util.*;
public class Problem1676 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode ans=null;
    private int lcLevel=-1;
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

    public TreeNode lowestCommonAncestorA3(TreeNode root, TreeNode[] nodes){
        // no need to use the level if we set ans once we encounter the
        // common element
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node: nodes)set.add(node);
        traverse2(root,set);
        return ans;
    }

    public TreeNode lowestCommonAncestorA2(TreeNode root, TreeNode[] nodes){
        Set<TreeNode> set = new HashSet<>();
        for(TreeNode node: nodes)set.add(node);
        traverse(root,set,0);
        return ans;
    }
    private TreeNode traverse3(TreeNode root, Set<TreeNode> set){
        if(root==null || set.contains(root) )return root;
        TreeNode l = traverse3(root.left,set);
        TreeNode r = traverse3(root.right,set);
        return l==null?r:r==null?l:root;
    }

    private int traverse2(TreeNode root, Set<TreeNode> set){
        if(root==null)return 0;
        int l = traverse2(root.left,set);
        int r = traverse2(root.right,set);
        int count = l+r;
        if(set.contains(root)) count++;
        if(count == set.size() && ans==null){
            ans = root;
        }
        return count;
    }

    private int traverse(TreeNode root, Set<TreeNode> set, int level){
        if(root==null)return 0;
        int l = traverse(root.left, set,level+1);
        int r = traverse(root.right, set, level+1);
        int count = l+r;
        if(set.contains(root)) count++;
        if(count == set.size() && lcLevel < level){
            ans = root;
            lcLevel = level;
        }
        return count;
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
                        if(count==1)lca = stack.peek();
                        if(count==set.size())return lca;
                    }
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
