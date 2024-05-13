package com.github.shashi.leetcode;
import com.github.pedrovgs.pair.Pair;
import java.util.*;
public class Problem236 {
    /*
        Lowest Common Ancestor of a Binary Tree

        Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

        According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
        as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

        Example 1:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Output: 3
        Explanation: The LCA of nodes 5 and 1 is 3.
        Example 2:
        Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        Output: 5
        Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
        Example 3:
        Input: root = [1,2], p = 1, q = 2
        Output: 1
        Constraints:
        The number of nodes in the tree is in the range [2, 105].
        -109 <= Node.val <= 109
        All Node.val are unique.
        p != q
        p and q will exist in the tree.

        Approach 1: iterative approach using map
        * intuition is to store the parents of p & q in map and traverse both p and q upwords to find the
        parent which is common.
        Algo:
        * create a HashMap with key and value of type TreeNode and queue(LinkedList) of type TreeNode
        * add root to queue and add root to map with null as parent value
        * iterate until either p or q not in map
        * poll the node from queue, if it has left then add left, node to map and left to queue
        * if it has right add right,node to map and right to queue
        * create a set of type TreeNode and add p and all its parents to set by initializing cur=p
        * iterate until cur != null, add cur to set and make cur = map.get(cur)
        * then make cur = q and iterate until cur not in set at each iteration make cur = map.get(cur)
        * return cur at the end which is the common among p & q
        time & space:
        * n time and n space

        Approach 2: recursion dfs
        * intuition is to traverse the tree with normal dfs and when we encounter either p or q then return
        node. if we found nodes from both left and right subtree then current node is LCA else whichever is
        the node we found then that will be our LCA
        algo:
        * in recursion base case is to check if root is null or equal to p or q then return it
        * find a node by calling recursion on left subtree
        * find a node by calling recursion on right subtree
        * if both are present then root is the LCA return it
        * if either is found then return the found node that is the LCA
        * here we traverse tree from top to bottom so if we find p first the tree then q will be somewhere
        in its subtree only so we can be sure that p is LCA similarly if we find q first then q will be
        our lca, if we find both then root will be ur lca
        time & space:
        * n time and n space for recursion
     */
     class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private TreeNode ans=null;
    private int lcLevel=-1;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lowestCommonAncestorA2(root,p,q);
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
