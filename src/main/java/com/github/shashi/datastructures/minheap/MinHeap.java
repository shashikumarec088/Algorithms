package com.github.shashi.datastructures.minheap;

import java.util.Arrays;
import java.util.List;

public class MinHeap {
    int size=0;
    int capacity=10;
    int[] heap;
    MinHeap(){
        heap = new int[capacity];
    }

    MinHeap(List<Integer> elements){
        this();
        size = elements.size();
        if(capacity < size){
            resizeHeap(elements.size());
        }
        int i=0;
        for(int element : elements)
            heap[i++] = element;
        for( i=size/2 -1;i>=0; i--)
            heapify(i);
    }

    private void heapify(int i){
        int smallest = i;
        int lc = 2*i + 1;
        int rc = 2*i + 2;

        if(lc < size && heap[lc] < heap[i])
            smallest = lc;

        if(rc < size && heap[rc] < heap[smallest])
            smallest = rc;

        if(i != smallest){
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapify(smallest);
        }
    }


    private void resizeHeap(int newSize){
        capacity = newSize;
        heap = Arrays.copyOf(heap,capacity);
    }

    public static void main(String[] args) {
        MinHeap mh = new MinHeap(Arrays.asList(5,3,6,7,1,0,12));
        mh.removeTop();
        System.out.println(mh.getTop());
    }

    public void insert(int val){
        if(size == capacity){
            System.out.println("heap is full");
        }
        heap[size] = val;
        size++;
        heapifyUp();
    }

    private void heapifyUp(){
        int i = size-1;
        while(hasParent(i)){
            int parent = getParent(i);
            if(heap[i] < heap[parent]){
                swap(i,parent);
                i= parent;
            }else break;
        }
    }

    public void removeTop(){
        if(size==0){
            System.out.println("heap is empty ");
            return;
        }
        size--;
        heap[0] = heap[size];
        heapifyDown();
    }

    private void heapifyDown(){
        int i = 0;
        while(hasLChild(i)){
            int sChild = getLChild(i);
            if(hasRChild(i) && heap[sChild] > heap[getRChild(i)])
                sChild = getRChild(i);
            if(heap[i] > heap[sChild]){
                swap(i,sChild);
                i=sChild;
            }else break;
        }
    }

    public int getTop(){
        if(size==0){
            System.out.println("heap is empty returning default value of -1");
            return -1;
        }
        return heap[0];
    }

    private void swap(int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private boolean hasParent(int i){
        return getParent(i) >=0;
    }

    private int getParent(int i){
        return (i-1)/2;
    }

    private int getLChild(int i){
        return 2*i+1;
    }

    private int getRChild(int i){
        return 2*i+2;
    }

    private boolean hasLChild(int i){
        return getLChild(i) < size;
    }

    private boolean hasRChild(int i){
        return getRChild(i) < size;
    }
    public int size(){
        return size;
    }

}
