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

    /*
     * intuition is that we can use the solution we use for LCA when both p & q
     * are present, if ans is p we are not sure if q is part of tree to check that
     * we can do dfs on subtree p to check q is present, similarly when ans is q
     * else whatever ans we got is correct
     *
     */
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
        TreeNode[] ans  =  new TreeNode[1];
        rec33(root,p,q,ans);
        return ans[0];
    }

    /*
     * intuition is to  traverse untill the leaf nodes and propogate
     * the matched count on left and right and mid, when count is > 1
     * then we found the ans, when 1 propogate it upwords so we can
     * find the other 1 if so that is the answer
     */

    public int rec33(TreeNode root, TreeNode p, TreeNode q, TreeNode[] ans){
        if(root == null)return 0;
        int l = rec33(root.left,p,q,ans);
        int r = rec33(root.right,p,q,ans);
        int m = p==root||q==root?1:0;
        if(l+r+m > 1)
            ans[0] = root;
        return l+r+m > 0 ? 1:0;
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
