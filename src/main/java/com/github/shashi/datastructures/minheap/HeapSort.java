package com.github.shashi.datastructures.minheap;

public class HeapSort {

    public static void main(String[] args) {
        int [] arr = {4,2,14,5,532,233};
        heapsort(arr);
        for(int i = 0; i<arr.length;i++)
            System.out.println(arr[i]);
    }
    public static void heapsort(int[] arr){
        for(int i=arr.length/2 - 1; i>=0; i--)
            maxHeapify(arr,arr.length,i);

        for(int i=arr.length-1; i>0; i--){
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            maxHeapify(arr,i,0);
        }
    }

    public static void maxHeapify(int[] arr, int size, int i){
        int largest = i;
        int lc = 2*i+1;
        int rc = 2*i+2;
        if(lc < size && arr[lc] > arr[largest])
            largest=lc;
        if(rc < size && arr[rc] > arr[largest])
            largest = rc;
        if(i !=largest){
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;
            maxHeapify(arr,size,largest);
        }
    }
}
