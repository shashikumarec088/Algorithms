package com.github.shashi.leetcode;
import java.util.*;
public class Problem212 {
    /*
    Word Search II
    Given an m x n board of characters and a list of strings words, return all words on the board.
    Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally
    or vertically neighboring. The same letter cell may not be used more than once in a word.
    Example 1:
    Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words =
    ["oath","pea","eat","rain"]
    Output: ["eat","oath"]
    Example 2:
    Input: board = [["a","b"],["c","d"]], words = ["abcb"]
    Output: []
    Constraints:
    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 104
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.

    Approach 1:
    * intuition is to try matching each word from the words list with each cell by traversing in all the direction
    this can be done effectively by using the trie ds, first we form the trie with all words and at each position
    we check if the root children contains the cell element then we traverse it to get the words
    algo:
    * create a trieNode with string word to store if the cur position has word and map of char, trienode to store
    the child chars.
    * we create the trie node root and traverse across each word, for each word traverse across each character
    and add to trie with standard trie addition process, at the end of insertion of each word, set the word of
    cur trie node with the word we inseted it will help us to get the word during the trie traversal  and also
    to avoid duplicates once we insert word as match to result.
    * we iterate over each cell in board, if root children contains board[i][j] then we make dfs call with root,
    i,j, board and res
    * in dfs first we get the child trie node of root as cur node, check if it has word, if so we add to result
    and make word as null to avoid duplicate insertion to result.
    * capture the char at cur position in variable letter and make value at i,j '#' this is to avoid cycles during
    recursion, it acts as visited map.
    * iterate in all 4 directions and check if new r,c values are within bound and cur node children has latest
    cell value, if so call dfs with r,c, cur,board, res
    * revert the board[i][j] to letter and return
    * one optimization is to remove the cur node children is empty then remove the child c from parent
    time & space:
    * it takes total m iterations for m words and if each has length l the for first step it take 4 direction and
    in each direction we explore 3 dimentions except in parent dimention so there will 4 * 3 ^ l-1 so total it
    will be m*4*3^l-1. and space needed is m*l when all letters in words are different

    Approach 2: using backtracking
    * intuition is to search for search for each word in the board using backtracking, this approach leads to TLE
    algo:
    * init global variables for res, m,n,board.
    * iterate over all words, for each word iterate over the board
    * if char at board is same as starting char of word then call backtrack if it returns true then
    word is found add to result and come out of both for loops and start for next word
    * to come out of both the loops we init found=false in i loop and we set it to true and break when we
    found the word, in the outer board loop we check if found is true, if so we add word and break.
    * return res at the end
    * in backtrack we check if index is word length if so we return true
    * if i, j are out of bound or char at i,j is not same as char at index then we return true
    * we mark board at i,j as visited and then we init found to false, iterate over all dirs
    * we call backtrack with new values of i, j then if found we set found to true and break
    * we revert the board with right char then we return found
    time & space:
    * it will be N words each of length l, M cells in board then we will have N * M * 4* 3^l-1
     */
    public static void main(String[] args) {
        Problem212 problem212 = new Problem212();
        char[][] brd = new char[][]{{'a','b'},{'a','a'}};
        String[] wrds = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        System.out.println(problem212.findWords(brd,wrds));

    }
    class TrieNode{
        String word;
        Map<Character,TrieNode> children;
        TrieNode(){
            children = new HashMap<>();
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        return findWordsA2(board,words);
    }

    int m,n;
    char[][] board;
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
    public List<String> findWordsA2(char[][] board, String[] words) {
        this.m = board.length;
        this.n = board[0].length;
        this.board=board;
        List<String> res = new ArrayList<>();
        for(String word : words){
            boolean found = false;
            for(int i=0; i<m;i++){
                for(int j=0; j<n;j++){
                    if(backTrack(i,j,0,word)){
                        res.add(word);
                        found = true;
                        break;
                    }
                }
                if(found)break;
            }
        }
        return res;
    }

    public boolean backTrack(int i, int j, int l, String word){
        if(l==word.length())return true;
        if(i<0 || j<0 || i>=m || j>= n || board[i][j] != word.charAt(l))return false;
        board[i][j]='#';
        boolean found = false;
        for(int[] dir : dirs){
            if(backTrack(dir[0]+i,dir[1]+j,l+1,word)){
                found=true;
                break;
            }
        }
        board[i][j]=word.charAt(l);
        return found;
    }
    public List<String> findWordsA1(char[][] board, String[] words) {
        int m = board.length, n=board[0].length;
        TrieNode root = new TrieNode();
        for(String word : words){
            TrieNode cur = root;
            for(char c : word.toCharArray()){
                cur.children.putIfAbsent(c,new TrieNode());
                cur = cur.children.get(c);
            }
            cur.word=word;
        }
        List<String> res = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0;j<n; j++){
                if(root.children.containsKey(board[i][j]))
                    dfs(root,board,res,i,j);
            }
        }
        return res;
    }

    public void dfs(TrieNode root, char[][] board, List<String> res, int i, int j){
        TrieNode cur = root.children.get(board[i][j]);
        if(cur.word !=null){
            res.add(cur.word);
            cur.word=null;
        }
        // to avoid repeat visiting
        char letter = board[i][j];
        board[i][j]='#';
        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        int m = board.length, n = board[0].length;
        for(int[] dir : dirs){
            int r=i+dir[0],c = j+dir[1];
            if(r<0 || c<0 || r>=m || c>=n ||
                    !cur.children.containsKey(board[r][c]))continue;
            dfs(cur,board,res,r,c);
        }
        board[i][j]=letter;
        if(cur.children.isEmpty())
            root.children.remove(letter);
    }



}