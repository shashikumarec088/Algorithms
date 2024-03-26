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



    /*
     * intuition behind the interative approach is to traverse the tree in bfs manner
     * until both p and queue is found, capture the depth for each node, once both the nodes
     * are found first move up the more depth node so that both are at the same height from
     * the root, then traverse upwords both the nodes step by step snd compare if the parent
     * is same
     */
    public TreeNode lowestCommonAncestorA4(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode,TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int pdist=0, qdist=0, dist=0;
        boolean pf=false, qf=false;
        map.put(root,null);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(node==p){
                    pf=true;
                    pdist=dist;
                }
                if(node==q){
                    qf=true;
                    qdist=dist;
                }
                if(node.left !=null){
                    queue.offer(node.left);
                    map.put(node.left,node);
                }
                if(node.right !=null){
                    queue.offer(node.right);
                    map.put(node.right,node);
                }
            }
            if(pf && qf)break;
            dist++;
        }
        if(qdist>pdist){
            TreeNode temp = q;
            q = p;
            p=temp;
            int td = qdist;
            qdist = pdist;
            pdist = qdist;
        }
        while(pdist > qdist){
            p = map.get(p);
            pdist--;
        }
        if(p==q)return p;
        while(pdist>=0){
            p = map.get(p);
            q = map.get(q);
            if(p==q)return p;
            pdist--;
        }
        return p;
    }


    /*
     * intuition behind the recursive approach is to find the root node which matches
     * p or queue, if ancestor likes in left part of the subtree then we find the
     * ancestor in left subtree and traverse upwards
     */
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
