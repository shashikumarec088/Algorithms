package com.github.shashi.leetcode;
import java.util.*;
public class Problem1644 {
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode ans = null;
    private int lcLevel=-1;
    private int cn = 0;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorA3(root,p,q);
    }

    public TreeNode lowestCommonAncestorA4(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || p==null || q==null)return root;
        TreeNode res = rec(root,p,q);
        if(res==p)
            return rec2(p,q,q)!=null?res:null;
        else if(res==q)
            return rec2(q,p,p)!=null?res:null;
        else return res;
    }

    public TreeNode rec2(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root == p || root == q)
            return root;
        TreeNode l = rec2(root.left,p,q);
        TreeNode r = rec2(root.right,p,q);
        return l==null?r:r==null?l:root;
    }

    public TreeNode lowestCommonAncestorA3(TreeNode root, TreeNode p, TreeNode q){

        TreeNode node = rec(root,p,q);
        return cn==2? node:null;
    }

    private TreeNode rec(TreeNode root, TreeNode p, TreeNode q){
        if(root==null)return root;
        TreeNode l = rec(root.left,p,q);
        TreeNode r = rec(root.right,p,q);
        if(root==p || root==q){
            cn++;
            return root;
        }
        return l==null?r:r==null?l:root;

    }

    public TreeNode lowestCommonAncestorA2(TreeNode root, TreeNode p, TreeNode q){

        traverse(root,p,q,0);
        return ans;
    }

    public int traverse(TreeNode root, TreeNode p, TreeNode q, int level){
        if(root==null)return 0;
        int lc = traverse(root.left,p,q,level+1);
        int rc = traverse(root.right,p,q,level+1);
        int count = lc+rc;
        if(root==p || root==q)count++;
        if(count==2 && lcLevel <level){
            lcLevel = level;
            ans = root;
        }
        return count;
    }


    public TreeNode lowestCommonAncestorA1(TreeNode root, TreeNode p, TreeNode q){
        TreeNode lca = null, prev=null,cur=root;
        Stack<TreeNode> stack = new Stack<>();
        int count =0;
        while(cur != null || !stack.isEmpty()){
            if(cur !=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(cur.right==null || cur.right==prev){
                    if(cur==p || cur==q){
                        count++;
                        if(count==1 && !stack.isEmpty())lca = stack.peek();
                        if(count==2)return lca;
                    }
                    if(cur==lca && !stack.isEmpty())lca=stack.peek();
                    prev=cur;
                    cur=null;
                }else{
                    stack.push(cur);
                    cur = cur.right;
                }
            }
        }
        return null;
    }
}
