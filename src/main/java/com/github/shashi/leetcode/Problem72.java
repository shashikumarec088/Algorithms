package com.github.shashi.leetcode;

public class Problem72 {
    /*
    Edit Distance
    Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
    You have the following three operations permitted on a word:
    Insert a character
    Delete a character
    Replace a character
    Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')
    Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation:
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
    Constraints:
    0 <= word1.length, word2.length <= 500
    word1 and word2 consist of lowercase English letters.

    Approach 1: recursion TLE
    * edit distance between the words is the number of operations needed to make them similar, operations are
    replace, delete and insert. this is used in spelling corrections and in NLP
    * if 2 strings are same then the distance is 0, consider example w1=abc and w2=abe, in these 2 strings the
    last char is different. in this case we need to perform some operation on w1 to make it equal to w2
    * option1 -> delete c from w1, 2 -> insert e in w1 3 -> replace c with e in w1
    * the goal is to choose the option that leads to minimal number of operations
    * consider option 1-> from abc if we delete c then w1=ab and w2=abe in this case once again we need to
    insert e to w1 which makes w1=abe and w2 = abe so the number of operations performed 2
    * consider option 2-> insert e to w1 now w1=abce and w2=abe then we need to delete c from w1 then
    w1=abe and w2=abe number of operations are 2
    * consider option 3 -> replace c with e in w1=abe and w2=abe number of operations are 1
    * hence min among these 3 options in 1 so the edit distance in this case is 1
    * this leads us to the conclusion that Edit distance to transform w1 to w2 = 1 + min(Number of operations
    after deleting e from w1, Number of operations after inserting e in w1, Number of operations after replacing
    c with e in w1) + 1. Here the 1 is needed to count the current operation which also a modification.
    * when we are trying to find all possible solutions and choosing the most optimal one then recursion is the
    natural way to implement it.
    * when we reach the start of string then we reached the base case then we return length of remaining among
    those 2 strings.
    * when the current chars are same then we check on the remaining part of both the string
    * consider two strings w1 and w2 and indices as i, j , here we compare both the strings from end
    * we have 2 possibilities when char at cur indices are matching or not matching, if matching we
    move on to checking the remaining indices.
    * if not matching then from our intuition we are clear that we need to consider the min among, delete,
    insert and update
    * when we replace then both chars become same then we need to move to next indices for both words
    * when we insert then  we need to reduce the j and check the remaining part
    * when we delete the char at w1 then reduce the i and check the remaining part
    * we need to add +1 to consider the current operation
    * the base case will be when both indices reach 0 or either is 0.
    algo:
    * init recursion with w1,w2 as words and i=m j=n where m,n are lengths respectively
    * we call the recursion with w1,w2,i,j
    * base case is when both i and j becomes 0 then we return 0 indicating we reached end of both strings
    * if either is 0 then return the other
    * then check if char at i,j are same if so return the rec with updating i=i-1 and j=j-1 to compare
    the remaining part of the words
    * else return 1+ min of rec for replace when we replace then i=i-1 and j=j-1.
    * rec for delete then i=i-1 and j remains same, rec for insert j becomes j-1 and i remains same
    time & space:
    * int takes 3^max(m,n) since in worst case we make 3 choices for each recursive call and it takes
    max(m,n) time for the recursion stack

    Approach 2: recursion with memoization
    * if we consider approach 1 we do many repetitive calls for same value of i, j consider example apc and aqe
    * we get replace (ap,aq) insert (apc,aq) and delete (ap,aqc)
    * if we consider (ap,aq) then replace(a,a) insert (ap,a) delete (a,aq)
    * consider (apc,aq) then replace(ap,a) insert (apc,a) and delete (ap,aq)
    * if we observe the above recursion tree there are many repetitions which can we avoided if we store the
    results and reuse this can be done using the 2d array at storing the result for each computed combination
    of i,j.
    * if it is already computed then we can return from the array this brings down compute from exponential to mn
    algo:
    * create global 2d memo array of Integers of size m+1, n+1 which track the result until that lengths
    * call recursion with w1,w2, m,n
    * logic remains the same as approach1
    * if memo[i][j] is not null then we return from memo
    * else if chars at i,j are equal then call rec with i-1, j-1 and store the result in memo[i][j] and return
    * else store the result from 1+min(rec(i-1,j-1),rec(i-1,j),rec(i-1,j-1))
    * return memo[i][j] at the end.
    time & space:
    * it takes mn time and mn space for array

    Approach 3: bottom up tabulation
    * intuition is same as for approach 2 but we build from bottom up we start from empty strings and start
    comparing the char at current position make the decision based on that.
    * when both the strings are empty ie when we are at position 0,0 then we will have edit distance as 0
    then either of the string is empty then the edit distance is length of other string, else we need
    to use the same intuition as we did in approach 2 it when char at current element is same then distance
    at i-1,j-1 will be the distance else it will be 1+ min of (i-1,j-1) (i,j-1) and (i-1,j-1)
    algo:
    * init m, n as w1, w2 lengths init 2d int dp array of size m+1, n+1.
    * iterate from i=0 to <=m and j=0 to <=n
    * when i,j=0 then dp[i][j] is 0
    * when i=0 then dp[i][j] is j
    * when j=0 then dp[i][j] is i
    * when char i,j are same then dp[i][j] is same as dp[i-1][j-1]
    * when not same then dp[i][j] is 1 + min (dp[i-1][j-1],dp[i][j-1],dp[i-1][j-1])
    * return dp[m][n] at the end
    time & space:
    * it takes mn time and mn space
     */
    public int minDistance(String word1, String word2) {
        return minDistanceA3(word1,word2);
    }

    public int minDistanceA3(String w1, String w2) {
        int m = w1.length(),n=w2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<=m;i++){
            for(int j=0; j<=n;j++){
                if(i==0 && j==0)dp[i][j]=0;
                else if(i==0)dp[i][j]=j;
                else if(j==0)dp[i][j]=i;
                else{
                    if(w1.charAt(i-1)==w2.charAt(j-1))dp[i][j]=dp[i-1][j-1];
                    else{
                        dp[i][j]=1+Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    }
                }
            }
        }
        return dp[m][n];
    }

    Integer[][] memo;
    public int minDistanceA2(String word1, String word2) {
        memo = new Integer[word1.length()+1][word2.length()+1];
        return rec2(word1,word2,word1.length(),word2.length());
    }

    public int rec2(String w1, String w2, int i, int j){
        if(j==0)return i;
        if(i==0)return j;
        if(memo[i][j]!=null)return memo[i][j];
        if(w1.charAt(i-1)==w2.charAt(j-1)){
            memo[i][j]=rec2(w1,w2,i-1,j-1);
            return memo[i][j];
        }
        int replace=rec2(w1,w2,i-1,j-1);
        int insert=rec2(w1,w2,i,j-1);
        int delete=rec2(w1,w2,i-1,j);
        memo[i][j]= Math.min(replace,Math.min(insert,delete))+1;
        return memo[i][j];
    }

    public int minDistanceA1(String word1, String word2) {
        return rec(word1,word2,word1.length(),word2.length());
    }

    public int rec(String w1, String w2, int i, int j){
        if(j==0)return i;
        if(i==0)return j;
        if(w1.charAt(i-1)==w2.charAt(j-1))return rec(w1,w2,i-1,j-1);
        int replace=rec(w1,w2,i-1,j-1);
        int insert=rec(w1,w2,i,j-1);
        int delete=rec(w1,w2,i-1,j);
        return Math.min(replace,Math.min(insert,delete))+1;
    }
}
