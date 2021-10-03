package com.github.shashi.cache;

import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev,next;
    int val,key;
    Node(int val){
        this.val = val;
    }
    Node(int key,int val){
        this.key = key;
        this.val = val;
    }
}

class DLinkedList{
    private Node head, tail;
    private int size;
    DLinkedList(){
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void add(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    public void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    public Node removeLast(){
        Node last = null;
        if(size >0){
            last = tail.prev;
            remove(tail.prev);
        }
        return last;
    }

}

public class LRUCache {

    private Map<Integer,Node> map;
    private DLinkedList ll;
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<Integer, Node>();
        this.ll = new DLinkedList();
        this.capacity = capacity;
    }

    public int get(int key) {
        int val = -1;
        if(map.containsKey(key)){
            Node node = map.get(key);
            val = node.val;
            ll.remove(node);
            ll.add(node);
        }
        return val;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            ll.remove(node);
            ll.add(node);
        }else{
            if(map.size() == capacity){
                Node lastNode = ll.removeLast();
                map.remove(lastNode.key);
            }
            Node newNode  = new Node(key,value);
            ll.add(newNode);
            map.put(key,newNode);
        }
    }
}

