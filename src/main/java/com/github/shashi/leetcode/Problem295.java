package com.github.shashi.leetcode;
import java.util.*;
public class Problem295 {
    /*
    Find Median from Data Stream
    The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle
    value, and the median is the mean of the two middle values.
    For example, for arr = [2,3,4], the median is 3.
    For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
    Implement the MedianFinder class:
    MedianFinder() initializes the MedianFinder object.
    void addNum(int num) adds the integer num from the data stream to the data structure.
    double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be
    accepted.

    Example 1:
    Input
    ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
    [[], [1], [2], [], [3], []]
    Output
    [null, null, null, 1.5, null, 2.0]
    Explanation
    MedianFinder medianFinder = new MedianFinder();
    medianFinder.addNum(1);    // arr = [1]
    medianFinder.addNum(2);    // arr = [1, 2]
    medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
    medianFinder.addNum(3);    // arr[1, 2, 3]
    medianFinder.findMedian(); // return 2.0
    Constraints:
    -105 <= num <= 105
    There will be at least one element in the data structure before calling findMedian.
    At most 5 * 104 calls will be made to addNum and findMedian.

    Follow up:
    If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

    Approach 1: using 2 heaps
    * intuition is if we could maintain the access for the median elements all the time then we can compute the
    median in constant time, and if we could find the way to add the elements with less time complexity then
    adding new elements will also incur less cost.
    * this can be achieved with 2 data structures heaps and self balancing BST, in heap we can get the min or
    max element in constant time and addition is log n time, so we can maintain the 2 heaps, min heap to hold
    the right half elements and maxheap tp hold the left half elements when we need median we can take peek
    elements from left and right element and take avg if even elements else return the peek of maxheap.
    * while adding elements to heap we can keep left elements always having size >= right elements, initially
    when we have empty heaps we can add to max heap else  if the cur element is > top element on max heap then
    add it to min heap else to maxheap, then we need to balance the size, by adding all the elements from minHeap
    to maxheap until the minHeap size > maxHeap size. then if size difference between maxheap and minheap is > 1
    then add element to minheap from maxheap.
    algo:
    * create the size, minheap and maxheap variables of type PriorityQueue with custom comparator for maxheap
    * initialize the min and max heap and zie to 0
    * in add function, check if size is 0 if so add num to maxHeap, else
    * check if num > top element in maxheap if so add to minheap else to maxheap
    * iterate until minheap has more elements than maxheap and add those from minheap to maxheap
    * iterate until size difference between maxheap and minheap is > 1 and add those to minheap
    * inc the size
    * in getMedian method if size%2 !=0 then return top element from maxHeap as median else take
    average of top element from both the heaps
    time & space:
    * log n time and n space

    Approach 2:
    * when all the integers are in the range of o to 100 then we can use counts array to store all the elements
    along with counts in sorted order.
    algo:
    * create an array of size 101, have totalCount variable initialized to 0,
    * add the element num, inc count at counts[num] and also inc totalCount
    * to find median we have 2 possibility when totalCount is odd then we have 1 element else avg of 2 elements
    * int get make mi1 = (n+1)/2 and mi2= if(n%2==0) then mi1+1 else mi1, m1=-1,m2=-1;
    * init count=0, iterate over i=0 to counts.length and make count+= counts[i]
    * if count >=mi1 and m1==-1 then make m1 = i, if count>= mi2 and m2=-1 then make m2=i and break
    time & space:
    * it takes const time since their are max 100 elements and const space

    Approach 3:
    * when we have most of the elements in the range 0 to 100 and some are > 100, then we can use counts array
    along with 2 heaps to handle elements.
    algo:
    * init counts array of size 101 and also init totalCount=0
    * in add if num<=100 inc counts[num]
    * else use the normal heaps logic along with balancing
    * inc the totalCounts
    * in get median method init mi1=(totalCount+1)/2 and mi2=if(totalCount%2==0) then mi1+1 else mi1
    * use the similar logic of approach 2 to get the m1 and m2 if still m1 is -1
    *  find the m1 from maxheap then in minheap.
    time & space:
    worst case n time is elements are not in the range of 0 to 100.

     */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
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

    static class MedianFinder3{
        int countTotal;
        int[] counts;
        PriorityQueue<Integer> minHeap, maxHeap;
        MedianFinder3(){
            countTotal=0;
            counts=new int[101];
            minHeap = new PriorityQueue<>();
            maxHeap =  new PriorityQueue<>((a,b)->b-a);
        }

        public void add(int num){
            if(num<=100) {
                counts[num]++;
                countTotal++;
            }else if(maxHeap.isEmpty() || num<=maxHeap.peek())
                maxHeap.add(num);
            else minHeap.add(num);
            if(maxHeap.size() > (minHeap.size()+1))minHeap.add(maxHeap.poll());
            else if(minHeap.size()>maxHeap.size())maxHeap.add(minHeap.poll());
            countTotal++;
        }

        public double findMedian(){
            int mi1 = (countTotal+1)/2;
            int mi2 = countTotal%2==0?mi1+1:mi1;
            int m1=-1,m2=-1,countFreq=0;
            for(int i=0; i<counts.length; i++){
                countFreq+=counts[i];
                if(m1==-1 && countFreq>=mi1)
                    m1 = i;
                if(m2==-1 && countFreq>=mi2){
                    m2=i;
                    break;
                }
            }
            if(m1==-1){
                if((mi1-countFreq-maxHeap.size()) <=0){
                    // need to correct this logic intuition is fine
                    int k = mi1-countFreq;
                    k = maxHeap.size()-k;
                    PriorityQueue<Integer> pq = new PriorityQueue<>(maxHeap);
                    while(!pq.isEmpty()) {
                        int element = pq.poll();
                        k--;
                        countFreq++;
                        if(m1==-1 && countFreq>=mi1)
                            m1 = element;
                        if(m2==-1 && countFreq>=mi2){
                            m2=element;
                            break;
                        }
                    }
                }
                if(m1==-1) {
                    countFreq+=maxHeap.size();
                    PriorityQueue<Integer> pq = new PriorityQueue<>(minHeap);
                    while (!pq.isEmpty()) {
                        int element = pq.poll();
                        countFreq++;
                        if (m1 == -1 && countFreq >= mi1)
                            m1 = element;
                        if (m2 == -1 && countFreq >= mi2) {
                            m2 = element;
                            break;
                        }
                    }
                }else if (m2==-1)m2=minHeap.peek();
            }
            return (m1+m2)/2.0;
        }
    }

    static class MedianFinder2{
        int countTotal;
        int[] counts;
        MedianFinder2(){
            countTotal=0;
            counts=new int[101];
        }

        public void add(int num){
            counts[num]++;
            countTotal++;
        }

        public double findMedian(){
            int mi1 = (countTotal+1)/2;
            int mi2 = countTotal%2==0?mi1+1:mi1;
            int m1=-1,m2=-1,countFreq=0;
            for(int i=0; i<counts.length; i++){
                countFreq+=counts[i];
                if(m1==-1 && countFreq>=mi1)
                    m1 = i;
                if(m2==-1 && countFreq>=mi2){
                    m2=i;
                    break;
                }
            }
            return (m1+m2)/2.0;
        }
    }

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