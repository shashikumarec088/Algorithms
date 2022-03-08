package com.github.shashi.leetcode;
import java.util.*;
public class Problem642  {
    public static void main(String[] args) {
        String[] sent = new String[]{"i love you","island","iroman","i love leetcode"};
        int[] times = {5,3,2,2};
        Problem642 problem642 = new Problem642(sent,times);
        //'i',' ','a','#','i',' ','a','#','i',' ','a','#'
        List<String> r1 = problem642.input('i');

       // ["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"]
//[[["i love you","island","iroman","i love leetcode"]
// ,[5,3,2,2]],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]

        //[null,["i love you","island","i love leetcode"]
        // ,["i love you","i love leetcode"]
        // ,[]
        // ,[]
        // ,["i love you","island","i love leetcode"]
        // ,["i love you","i love leetcode","i a"]
        // ,["i a"],[],["i love you","island","i a"],["i love you","i a","i love leetcode"],["i a"],[]]
        List<String> r2 = problem642.input(' ');
        List<String> r3 = problem642.input('a');
        List<String> r4 = problem642.input('#');
        List<String> r5 = problem642.input('i');
        List<String> r6 = problem642.input(' ');
        List<String> r7 = problem642.input('a');
        List<String> r8 = problem642.input('#');
        List<String> r9 = problem642.input('i');
        List<String> r10 = problem642.input(' ');
        List<String> r11 = problem642.input('a');
        List<String> r12 = problem642.input('#');
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
        System.out.println(r5);
        System.out.println(r6);
        System.out.println(r7);
        System.out.println(r8);
        System.out.println(r9);
        System.out.println(r10);
        System.out.println(r11);
        System.out.println(r12);

    }
    StringBuilder sbr = new StringBuilder();
    Trie trieObj;
    class Trie{
        class Pair<K,V>{
            K k;
            V v;
            Pair(K k, V v){
                this.k = k;
                this.v = v;
            }
        }
        Map<Character,Trie> children = new HashMap<>();
        int freq=0;
        boolean isWord = false;
        Trie(){}

        public void insert(String word, int frequency){
            Trie cur = this;
            for(char c: word.toCharArray()){
                if(!cur.children.containsKey(c))
                    cur.children.put(c, new Trie());
                cur = cur.children.get(c);
            }
            cur.isWord = true;
            cur.freq += frequency;
        }

        public PriorityQueue<Pair<String,Integer>> dfs(Trie trie, PriorityQueue<Pair<String,Integer>>
                pq, StringBuilder sb){
            if(trie.children.isEmpty()){
                pq.add(new Pair<>(sb.toString(),trie.freq));
                return pq;
            }
            if(trie.isWord){
                pq.add(new Pair<>(sb.toString(),trie.freq));
            }
            for(char child : trie.children.keySet()){
                sb.append(child);
                dfs(trie.children.get(child),pq,sb);
                sb.deleteCharAt(sb.length()-1);
            }
            return pq;
        }

        public List<String> getWords(StringBuilder sb){
            List<String> result = new ArrayList<>();
            Trie cur = this;
            for(int i=0; i<sb.length(); i++){
                char c = sb.charAt(i);
                if(!cur.children.containsKey(c))
                    return result;
                cur = cur.children.get(c);
            }

            if(cur != null){
                PriorityQueue<Pair<String,Integer>> pq = new PriorityQueue<>((a,b)->a.v!=b.v?a.v-b.v:b.k.compareTo(a.k));
                dfs(cur,pq,sb);
                List<String> l1 = new ArrayList<>();
                while(!pq.isEmpty())l1.add(pq.poll().k);
                Collections.reverse(l1);
                for(int i=0;i<l1.size();i++){
                    if(i<3)result.add(l1.get(i));
                    else break;
                }

            }
            return result;
        }
    }

    public Problem642(String[] sentences, int[] times) {
        trieObj = new Trie();
        int n = sentences.length;
        for(int i=0; i<n; i++)
            trieObj.insert(sentences[i],times[i]);
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c !='#'){
            sbr.append(c);
            result = trieObj.getWords(sbr);
        }
        if(c=='#'){
            trieObj.insert(sbr.toString(),1);
            sbr = new StringBuilder();
        }
        return result;
    }
}