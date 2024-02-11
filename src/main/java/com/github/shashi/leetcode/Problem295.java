package com.github.shashi.leetcode;
import java.util.*;
public class Problem295 {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(-1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
    }
    PriorityQueue<Integer> lq = new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> rq = new PriorityQueue<>();

    static class MedianFinder {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int size=0;

        public MedianFinder() {

        }

        public void addNum(int num) {
            if(size==0) maxHeap.add(num);
            else{
                if(num > maxHeap.peek())
                    minHeap.add(num);
                else maxHeap.add(num);

                while(minHeap.size() > maxHeap.size()){
                    maxHeap.add(minHeap.poll());
                }
                while(maxHeap.size() - minHeap.size() > 1){
                    minHeap.add(maxHeap.poll());
                }
            }
            size++;
        }

        public double findMedian() {
            if(size%2 !=0) return maxHeap.peek();
            else{
                int last = maxHeap.peek();
                int last2 = minHeap.peek();
                return (last + last2)/(double)2.0;
            }
        }
    }
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