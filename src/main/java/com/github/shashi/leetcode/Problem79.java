package com.github.shashi.leetcode;

public class Problem79 {
    private int nr;
    private int nc;
    private char[][] board;
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    public boolean exist(char[][] board, String word) {
        return existA2(board,word);
    }

    public boolean existA2(char[][] board, String word){
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
                if(backTrack2(i,j,0,word))
                    return true;
        return false;
    }

    public boolean backTrack2(int r, int c, int i, String word){
        if(i== word.length())return true;
        if(r>=nr || r<0 || c>= nc || c<0 ||
                word.charAt(i) != board[r][c])
            return false;
        board[r][c]='*';
        boolean result = false;
        for(int[] dir : dirs){
            if(backTrack2(r+dir[0],c+dir[1],i+1,word))
                result=true;
            if(result)
                break;
        }
        board[r][c]=word.charAt(i);
        return result;
    }

    public boolean existA1(char[][] board, String word){
        int nr = board.length, nc = board[0].length;
        char[] str = word.toCharArray();
        for(int i=0; i<nr; i++)
            for(int j=0; j<nc; j++)
                if(board[i][j]==str[0]){
                    boolean[][] visited = new boolean[nr][nc];
                    visited[i][j]=true;
                    if(dfs(i,j,1,str,visited,board))return true;
                }

        return false;
    }

    public boolean dfs(int r, int c, int i, char[] str, boolean[][] visited, char[][] board){
        if(i==str.length)return true;
        int nr = board.length, nc = board[0].length;
        for(int[] dir : dirs){
            int ur = dir[0]+r;
            int uc = dir[1]+c;
            if(ur>=0 && ur<nr && uc >=0 && uc < nc &&
                    !visited[ur][uc] && board[ur][uc]==str[i]){
                visited[ur][uc] = true;
                if(dfs(ur,uc,i+1,str,visited,board))return true;
                visited[ur][uc] = false;
            }
        }
        return false;
    }
}
