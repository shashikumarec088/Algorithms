package com.github.shashi.leetcode;

public class Problem37 {
    private int n=3;
    private int N = n*n;
    private int[][] rows = new int[N][N+1];
    private int[][] cols = new int[N][N+1];
    private int[][] boxes = new int[N][N+1];
    private boolean solved = false;
    private char[][] board;
    public void solveSudoku(char[][] board1) {
        solveSuduku1(board1);
    }

    public void solveSuduku1(char[][] board1){
        board = board1;
        for(int r=0; r<N;r++){
            for(int c=0; c<N; c++){
                if(board[r][c] !='.')
                    place(r,c,board[r][c] - '0');
            }
        }
        backTrack(0,0);
    }

    private boolean couldPlace(int r, int c, int d){
        // boxes index
        int idB = r/n*n+c/n;
        return rows[r][d]+cols[c][d]+boxes[idB][d] == 0;

    }

    private void placeNext(int r, int c){
        if(r==N-1 && c==N-1)
            solved=true;
        else{
            if(c==N-1) backTrack(r+1,0);
            else backTrack(r,c+1);
        }
    }

    private void place(int r, int c, int d){
        int idB = r/n*n+c/n;
        rows[r][d]++;
        cols[c][d]++;
        boxes[idB][d]++;
        board[r][c] = (char)(d+'0');
    }
    private void remove(int r, int c, int d){
        int idB = r/n*n+c/n;
        rows[r][d]--;
        cols[c][d]--;
        boxes[idB][d]--;
        board[r][c] = '.';
    }

    public void backTrack(int r, int c){
        if(board[r][c]=='.'){
            for(int d=1; d<=9; d++){
                if(couldPlace(r,c,d)){
                    place(r,c,d);
                    placeNext(r,c);
                    if(!solved) remove(r,c,d);
                }
            }
        }else placeNext(r,c);
    }
}
