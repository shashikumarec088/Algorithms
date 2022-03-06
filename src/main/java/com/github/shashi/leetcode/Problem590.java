package com.github.shashi.leetcode;
import java.util.*;
public class Problem590 {
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
    public List<Integer> postorder(Node root) {
        return postorderA3(root);
    }

    class Pair<K,V>{
        K key;
        V value;
        Pair(K k, V v){
            this.key = k;
            this.value = v;
        }
    }

    public List<Integer> postorderA3(Node root){
        List<Integer> result = new ArrayList<>();
        if(root==null)return result;
        Stack<Pair<Node,Boolean>> stack = new Stack<>();
        stack.push(new Pair<Node,Boolean>(root,false));
        while(!stack.isEmpty()){
            Pair<Node,Boolean> pair = stack.pop();
            if(pair.value)result.add(pair.key.val);
            else{
                pair.value=true;
                stack.push(pair);
                List<Node> children = pair.key.children;
                for(int i=children.size()-1; i>-1; i--)
                    stack.push(new Pair<Node,Boolean>(children.get(i),false));
            }
        }
        return result;
    }



    public List<Integer> postorderA1(Node root){
        List<Integer> result = new ArrayList<>();
        rec(root, result);
        return result;
    }

    public List<Integer> postorderA2(Node root){
        LinkedList<Integer> result = new LinkedList<>();
        if(root==null) return result;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            result.addFirst(node.val);
            for(Node child : node.children)
                stack.push(child);
        }
        return result;
    }

    public List<Integer> rec(Node root, List<Integer> result){
        if(root==null)return result;
        for(Node child : root.children)
            rec(child,result);
        result.add(root.val);
        return result;
    }
}
