package com.github.shashi.leetcode;

import java.util.*;

public class Problem433 {
    /*
    Minimum Genetic Mutation
    A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
    Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation
    is defined as one single character changed in the gene string.
    For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
    There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a
    valid gene string.
    Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations
    needed to mutate from startGene to endGene. If there is no such a mutation, return -1.
    Note that the starting point is assumed to be valid, so it might not be included in the bank.

    Example 1:
    Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
    Output: 1
    Example 2:
    Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
    Output: 2
    Constraints:
    0 <= bank.length <= 10
    startGene.length == endGene.length == bank[i].length == 8
    startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].

    Approach 1: bfs
    * intuition is that problem asks for the minimum number of mutations needed to get to end sequence with
    only one change at a time, which resembles a graph structure where each state is a node and transition is
    represented by edge, since we are asked to get the minimum moves, it is a shorted path problem, to get the
    shortest path bfs is the better approach to solve.
    algo:
    * convert the bank to list or set, so that we can do const time lookup to check if transition is in bank
    * initialize the queue and add start to the queue, make d=0, create empty set to hold visited nodes
    * iterate until queue is empty, for each iteration
    * get the queue size inc d, iterate over the size, get the cur state
    * iterate over the length of the sequence for each position iterate over all possible chars from ACGT
    * form the new string and check if it is in back and not visited if so add to queue, if new string is
    equal to end string then return d
    * return -1 at the end
    time & space:
    * it takes m*n time to get all states where m=possible chars and n is sequence length for each sequence
    there will be total m^n states and for each word we use loops m*n and we create new string n total
    m^n * n^2 * m time
     space is b for holding the visited nodes and also for queue

    Approach 2: dfs with backtracking
    * intuition is to do the dfs by consider all the states in bank and checking if the difference with
    the start node in char positions is 1 if so then we can consider that sequence as next sequence in
    chain and do dfs from there and keep counting when we move to next sequence, when we reach the
    end sequence we update the count with min value.
    algo:
    * initialize global variable to track the min count, create the hashset to track the visited nodes
    in the current path
    * call rec with start, end, count=0, empty hashset and bank
    * if the start string is equal to end string then we reached the end sequence update the mincount
    with the count
    * iterate over all the sequences in bank, for each sequence iterate over each char and check number
    of different char compared to start if difference is greater than 1 then break
    * if difference is 1 and if the sequence is not visited then add this to visited sequence, inc
    count and call rec with present sequence as start, updated count. after calling rec remove seq
    from the set so we backtrack and explore the other sequences
    time & space:
    * we iterate over all elements in bank which is b time and we check for reach char in the sequence
    which is n time this implies b*n time and we call recursion with possibility of all valid sequences
    from the start sequence in worst case it will be b times, then b-1 time then b-2 time which is b!
    total time complexity is b! * n
     */

    public int minMutation(String startGene, String endGene, String[] bank) {
        return minMutationA2(startGene, endGene, bank);
    }

    int minCount = Integer.MAX_VALUE;
    public int minMutationA2(String s, String e, String[] bank) {
        rec(s,e,bank,0,new HashSet<>());
        return minCount == Integer.MAX_VALUE?-1:minCount;
    }

    public void rec(String s, String e, String[] bank, int count, Set<String> visit){
        if(s.equals(e)){
            minCount = Math.min(minCount,count);
            return;
        }
        for(String str : bank){
            int diff = 0;
            for(int i=0; i<str.length();i++){
                if(s.charAt(i)!=str.charAt(i))
                    diff++;
                if(diff>1)break;
            }
            if(diff==1 && !visit.contains(str)){
                visit.add(str);
                rec(str,e,bank,count+1,visit);
                visit.remove(str);
            }
        }

    }

    public void dfs(String s, String e, Set<String> set, Set<String> visited, int d){
        if(s.equals(e)){
            distance = Math.min(d,distance);
            return;
        }
        if(visited.contains(s))return;
        visited.add(s);
        for(int i=0; i<s.length();i++){
            for(char c : "ACGT".toCharArray()){
                char[] arr = s.toCharArray();
                arr[i]=c;
                String s2 = new String(arr);
                if(set.contains(s2) && !visited.contains(s2)){
                    dfs(s2,e,set,visited,d+1);
                }
            }
        }
    }

    int distance = Integer.MAX_VALUE;
    public void dfs2(String s, String e, Set<String> set, Set<String> visited, int d){
        if(s.equals(e)){
            distance = Math.min(d,distance);
            return;
        }
        if(visited.contains(s))return;
        visited.add(s);
        for(String word : set){
            int dist=0;
            for(int i=0; i<word.length();i++){
                if(word.charAt(i)!=s.charAt(i))
                    dist++;
                if(dist>1)break;
            }
            if(dist==1)dfs2(word,e,set,visited,d+1);
        }
    }

    public int minMutationA1(String s, String e, String[] bank) {
        if(bank.length==0)return -1;
        Set<String> list =  new HashSet<>(Arrays.asList(bank));
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        int d=0;
        set.add(s);
        while(!queue.isEmpty()){
            d++;
            int size = queue.size();
            for(int i=0; i<size; i++){
                String cur = queue.poll();
                for(int j=0; j<8; j++){
                    char[] str = cur.toCharArray();
                    for(char c : "ACGT".toCharArray()){
                        str[j]=c;
                        String nstr = new String(str);
                        if(list.contains(nstr)&& !set.contains(nstr)){
                            if(e.equals(nstr))return d;
                            queue.offer(nstr);
                            set.add(nstr);
                        }

                    }
                }
            }
        }
        return -1;
    }
}
