package com.github.shashi.leetcode;
import java.util.*;
public class Problem77 {
    /*
    77. Combinations
    Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    You may return the answer in any order.
    Example 1:
    Input: n = 4, k = 2
    Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Explanation: There are 4 choose 2 = 6 total combinations.
    Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
    Example 2:
    Input: n = 1, k = 1
    Output: [[1]]
    Explanation: There is 1 choose 1 = 1 total combination.
    Constraints:
    1 <= n <= 20
    1 <= k <= n

    Approach 1:
    * intuition is to use the backtracking to generate all the combinations, start generating from 1 and move till
    n. at every step whenever the current combination becomes size of k then we add it to result then we backtrack
    and try other combinations
    algo:
    * create global variables for n, k, result. call rec with empty list and 1
    * in rec if cur size is k then we clone the cur add to res and return
    * we iterate from i=first to n and for each iteration add i to cur and call rec with i+1 and cur
    * we remove the last added element from cur and we repeat for other values of i
    * return res at the end
    time & space:
    * it takes n!/k!(n-k)! it is by combination formula and takes k space for recursion.

    Approach 2:
    * intuition is same as approach 1 but we avoid some calculations by ignoring the paths which does not lead to
    solution
    algo:
    * consider in the cur state if list size in s then number of elements needed to form the list need = k-cur.size
    * remaining elements are rem = n - first+1
    * total available elements is available=remain-need. this value represents count of numbers available as children
    we should consider only children in the range of first,first+available instead of first, n
     */
    public List<List<Integer>> combine(int n, int k) {
        return combineA1(n,k);
    }

    public static void main(String[] args) {
        Problem77 Problem77 = new Problem77();
        System.out.println(Problem77.combine(4,3));
    }

    int n,k;
    List<List<Integer>> res;
    public List<List<Integer>> combineA1(int n, int k) {
        this.n=n;
        this.k=k;
        res = new ArrayList<>();
        rec(new ArrayList<>(),1);
        return res;
    }

    public List<List<Integer>> combineA2(int n, int k) {
        this.n=n;
        this.k=k;
        res = new ArrayList<>();
        rec2(new ArrayList<>(),1);
        return res;
    }

    public void rec(List<Integer> cur, int first){
        if(cur.size()==k){
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i=first; i<=n; i++){
            cur.add(i);
            rec(cur,i+1);
            cur.remove(cur.size()-1);
        }
    }

    public void rec2(List<Integer> cur, int first){
        if(cur.size()==k){
            res.add(new ArrayList<>(cur));
            return;
        }
        int need = k - cur.size();
        int rem = n - first+1;
        int available = rem-need;
        for(int i=first; i<=first+available; i++){
            cur.add(i);
            rec(cur,i+1);
            cur.remove(cur.size()-1);
        }
    }
}
