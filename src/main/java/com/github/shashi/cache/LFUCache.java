package com.github.shashi.cache;

import java.util.HashMap;
import java.util.Map;

 class LFUNode{
    LFUNode prev,next;
    int key,val,count;
    LFUNode(int key,int val){
        this.key = key;
        this.val = val;
        this.count =1;
    }
}

class DLL{
    LFUNode head,tail;
    int size;
    DLL(){
        this.head = new LFUNode(-1,-1);
        this.tail = new LFUNode(-1,-1);
        head.next = tail;
        tail.prev = head;
        size=0;
    }

    public void add(LFUNode node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    public void remove(LFUNode node){
        if(size >0){
            LFUNode prev = node.prev;
            LFUNode next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }
    }

    public LFUNode removeLast(){
        LFUNode node = null;
        if(size>0){
            node = tail.prev;
            remove(node);
        }
        return node;
    }
}

public class LFUCache {

    private Map<Integer,LFUNode> map;
    private Map<Integer,DLL> freqMap;
    int capacity,minFreq;

    public LFUCache(int capacity) {
        this.map = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        minFreq = 0;
    }


    public int get(int key) {
        int value = -1;
        if(map.containsKey(key)){
            LFUNode node = map.get(key);
            value = node.val;
            updateFreq(node);
        }
        return value;
    }

    public void updateFreq(LFUNode node){
        DLL list = freqMap.get(node.count);
        list.remove(node);
        if(minFreq == node.count && list.size ==0){
            minFreq++;
        }
        node.count++;
        list = freqMap.getOrDefault(node.count,new DLL());
        list.add(node);
        freqMap.put(node.count,list);
    }

    public void put(int key, int value) {
        if(capacity == 0) return;
        if(map.containsKey(key)){
            LFUNode node = map.get(key);
            node.val = value;
            updateFreq(node);
        }else{
            if(map.size() == capacity){
                LFUNode node = freqMap.get(minFreq).removeLast();
                map.remove(node.key);
            }
            LFUNode node = new LFUNode(key,value);
            minFreq = 1;
            map.put(key,node);
            DLL list = freqMap.getOrDefault(node.count,new DLL());
            list.add(node);
            freqMap.put(node.count,list);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */