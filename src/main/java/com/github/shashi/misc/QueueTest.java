package com.github.shashi.misc;

public class QueueTest {

    public static void main(String[] args) {
        QueueLL queueArray = new QueueLL(5);
        queueArray.enQueue(1);
        queueArray.enQueue(2);
        queueArray.enQueue(3);
        queueArray.enQueue(4);
        queueArray.enQueue(5);
        queueArray.enQueue(6);
        queueArray.print();
    }

    public static class QueueLL{
        Node head;
        Node tail;
        int size;
        int capacity;
        QueueLL(int capacity){
            this.size = 0;
            this.capacity = capacity;
            this.head = this.tail = null;
        }

        public void print(){
            Node first = head;
            while (first != null){
                System.out.println(first.data);
                first = first.next;
            }
        }

        public int deQueue(){
            int data = -1;
            if(size == 0){
                System.out.println("queue is empty");
            }else{
                data = head.data;
                head = head.next;
                size--;
                if(size == 0){
                    tail = null;
                }
            }
            return data;
        }

        public void enQueue(int data){
            if(size == capacity){
                System.out.println("queue is full");
            }else{
                Node newNode = new Node(data);
                if(size == 0){
                    head = tail = newNode;
                }else{
                    tail.next = newNode;
                    tail = newNode;
                }
                size++;
            }
        }

        public static class Node{
            int data;
            Node next;
            Node(int data){
                this.data = data;
            }
        }
    }

    public static class QueueArray{
        int front, rear,size;
        int array[];
        QueueArray(int size){
            front = 0;
            rear = 0;
            this.array = new int[size];
            this.size = 0;
        }

        public void enQueue(int data){
            if(size == array.length){
                System.out.println("queue is full");
            }else{
                array[rear++] = data;
                rear = rear % array.length;
                size += 1;

            }
        }

        public int deQueue(){
            int data = -1;
            if(size == 0){
                System.out.println("queue is empty");
            }else{
                data = array[front++];
                front = front % array.length;
                size -= 1;
            }
           return data;
        }

        public void printElements(){
            if(size != 0){
                int first = front;
                for (int i = first; i < size; i++){
                    System.out.println(array[i]);
                    i = i % array.length;
                }
            }
        }
    }
}
