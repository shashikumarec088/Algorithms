package com.github.shashi.leetcode;
import java.util.*;
public class Problem895 {
    // 2nd approach usign stack of stack it is very easy to understand and implement
    class FreqStack{
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Stack<Integer>> freqMap = new HashMap<>();
        int maxFreq=0;
        FreqStack(){}

        public void push(int val){
            int freq = map.getOrDefault(val,0)+1;
            map.put(val,freq);
            Stack<Integer> stack = freqMap.getOrDefault(freq, new Stack<>());
            freqMap.put(freq,stack);
            stack.push(val);
            maxFreq = Math.max(maxFreq,freq);
        }

        public int pop(){
            int val = freqMap.get(maxFreq).pop();
            map.put(val,map.get(val)-1);
            if(freqMap.get(maxFreq).isEmpty())
                maxFreq--;
            return val;
        }
    }
    public static void main(String[] args) {
        Problem895 problem895 = new Problem895();
        problem895.push(5);
        problem895.push(7);
        problem895.push(5);
        problem895.push(7);
        problem895.push(4);
        problem895.push(5);
    }
    class Node{
        Node prev, next;
        int count, value;
        Node(int value, int count){
            this.value = value;
            this.count = count;
        }
    }

    class DLL{
        Node head, tail;
        int size;
        DLL(){
            head = new Node(-1,0);
            tail = new Node(-1,0);
            head.next = tail;
            tail.prev = head;
            size=0;
        }

        public void addAfter(Node node, Node nNode){
            Node next = node.next;
            node.next = nNode;
            nNode.prev = node;
            nNode.next = next;
            next.prev = nNode;
            size++;
        }

        public void add(Node node){
            if(size==0)addAfter(head,node);
            else addAfter(getLast(),node);
        }

        public void remove(Node node){
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public Node getLast(){
            Node last=null;
            if(size>0)
                last = tail.prev;
            return last;
        }

        public Node removeLast(){
            Node last = getLast();
            if(last != null)
                remove(last);
            return last;
        }
    }

    Map<Integer,Node> map = new HashMap<>();
    Map<Integer,DLL> freqMap = new HashMap<>();
    int maxFreq=0;

    public Problem895() {}

    public void push(int val) {
        if(map.containsKey(val)){
            Node node = map.get(val);
            node.count++;
            DLL dll = freqMap.getOrDefault(node.count, new DLL());
            dll.add(new Node(val,node.count));
            maxFreq = Math.max(node.count,maxFreq);
            freqMap.put(node.count,dll);
        }else{
            Node node = new Node(val,1);
            map.put(val,node);
            DLL dll = freqMap.getOrDefault(node.count, new DLL());
            dll.add(new Node(val,node.count));
            maxFreq = Math.max(node.count,maxFreq);
            freqMap.put(node.count,dll);
        }
    }

    public int pop() {
        DLL dll = freqMap.get(maxFreq);
        Node node = dll.removeLast();
        if(dll.size==0)maxFreq--;
        if(node.count==1)map.remove(node.value);
        else map.get(node.value).count--;
        return node.value;
    }

}