package com.github.shashi.leetcode;

import java.util.*;

public class Problem1207 {
    /*
    1207. Unique Number of Occurrences
    Given an array of integers arr, return true if the number of occurrences of each value in the array is
    unique or false otherwise.



    Example 1:

    Input: arr = [1,2,2,1,1,3]
    Output: true
    Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same
    number of occurrences.
    Example 2:

    Input: arr = [1,2]
    Output: false
    Example 3:

    Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
    Output: true


    Constraints:

    1 <= arr.length <= 1000
    -1000 <= arr[i] <= 1000

    approach 1: counting sort
    * inuition is to count the frequencies and sort them then check if any 2 adjacent elements are same
    algo:
    * since numbers range is between -1000 to 1000 create an array of size 2001 this is to adjust
    the negative numbers
    * iterate over the array and increment the count at index arr[i+1000]
    * sort the array
    * iterate from i=1 to 2000 and check if arr[i]>0 and arr[i-1] == arr[i] then return false
    * return true at the end
    time & space:
    * takes n + k log k time, where k is 2001, takes n space

    approach 2: using map and set
    * iterate over numbers and count frequencies, then iterate over map and check if any freq seen before
    and return false else return true at the end
    algo:
    * create a map of type integer and integer and iterate over the array and count the frequencies
    * create a set of type integer
    * iterate over the map
        * check if the frequency is already seen if so return false
        * add freq to set
    * return false at the end
    time & space:
    * n time and n space

     */

    public boolean uniqueOccurrences(int[] arr) {
        return uniqueOccurrencesA1(arr);
    }

    public boolean uniqueOccurrencesA2(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for(int num: arr){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for(int key : map.keySet()){
            if(set.contains(map.get(key)))
                return false;
            set.add(map.get(key));
        }
        return true;
    }

    public boolean uniqueOccurrencesA1(int[] arr) {
        int[] counts = new int[2001];
        for(int num: arr)
            counts[num+1000]++;
        Arrays.sort(counts);
        for(int i=1;i<counts.length;i++){
            if(counts[i]!=0 && counts[i-1]==counts[i])
                return false;
        }
        return true;
    }
}
