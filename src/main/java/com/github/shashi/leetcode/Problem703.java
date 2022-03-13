package com.github.shashi.leetcode;
import java.util.*;
public class Problem703 {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int k;

    public Problem703(int k, int[] nums) {
        this.k = k;
        for(int num: nums)
            add(num);
    }

    public int add(int val) {

        if(pq.size()>=k){
            if(val > pq.peek()){
                pq.poll();
                pq.add(val);
            }
        }else pq.add(val);
        return pq.peek();
    }
}