package com.github.shashi.datastructures.minheap;

import com.sun.xml.internal.ws.api.model.CheckedException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class NthLargestNumber {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3,4,5,6,7,8,20);
        System.out.println(getNthLargestA3(list,7));
    }
    public static int getNthLargestA1(List<Integer> elements,int n){
        MinHeap heap = new MinHeap();
        for(Integer element : elements)
            heap.insert(-1 * element);
        for(int i=0; i<n-1; i++)
            heap.removeTop();
        return heap.getTop()*-1;
    }

    public static int getNthLargestA2(List<Integer> elements, int n){
        MinHeap heap = new MinHeap();
        for(Integer element : elements){
            if(heap.size()<n)
                heap.insert(element);
            else {
                if(element > heap.getTop()){
                    heap.removeTop();
                    heap.insert(element);
                }
            }
        }
        return heap.getTop();
    }

    public static int getNthLargestA3(List<Integer> elements, int n) {
        //PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->{
            if(a<b) return 1;
            else if (a==b) return 0;
            else return -1;
        });
        for (Integer element : elements)
            pq.add(element);
        for (int i = 0; i < n - 1; i++)
            pq.remove();
        return pq.peek();
    }

    public static int getNthLargestA4(List<Integer> elements, int n){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(Integer element : elements){
            if(heap.size()<n)
                heap.add(element);
            else {
                if(element > heap.peek()){
                    heap.poll();
                    heap.add(element);
                }
            }
        }
        return heap.peek();
    }

}
