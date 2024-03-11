package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem346 {
    Queue<Integer> queue;
    int sum=0, capacity;

    public Problem346(int size) {
        capacity = size;
        queue = new LinkedList<>();
    }

    public double next(int val) {
        if(capacity==queue.size()){
            sum -= queue.poll();
        }
        sum += val;
        queue.offer(val);
        return sum / (double)queue.size();
    }
}