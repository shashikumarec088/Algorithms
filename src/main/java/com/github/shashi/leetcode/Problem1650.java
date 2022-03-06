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

    public Node lowestCommonAncestorA2(Node p, Node q){
        Node a = p, b = q;
        while(a != b){
            a = a == null ? q : a.parent;
            b = b == null ? p : b.parent;
        }
        return a;
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
