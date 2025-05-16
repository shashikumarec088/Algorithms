package com.github.shashi.leetcode;

import java.util.PriorityQueue;

public class Problem2462 {
    /*
    2462. Total Cost to Hire K Workers

    You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.

    You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:

    You will run k sessions and hire exactly one worker in each session.
    In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the
    last candidates workers. Break the tie by the smallest index.
    For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the
    4th worker because they have the lowest cost [3,2,7,7,1,2].
    In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but
    they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
    If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them.
    Break the tie by the smallest index.
    A worker can only be chosen once.
    Return the total cost to hire exactly k workers.



    Example 1:

    Input: costs = [17,12,10,2,7,2,11,20,8], k = 3, candidates = 4
    Output: 11
    Explanation: We hire 3 workers in total. The total cost is initially 0.
    - In the first hiring round we choose the worker from [17,12,10,2,7,2,11,20,8]. The lowest cost is 2, and we break
    the tie by the smallest index, which is 3. The total cost = 0 + 2 = 2.
    - In the second hiring round we choose the worker from [17,12,10,7,2,11,20,8]. The lowest cost is 2 (index 4). The
    total cost = 2 + 2 = 4.
    - In the third hiring round we choose the worker from [17,12,10,7,11,20,8]. The lowest cost is 7 (index 3). The
    total cost = 4 + 7 = 11. Notice that the worker with index 3 was common in the first and last four workers.
    The total hiring cost is 11.
    Example 2:

    Input: costs = [1,2,4,1], k = 3, candidates = 3
    Output: 4
    Explanation: We hire 3 workers in total. The total cost is initially 0.
    - In the first hiring round we choose the worker from [1,2,4,1]. The lowest cost is 1, and we break the tie by
    the smallest index, which is 0. The total cost = 0 + 1 = 1. Notice that workers with index 1 and 2 are common
    in the first and last 3 workers.
    - In the second hiring round we choose the worker from [2,4,1]. The lowest cost is 1 (index 2). The total cost = 1 + 1 = 2.
    - In the third hiring round there are less than three candidates. We choose the worker from the remaining
    workers [2,4]. The lowest cost is 2 (index 0). The total cost = 2 + 2 = 4.
    The total hiring cost is 4.


    Constraints:

    1 <= costs.length <= 105
    1 <= costs[i] <= 105
    1 <= k, candidates <= costs.length

    approach 1: using 2 pqs
    * intuition is to have head and tail pq and add first candidates
    and last candidates to respective queues and get the smallest
    among both and add remaining candidates to queue

    algo:
    * init headpq , tailpq, nexth=0, nextt = n-1
    * iterate until nexth < candidates and nexth <= nextt
        * add candidates to headpq
    * iterate until nextt >= nexth and size of tailpq < candidates
        * add candidates to tailpq
    * iterate until k>0 and either is not empty
        * check if either tail is empty or head peek <= tail peek
            * add head peek to cost poll it
            * if nexth <= nextt
                * then add element at nexth to head
                * inc nexth
        * else addtail peek to cost
            * if nextt >= nexth
                * add element at nextt to tail pq
                * dec nextt
        * dec k
    * return cost

    time & space:
        * it takes if m is candidates m log m for initial heap
        and k log m to get k workers total = (mlogm + k logm)


    approach 2: using single heap
    * intuition is same as approach 1 instead of 2 heaps we can
    use 1 heap with exta variable in pq
    algo:
    * most of the part is same as approach 1
    * while adding tail elements we set second variable to 1
    indicating this if from the tail part and also total count is 2
    times the candidates
    * rest of the part is same
    * iterate until k>0 and pq is not empty
    * poll the element
    * if 0 then from head portition so
        * add the cost and also check if nexth <= nextt
            * if so add the element at nexth and inc it
        * else
            * check if nextt >= nexth and add the cost at
            nextt with 1
    * return cost at the end

    time & space:
    * it takes mlogm+klogm time and n space

     */

    public long totalCost(int[] costs, int k, int candidates) {
        return totalCostA2(costs,k,candidates);
    }

    public long totalCostA1(int[] costs, int k, int candidates) {
        int n = costs.length;
        int nexth = 0, nextt = n-1;
        PriorityQueue<Integer> headpq = new PriorityQueue<>();
        PriorityQueue<Integer> tailpq = new PriorityQueue<>();
        while(nexth<=nextt && headpq.size()<candidates)
            headpq.offer(costs[nexth++]);
        while(nextt>=nexth && tailpq.size()<candidates)
            tailpq.offer(costs[nextt--]);
        long cost = 0;
        while(k>0 && (!headpq.isEmpty() || !tailpq.isEmpty())){
            if(tailpq.isEmpty() || !headpq.isEmpty() &&
                    headpq.peek()<=tailpq.peek()){
                cost += headpq.poll();
                if(nexth<=nextt)
                    headpq.offer(costs[nexth++]);
            }
            else{
                cost += tailpq.poll();
                if(nextt>=nexth)
                    tailpq.offer(costs[nextt--]);
            }
            k--;
        }
        return cost;
    }

    public long totalCostA2(int[] costs, int k, int candidates) {
        int n = costs.length;
        int nexth = 0, nextt = n-1;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->{
            if(a[0]==b[0])
                return a[1]-b[1];
            else return a[0] - b[0];
        });
        while(nexth<=nextt && pq.size()<candidates)
            pq.offer(new int[]{costs[nexth++],0});
        while(nextt>=nexth && pq.size()< 2* candidates)
            pq.offer(new int[]{costs[nextt--],1});
        long cost = 0;
        while(k>0 && !pq.isEmpty()){
            int[] elem = pq.poll();
            cost += elem[0];
            if(elem[1]==0){
                if(nexth<=nextt)
                    pq.offer(new int[]{costs[nexth++],0});
            }else{
                if(nextt>=nexth)
                    pq.offer(new int[]{costs[nextt--],1});
            }
            k--;
        }
        return cost;
    }
}
