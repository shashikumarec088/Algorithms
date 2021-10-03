package com.github.shashi.misc;

class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList obj = new MyLinkedList();
  obj.addAtIndex(0,10);
        obj.addAtIndex(0,20);
        obj.addAtIndex(1,30);
        System.out.println(obj.get(0));
    }
    static class Node {
        Node next;
        Node prev;
        int val;
        public Node(int val){
            this.val = val;
        }
    }
    private Node head;
    private Node tail;
    private int length;

    public MyLinkedList() {
        this.length = 0;
    }

    public int get(int index) {
        int value = -1;
        Node node = getNode(index);
        if(node != null){
            value = node.val;
        }
        return value;
    }

    private Node getNode(int index){
        Node node = null;
        if(index > -1 && index < length){
            node = head;
            for(int i = 1; i <= index; i++)
                node = node.next;
        }
        return node;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        if(length == 0){
            this.head = node;
            this.tail = head;
        }else{
            node.next = head;
            head.prev = node;
            head = node;
        }
        length++;

    }

    public void addAtTail(int val) {
        if(length == 0){
            addAtHead(val);
        }else{
            Node node = new Node(val);
            node.prev = tail;
            tail.next = node;
            tail = node;
            length++;
        }
    }

    public void addAtIndex(int index, int val) {
        if(index <= length && index > -1){
            if(length == 0){
                addAtHead(val);
            }else if(index == length){
                addAtTail(val);
            }else{
                Node node = getNode(index);
                Node newNode = new Node(val);
                Node prev = node.prev;
                newNode.prev = prev;
                if(prev != null)
                    prev.next = newNode;
                else head = newNode;
                newNode.next = node;
                node.prev = newNode;
                length++;
            }
        }
    }

    public void deleteAtIndex(int index) {
        if(index < length && index > -1){
            if(length == 1){
                head = null;
                tail = null;
            }else{
                Node node = getNode(index);
                Node prev = node.prev;
                if(prev != null)
                    prev.next = node.next;
                else head = node.next;
                if(node.next != null)
                    node.next.prev = prev;
                else tail = prev;
            }
            length--;
        }
    }
}
