package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;

public class Problem36 {
    /*
       36. Valid Sudoku
       Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be
       validated according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        Note:

        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.

        Input: board =
            [["5","3",".",".","7",".",".",".","."]
            ,["6",".",".","1","9","5",".",".","."]
            ,[".","9","8",".",".",".",".","6","."]
            ,["8",".",".",".","6",".",".",".","3"]
            ,["4",".",".","8",".","3",".",".","1"]
            ,["7",".",".",".","2",".",".",".","6"]
            ,[".","6",".",".",".",".","2","8","."]
            ,[".",".",".","4","1","9",".",".","5"]
            ,[".",".",".",".","8",".",".","7","9"]]
            Output: true

        Approach 1:
         * intuition is to validate the rows and columns first then validate the boxes
         * we can iterate over each row, within each row we can create 2 sets
         one for row and one for column since rows and columns are same we can validate
         both within each iteration, if we find repeating element then we return false
         * then we iterate over each boxes, when iterating over boxes we increment by 3
         since box size is 3 both row and col, within each iteration of r and c we
         then iterate from that position r and c by 3 values

         Approach 2:

         * intuition is to validate row, cols and boxes in n2 time complexity, by
         creating array of hashmaps for each row, column and boxes,
         * for getting the index for boxes we use logic for (r/3)*3 + c/3 here
         we multiply rows by 3 because we need index as 3 for 3rd box

         Approach 3:
         * intuition is to validate row, coles and boxes, we can reduce the space to 3 n
         by using the bit manipulations
         * since values ranges from 1 to 9, we can have the integer whose bits represents the
         presence of value or not for example 2 & 2 represents presence of 2 to add we can
         use the or operator
         * rest is similar to approach 2

     */
    public boolean isValidSudoku(char[][] board) {
        return isValidSudokuA3(board);
    }

    public static void main(String[] args) {
        Problem36 pp36 = new Problem36();
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(pp36.isValidSudokuA4(board));
    }

    public boolean isValidSudokuA4(char[][] board) {
        int[] rows = new int[board.length];
        int[] cols = new int[board.length];
        int[] boxes = new int[board.length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length;j++){
                int id = 3*(i/3)+(j/3);
                if(board[i][j]=='.')continue;
                int num=board[i][j] - '0';
                if((rows[i] & (1<<num))>0||(cols[j] & (1<<num))>0
                        || (boxes[i] & (1<<num))>0)return false;
                rows[i]|=(1<<num);
                cols[j]|=(1<<num);
                boxes[id]|=(1<<num);
            }
        }
        return true;
    }

    public boolean isValidSudokuA3(char[][] board) {
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