package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem2352 {
    /*
    2352. Equal Row and Column Pairs
    Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj) such that row ri and column cj are equal.

    A row and column pair is considered equal if they contain the same elements in the same order (i.e., an equal array).



    Example 1:


    Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
    Output: 1
    Explanation: There is 1 equal row and column pair:
    - (Row 2, Column 1): [2,7,7]
    Example 2:


    Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
    Output: 3
    Explanation: There are 3 equal row and column pairs:
    - (Row 0, Column 0): [3,1,2,2]
    - (Row 2, Column 2): [2,4,2,2]
    - (Row 3, Column 2): [2,4,2,2]


    Constraints:

    n == grid.length == grid[i].length
    1 <= n <= 200
    1 <= grid[i][j] <= 105

    approach 1: bf
    * intuition is to iterate over rows and cols for each combination check if they are same and count
    algo:
    * iterate over rows
        * iterate over cols
            * iterate over elements
                * if elements are not same then break
            * if all elements are same then increment count
    * return count
    time & space:
    * n^3 time and 1 space

    Approach 2: using the map to store rows and freq
    * intuition is to store the rows in map and check if the row is already present in map
    algo:
    * create a map of type string, integer
    * iterate over the rows
        * convert the row to string using Arrays.toString(row) and put to map with freq
    * iterate over the cols
        * create cols array and convert to string
        * check if in map increment freq times count
    * return the count
    time & space:
    * n^2 time and n^2 space

    approach 3: trie based approach
    * intuition is to store the rows in trie along with its frequency and check if col in trie
    if so return freq
    algo:
    * create a class TrieNode with children of type map of integer,trieNode and freq
    * create a class Trie with root of type TrieNode
    * create a method insert which takes row and inserts
        * init cur = root
        * iterate over the row
            * if cur does not have row[i] then add to cur
            * move cur to cur.children.get(row[i])
        * increment cur.freq
    * create a method search which takes col and returns freq
        * init cur = root
        * iterate over the col
            * if cur does not have col[i] then return 0
            * move cur to cur.children.get(col[i])
        * return cur.freq
    * create a trie and insert all rows
    * iterate over cols and search in trie
        * add to count
    * return count
    time & space:
    * n^2 time and n^2 space

     */

    public int equalPairs(int[][] grid) {
        return equalPairsA1(grid);
    }

    class TrieNode{
        int freq;
        Map<Integer,TrieNode> children;

        TrieNode(){
            children = new HashMap<>();
        }
    }

    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
        }

        public void insert(int[] row){
            TrieNode cur = root;
            for(int num: row){
                cur.children.putIfAbsent(num,new TrieNode());
                cur = cur.children.get(num);
            }
            cur.freq++;
        }

        public int search(int[] col){
            TrieNode cur = root;
            for(int num: col){
                cur = cur.children.get(num);
                if(cur==null)return 0;
            }
            return cur.freq;
        }
    }

    public int equalPairsA3(int[][] grid) {
        int count=0, n=grid.length;
        Trie root = new Trie();
        for(int[] row: grid){
            root.insert(row);
        }
        for(int c=0; c<n;c++){
            int[] col = new int[n];
            for(int r=0; r<n;r++){
                col[r] = grid[r][c];
            }

            count += root.search(col);
        }
        return count;
    }

    public int equalPairsA2(int[][] grid) {
        int count=0, n = grid.length;
        Map<String,Integer> map = new HashMap<>();
        for(int[] row: grid){
            String key = Arrays.toString(row);
            map.put(key,map.getOrDefault(key,0)+1);
        }
        for(int c=0; c<n;c++){
            int[] col = new int[n];
            for(int r=0; r<n;r++){
                col[r] = grid[r][c];
            }
            String key = Arrays.toString(col);
            count += map.getOrDefault(key,0);
        }
        return count;
    }

    public int equalPairsA1(int[][] grid) {
        int n = grid.length, count=0;
        for(int r=0; r<n;r++){
            for(int c=0;c<n;c++){
                boolean match = true;
                for(int i=0;i<n;i++){
                    if(grid[i][r]!=grid[c][i]){
                        match=false;
                        break;
                    }
                }
                count += match?1:0;
            }
        }
        return count;
    }
}
