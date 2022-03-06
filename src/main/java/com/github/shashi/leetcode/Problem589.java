package com.github.shashi.leetcode;
import java.util.*;
public class Problem589 {
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
    public List<Integer> preorder(Node root) {
        return preorderA2(root);
    }

    public List<Integer> preorderA1(Node root){
        List<Integer> result = new ArrayList<>();
        rec(root, result);
        return result;
    }

    public List<Integer> rec(Node root, List<Integer> result){
        if(root==null)return result;
        result.add(root.val);
        for(Node child : root.children)
            rec(child,result);
        return result;
    }

    public List<Integer> preorderA2(Node root){
        List<Integer> result = new ArrayList<>();
        if(root==null)return result;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            result.add(node.val);
            List<Node> children = node.children;
            for(int i=children.size()-1; i>-1; i--)
                stack.push(children.get(i));
        }
        return result;
    }
}