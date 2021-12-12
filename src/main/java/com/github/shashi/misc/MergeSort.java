package com.github.shashi.misc;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] input = new int[]{5,2,3,1};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(input,0,input.length-1);
        for(int item : input)
            System.out.print(item+",");
    }
    public void sort(int[] nums, int l, int r){
        if(l>= r) return;
        int mid = l + (r-l)/2;
        sort(nums,l,mid);
        sort(nums,mid+1,r);
        merge(nums,l,mid,r);
    }

    private void merge(int[] a3, int start, int mid, int end){
        int[] a1 = Arrays.copyOfRange(a3,start,mid+1);
        int[] a2 = Arrays.copyOfRange(a3,mid+1,end+1);
        int l = a1.length, r = a2.length,i =0,j=0,k=start;
        while(i<l && j<r){
            if(a1[i] <= a2[j])
                a3[k++] = a1[i++];
            else a3[k++] = a2[j++];
        }
        if(i<l)
            while (i<l) a3[k++] = a1[i++];
        if(j<l)
            while (j<r) a3[k++] = a2[j++];
    }
}
