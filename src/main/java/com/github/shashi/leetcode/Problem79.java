package com.github.shashi.leetcode;

public class Problem79 {
    /*
    Word Search
    Given an m x n grid of characters board and a string word, return true if word exists in the grid.
    The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
    vertically neighboring. The same letter cell may not be used more than once.

    Example 1:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
    Output: true
    Example 2:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
    Output: true
    Example 3:
    Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
    Output: false
    Constraints:
    m == board.length
    n = board[i].length
    1 <= m, n <= 6
    1 <= word.length <= 15
    board and word consists of only lowercase and uppercase English letters.
    Follow up: Could you use search pruning to make your solution faster with a larger board?

    Approach 1: backtracking
    * intuition we can check for each position in the board if the word can be found, we can use backtracking
    to check this effectively at each step before moving to the neighboring cell we mark the cur cell as visited
    and move to the next cell and we revert the cur cell once we return from the next cell.
    algo:
    * this is similar to other backtracking problems, we initialize global board, m as rows, n as cols
    * we check if word length is more than board length or all chars in word is not in board using extra hashset
    this is to prune the board when word length is very big, this is for the follow up question.
    * we iterate over all cells and call the backtrack method with i,j,index and word
    * in backtrack we check if index >= word.length() if this is true then we found the match we return true
    * if i,j or cell position is not matching with index char of word then we return false indicating that
    no match so we need to backtrack
    * store the char at i,j in variable and mark the value at i,j as * indicating that the cell position is
    visited, initialize variable found to false, this is needed inorder to revert the cell position value
    once we found the match instead of returning directly which makes the board unclean
    * iterate over the directions and call backtrack with updated values of the i,j if backtrack returns true
    then set found and break
    * revert the cell value from * to actual value and return the found
    time & space:
    * it take N * 3^L where N is board length and L is word length, initially we will explore 4 directions but
    further the choices reduces into 3 since we wont goback to where we come from, hence worst case their will
    be 3^L invocations and we do this for all cells hence N * 3^L. Takes L space for recursion tree.
     */
    private int nr;
    private int nc;
    private char[][] board;
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public boolean exist(char[][] board, String word) {
        return existA1(board,word);
    }

    public boolean existA1(char[][] board, String word){
        // time complexity is N * 3^L
        // where N is number of cells and L is word length
        // for each cell we will have 4 directions to explore
        // but one dir will be skipped as this is the dir from which
        // we reached the current cell so 3 paths we have to explore and
        // depth of exploration is word length which is L
        // total we need to iterate through all cells so N * 3^l
        // space complexity is L
        // space is used by recursion stack which is equal to word length L
        this.nr = board.length;
        this.nc = board[0].length;
        this.board = board;
        for(int i=0;i<nr;i++)
            for(int j=0;j<nc;j++)
                if(backTrack(i,j,0,word))
                    return true;
        return false;
    }

    public boolean backTrack(int r, int c, int i, String word){
        if(i== word.length())return true;
        if(r>=nr || r<0 || c>= nc || c<0 ||
                word.charAt(i) != board[r][c])
            return false;
        board[r][c]='*';
        boolean result = false;
        for(int[] dir : dirs){
            if(backTrack(r+dir[0],c+dir[1],i+1,word))
                result=true;
            if(result)
                break;
        }
        board[r][c]=word.charAt(i);
        return result;
    }
}
