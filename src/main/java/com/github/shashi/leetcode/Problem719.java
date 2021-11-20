package com.github.shashi.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem719 {

    public static void main(String[] args) {
        Problem719 soln = new Problem719();
        int[] nums = new int[]{1,6,1};
        soln.smallestDistancePair(nums,3);
    }
    public int smallestDistancePair(int[] nums, int k) {
        return smallestDistanceUsingHeap(nums,k);
    }

    public int smallestDistanceUsingHeap(int[] nums, int k){
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k, new Comparator<Integer>(){
            @Override
            public int compare(Integer i1, Integer i2){
                if(i1 > i2)
                    return -1;
                else if(i1<i2)
                    return 1;
                else return 0;
            }
        });

        for(int i = 0; i < nums.length-1; i++){
            for(int j = i+1; j < nums.length;j++){
                int count = Math.abs(nums[i]-nums[j]);
                if(queue.size() < k)
                    queue.add(count);
                else if(count < queue.peek()){
                    queue.poll();
                    queue.add(count);
                }
            }
        }
        return queue.poll();
    }
}
