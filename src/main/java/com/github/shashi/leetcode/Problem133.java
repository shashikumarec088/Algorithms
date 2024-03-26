package com.github.shashi.leetcode;

import java.util.*;

public class Problem133 {
    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }
    public Node cloneGraph(Node node) {
        return cloneGraphA2(node,new HashMap<>());
    }

    public Node cloneGraphA2(Node node, Map<Node,Node> map) {
        if(node == null)return null;
        if(map.containsKey(node))return map.get(node);
        Node newNode = new Node(node.val);
        map.put(node,newNode);
        for(Node nei : node.neighbors)
            newNode.neighbors.add(cloneGraphA2(nei,map));
        return newNode;
    }

    public Node cloneGraphA3(Node node) {
        if(node==null)return null;
        Queue<Node> queue = new ArrayDeque<>();
        Map<Node, Node> map = new HashMap<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node old = queue.poll();
            Node newN = null;
            if(!map.containsKey(old)){
                newN = new Node(old.val);
                map.put(old,newN);
            }else newN = map.get(old);
            for(Node nei : old.neighbors){
                if(!map.containsKey(nei)){
                    queue.offer(nei);
                }
                Node nNei = null;
                if(!map.containsKey(nei)){
                    nNei = new Node(nei.val);
                    map.put(nei,nNei);
                }else nNei = map.get(nei);

                newN.neighbors.add(nNei);
            }

        }
        return map.get(node);
    }



    public Node cloneGraphA1(Node node) {
        if(node==null)return null;
        Stack<Node> stack = new Stack<>();

        Map<Node, Node> map = new HashMap<>();
        stack.push(node);
        while(!stack.isEmpty()){
            Node old = stack.pop();
            Node newN = null;
            if(!map.containsKey(old)){
                newN = new Node(old.val);
                map.put(old,newN);
            }else newN = map.get(old);
            for(Node nei : old.neighbors){
                if(!map.containsKey(nei)){
                    stack.push(nei);
                }
                Node nNei = null;
                if(!map.containsKey(nei)){
                    nNei = new Node(nei.val);
                    map.put(nei,nNei);
                }else nNei = map.get(nei);

                newN.neighbors.add(nNei);
            }

        }
        return map.get(node);
    }
}