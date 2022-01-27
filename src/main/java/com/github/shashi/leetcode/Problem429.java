package com.github.shashi.leetcode;
import java.util.*;
public class Problem429 {
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
    public List<List<Integer>> levelOrder(Node root) {
        return levelBfs(root);
    }

    public List<List<Integer>> levelRec(Node root){
        List<List<Integer>> result = new ArrayList<>();
        rec(root,0,result);
        return result;
    }

    public void  rec(Node root, int level, List<List<Integer>> result){
        if(root == null)return;
        if(result.size()==level)
            result.add(new ArrayList<>());
        result.get(level).add(root.val);
        for(Node child : root.children)
            rec(child,level+1,result);
    }

    public List<List<Integer>> levelBfsPrev(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        List<Node> prevLayer = new ArrayList<>();
        prevLayer.add(root);
        while(!prevLayer.isEmpty()){
            List<Node> curLayer = new ArrayList<>();
            List<Integer> prevVals = new ArrayList<>();
            for(Node node : prevLayer){
                prevVals.add(node.val);
                curLayer.addAll(node.children);
            }
            result.add(prevVals);
            prevLayer = curLayer;
        }
        return result;
    }

    public List<List<Integer>> levelBfs(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<size; i++){
                Node node = queue.poll();
                list.add(node.val);
                if(node.children != null){
                    for(Node child : node.children)
                        queue.add(child);
                }
            }
            result.add(list);
        }
        return result;
    }
}
