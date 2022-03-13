package com.github.shashi.leetcode;
import java.util.PriorityQueue;
public class Problem215 {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        Problem215 problem215 = new Problem215();
        System.out.println(problem215.findKthLargest(nums,2));
    }
    public int findKthLargest(int[] nums, int k) {
        return findKthLargestA1(nums,k);
    }

    public int findKthLargestA1(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int num : nums){
            if(pq.size() == k){
                if( num > pq.peek()) {
                    pq.poll();
                    pq.add(num);
                }
            }else pq.add(num);
        }
        return pq.peek();
    }
}