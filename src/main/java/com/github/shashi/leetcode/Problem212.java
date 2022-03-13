package com.github.shashi.leetcode;
import java.util.*;
public class Problem212 {
    public static void main(String[] args) {
        Problem212 problem212 = new Problem212();
        char[][] brd = new char[][]{{'a','b'},{'a','a'}};
        String[] wrds = new String[]{"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        System.out.println(problem212.findWords(brd,wrds));
    }
    private int nr;
    private int nc;
    private int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
    private char[][] board;
    private List<String> result;

    class Pair<K,V>{
        K k;
        V v;
        Pair(K k, V v){
            this.k=k;
            this.v=v;
        }
    }

    class Trie{
        int r;
        int c;
        Map<Character,Trie> children = new HashMap<>();

        Trie(int r, int c){
            this.r=r;
            this.c=c;
        }

        public Pair<Trie,Integer> get(String word){
            Trie cur = this;
            int i =0;
            for(char c : word.toCharArray()){
                if(!cur.children.containsKey(c))
                    break;
                cur = cur.children.get(c);
                i++;
            }
            return new Pair<>(cur,i);
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        return findWordsA2(board, words);
    }

    public List<String> findWordsA2(char[][] board, String[] words){
        this.nr = board.length;
        this.nc = board[0].length;
        this.board = board;
        result = new ArrayList<>();
        Trie trie = new Trie(0,0);
        for(String word: words){
            Pair<Trie,Integer> pair = trie.get(word);
            boolean found = false;
            int r = pair.k.r, c = pair.k.c, index = pair.v>0? pair.v-1 : 0;
            while(r<nr){
                while(c<nc){
                    if(backTrack2(r,c,index,pair.k,word)){
                        result.add(word);
                        found=true;
                        break;
                    }
                    c++;
                }
                c=0;
                r++;
                if(found)break;
            }
        }
        return result;
    }

    public boolean backTrack2(int r, int c, int index, Trie trie, String word){
        if(index==word.length())return true;
        char c1 = word.charAt(index);
        if(r>=nr || r<0 || c>=nc || c<0 || board[r][c] !=c1)return false;
        if(!trie.children.containsKey(c1))trie.children.put(c1,new Trie(r,c));
        trie = trie.children.get(c1);
        boolean found = false;
        board[r][c]='*';
        for(int[] dir : dirs){
            if(backTrack2(r+dir[0],c+dir[1],index+1,trie,word)){
                found = true;
                break;
            }
        }
        board[r][c]=c1;
        return found;
    }

    public List<String> findWordsA1(char[][] board, String[] words){
        this.nr = board.length;
        this.nc = board[0].length;
        this.board = board;
        List<String> result = new ArrayList<>();
        for(String word : words){
            boolean found = false;
            for(int r =0; r<nr; r++){
                for(int c=0; c<nc; c++){
                    if(backTrack(r,c,0,word)){
                        found=true;
                        result.add(word);
                        break;
                    }
                }
                if(found)break;
            }
        }
        return result;
    }

    public boolean backTrack(int r, int c, int i, String word){
        if(i==word.length())return true;
        if(r>=nr || r<0 || c>=nc || c<0
                || board[r][c] != word.charAt(i))
            return false;
        board[r][c]='*';
        boolean found = false;
        for(int[] dir: dirs)
            if(backTrack(r+dir[0],c+dir[1],i+1,word)){
                found=true;
                break;
            }
        board[r][c] = word.charAt(i);
        return found;
    }

}