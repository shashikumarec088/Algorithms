package com.github.shashi.leetcode;

public class Problem348 {
    int[][] board;
    int[] pr1,pr2,pc1,pc2;
    int d1,d2,ad1,ad2,n;

    public static void main(String[] args) {
        Problem348 problem348 = new Problem348(2);
        System.out.println(problem348.move(0,0,2));
        System.out.println(problem348.move(1,1,1));
        System.out.println(problem348.move(0,1,2));
    }

    public Problem348(int n){
        pr1 = new int[n];
        pr2 = new int[n];
        pc1 = new int[n];
        pc2 = new int[n];
        this.n = n;
    }

    public int move(int r, int c, int p){
        int won =0;
        if(p==1){
            pr1[r]++;
            pc1[c]++;
            if(r==c)d1++;
            if(n-r-1==c)ad1++;
            if(pr1[r]==n || pc1[c]==n || d1==n || ad1==n)
                won=p;
        }else{
            pr2[r]++;
            pc2[c]++;
            if(r==c)d2++;
            if(n-r-1==c)ad2++;
            if(pr2[r]==n || pc2[c]==n || d2==n || ad2==n)
                won=p;
        }
        return won;
    }

    public void Problem3481(int n) {
        board = new int[n][n];
    }

    private int playerWon(int r, int c, int p){
        int n=board.length;
        for(int i=0; i<n; i++){
            if(board[r][i]!=p)
                break;
            if(i+1==n)return p;
        }
        for(int i=0; i<n; i++){
            if(board[i][c]!=p)
                break;
            if(i+1==n)return p;
        }
        if(r==c || n-1-r==c){
            int i=0;
            while(i<n){
                if(board[i][i]!=p)
                    break;
                if(i+1==n)return p;
                i++;
            }
            i=n-1;
            while(i>-1){
                if(board[n-i-1][i]!=p)
                    break;
                if(i==0)return p;
                i--;
            }
        }
        return 0;
    }

    public int move1(int row, int col, int player) {
        board[row][col]=player;
        return playerWon(row,col,player);
    }
}