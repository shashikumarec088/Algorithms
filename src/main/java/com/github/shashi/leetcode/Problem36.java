package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem36 {
    public boolean isValidSudoku(char[][] board) {
        return isValidSudokuA4(board);
    }

    /*
        intuition is to use the binary represents of bits
        to represent numbers 1 to 9, which reduces the memory
        by half, we shift the 1 to left by number positions -1
        to represent the set bit and to check if set we use &
        and to set we use | (or). this reduces the space usage
        to 3N and time complexity is N2
    */
    public boolean isValidSudokuA4(char[][] board) {
        int n = 9;
        int[] rows = new int [n];
        int[] cols = new int [n];
        int[] boxes = new int [n];

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                char ch = board[r][c];
                int val = ch-'0';
                if(ch=='.')continue;
                int pos = 1 << val-1;
                if((rows[r] & pos) > 0 )return false;
                rows[r] |=pos;
                if((cols[c] & pos) > 0 )return false;
                cols[c] |=pos;
                int id = (r/3)*3 + c/3;
                if((boxes[id] & pos) > 0 )return false;
                boxes[id] |=pos;
            }
        }
        return true;
    }

    /*
        intuition is to use the arrays instead of sets,
        here we need to remember that the positions are
        offset by 1 to match the indexes and also we use
        char 1 to subtract since the numbers int input
        are in the form of characters
    */
    public boolean isValidSudokuA3(char[][] board) {
        int n = 9;
        char[][] rows = new char[n][n];
        char[][] cols = new char[n][n];
        char[][] boxes = new char[n][n];

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                char ch = board[r][c];
                if(ch=='.')continue;
                int pos = ch-'1';
                if(rows[r][pos]==1)return false;
                rows[r][pos]=1;
                if(cols[c][pos]==1)return false;
                cols[c][pos]=1;
                int id = (r/3)*3 + c/3;
                if(boxes[id][pos]==1)return false;
                boxes[id][pos]=1;
            }
        }
        return true;
    }

    /*
        intuition is to maintain maps for all the
        rows, cols and boxes, time complexity is
        o(n2) and space 3 n2
    */
    public boolean isValidSudokuA2(char[][] board) {
        int n = 9;
        Set<Character>[] rows = new HashSet[n];
        Set<Character>[] cols = new HashSet[n];
        Set<Character>[] boxes = new HashSet[n];
        for(int i=0; i<n; i++){
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        for(int r=0; r<n; r++){
            for(int c=0; c<n; c++){
                char ch = board[r][c];
                if(ch=='.')continue;
                if(rows[r].contains(ch))return false;
                rows[r].add(ch);
                if(cols[c].contains(ch))return false;
                cols[c].add(ch);
                int id = (r/3)*3 + c/3;
                if(boxes[id].contains(ch))return false;
                boxes[id].add(ch);
            }
        }
        return true;
    }

    /*
        intuition is to validate rows and cols first
        then validate boxes, time complexity is o(2 n2)
        and space complexity is 2n since we hold only
        one row and column in memory once and also one box
        at once
    */
    public boolean isValidSudokuA1(char[][] board) {
        int m = board.length, n = board[0].length,b=3;
        for(int i=0; i<m; i++){
            Set<Character> set1 = new HashSet<>();
            Set<Character> set2 = new HashSet<>();
            for(int j=0; j<n; j++){
                if(board[i][j] != '.' && set1.contains(board[i][j]))return false;
                else set1.add(board[i][j]);
                if(board[j][i] != '.' &&  set2.contains(board[j][i]))return false;
                else set2.add(board[j][i]);
            }
        }
        for(int i = 0; i<m; i=i+3){
            for(int j=0; j<n; j=j+3){
                Set<Character> seen = new HashSet<>();
                for(int i2=i; i2<i+3; i2++){
                    for(int j2=j; j2<j+3; j2++){
                        if(board[i2][j2] != '.' &&  seen.contains(board[i2][j2]))return false;
                        else seen.add(board[i2][j2]);
                    }
                }
            }
        }
        return true;
    }
}