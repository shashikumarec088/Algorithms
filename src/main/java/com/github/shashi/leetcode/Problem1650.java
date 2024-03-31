package com.github.shashi.leetcode;
import java.util.*;
public class Problem1650 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
    public Node lowestCommonAncestor(Node p, Node q) {
        return lowestCommonAncestorA2(p,q);
    }

    /*
    * intuition behind the below approach is keep traversing the both the paths
    * when short one reaches the end reset to long start,during that time the pending
    * lenght the long pointer needs to travel is the length difference between the paths
    * when the long pointer reaches end reset it to short, at this point they are at the
    * same distance from the root then they will meet at the parent
     */
    public Node lowestCommonAncestorA2(Node p, Node q){
        Node a = p, b = q;
        while(a != b){
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
    }

    public Node lowestCommonAncestorA3(Node p, Node q) {
        int dp = depth(p);
        int dq = depth(q);
        while(dp>dq){
            p = p.parent;
            dp--;
        }
        while(dq>dp){
            q = q.parent;
            dq--;
        }
        while(p!=q){
            p=p.parent;
            q=q.parent;
        }
        return p;
    }

    public int depth(Node p){
        int count=0;
        while(p !=null){
            p = p.parent;
            count++;
        }
        return count;
    }

    public Node lowestCommonAncestorA1(Node p, Node q){
        Set<Node> parents = new HashSet<>();
        Node node = p;
        while(node != null){
            parents.add(node);
            node = node.parent;
        }
        node = q;
        while(node != null && !parents.contains(node))
            node = node.parent;
        return node;
    }
}
