package com.github.shashi.leetcode;

import java.util.*;

public class Problem373 {
    /*
    Find K Pairs with Smallest Sums
    You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
    Define a pair (u, v) which consists of one element from the first array and one element from the second array.
    Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

    Example 1:
    Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
    Output: [[1,2],[1,4],[1,6]]
    Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],
    [11,6]
    Example 2:
    Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
    Output: [[1,1],[1,1]]
    Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],
    [2,3]
    Constraints:
    1 <= nums1.length, nums2.length <= 105
    -109 <= nums1[i], nums2[i] <= 109
    nums1 and nums2 both are sorted in non-decreasing order.
    1 <= k <= 104
    k <= nums1.length * nums2.length

    Approach 1:
    * we can generate all the pairs and then sort those and consider the k pais but it will be mn log mn time and
    it will not take the advantage of the sorted nature of the lists.
    * since both arrays are sorted we can start considering the elements from the start of the arrays, first element
    from both arrays forms the first res pair and the next smallest pair will be either first element from A and
    second element from B or second element from A and first element from B
    * if (0,0) is the first pair then next pair can be either (0,1) or (1,0) this is true because both arrays are
    sorted, assume (0,1) is the next smallest pair then the next posible pairs are (1,1),(0,2) and left over from
    previous pairs ie (1,0). we see the pattern that if(p1,p2) is current pair then next pair will be from (p1+1,p2)
    (p1,p2+1) and prev remaining pairs
    * if we consider the next pair as (1,0) then next pairs are (1,1) (2,0) and prev remaining pairs are (1,1)(0,2)
    here we wee that some of the pairs are duplicated, this can be avoided by storing the visited pairs in set
    * to get the next lowest pair from the available pairs we use min heap.
    * iterate over the heap until we have k pairs or heap is empty
    algo:
    * create a pais class with key and value of type integer and define equals and hashcode methods, create
    visited set of type pais and res of type list of list of integers
    * create priority queue of type int[] and add nums1[0]+nums2[0],0,0 to it and also add pair(0,0) to visited
    * iterate until res.size is < k and pq is not empty
    * poll the element from pq, add pos[1],pos[2] to res
    * create pairs p1 = pos[1]+1,pos[2] and p2 = pos[1],pos[2]+1
    * check if pos[1]+1 < m and the p1 is not visited then add p1 to visited and nums1[pos[1]+1]+nums2[pos[1]],
    pos[1]+1, pos[2] to pq
    * check if pos[2]+1<n and the p2 is not visited then add p2 to visited and nums1[pos[1]]+nums2[pos[2]+1],
    pos[1],pos[2]+1 to pq
    * return res at the end
    time & space:
    * it takes min(klogk,mnlog(mn)) time since we iterate minimum either k times or if k>mn then mn times
    so for each iteration it takes log time for getting the element from pq. space is min(k,m*n) at max
    pq holds m*n or k elements.
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return kSmallestPairsA1(nums1,nums2,k);
    }

    class Pair<A,B>{
        private final A a;
        private final B b;
        Pair(A a, B b){
            this.a=a;
            this.b=b;
        }
        public A getKey(){
            return a;
        }
        public B getValue(){
            return b;
        }
        public boolean equals(Object other){
            if(this==other)return true;
            if(!(other instanceof Pair))return false;
            Pair o2 = (Pair)other;
            if(this.a.equals(o2.getKey())&& this.b.equals(o2.getValue()))
                return true;
            return false;
        }

        public int hashCode(){
            int res = a.hashCode();
            res = res*31+ b.hashCode();
            return res;
        }
    }
    public List<List<Integer>> kSmallestPairsA1(int[] nums1, int[] nums2, int k) {
        int m=nums1.length, n=nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[0]-b[0]);
        Set<Pair<Integer,Integer>> visited = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        pq.offer(new int[]{nums1[0]+nums2[0],0,0});
        visited.add(new Pair<>(0,0));
        while(res.size()<k && !pq.isEmpty()){
            int[] pos =pq.poll();
            res.add(Arrays.asList(nums1[pos[1]],nums2[pos[2]]));
            Pair<Integer,Integer> p1 = new Pair<>(pos[1]+1,pos[2]);
            Pair<Integer,Integer> p2 = new Pair<>(pos[1],pos[2]+1);
            if((pos[1]+1)<m && !visited.contains(p1)){
                visited.add(p1);
                pq.offer(new int[]{nums1[pos[1]+1]+nums2[pos[2]],pos[1]+1,pos[2]});
            }
            if((pos[2]+1)<n && !visited.contains(p2)){
                visited.add(p2);
                pq.offer(new int[]{nums1[pos[1]]+nums2[pos[2]+1],pos[1],pos[2]+1});
            }
        }
        return res;
    }
}
