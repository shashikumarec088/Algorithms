package com.github.shashi.leetcode;
import java.util.*;
public class Problem1346  {
    public boolean checkIfExist(int[] arr) {
        return checkIfExistsA2(arr);
    }

    public static void main(String[] args) {
        int[] arr = {7,1,14,11};
        Problem1346 problem1346 = new Problem1346();
        problem1346.checkIfExistA3(arr);
    }
    public boolean checkIfExistA3(int[] arr) {
        Arrays.sort(arr);
        int n=arr.length;
        int j=n-1;
        for(int i=0; i<n;i++){
            if(binSearch(arr,i+1,j,arr[i]))return true;
        }
        return false;
    }

    public boolean binSearch(int[]arr, int s, int e, int val){
        while(s<=e){
            int mid = s + (e-s)/2;
            if((arr[mid]==val * 2) || (arr[mid] * 2==val))return true;
            else if(arr[mid]*2 <val || arr[mid] <val*2 )s=mid+1;
            else e=mid-1;
        }
        return false;
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
