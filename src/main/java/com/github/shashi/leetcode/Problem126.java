package com.github.shashi.leetcode;
import jdk.jfr.internal.tool.Main;

import java.util.*;
public class Problem126 {
    public static void main(String[] args) {
        // reference for bfs path
        // https://leetcode.com/problems/word-ladder-ii/discuss/40434/C%2B%2B-solution-using-standard-BFS-method-no-DFS-or-backtracking
        Problem126 problem126 = new Problem126();
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> result = problem126.findLadders("hit","cog",wordList);
    }
    private Map<String,List<String>> adjMap = new HashMap<>();
    private List<List<String>> results = new ArrayList<>();
    private List<String> cur = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        findLaddersA3(beginWord,endWord,wordList);
        return results;
    }

    public List<List<String>> findLaddersA2(String beginWord, String endWord,List<String>
            wordList){
        Set<String> words = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if(!words.contains(endWord))return result;
        Queue<List<String>> queue = new LinkedList<>();
        queue.add(Arrays.asList(beginWord));
        boolean found = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            Set<String> visited = new HashSet<>();
            for(int i=0; i<size; i++){
                List<String> list = queue.poll();
                String word = list.get(list.size()-1);
                for(String nei : getNeighbors(word,words)){
                    visited.add(nei);
                    List<String> nList = new ArrayList<>(list);
                    nList.add(nei);
                    if(nei.equals(endWord)){
                        found =true;
                        result.add(nList);
                    }else queue.add(nList);
                }
            }
            if(found)break;
            for(String str :visited)words.remove(str);
        }
        return result;
    }

    public List<List<String>> findLaddersA3(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord))return results;
        if(!bfs2(beginWord,endWord,words)) return results;
        cur.add(beginWord);
        backTrack(beginWord,endWord);
        return results;
    }



    public boolean bfs2(String begin, String end, Set<String> words){
        if(!words.contains(end))return false;
        Set<String> forward = new HashSet<>();
        Set<String> backward = new HashSet<>();
        forward.add(begin);
        backward.add(end);
        boolean found = false;
        int direction = 1;
        while(!forward.isEmpty()){
            Set<String> visited = new HashSet<>();
            if(forward.size()>backward.size()){
                Set<String> temp = forward;
                forward = backward;
                backward = temp;
                direction *= -1;
            }
            for(String word : forward){
                for(String nei : getNeighbors(word,words)){
                    if(backward.contains(nei)){
                        found = true;
                        connectEdges(word,nei,direction);
                    }else if(!found && words.contains(nei)&&!forward.contains(nei)){
                        visited.add(nei);
                        connectEdges(word,nei,direction);
                    }
                }
            }
            if(found)break;
            for(String word : visited)words.remove(word);
            forward = visited;
        }
        return found;
    }

    public void connectEdges(String word1, String word2,int direction){
        String parent = word1;
        String child = word2;
        if(direction==-1){
            parent = word2;
            child = word1;
        }
        List<String> children = adjMap.getOrDefault(parent,new ArrayList<>());
        children.add(child);
        adjMap.put(parent,children);
    }

    public List<String> getNeighbors(String word, Set<String> words){
        List<String> nei = new ArrayList<>();
        char[] arr = word.toCharArray();
        for(int i=0; i<word.length(); i++){
            char temp = arr[i];
            for(char c='a'; c<='z';c++){
                arr[i] = c;
                String nWord = new String(arr);
                if(words.contains(nWord))
                    nei.add(nWord);
            }
            arr[i]=temp;
        }
        return nei;
    }

    public void bfs(String beginWord, Set<String> words){
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        words.remove(beginWord);
        while(!q.isEmpty()){
            int size = q.size();
            Set<String> visitedInner = new HashSet<>();
            for(int i=0; i<size;i++){
                String word = q.poll();
                for(String adj : getNeighbors(word,words)){
                    visitedInner.add(adj);
                    List<String> adjList = adjMap.getOrDefault(
                            word,new ArrayList<>());
                    adjMap.put(word,adjList);
                    adjList.add(adj);
                    if(!visited.contains(adj)){
                        visited.add(adj);
                        q.add(adj);
                    }
                }
            }
            for(String str : visitedInner)
                words.remove(str);
        }
    }

    public void backTrack(String begin, String end){
        if(begin.equals(end)){
            List<String> path = new ArrayList<>(cur);
            results.add(path);
        }
        if(!adjMap.containsKey(begin))return;
        for(int i=0; i<adjMap.get(begin).size();i++){

            cur.add(adjMap.get(begin).get(i));
            backTrack(adjMap.get(begin).get(i),end);
            cur.remove(cur.size()-1);
        }
    }

    public List<List<String>> findLaddersA1(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return results;
        cur.add(beginWord);
        bfs(beginWord,words);
        backTrack(beginWord,endWord);
        return results;
    }
}
