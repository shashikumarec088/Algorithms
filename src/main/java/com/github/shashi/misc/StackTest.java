package com.github.shashi.misc;

public class StackTest {
    public static void main(String[] args) {
        StackArray st = new StackArray(5);
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);
        st.push(6);
        st.printStack();

    }

    public static class StackLL{
         Node head;
         int size;
         int maxSize;
         StackLL(int maxSize){
             this.head = null;
             this.size = 0;
             this.maxSize = maxSize;
         }

         public void print(){
             Node current = head;
             while (current != null){
                 System.out.println(current.data);
                 current = current.next;
             }
         }

         public int peek(){
             int data = -1;
             if(size == 0){
                 System.out.println("Stack is empty");
             }else {
                 data = head.data;
             }
             return data;
         }
         public int pop(){
             int data = -1;
             if(size == 0){
                 System.out.println("Stack is empty");
             }else {
               data = head.data;
               head = head.next;
               size -= 1;
             }
             return data;
         }

         public void push(int data){
             if(size == maxSize){
                 System.out.println("Stack is full");
             }else{
                 Node newNode = new Node(data);
                 if(this.head == null){
                     this.head = newNode;
                 }else{
                     newNode.next = head;
                     head = newNode;
                 }
                 size += 1;
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
    public static class StackArray{
        int arr[];
        int size;
        StackArray(int size){
            this.arr = new int[size];
            this.size = 0;
        }
        public void push(int data){
            if(size == arr.length){
                System.out.println("stack full");
            }else{
                arr[size++] = data;
            }
        }
        public int peek(){
            if(size == 0){
                System.out.println("stack is empty");
                return -1;
            }else
                return arr[size - 1];
        }

        public int pop(){
            if(size == 0){
                System.out.println("stack is empty");
                return -1;
            }else{
                size -= 1;
                int value = arr[size];
                arr[size] = 0;
                return value;
            }

        }

        public int getSize(){
            return size;
        }

        public void printStack(){
            for(int i = 0 ; i < size; i ++)
                System.out.println(arr[i]);
        }
    }
}
