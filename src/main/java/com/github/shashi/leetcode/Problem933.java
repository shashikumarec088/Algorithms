package com.github.shashi.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem933 {
    /*
    933. Number of Recent Calls

    You have a RecentCounter class which counts the number of recent requests within a certain time frame.

    Implement the RecentCounter class:

    RecentCounter() Initializes the counter with zero recent requests.
    int ping(int t) Adds a new request at time t, where t represents some time in milliseconds, and returns the number of requests that has happened in the past 3000 milliseconds (including the new request). Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
    It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.



    Example 1:

    Input
    ["RecentCounter", "ping", "ping", "ping", "ping"]
    [[], [1], [100], [3001], [3002]]
    Output
    [null, 1, 2, 3, 3]

    Explanation
    RecentCounter recentCounter = new RecentCounter();
    recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
    recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
    recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
    recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3


    Constraints:

    1 <= t <= 109
    Each test case will call ping with strictly increasing values of t.
    At most 104 calls will be made to ping.

    approach 1: using queue
    * intuition is to keep adding the current time to queue and remove all elements which are less than t-3000
    * return the size of queue
    algo:
    * create a queue of type integer in constructor
    * in ping method add the current time to queue
    * iterate over the queue until the first element is less than t-3000
    * remove the first element
    * return the size of queue
    time & space:
    * it takes const time and space as always we will have 3k records


     */

    class RecentCounter {

        Queue<Integer> queue;
        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            if(queue.size()==1)return 1;
            while(queue.size()>1 && (t - queue.peek())>3000)
                queue.poll();
            return queue.size();
        }
    }
}
