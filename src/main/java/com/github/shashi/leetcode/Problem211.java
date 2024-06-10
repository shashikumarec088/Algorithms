package com.github.shashi.leetcode;
import java.util.*;
public class Problem211 {
    /*
    Design Add and Search Words Data Structure
    Design a data structure that supports adding new words and finding if a string matches any previously added string.
    Implement the WordDictionary class:
    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
    word may contain dots '.' where dots can be matched with any letter.

    Example:
    Input
    ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
    [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
    Output
    [null,null,null,null,false,true,true,true]
    Explanation
    WordDictionary wordDictionary = new WordDictionary();
    wordDictionary.addWord("bad");
    wordDictionary.addWord("dad");
    wordDictionary.addWord("mad");
    wordDictionary.search("pad"); // return False
    wordDictionary.search("bad"); // return True
    wordDictionary.search(".ad"); // return True
    wordDictionary.search("b.."); // return True

    Constraints:
    1 <= word.length <= 25
    word in addWord consists of lowercase English letters.
    word in search consist of '.' or lowercase English letters.
    There will be at most 2 dots in word for search queries.
    At most 104 calls will be made to addWord and search.

    Approach 1: trie implementation with dfs
    * intuition is to use the trie ds for adding the words and during the search we do dfs if the char at current
    index is dot else we do the normal search.
    algo:
    * create the trieNode class which has isWord flag to indicate if the word is ending at this node and also
    the map of char, TrieNode to store its child nodes
    * in Dictionary class define the TrieNode root.
    * in add method, initialize the cur as root and iterate over all the chars of adding word, if char is not part
    of cur child then add it as child and make cur = cur.children.get(c)
    * set isWord flag at the end
    * search method we need to handle the case of dot, when the char is dot we need to consider all the paths its
    children has, to do so we need to use the recursion method search which takes, word, root, index
    * call recursive search method from main search method
    * in search check if i is equal to word length if so return root.isWord
    * check if char at i is not dot if so then call search with root child if it has c by inc i, else return false
    * if dot, then iterate over all children of root and call search with i+1 and return true if this search returns
    true, return false at the end.
    time & space:
    * it takes 26^M if word has all wild characters if we are searching for N words then it is N*26^M total, space
    is M*N if we added N words each of length M.
     */
    class WordDictionary {
        class TrieNode{
            boolean isWord;
            Map<Character,TrieNode> children;
            TrieNode(){
                children = new HashMap<>();
            }
        }

        TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode cur = root;
            for(char c : word.toCharArray()){
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            return search(word, root,0);
        }

        public boolean search(String word, TrieNode root, int index){
            if(index==word.length())return root.isWord;
            char c=word.charAt(index);
            if(c!='.'){
                if(!root.children.containsKey(c))return false;
                root = root.children.get(c);
                return search(word,root,index+1);
            }else{
                for(char c1 : root.children.keySet())
                    if(search(word,root.children.get(c1),index+1))
                        return true;
                return false;
            }
        }
    }
}