package com.github.shashi.leetcode;
import com.github.pedrovgs.pair.Pair;
import java.util.*;
public class Problem236 {
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode ans=null;
    private int lcLevel=-1;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorA4(root,p,q);
    }

    public TreeNode lowestCommonAncestorA5(TreeNode root, TreeNode p, TreeNode q) {
        traverse(root,p,q,0);
        return ans;
    }

    public int traverse(TreeNode root, TreeNode p, TreeNode q, int level){
        if(root==null)return 0;
        int lc = traverse(root.left,p,q,level+1);
        int rc = traverse(root.right,p,q,level+1);
        int count = lc+rc;
        if(root==p||root==q)count++;
        if(count==2 && lcLevel<level){
            lcLevel=level;
            ans=root;
        }
        return count;
    }

    public TreeNode lowestCommonAncestorA4(TreeNode root, TreeNode p, TreeNode q){
        TreeNode lca=null,prev=null,cur=root;
        Stack<TreeNode> stack = new Stack<>();
        int count = 0;
        while(cur!=null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(cur.right==null || cur.right==prev){
                    if(cur==p||cur==q){
                        count++;
                        if(count==1)lca=stack.peek();
                        if(count==2)return lca;
                    }
                    if(lca==cur)lca=stack.peek();

                    prev=cur;
                    cur=null;
                }else{
                    stack.push(cur);
                    cur=cur.right;
                }
            }
        }
        return null;
    }

    public TreeNode lowestCommonAncestorA3(TreeNode root, TreeNode p, TreeNode q){
        rec(root,p,q);
        return ans;
    }

    public boolean rec(TreeNode root, TreeNode p, TreeNode q){
        if(root==null)return false;
        int l=rec(root.left,p,q)?1:0;
        int r=rec(root.right,p,q)?1:0;
        int mid = (root==p||root==q)?1:0;
        if(l+r+mid>1)
            ans=root;
        return (l+r+mid)>0;
    }

    public TreeNode lowestCommonAncestorA2(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root==p || root==q)
            return root;
        TreeNode l = lowestCommonAncestorA2(root.left, p,q);
        TreeNode r = lowestCommonAncestorA2(root.right,p,q);
        if(l!=null && r!=null)return root;
        return l!=null?l:r;
    }

    public TreeNode lowestCommonAncestorA1(TreeNode root, TreeNode p, TreeNode q){
        Map<TreeNode,TreeNode> parents = new HashMap<>();
        parents.put(root,null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!parents.containsKey(p)||!parents.containsKey(q)){
            TreeNode node = queue.poll();
            if(node.left!=null){
                parents.put(node.left,node);
                queue.add(node.left);
            }
            if(node.right!=null){
                parents.put(node.right,node);
                queue.add(node.right);
            }
        }
        Set<TreeNode> set = new HashSet<>();
        TreeNode node = p;
        while(node != null){
            set.add(node);
            node = parents.get(node);
        }
        node = q;
        while(!set.contains(node))
            node = parents.get(node);
        return node;
    }
}
