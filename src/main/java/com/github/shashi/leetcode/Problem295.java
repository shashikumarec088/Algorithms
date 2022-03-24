package com.github.shashi.leetcode;
import java.util.*;
public class Problem295 {
    public static void main(String[] args) {
        Problem295 problem295 = new Problem295();
        int[] nums = {-1,-2,-3,-4,-5};
        LinkedList<Integer> l = new LinkedList<>();
        for(int num : nums){
            problem295.addNum(num);
            System.out.println(problem295.findMedian());
        }
    }
    PriorityQueue<Integer> lq = new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> rq = new PriorityQueue<>();
    public Problem295() {

    }

    public void addNum(int num) {
        lq.add(num);
        if(!lq.isEmpty()&&!rq.isEmpty()){
            if(lq.peek()>rq.peek())
                rq.add(lq.poll());
        }
        if(lq.size()>rq.size()+1)
            rq.add(lq.poll());
        if(rq.size()>lq.size()+1)
            lq.add(rq.poll());
    }

    public double findMedian() {
        if(lq.size()>rq.size())return lq.peek();
        if(rq.size()>lq.peek())return rq.peek();
        return (double)((double)lq.peek()+rq.peek())/(double)2.0;

    }
}