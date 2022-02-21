package com.github.shashi.leetcode;
import com.github.pedrovgs.pair.Pair;

import java.util.*;
public class Problem127 {
    private Map<String,List<String>> genericMap = new HashMap<>();
    private int n=0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return ladderLengthA3(beginWord,endWord,wordList);
    }

    public int ladderLengthA3(String beginWord, String endWord, List<String> wordList){
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord))return 0;
        Queue<Pair<String,Integer>> q1 = new LinkedList<>();
        Queue<Pair<String,Integer>> q2 = new LinkedList<>();
        Map<String,Integer> visitedBegin = new HashMap<>();
        Map<String,Integer> visitedEnd = new HashMap<>();
        q1.add(new Pair<>(beginWord,1));
        q2.add(new Pair<>(endWord,1));
        visitedBegin.put(beginWord,1);
        visitedEnd.put(endWord,1);
        n = beginWord.length();
        words.forEach(word->{
            for(int i=0; i<n; i++){
                String newWord = word.substring(0,i)+"*"+word.substring(i+1,n);
                List<String> genWords = genericMap.getOrDefault(newWord,new ArrayList<>());
                genWords.add(word);
                genericMap.put(newWord,genWords);
            }
        });
        while(!q1.isEmpty() && !q2.isEmpty()){
            int ans = -1;
            if(q1.size()<= q2.size())
                ans = visitWord(q1,visitedBegin,visitedEnd);
            else ans = visitWord(q2,visitedEnd,visitedBegin);
            if(ans>-1)return ans;
        }
        return 0;
    }

    public int visitWord(Queue<Pair<String,Integer>> q, Map<String,Integer> visited,
                         Map<String,Integer> other){
        int size = q.size();
        for(int i=0; i<size; i++){
            Pair<String,Integer> pair = q.poll();
            int level = pair.getValue();
            String word = pair.getKey();
            for(int j=0; j<n; j++){
                String newWord = word.substring(0,j)+"*"+word.substring(j+1,n);
                for(String adjWord : genericMap.getOrDefault(newWord,
                        new ArrayList<>())){
                    if(other.containsKey(adjWord))
                        return level+other.get(adjWord);
                    if(!visited.containsKey(adjWord)){
                        q.add(new Pair<>(adjWord,level+1));
                        visited.put(adjWord,level+1);
                    }
                }
            }
        }
        return -1;
    }

    public int ladderLengthA2(String beginWord, String endWord, List<String> wordList){
        // M word length, n number of words in input list
        // time: O(M^2*N) for generic map
        // bfs we traverse for N words and for each
        // word we have M intermediate words m^2 so O(M^2*N)
        Map<String,List<String>> genericMap = new HashMap<>();
        int l = beginWord.length();
        wordList.forEach(word->{
            for(int i=0; i<l; i++){
                String genWord = word.substring(0,i)+"*"
                        +word.substring(i+1,l);
                List<String> allWords = genericMap.getOrDefault(
                        genWord,new ArrayList<>());
                allWords.add(word);
                genericMap.put(genWord,allWords);
            }
        });
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        queue.add(beginWord);
        int level=1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                String qWord = queue.poll();
                if(qWord.equals(endWord))return level;
                for(int j=0; j<l; j++){
                    String genWord = qWord.substring(0,j)+
                            "*"+qWord.substring(j+1,l);
                    for(String nWord : genericMap.getOrDefault(
                            genWord,new ArrayList<>())){

                        if(!visited.contains(nWord)){
                            queue.add(nWord);
                            visited.add(nWord);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }

    public int ladderLengthA1(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return 0;
        queue.add(beginWord);
        int level=0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while(!queue.isEmpty()){
            level++;
            int length = queue.size();
            for(int i=0; i<length; i++){
                String wordEach = queue.poll();
                if(wordEach.equals(endWord))return level;
                for(String nei: getNeighbors(wordEach)){
                    if(words.contains(nei)&&!visited.contains(nei))
                        queue.add(nei);
                }
            }
        }
        return 0;
    }

    public List<String> getNeighbors(String word){
        char[] chars = word.toCharArray();
        List<String> result = new ArrayList<>();
        int n = word.length();
        for(int i=0; i<n; i++){
            char temp = chars[i];
            for(char j='a'; j<='z'; j++){
                chars[i] = j;
                result.add(new String(chars));
            }
            chars[i]=temp;
        }
        return result;
    }
}
