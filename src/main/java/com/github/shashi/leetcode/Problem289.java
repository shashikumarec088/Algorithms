package com.github.shashi.leetcode;

public class Problem289 {
    /*
    Game of Life
    According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton
    devised by the British mathematician John Horton Conway in 1970."
    The board is made up of an m x n grid of cells, where each cell has an initial state: live
    (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors
    (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population.
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    The next state is created by applying the above rules simultaneously to every cell in the current state,
    where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.

    Example 1:
    Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
    Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
    Example 2:
    Input: board = [[1,1],[1,0]]
    Output: [[1,1],[1,1]]

    Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 25
    board[i][j] is 0 or 1.

    Follow up:
    Could you solve it in-place? Remember that the board needs to be updated simultaneously: You cannot update some cells
    first and then use their updated values to update other cells.
    In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause
    problems when the active area encroaches upon the border of the array (i.e., live cells reach the border).
    How would you address these problems?

    Approach 1:
    * intuition is to make a copy of existing matrix and iterate each element and calculate neighbors and update
    the values according to the rules
    algo:
    * create an matrix of size m,n and copy the values from original array
    * iterate over the copied array, determine the neighbor boundaries for each element
    * top = max(0,row-1), left = max(0, col-1) right = min(n-1, col+1) and bottom = min(m-1,row+1)
    * iterate over the boundaries from top to bottom and left to right, count number of live cells exclude the
    current cell since for that cell that itself will not be the neighbor
    * if live neighbors are > 3 or < 2 then reset the current cell of original board
     else if 3 then set it else keep the existing value
    time & space:
    mn time and mn space

    Approach 2:
    * intuition is same as approach 1 but instead if using extra matrix we can update in the input matrix, transition
    from 0 -> 1 can be represented with -2 and from 1 -> 0 with -1 and at the end we can update these with right values
    algo:
    * iterate over the rows and cols and over boundaries as in approach one count live neighbors when the cell is
    1 or -1 (we are considering -1 because it was originally 1 after update it got changed to -1 which will be replaced
    withh 0 at the end)
    * if live is 3 then update the cell value to -2 if original value is 0 (we are using -2 to represent that it is
    updated value and should not be counted as one when this cell is neighbor for other cells)
    * if live <2 or > 3 and current cell is 1 then update to -1
    * at the end iterate over the array once again and reset -1 to 0  and -2 to 1 hence we get the modified next state
    time & space:
    mn time and constant space

      */

    public void gameOfLife(int[][] board) {
        gameOfLifeA1(board);
    }

    public void gameOfLifeA2(int[][] board) {
        int rows = board.length, cols = board[0].length;
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                int top = Math.max(0, i-1);
                int left = Math.max(0, j-1);
                int right = Math.min(cols-1,j+1);
                int bottom = Math.min(rows-1,i+1);
                int live=0;
                for(int r = top; r<=bottom;r++){
                    for(int c=left; c<=right; c++){
                        if(i==r && j==c)continue;
                        if(board[r][c]==1 || board[r][c]==-1)live++;
                    }
                }
                if(live>3 || live <2){
                    if(board[i][j]==1)board[i][j]=-1;
                }
                else if (live==3){
                    if(board[i][j]==0)board[i][j]=-2;
                };
            }
        }
        for(int i=0; i<rows;i++)
            for(int j=0; j<cols; j++)
                if(board[i][j]==-1)board[i][j]=0;
                else if(board[i][j]==-2)board[i][j]=1;
    }

    public void gameOfLifeA1(int[][] board) {
        int rows = board.length, cols = board[0].length;
        int[][] clone = new int[rows][cols];
        for(int i=0; i<rows;i++)
            for(int j=0; j<cols; j++)
                clone[i][j] = board[i][j];
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                int top = Math.max(0, i-1);
                int left = Math.max(0, j-1);
                int right = Math.min(cols-1,j+1);
                int bottom = Math.min(rows-1,i+1);
                int live=0;
                for(int r = top; r<=bottom;r++){
                    for(int c=left; c<=right; c++){
                        if(i==r && j==c)continue;
                        if(clone[r][c]==1)live++;
                    }
                }
                if(live>3 || live <2)board[i][j]=0;
                else if (live==3)board[i][j]=1;
            }
        }
    }
}
