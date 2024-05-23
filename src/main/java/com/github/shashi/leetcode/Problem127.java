package com.github.shashi.leetcode;
import com.github.pedrovgs.pair.Pair;

import java.util.*;
public class Problem127 {
    /*
    Word Ladder
    A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
    beginWord -> s1 -> s2 -> ... -> sk such that:
    Every adjacent pair of words differs by a single letter.
    Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
    sk == endWord
    Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
    transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

    Example 1:
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5
    Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
     which is 5 words long.
    Example 2:\
    Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
    Output: 0
    Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

    Constraints:
    1 <= beginWord.length <= 10
    endWord.length == beginWord.length
    1 <= wordList.length <= 5000
    wordList[i].length == beginWord.length
    beginWord, endWord, and wordList[i] consist of lowercase English letters.
    beginWord != endWord
    All the words in wordList are unique.

    Approach 1: bfs
    * since we need to reach the end word from start word with 1 modification to start word to goto intermediate
    word, this resembles the graph structure where we are asked to find the shortest path between the start and
    end words, we can use the bfs to find the shortest path.
    algo:
    * create since the strings can contain the alphabetic characters, with each change generating the intermediate
    state will lead to many words which will not scale, instead we can create the map of all intermediate words to
    list of actual words mapping by making one character generic in the word.
    * iterate over the each word in the wordlist, for each word iterate over the length of the word
    * for each position, change words value at position to generic character '*' and put with empty list to map
    if the generic word is not in map, add the actual word to list of worlds whose generic word is same as current
    generic word
    * create a set of strings to track the visited nodes in the graph
    * create a empty queue of type string and add the start word to queue, and also to visited set
    * initialize d=0
    * iterate until queue is empty
    * in each iteration, inc d, get the queue size, iterate over the queue size times, for each iteration poll the string
    * if word is equal to end word then return d;
    * for the generic words for the current word check if any in map, if so, iterate over the actual words for the
    generic word
    * if the word is not visited then add it to the queue and visited set
    * at the end return 0 if no path is found.
    time & space
    * if m is word length, n is number of words in list then it take n*m^2 time to build map and for iterating
    over the queue it take n elements and for each element it is m^2 time to get the generic words. total
    time complexity is m^2 * n. space is also same. space can be optimized by storing strings to indices in
    seperate map.

    Approach 2: bidirectional bfs
    * intuition is same as approach 1, the search space consdiered by bfs depends on the branching factor of nodes
    at each level, if branching factor remains same for all nodes, then search space increased exponentially, for
    example in complete binary tree search space at each level increase at powers of 2. we can cut down the
    search space if we traverse the graph from both the ends and stop at point when we find common node in both
    searches, this is called bidirectional bfs, remember to check presence of end word in the list else it
    leads to wrong results.
    algo:
    * check if end word in worldlist if not return 0 this is important since this can lead to wrong results
    * create the combinations map same as approach1,
    * have 2 queues and 2 visited sets add start and end respectively to all nodes
    * have 2 variables to capture levels for each queue, d1, d2
    * iterate until q1 and q2 are not null
    * at each level check if q1 size is <= q2 size if so inc d1 and call bfs, if bfs returns true then we
    found the common node between both searches then return d1+d2 as the result
    * else inc d2 do bfs on q2  if returns true then return d1+d2 as result
    * return 0 at the end
    * for bfs we pass q, combinations, visited set of q1 and visited set visit of other queue other
    * in bfs we get the size of queue iterate over all elements,
    * for each element we check if element in other set if so we return true, else we do rest of
    the things same as approach 1
    * we return false at the end of bfs indicating that still we did not find the common node.
    time & space:
    * if m is word length, n is number of words in list then it take n*m^2 time to build map and for iterating
    over the queue it take n elements and for each element it is m^2 time to get the generic words, but search
    space reduced to half as we are doing the parallel searches. space is also same for generic map.

     */
    private Map<String,List<String>> genericMap = new HashMap<>();
    private int n=0;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return ladderLengthA2(beginWord,endWord,wordList);
    }

    public static void main(String[] args) {
        Problem127 problem127 = new Problem127();
        problem127.ladderLengthA2("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"));
    }

    public int ladderLengthA2(String b, String e, List<String> wordList) {
        Map<String,List<String>> comb = new HashMap<>();
        if(!wordList.contains(e))return 0;
        for(String word : wordList){
            for(int i=0; i<word.length();i++){
                char[] wordAr = word.toCharArray();
                wordAr[i]='*';
                String s1 = new String(wordAr);
                comb.putIfAbsent(s1,new ArrayList<>());
                comb.get(s1).add(word);
            }
        }
        Queue<String> q1 = new LinkedList<>();
        Queue<String> q2 = new LinkedList<>();
        Set<String> v1 = new HashSet<>();
        Set<String> v2 = new HashSet<>();
        q1.offer(b);
        v1.add(b);
        q2.offer(e);
        v2.add(e);
        int d1=0,d2=0;
        while(!q1.isEmpty() && !q2.isEmpty()){
            if(q1.size()<=q2.size()){
                d1++;
                if(bfs(q1,comb,v1,v2))return d1+d2;
            }else{
                d2++;
                if(bfs(q2,comb,v2,v1))return d1+d2;
            }
        }
        return 0;
    }

    public boolean bfs(Queue<String> queue, Map<String,List<String>> comb,
                       Set<String> visit, Set<String> other){
        int size=queue.size();
        for(int i=0; i<size;i++){
            String word = queue.poll();
            if(other.contains(word))return true;
            for(int j=0; j<word.length();j++){
                char[] wordAr = word.toCharArray();
                wordAr[j]='*';
                String s2 = new String(wordAr);
                if(comb.containsKey(s2)){
                    for(String s1 : comb.get(s2)){
                        if(!visit.contains(s1)){
                            visit.add(s1);
                            queue.offer(s1);
                        }
                    }
                }
            }
        }
        return false;
    }


    public int ladderLengthA1(String b, String e, List<String> wordList) {
        Map<String,List<String>> comb = new HashMap<>();
        for(String word : wordList){
            for(int i=0; i<word.length();i++){
                char[] wordAr = word.toCharArray();
                wordAr[i]='*';
                String s1 = new String(wordAr);
                comb.putIfAbsent(s1,new ArrayList<>());
                comb.get(s1).add(word);
            }
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visit = new HashSet<>();
        queue.offer(b);
        visit.add(b);
        int d=0;
        while(!queue.isEmpty()){
            d++;
            int size=queue.size();
            for(int i=0; i<size;i++){
                String word = queue.poll();
                if(word.equals(e))return d;
                for(int j=0; j<word.length();j++){
                    char[] wordAr = word.toCharArray();
                    wordAr[j]='*';
                    String s2 = new String(wordAr);
                    if(comb.containsKey(s2)){
                        for(String s1 : comb.get(s2)){
                            if(!visit.contains(s1)){
                                visit.add(s1);
                                queue.offer(s1);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

}
