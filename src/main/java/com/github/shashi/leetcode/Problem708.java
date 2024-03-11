package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem708 {
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
    public Node insert(Node head, int insertVal) {
        return insertA2(head, insertVal);
    }

    public Node insertA2(Node head, int val){

        Node node = new Node(val);
        if(head==null){
            node.next = node;
            return node;
        }

        Node prev = head, cur = head.next;
        boolean flag = false;
        do{
            if(val >= prev.val && val <= cur.val)
                flag=true;
            else if(prev.val> cur.val){
                if(val >= prev.val || val <= cur.val)
                    flag = true;
            }
            if(flag){
                prev.next = node;
                node.next = cur;
                return head;
            }
            prev = cur;
            cur = cur.next;
        }while(prev != head);
        prev.next = node;
        node.next = cur;
        return head;
    }

    public Node insertA1(Node head, int val) {
        Node node = new Node(val);
        if(head==null){
            node.next = node;
            return node;
        }
        if(head.next == head){
            head.next = node;
            node.next = head;
            return head;
        }
        Node prev = head, cur=head.next;
        while(cur!= null ){
            System.out.println(cur.val);
            if(prev.val > cur.val || cur == head)break;
            prev = cur;
            cur = cur.next;
        }
        Node start = cur;
        while(cur.val <val){
            System.out.println(cur.val);
            prev = cur;
            cur = cur.next;
            if(cur == start)break;

        }
        prev.next = node;
        node.next = cur;
        return head;
    }
}