package com.github.shashi.leetcode;

import java.util.*;

public class Problem2336 {
    /*

    You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

    Implement the SmallestInfiniteSet class:

    SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
    int popSmallest() Removes and returns the smallest integer contained in the infinite set.
    void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.


    Example 1:

    Input
    ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest",
     "popSmallest", "popSmallest"]
    [[], [2], [], [], [], [1], [], [], []]
    Output
    [null, null, 1, 2, 3, null, 1, 4, 5]

    Explanation
    SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
    smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
    smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
    smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
    smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
    smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
    smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                       // is the smallest number, and remove it from the set.
    smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
    smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


    Constraints:

    1 <= num <= 1000
    At most 1000 calls will be made in total to popSmallest and addBack.

    Approach 1: using priority queue and set
    algo:
    * init pq and set, cur=1
    * in addback check if num is in set if so ignore
    * else add to pq and also to set
    * in popsmallest get the element from pq
    * and also remove from the set

    time & space:
    * n space and log n time

    approach 2: using treeset and cur
    * intuition is to use treeset instead of heap
    algo:
    * init cur=1, set, cur = 1
    * in pop if set is not empty then take first from set
    * else c ur as ans and inc cur
    * in add to set if not in set

    time & space:
    *  per call log n time and takes n space
     */

    class SmallestInfiniteSetA1 {

        PriorityQueue<Integer> pq;
        Set<Integer> set;
        int cur;
        public SmallestInfiniteSetA1() {
            pq = new PriorityQueue<>();
            set = new HashSet<>();
            cur = 1;
        }

        public int popSmallest() {
            int ans = -1;
            if(!pq.isEmpty()){
                ans = pq.poll();
            }else{
                ans = cur;
            }
            if(cur == ans)
                cur++;
            if(set.contains(ans))
                set.remove(ans);
            return ans;
        }

        public void addBack(int num) {
            if(num < cur && !set.contains(num)){
                pq.offer(num);
                set.add(num);
            }
        }
    }

    class SmallestInfiniteSet2 {

        SortedSet<Integer> set;
        int cur;
        public SmallestInfiniteSet2() {
            set = new TreeSet<>();
            cur = 1;
        }

        public int popSmallest() {
            int ans = -1;
            if(set.size() >0){
                ans = set.first();
                set.remove(ans);
            }else{
                ans = cur;
                cur++;
            }
            return ans;
        }

        public void addBack(int num) {
            if(num < cur && !set.contains(num)){
                set.add(num);
            }
        }
    }
}
