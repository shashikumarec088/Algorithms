package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Problem502 {
    /*
    IPO
    Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital, LeetCode
    would like to work on some projects to increase its capital before the IPO. Since it has limited resources, it can
    only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total
    capital after finishing at most k distinct projects.
    You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is
    needed to start it.
    Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be
    added to your total capital.
    Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final
    maximized capital.
    The answer is guaranteed to fit in a 32-bit signed integer.

    Example 1:
    Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
    Output: 4
    Explanation: Since your initial capital is 0, you can only start the project indexed 0.
    After finishing it you will obtain profit 1 and your capital becomes 1.
    With capital 1, you can either start the project indexed 1 or the project indexed 2.
    Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
    Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
    Example 2:
    Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
    Output: 6
    Constraints:
    1 <= k <= 105
    0 <= w <= 109
    n == profits.length
    n == capital.length
    1 <= n <= 105
    0 <= profits[i] <= 104
    0 <= capital[i] <= 109

    Approach 1: sort and heap
    * intuition is to choose the project which has high profit among all the available projects, all the projects
    whose capital need is <= working captial w are considered to choose the best project at each iteration. to
    consider all the eligible projects to choose we need to sort the projects based on capital and we need
    to iterate k times since we can take k projects to get the maximum profit, we need to have projects
    sorted in asc order of capital so that we choose all eligible projects in order. at each step we can push all
    the profits from available projects to pq and take the top one, if pq is empty then no eligible projects
    return the profit made till now.
    algo:
    * create the Project class which implements Comparable interface of type Project, has capital and profit
    variable and has 2 argument constructor, has compareTo method which takes other project and returns capital-
    other-capital
    * create the projects array of size n where n is number of profits
    * add all profits and capitals, sort projects, create pq of type integer, have index init to 0
    * iterate from i=0 to <k, at each iteration
    * iterate until index<n and project at index has capital <= w and add profit at projects[i] to pq and inc index
    * if pq is empty this means no available project then break loop
    * poll the top value in pq and add it to w
    * return w at the end
    time & space:
    * n log n time for sorting and we perform in worst case n operations on pq which is n log n total hence
    total n log n time and n space for sorting as well as pq
     */

    class Project implements Comparable<Project>{
        int profit, capital;
        Project(int profit, int capital){
            this.profit = profit;
            this.capital = capital;
        }
        public int compareTo(Project other){
            return capital-other.capital;
        }
    }
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        return findMaximizedCapitalA1(k,w,profits,capital);
    }

    public int findMaximizedCapitalA1(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->b-a);
        int n = profits.length;
        Project[] projects = new Project[n];
        for(int i=0; i<n;i++){
            projects[i] = new Project(profits[i],capital[i]);
        }
        Arrays.sort(projects);
        int index=0;
        for(int i=0; i<k;i++){
            while(index<n && projects[index].capital<=w)
                pq.offer(projects[index++].profit);
            if(pq.isEmpty())break;
            w+= pq.poll();
        }
        return w;
    }
}
