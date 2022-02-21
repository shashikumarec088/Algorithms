package com.github.shashi.leetcode;
import java.util.*;
public class Problem138 {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    Map<Node,Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        return copyRandomListA4(head);
    }

    public Node copyRandomListA4(Node head){
        Node original = head;
        Node dummy = new Node(-1);
        Node clone = dummy;
        while(original != null){
            Node newNode = new Node(original.val);
            newNode.next = original.next;
            original.next = newNode;
            original = newNode.next;
        }
        original = head;
        while(original != null){
            Node newNode = original.next;
            if(original.random != null)
                newNode.random = original.random.next;
            original = newNode.next;
        }
        original = head;
        while(original != null){
            Node newNode = original.next;
            original.next = newNode.next;
            clone.next = newNode;
            clone = clone.next;
            original = original.next;
        }
        return dummy.next;
    }

    public Node copyRandomListA1(Node node){
        HashMap<Node,Node> map = new HashMap<>();
        return dfs(node,map);
    }

    public Node cloneNode(Node node){
        if(node != null){
            if(map.containsKey(node))
                return map.get(node);
            else{
                map.put(node,new Node(node.val));
                return map.get(node);
            }
        }
        return null;
    }

    public Node copyRandomListA3(Node head){
        if(head==null) return null;
        Node oldNode = head;
        Node newNode = new Node(oldNode.val);
        map.put(oldNode,newNode);
        while(oldNode != null){
            newNode.random = cloneNode(oldNode.random);
            newNode.next = cloneNode(oldNode.next);
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return map.get(head);
    }

    public Node copyRandomListA2(Node node){
        Node dummy = new Node(-1);
        Node newClone = dummy, cur = node;
        Map<Node, Node> map = new HashMap<>();
        while(cur != null){
            Node newNode;
            if(map.containsKey(cur))
                newNode = map.get(cur);
            else{
                newNode = new Node(cur.val);
                map.put(cur,newNode);
            }
            if(cur.random != null){
                if(map.containsKey(cur.random))
                    newNode.random = map.get(cur.random);
                else{
                    Node rand = new Node(cur.random.val);
                    newNode.random = rand;
                    map.put(cur.random,rand);
                }
            }
            newClone.next = newNode;
            newClone = newClone.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public Node dfs(Node node, HashMap<Node,Node> map){
        if(node == null)return null;
        if(map.containsKey(node)) return map.get(node);
        Node nNode = new Node(node.val);
        map.put(node, nNode);
        nNode.next = dfs(node.next,map);
        nNode.random = dfs(node.random,map);
        return map.get(node);
    }
}