package com.github.shashi.leetcode;
import java.util.*;
public class Problem559 {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
    private int depth=-1;
    public int maxDepth(Node root) {
        return maxDepthA3(root);
    }

    class Pair<K,V>{
        K key;
        V value;
        Pair(K k, V v){
            key=k;
            value=v;
        }
    }

    public int maxDepthA3(Node root){
        if(root==null)return 0;
        Stack<Pair<Node,Integer>> stack = new Stack<>();
        int depth=0;
        stack.push(new Pair<>(root,1));
        while(!stack.isEmpty()){
            Pair<Node,Integer> pair = stack.pop();
            depth=Math.max(depth,pair.value);
            for(Node child: pair.key.children)
                stack.push(new Pair<>(child,pair.value+1));
        }
        return depth;
    }

    public int maxDepthA2(Node root){
        if(root==null)return 0;
        else if(root.children.isEmpty())return 1;
        else{
            List<Integer> heights = new ArrayList<>();
            for(Node child: root.children)
                heights.add(maxDepthA2(child));
            return Collections.max(heights)+1;
        }
    }

    public int maxDepthA1(Node root){
        if(root==null)return 0;
        rec(root,1);
        return depth;
    }

    public void rec(Node root, int level){
        if(root==null)return;
        depth = Math.max(depth,level);
        for(Node child: root.children)
            rec(child,level+1);
    }
}