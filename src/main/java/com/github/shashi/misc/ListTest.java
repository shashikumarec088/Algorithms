package com.github.shashi.misc;

import java.util.*;

public class ListTest {
    public static void main(String[] args) {
       int[] x = {1,2,3};
       for(int a : x){
           System.out.println(a);
       }

       for(int i=0; i<x.length; i++){
           System.out.println(x[i]);
       }

       List<Integer> ll = new ArrayList<>();

    }

    public static class CircularLL{
        Node head;
        CircularLL(){
            this.head = null;
        }

        public void add(int data){
            Node newNode = new Node(data, this.head);
            if(this.head == null) {
                newNode.next = newNode;
                this.head = newNode;
            }
            else{
                Node current = this.head;
                while (current.next != this.head){
                    current = current.next;
                }
                newNode.next = current.next;
                current.next = newNode;
            }
        }

        public void print(){
            Node current = this.head;

            if(current != null){
                System.out.println(current.data);
                while (current.next != this.head){
                    current = current.next;
                    System.out.println(current.data);
                }
            }
        }
        public static class Node{
            int data;
            Node next;
            Node(int data, Node next){
                this.data = data;
                this.next = next;
            }
        }
    }

    static List<String> generateUsingQueue(int n){
        Queue<String> queue = new ArrayDeque<>();
        List<String> list = new ArrayList<>();
        queue.add("1");
        if(n > 1){
            int i = 2;
            while(i <= n){
                queue.add(queue.peek()+"0");
                queue.add(queue.peek()+"1");
                list.add(queue.poll());
            }
        }
        return list;
    }
    static ArrayList<String> generate(int N)
    {
        ArrayList<String> values = new ArrayList<>();
        if(N>0 && N <= Math.pow(10,9)) {
            values.add("1");
            if (N == 1)
                return values;
            for (int i = 2; i <= N; i++) {
                int times = i / 2;
                int mod = i % 2;
                String timesStr = values.get(times -1) + mod;
                values.add(timesStr);
            }
        }
        return values;
    }

    public static class DoubleLinkedList{
        Node head;
        DoubleLinkedList(){
            this.head = null;
        }

        public void addAtFront(int data){
            Node node = new Node(data,null,this.head);
            if(this.head != null)
                this.head.prev = node;
            this.head = node;
        }

        public void addAfter(Node node, int data){
            if(node != null){
                Node nextNode = node.next;
                Node newNode = new Node(data,node,nextNode);
                node.next = newNode;
                if(nextNode != null)
                    nextNode.prev = newNode;
            }
        }

        public void addBefore(Node node, int data){
            if(node != null){
                Node prevNode = node.prev;
                Node newNode = new Node(data,prevNode,node);
                node.prev = newNode;
                if(prevNode != null)
                    prevNode.next = newNode;
                else this.head = newNode;
            }
        }

        public void addAtTheEnd(int data){
            Node node = new Node(data,null,null);
            if(this.head == null){
                this.head = node;
            }else{
                Node firstNode = this.head;
                while(firstNode.next != null){
                    firstNode = firstNode.next;
                }
                node.prev = firstNode;
                firstNode.next = node;
            }
        }

        public void printList(){
            Node first = this.head;
            while (first != null){
                System.out.println(first.data);
                first = first.next;
            }
        }


        public static class Node{
            Node prev;
            Node next;
            int data;
            Node(int data, Node prev, Node next){
                this.data = data;
                this.prev = prev;
                this. next = next;
            }
        }
    }

    public static class SingleLinkedList{
        Node head;
        Node tail;
        SingleLinkedList(){
            this.head = null;
            this.tail = null;
        }

        public int getCount(Node node){
            if(node == null)
                return 0;
            return 1 + getCount(node.next);
        }

        public boolean findRec(Node head, int data){
            if(head == null )
                return false;
            else if(head.data == data){
                return  true;
            }
            else return findRec(head.next, data);
        }

        public boolean find(int data){
            boolean found = false;
            Node firstNode = this.head;
            while (firstNode != null){
                if(firstNode.data == data){
                    found = true;
                    break;
                }
                firstNode = firstNode.next;

            }
            return found;
        }

        public void insertFirst(int data){
            Node newNode = new Node(data, this.head);
            this.head = newNode;
            if(head.next == null)
                this.tail = this.head;
        }

        public void addAfter(int data, Node node){
            if(node == null)
                this.insertFirst(data);
            else{
                Node newNode = new Node(data, node.next);
                node.next = newNode;
                if(newNode.next == null)
                    this.tail = newNode;
            }
        }

        public void deleteByIndex(int index){

            if(index == 0){
                if(this.head != null){
                    Node next = this.head.next;
                    this.head = next;
                    if(next == null)
                        this.tail = null;
                }
            }else{
                Node currentNode = this.head;
                Node prev = null;
                for(int i = 1 ; i <= index ; i ++){
                    if(currentNode != null){
                        prev = currentNode;
                        currentNode = currentNode.next;
                    }
                }
                if(currentNode != null){
                    if(prev == null){
                        this.head = currentNode.next;
                        if(this.head == null)
                            this.tail = null;
                    }else{
                        prev.next = currentNode.next;
                        if(prev.next == null)
                            this.tail = prev;

                    }
                }
            }

        }

        public void delete(int data){
            Node firstNode = this.head;
            Node prev = null;
            while(firstNode != null){
                if(data == firstNode.data){
                    if(prev != null){
                        prev.next = firstNode.next;
                        if(prev.next == null)
                            this.tail = prev;
                    }else{
                        this.head = firstNode.next;
                        if(this.head != null && this.head.next == null)
                            this.tail = this.head;
                    }
                }
                prev = firstNode;
                firstNode = firstNode.next;
            }
        }

        public void add(int data){
            if(this.head == null) {
                this.head = new Node(data, null);
                this.tail = this.head;
            }
            else{
                this.tail.next = new Node(data,null);
                this.tail = this.tail.next;
            }
        }

        public static class Node{
            int data;
            Node next;
            Node(int data, Node next){
                this.data = data;
                this.next = next;
            }
        }
    }
}
