package com.github.shashi.leetcode;

import java.util.Arrays;

public class Problem274 {
    /*
    H-Index
    Given an array of integers citations where citations[i] is the number of citations a researcher received for
    their ith paper, return the researcher's h-index.

    According to the definition of h-index on Wikipedia: The h-index is defined as the maximum value of h such that
    the given researcher has published at least h papers that have each been cited at least h times.

    Example 1:
    Input: citations = [3,0,6,1,5]
    Output: 3
    Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5
    citations respectively.
    Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3
    citations each, their h-index is 3.
    Example 2:
    Input: citations = [1,3,1]
    Output: 1

    Constraints:
    n == citations.length
    1 <= n <= 5000
    0 <= citations[i] <= 1000

    Approach 1:
    * intuition is to sort the array in desc order and check if citations at index i
    from end are > i. find the max value of i for which condition holds true.
    algo:
    * sort the citations initialize i=0, n = citations.length
    * iterate from end and check if citations[n-1-i] > i then inc i.
    * return value of i at the end

    Approach 2:
    * intuition is to use the counting sort to reduce the time complexity to n from nlogn. create
    the counts array of size n+1. count the citations frequencies, if it is >n then make it as n
    since maximum having value more than n will not make any difference to get the h index.
    then count from end how many citations are there whose value is > i. the i will be the h index
    algo:
    * create the array of size n+1. do freq count by keeping the maxvalue of citation to n.
    * keep h = n and start counting the sum of citations until h > s, at step reduce the h
    * when sum becomes => h then that is the value of h
    time & space:
    * n time ans n space
     */
    public int hIndex(int[] citations) {
        return hIndexA2(citations);
    }
    public int hIndexA2(int[] citations) {
        int n=citations.length;
        int[] count = new int[n+1];
        for(int c : citations)count[Math.min(n,c)]++;
        int hi =n,s = count[n];
        while(hi > s){
            hi--;
            s += count[hi];
        }
        return hi;
    }

    public int hIndexA1(int[] citations) {
        Arrays.sort(citations);
        int h=0, n =citations.length;
        while(h<n && citations[n-h-1]>h)
            h++;
        return h;
    }


}