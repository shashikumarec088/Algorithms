package com.github.shashi.leetcode;
import java.util.*;
public class Problem1346  {
    public boolean checkIfExist(int[] arr) {
        return checkIfExistsA2(arr);
    }

    public boolean checkIfExistA1(int[] arr){
        int n = arr.length;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && arr[i] == 2*arr[j])
                    return true;
            }
        }
        return false;
    }

    public boolean checkIfExistsA2(int[] arr){
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<arr.length; i++){
            if(set.contains(2*arr[i]) || (arr[i]%2 == 0 && set.contains(arr[i]/2)))
                return true;
            set.add(arr[i]);
        }
        return false;
    }
}
