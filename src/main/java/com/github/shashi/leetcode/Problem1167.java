package com.github.shashi.leetcode;
import java.util.*;
public class Problem1167 {
    public static void main(String[] args) {
        Problem1167 problem1167 = new Problem1167();
        int[] input = {3354,4316,3259,4904,4598,474,3166,6322,8080,9009};
    }
    public int connectSticks(int[] sticks) {
        return connectSticksA1(sticks);
    }


    public int connectSticksA1(int[] sticks){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for(int stick : sticks)pq.add(stick);
        int sum=0;
        while(pq.size()>1){
            int count = pq.poll()+pq.poll();
            sum+= count;
            pq.add(count);
        }
        return sum;
    }
}