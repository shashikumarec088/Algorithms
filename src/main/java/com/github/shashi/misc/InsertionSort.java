package com.github.shashi.misc;

public class InsertionSort {
    public static void main(String[] args) {
        InsertionSort ins = new InsertionSort();
        int[] arr = new int[]{4,5,6,2,4,1};
        ins.sort(arr);
        for(int i=0; i<arr.length;i++){
            System.out.print(arr[i]+",");
        }
    }
    public void sort(int[] nums){
        int n = nums.length;
        for(int i = 1; i <n;i++ ){
            int hole = i;
            int value = nums[i];
            while(hole >0 && nums[hole-1] > value){
                nums[hole] = nums[hole-1];
                hole--;
            }
            nums[hole] = value;
        }
    }
}
