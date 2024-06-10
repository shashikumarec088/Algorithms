package com.github.shashi.leetcode;

import java.util.*;


public class Problem52 {
    /*
    N-Queens II
    The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each
    other.
    Given an integer n, return the number of distinct solutions to the n-queens puzzle.
    Example 1:
    Input: n = 4
    Output: 2
    Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
    Example 2:
    Input: n = 1
    Output: 1
    Constraints:
    1 <= n <= 9

    Approach 1:
    * intuition is to use the backtracking to generate all the possibilities and count all the candidates
    that lead to solution, but brute force way will lead to n^n time complexity which is very slow, if we
    backtrack and avoid trying the attacked cells then we can reduce it to n!.
    * whenever we try to place the queen in any column of the row we have to make sure that the cell
    is not attacked, to track that we need to maintain the attacked rows, cols and diagonals and anti diagonals
    * we no need to track the rows since at each row we place at once and then we move to next row
    * to track the diagonals and anti diagonals we can use the property that for all diagonal elements will have
    same value for row - col and for anti diagonals row+col
    algo:
    * initialize the global variable size, call backtrack with 0 and 3 empty sets for cols, diags and antiDiags
    * in backtrack, check if row is equal to size if so we placed all queens successfully return 1
    * initialize the sols =0, to capture all solutions for placing the queen at different cols in row
    * iterate over all the cols from 0 to size, get diag=r-c and antiDiag=r+c, check if either cell is
    under attack by cols, diags or antiDiags if so skip else add the values to cols,diags and antiDiags call backtrack
    with r+1 and add the result to solution
    * after calling the backtrack remove the values from cols, diags and antiDiags
    * return sols at the end
    time & space:
    * at 0th row we have n positions in 1st row we have n-2 positions and for 2nd row we have n-4. this pattern
    continues to form the time complexity of total n! and takes n time for sets.
    *
     */
    public static void main(String[] args) {
        Problem52 problem119 = new Problem52();
        System.out.println(problem119.totalNQueens(4));
    }

    public int totalNQueens(int n) {
        return totalQns(n);
    }

    public int totalQns(int n){
        Set<Integer> cols = new HashSet<>();
        Set<Integer> ds = new HashSet<>();
        Set<Integer> ads = new HashSet<>();
        return tQs(n,cols,ds,ads,0);
    }

    public int tQs(int n,Set<Integer> cols,
                   Set<Integer> ds,Set<Integer> ads,int r){

        if(r==n) return 1;
        int solns = 0;
        for(int c=0; c<n;c++){
            int d = r-c;
            int ad = r+c;
            if(cols.contains(c) || ds.contains(d)||
                    ads.contains(ad)) continue;
            cols.add(c);
            ds.add(d);
            ads.add(ad);
            solns += tQs(n,cols,ds,ads,r+1);
            cols.remove(c);
            ds.remove(d);
            ads.remove(ad);
        }
        return solns;
    }
}
