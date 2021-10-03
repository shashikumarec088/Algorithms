package com.github.shashi.misc;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Heap {

    public static void main(String[] args){
        Heap heap = new Heap();
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.add(4);
        heap.add(5);
        heap.printHeap();
    }
    private int[] heap;
    private int size;
    private int capacity = 10;
    public Heap(){
        this.heap = new int[capacity];
        this.size = 0;
    }

    private int getParentIndex(int i){
        return (i -1)/2;
    }

    private int getLeftChildIndex(int i){
        return 2*i + 1;
    }

    private int getRightChildIndex(int i){
        return 2*i + 2;
    }

    private boolean hasParent(int i){
        return getParentIndex(i) >= 0;
    }

    private boolean hasLeftChild(int i){
        return getLeftChildIndex(i) < size;
    }

    private boolean hasRightChild(int i){
        return getRightChildIndex(i) < size;
    }

    private int getParent(int i){
        return heap[getParentIndex(i)];
    }

    private int getLeftChild(int i){
        return heap[getParentIndex(i)];
    }

    private int getRightChild(int i){
        return heap[getRightChildIndex(i)];
    }

    public int peek(){
        if(size == 0) throw new IllegalStateException("heap is empty");
        return heap[0];
    }

    public int getMin(){
        if(size == 0) throw new IllegalStateException("heap is empty");
        return heap[0];
    }

    private void ensureCapacity(){
        if(size == heap.length){
            Arrays.copyOf(heap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    public void add(int item){
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    public int poll(){
        if(size == 0) throw new IllegalStateException("heap is empty");
        int item = heap[0];
        size --;
        heap[0] = heap[size];
        heapifyDown();
        return item;
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyDown(){
       int index = 0;
       while (hasLeftChild(index)){
           int smallerIndex = getLeftChildIndex(index);
           if(hasRightChild(index) && heap[smallerIndex] > getRightChild(index)){
               smallerIndex = getRightChildIndex(index);
           }
           if(heap[index] < heap[smallerIndex]){
               break;
           }else{
               swap(index, smallerIndex);
           }
           index = smallerIndex;
       }
    }

    public void printHeap(){
        System.out.println(Arrays.stream(heap)
                .boxed()
                .collect(Collectors.toList()));
    }

    private void heapifyUp(){
        int index = size - 1;
        while (hasParent(index) && getParent(index) > heap[index]){
            swap(index,getParentIndex(index));
            index = getParentIndex(index);
        }
    }

}
