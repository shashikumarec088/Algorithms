package com.github.shashi.leetcode;
import java.util.*;
public class Problem208 {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apt");
        trie.insert("apat");
        boolean found = trie.search("apple");
        System.out.println(found);
        trie.delete("apple");
        found = trie.search("apple");
        System.out.println(found);
    }
    static class Trie {
        Map<Character,Trie> children = new HashMap<>();
        boolean isWord = false;
        public Trie() {

        }

        public void delete(String word){
            deleteRec(this,word,0);
        }

        private Trie deleteRec(Trie root, String word, int depth){
            if(root==null)return null;
            if(depth==word.length()){
                root.isWord = false;
                if(root.children.isEmpty())return null;
                return root;
            }
            char c = word.charAt(depth);
            Trie node = deleteRec(root.children.get(c),word,depth+1);
            if(node==null)
                root.children.remove(c);
            else root.children.put(c,node);
            if(!root.isWord && root.children.isEmpty())return null;
            return root;
         }

        public void insert(String word) {
            Trie cur = this;
            for(int i=0; i<word.length();i++){
                char c = word.charAt(i);
                if(!cur.children.containsKey(c))
                    cur.children.put(c,new Trie());
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            Trie cur = this;
            for(char c: word.toCharArray()){
                if(!cur.children.containsKey(c))
                    return false;
                cur = cur.children.get(c);
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            Trie cur = this;
            for(char c: prefix.toCharArray()){
                if(cur.children.containsKey(c))
                    return false;
                cur = cur.children.get(c);
            }
            return true;
        }
    }
}
