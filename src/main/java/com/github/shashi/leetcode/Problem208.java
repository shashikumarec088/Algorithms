package com.github.shashi.leetcode;
import java.util.*;
public class Problem208 {
    /*
    Implement Trie (Prefix Tree)
    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys
    in a dataset of strings. There are various applications of this data structure, such as autocomplete and
    spellchecker.

    Implement the Trie class:
    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false
    otherwise. boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has
    the prefix prefix, and false otherwise.
    Example 1:
    Input
    ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
    [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
    Output
    [null, null, true, false, true, null, true]
    Explanation
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // return True
    trie.search("app");     // return False
    trie.startsWith("app"); // return True
    trie.insert("app");
    trie.search("app");     // return True

    Constraints:
    1 <= word.length, prefix.length <= 2000
    word and prefix consist only of lowercase English letters.
    At most 3 * 104 calls in total will be made to insert, search, and startsWith.

    Approach 1: Trie using map
    * intuition is to use the map to represent the children of specific node, where in key we store
    character and in value we store the actual trieNode for that specific key

    algo:
    * create a TrieNode class which will have children variable of type map of character and TrieNode to hold
    the child element and isWord flag to indicate if it is word ending or not. In constructor of TrieNode
    initialize the children map
    * in Trie class with root variable of Type TrieNode
    * in insert method, initialize the cur as root, iterate over all chars in word, if c is not in cur children,
    then create new trieNode and put it in cur children, make cur as cur.children.get(c).
    * in search initialize the cur as root, iterate over all elements in word, if c is not present in cur.children
    then return false else make cur = cur.children.get(c). at the end retunr cur.isWord
    * in startsWith do the same as search, at the end return true.
     */
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
