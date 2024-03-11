package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem430 {
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
        Node(int val){
            this.val = val;
        }
    }
    Node dummy;
    Node cur;
    public Node flatten(Node head) {
        dummy = new Node(0);
        dummy.next = head;
        cur = dummy;
        rec2(dummy.next);
        if(dummy.next != null)dummy.next.prev =null;
        head = dummy.next;
        dummy.next = null;
        return head;
    }

    public void rec2(Node head){
        if(head == null)return;
        System.out.println(head.val);
        cur.next = head;
        head.prev = cur;
        cur = cur.next;
        Node next = head.next;
        rec2(head.child);
        head.child = null;
        rec2(next);
    }


    public Node rec(Node cur){
        if(cur==null || (cur.next == null && cur.child==null))
            return cur;
        Node next = cur.next, tail = null;
        if(cur.child != null){
            Node child = cur.child;
            cur.child = null;
            cur.next = child;
            child.prev = cur;
            tail = rec(child);
        }
        if(tail != null){
            tail.next = next;
            if(next != null)
                next.prev = tail;
        }
        if(next == null)return tail;
        else return rec(next);
    }

    public Node flattenA1(Node head) {
        if(head == null)return head;
        Node dummy = new Node(1);
        Node cur = dummy;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while(!stack.empty()){
            Node node = stack.pop();
            if(node.next != null)stack.push(node.next);
            if(node.child != null)stack.push(node.child);
            node.next = null;
            node.child = null;
            cur.next = node;
            node.prev = cur;
            cur = cur.next;
        }
        dummy.next.prev = null;
        return dummy.next;
    }
}