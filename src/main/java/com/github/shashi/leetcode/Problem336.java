package com.github.shashi.leetcode;
import java.util.*;
public class Problem336 {
    public static void main(String[] args) {
        Problem336 problem336 = new Problem336();
        String[] str = {"abcd","dcba","lls","s","sssll"};
        System.out.println(problem336.palindromePairs(str));
    }
    class Trie{
        int index = -1;
        Map<Character,Trie> children= new HashMap<>();
        List<Integer> remPal = new ArrayList<>();
        Trie(){}
    }
    public List<List<Integer>> palindromePairs(String[] words) {
        return palindromePairsA2(words);
    }

    public List<List<Integer>> palindromePairsA2(String[] words){
        int n = words.length;
        Trie trie = new Trie();
        for(int i=0; i<n; i++){
            Trie cur = trie;
            String word = words[i];
            int l = word.length();
            String rev = new StringBuilder(word).reverse().toString();
            for(int j=0; j<l;j++){
                if(isPalindrome(rev,j,l-1))
                    cur.remPal.add(i);
                char c = rev.charAt(j);
                if(!cur.children.containsKey(c))
                    cur.children.put(c, new Trie());
                cur = cur.children.get(c);
            }
            cur.index=i;
        }

        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<n; i++){
            Trie cur = trie;
            String word = words[i];
            int l = word.length();
            for(int j=0; j<l; j++){
                if(cur.index !=-1 &&
                        isPalindrome(word,j,l-1))
                    result.add(Arrays.asList(i,cur.index));
                cur = cur.children.get(word.charAt(j));
                if(cur==null)break;
            }
            if(cur==null)continue;
            if(cur.index != -1 && i !=cur.index)
                result.add(Arrays.asList(i,cur.index));

            for(Integer id : cur.remPal)
                result.add(Arrays.asList(i,id));
        }
        return result;
    }

    public List<List<Integer>> palindromePairsA1(String[] words){
        Map<String,Integer> map = new HashMap<>();
        int n = words.length;
        for(int i=0; i<n; i++)map.put(words[i],i);
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<n; i++){
            String word = words[i];
            String rev = new StringBuilder(word).reverse().toString();

            if(map.containsKey(rev)){
                int index = map.get(rev);
                if(i!=index)
                    result.add(Arrays.asList(i,index));
            }

            for(String sufix : getValidSufixes(word)){
                rev = new StringBuilder(sufix).reverse().toString();
                if(map.containsKey(rev))
                    result.add(Arrays.asList(map.get(rev),i));
            }

            for(String prefix : getValidPrefixes(word)){
                rev = new StringBuilder(prefix).reverse().toString();
                if(map.containsKey(rev))
                    result.add(Arrays.asList(i,map.get(rev)));
            }
        }
        return result;
    }

    public List<String> getValidSufixes(String word){
        List<String> result = new ArrayList<>();
        int n = word.length();
        for(int i=0; i<n; i++){
            if(isPalindrome(word,0,i))
                result.add(word.substring(i+1,n));
        }
        return result;
    }

    public List<String> getValidPrefixes(String word){
        List<String> result = new ArrayList<>();
        int n = word.length();
        for(int i=n-1; i>-1; i--){
            if(isPalindrome(word,i,n-1))
                result.add(word.substring(0,i));
        }
        return result;
    }

    public boolean isPalindrome(String word, int start, int end){
        while(start<end){
            if(word.charAt(start)!=word.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}