package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem130 {
    /*
    Surrounded Regions
    Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.

    A region is captured by flipping all 'O's into 'X's in that surrounded region.

    Example 1:
    Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
    Explanation: Notice that an 'O' should not be flipped if:
    - It is on the border, or
    - It is adjacent to an 'O' that should not be flipped.
    The bottom 'O' is on the border, so it is not flipped.
    The other three 'O' form a surrounded region, so they are flipped.
    Example 2:
    Input: board = [["X"]]
    Output: [["X"]]
    Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 200
    board[i][j] is 'X' or 'O'.

    Approach 1: recursion
    * intuition is to first consider the border elements and do dfs on O and mark those as retaining elements
    then mark remaining to X and revert retained ones to O
    algo:
    * initialize m,n to rows and cols respectively, list of int arrays to capture the border elements
    * do dfs on border elements
    * iterate over all the elements and mark first O to X and E to O (remember if we mark E to O first then
    it will be overwritten by next if condition or use else if)
    * int dfs check row and col boundaries and element is not O then return
    * mark element as E then traverse in all directions and call dfs with updated i,j
    time & space:
    * takes mn time to traverse all elements and also takes mn space if all are O

    Approach 2: bfs
    * intuition is same as approach 1 instead of dfs we use bfs to visit the neighbors
    algo:
    * initialize m,n to rows and cols respectively, list of int arrays to capture the border elements
    * do bfs on border elements
    * iterate over all the elements and mark first O to X and E to O (remember if we mark E to O first then
    it will be overwritten by next if condition or use else if)
    * int bfs check if element is not O then return
    * create a queue of type linkedlist and add i,j as int array elements
    * iterate until queue is empty
    * get the size of queue and iterate from k=0 to <size
    * get the positions and set the board position to E
    * check all directions and add to queue is next element is within bound and O
    time & space:
    * takes mn time to traverse all elements and also takes mn space in worst case
     */

    public void solve(char[][] board) {
        solveA1(board);
    }

    public void solveA1(char[][] board) {
        int m=board.length, n=board[0].length;
        List<int[]> borders = new ArrayList<>();
        for(int r=0; r<m; r++){
            borders.add(new int[]{r,0});
            borders.add(new int[]{r,n-1});
        }
        for(int c=1; c<n; c++){
            borders.add(new int[]{0,c});
            borders.add(new int[]{m-1,c});
        }

        for(int[] pos: borders)
            dfs(board,pos[0],pos[1]);
        for(int i=0; i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O')board[i][j]='X';
                if(board[i][j]=='E')board[i][j]='O';
            }
        }
    }

    public void solveA2(char[][] board) {
        int m=board.length, n=board[0].length;
        List<int[]> borders = new ArrayList<>();
        for(int r=0; r<m; r++){
            borders.add(new int[]{r,0});
            borders.add(new int[]{r,n-1});
        }
        for(int c=1; c<n; c++){
            borders.add(new int[]{0,c});
            borders.add(new int[]{m-1,c});
        }

        for(int[] pos: borders)
            bfs2(board,pos[0],pos[1]);
        for(int i=0; i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='O')board[i][j]='X';
                if(board[i][j]=='E')board[i][j]='O';
            }
        }
    }

    public void dfs(char[][] board,int i,int j){
        if(i<0 || j<0 || i>=board.length || j>=board[0].length
                || board[i][j]!='O')return;
        board[i][j] = 'E';
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        for(int[] dir:dirs)
            dfs(board,i+dir[0],j+dir[1]);
    }

    public void bfs2(char[][] board,int i,int j){
        if(board[i][j]!='O')return;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            int r = pos[0], c = pos[1];
            if(board[r][c]!='O')continue;
            board[r][c]='E';
            if(c+1<board[0].length)queue.offer(new int[]{r,c+1});
            if(r+1<board.length)queue.offer(new int[]{r+1,c});
            if(c-1>=0)queue.offer(new int[]{r,c-1});
            if(r-1>=0)queue.offer(new int[]{r-1,c});
        }
    }
}
