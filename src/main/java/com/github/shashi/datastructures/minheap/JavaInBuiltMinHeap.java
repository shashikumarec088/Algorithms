package com.github.shashi.datastructures.minheap;

import java.util.PriorityQueue;

public class JavaInBuiltMinHeap {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(1);
        pq.add(2);
        pq.add(3);

        System.out.println("top element in the heap "+pq.peek());
        System.out.println("print complete heap : "+pq.toString());
        System.out.println("remove top element "+pq.poll());
        System.out.println("print heap after removing the top element : "+pq.toString());

    }
}
