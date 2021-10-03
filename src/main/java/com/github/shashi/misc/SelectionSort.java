package com.github.shashi.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectionSort {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(3,4,2,1,0,5));
        int [] input = {3,4,2,1,0,5};
        selectionSort(input);
        for(int i : input){
            System.out.print(i);
            System.out.println();
        }
    }
    public static void selectionSort(int[] array){
        for(int i = 0; i < array.length;i++ ){
            int minElementIndex = i;
            for(int j = i+1; j < array.length; j++){
                if(array[j] < array[minElementIndex]){
                    minElementIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minElementIndex];
            array[minElementIndex] = temp;
        }
    }

    public static List<Integer> sort(List<Integer> input){
        for(int i = 0; i < input.size() - 1; i ++){
            int jIndex = i;
            for(int j = i+1; j < input.size(); j++){
                if(input.get(j) < input.get(jIndex)){
                    jIndex = j;
                }
            }
                Integer temp = input.remove(jIndex);
                input.add(i,temp);
        }
        return input;
    }

    public static int[] sort(int[] input){
        for(int i = 0; i < input.length - 1; i ++){
            int jIndex = i;
            for(int j = i+1; j < input.length; j++){
                if(input[j] < input[jIndex]){
                    jIndex = j;
                }
            }
            int key = input[jIndex];
            while(jIndex > i){
                input[jIndex] = input[jIndex -1];
                jIndex --;
            }
            input[i] = key;
        }
        return input;
    }
}
