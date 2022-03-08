package com.github.shashi.leetcode;
import java.util.*;
public class Problem211 {
    class MapSolution{
        Map<Integer,Set<String>> map = new HashMap<>();
        MapSolution(){}
        public void insert(String word){
            int l=word.length();
            if(!map.containsKey(l))
                map.put(l,new HashSet<>());
            map.get(l).add(word);
        }

        public boolean search(String word){
            int l = word.length();
            if(map.containsKey(l)){
                for(String s1 : map.get(l)){
                    int i=0;
                    while(i<l){
                        if(word.charAt(i)!='.' &&
                                word.charAt(i)!=s1.charAt(i))
                            break;
                        i++;
                    }
                    if(i==l)return true;
                }
            }
            return false;
        }
    }
    class Trie{
        Map<Character,Trie> children = new HashMap<>();
        boolean isWord=false;
        Trie(){}

        public void insert(String word){
            Trie cur = this;
            for(char c : word.toCharArray()){
                if(!cur.children.containsKey(c))
                    cur.children.put(c,new Trie());
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(String word){
            return dfs(this,word,0);
        }

        public boolean dfs(Trie trie, String word, int i){
            if(word.length()==i) return trie.isWord;
            char c = word.charAt(i);
            if(c!='.' && !trie.children.containsKey(c))
                return false;
            if(c=='.'){
                for(char c1 : trie.children.keySet())
                    if(dfs(trie.children.get(c1),word,i+1))
                        return true;
            }
            else if(dfs(trie.children.get(c),word,i+1))
                return true;
            return false;
        }
    }
    Trie trie;
    MapSolution mapSol;
    public Problem211() {
        trie = new Trie();
        mapSol = new MapSolution();
    }

    public void addWord(String word) {
        //trie.insert(word);
        mapSol.insert(word);
    }

    public boolean search(String word) {
        //return trie.search(word);
        return mapSol.search(word);
    }
}