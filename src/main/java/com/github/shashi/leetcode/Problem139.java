package com.github.shashi.leetcode;
import java.util.*;
public class Problem139 {
    /*
    Word Break
    Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated
    sequence of one or more dictionary words.
    Note that the same word in the dictionary may be reused multiple times in the segmentation.
    Example 1:
    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:
    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.
    Example 3:
    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false
    Constraints:

    1 <= s.length <= 300
    1 <= wordDict.length <= 1000
    1 <= wordDict[i].length <= 20
    s and wordDict[i] consist of only lowercase English letters.
    All the strings of wordDict are unique.

    Approach 1: brute force
    * intuition is to consider all the prefix matches and start considering the remaining part of the string from
    all those prefixes end points and check if they are in words bank, if once we considered any endpoint once then
    we can mark it as visited and ignore for further considering that point.
    algo:
    * convert the wordList to words set so that we can look up for the word in const time
    * init n as string length, create boolean visited array of size n+1
    * create a queue of integer to hold the end indexes
    * add 0 to queue and iterate until queue is not empty
    * get the start from queue
    * check if start == n if so all the parts of the string is present in words return true
    * iterate from end=start+1 to end<=n
    * check if end is seen before if so then skip the end index and move to next iteration
    * if substring from start to end in words then add end to queue and set visited of end to true
    * return false at the end
    time & space:
    * it takes n^3 time max their will be n positions in queue, we need to iterate for n iterations for each
    values of n and it takes n time to get the substring. for converting words to set takes m*k where m is num
    of words and k is each word length. and also takes m*k space.

    Approach 2: dp top down
    * intuition is similar to approach 1 but using recursion and dp array, we start from empty string and
    when we match the prefix then we recursively call the method with updated value of start, if we are
    able to match complete string we reach the base case where start index is string length in which case
    we return true, to avoid duplicate calculations we use dp and store the value for visited positions
    algo:
    * call rec with words set, Boolean[] dp array and start index 0
    * base case is if start == s.length then return true
    * if dp[start] is not null then return dp[start]
    * iterate from end=start+1 till end<=s.length
    * check if substring from start to end in words and call rec with end value if true then set
    dp[end]=true and return true
    * set dp[start] = false and return false at the end
    time & space:
    * it take n^3 time, n recursive calls, for each call we iterate n time and for each iteration we compute
    substring which takes n time. and takes n space

    Approach 3: dp bottom up
    * intuition is we start building the solution from empty prefix to end, this can be done by using the dp
    array and setting the 0th position value to true consider example heet
    * for this the way we build solution is w(), h -> w() & h, he -> w() he, w(h) e, hee -> w()hee, w(h)ee,w(he)e,
     heet -> w() heet, w(h)eet, w(he)et, w(hee)t, w(heet)
    * we store w(),w(h),w(he),w(hee), w(heet) in dp array
    * to get this combinations we use the nested loops outer one for end and inner one for different star indexes
    * we need to check if until start position the prefixes exists in map using the cached dp and check if the
    prefix from start to end exists in words if so then we can set the dp[end] to true and start over for next end
    algo:
    * convert the list of words to set, init n = s.length
    * create boolean dp of size n+1, set dp[0] = true
    * iterate from i=1 to <=n
    * inner loop j=0 to <i
    * check if dp[j] is true and substring j to i in words if so set dp[i] and break
    * return dp[n] at the end
    time & space:
     * it takes n^3 time n^2 for 2 loops and n for substring. takes n space.

    Approach 4: dp with bottom up iterating over words bank
    * intuition is to iterate over the input string and when prefix matches on any word then consider matching
    the prefix from that point with the words
    algo:
    * create boolean array dp of size n,
    * iterate from i=0 to < n , iterate over the words in wordDict
    * if i is less than word length then skip iteration
    * when i = word.length()-1 or when dp[i-word.length()] is true
    * then check if substring from i-word.length()+1 to i+1 (here i+1 becouse we need to consider ith char as well)
    check if equal to word if so set dp[i]=true, break
    * return dp[i-1] at the end
    time & space:
    * it takes n*m*k time and n space.
     */

    public static void main(String[] args) {
        String s = "leetcode";
        String[] arr = {};
        Problem139 problem139 = new Problem139();
        LinkedList<Integer> list = new LinkedList<>();
        list.removeLast();
        System.out.println(problem139.wordBreak(s,Arrays.asList("leet","code")));
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakA4(s,wordDict);
    }


    public boolean wordBreakA4(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        for(int i=0; i<n; i++){
            for(String word: wordDict){
                if(i<(word.length()-1))continue;
                if(i==(word.length()-1) || dp[i-word.length()]){
                    if( s.substring(i-word.length()+1,i+1).equals(word)){
                        dp[i]=true;
                        break;
                    }
                }
            }
        }
        return dp[n-1];
    }


    public boolean wordBreakA3(String s, List<String> wordDict){
        int n = s.length();
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i=1; i<=n; i++){
            for(int j=0; j<i; j++){
                if(dp[j] && set.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    public boolean wordBreakA2(String s, List<String> wordDict) {
        return rec(s,new HashSet<>(wordDict), new Boolean[s.length()+1],0);
    }

    public boolean rec(String s, Set<String> words, Boolean[] dp, int start){
        if(start==s.length())return true;
        if(dp[start]!=null)return dp[start];
        for(int end=start+1; end <=s.length(); end++){
            if(words.contains(s.substring(start,end)) &&
                    rec(s,words,dp,end)){
                dp[end]=true;
                return dp[end];
            }
        }
        dp[start]=false;
        return dp[start];
    }

    public boolean wordBreakA1(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while(!queue.isEmpty()){
            int start = queue.poll();
            if(start==n)return true;
            for(int end=start+1; end<=n; end++){
                if(visited[end])continue;
                if(words.contains(s.substring(start,end))){
                    visited[end]=true;
                    queue.offer(end);
                }
            }
        }
        return false;
    }
}