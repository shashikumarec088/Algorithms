package com.github.shashi.misc;

public class BubleSort {
    public static void main(String[] args) {
        int [] input = {3,4,2,1,0,5};
        bubbleSort(input);
        for(int i : input){
            System.out.print(i);
            System.out.println();
        }
    }

    public static void bubbleSort(int[] array){
        boolean swapped = false;
        for(int i = 0; i < array.length -1; i++){
            for(int j = 0; j < array.length - i -1; j++){
                if(array[j] > array[j+1]) {
                    swapped = true;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if(!swapped) break;
        }
    }
}
