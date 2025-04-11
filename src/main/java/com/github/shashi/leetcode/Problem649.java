package com.github.shashi.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Problem649 {
    /*
    649. Dota2 Senate

    In the world of Dota2, there are two parties: the Radiant and the Dire.

    The Dota2 senate consists of senators coming from two parties. Now the Senate wants to decide on a change in the
    Dota2 game. The voting for this change is a round-based procedure. In each round, each senator can exercise one
    of the two rights:

    Ban one senator's right: A senator can make another senator lose all his rights in this and all the following rounds.
    Announce the victory: If this senator found the senators who still have rights to vote are all from the same party,
    he can announce the victory and decide on the change in the game.
    Given a string senate representing each senator's party belonging. The character 'R' and 'D' represent the Radiant
    party and the Dire party. Then if there are n senators, the size of the given string will be n.

    The round-based procedure starts from the first senator to the last senator in the given order. This procedure
    will last until the end of voting. All the senators who have lost their rights will be skipped during the procedure.

    Suppose every senator is smart enough and will play the best strategy for his own party. Predict which party will
    finally announce the victory and change the Dota2 game. The output should be "Radiant" or "Dire".

    Example 1:

    Input: senate = "RD"
    Output: "Radiant"
    Explanation:
    The first senator comes from Radiant and he can just ban the next senator's right in round 1.
    And the second senator can't exercise any rights anymore since his right has been banned.
    And in round 2, the first senator can just announce the victory since he is the only guy in the senate who can vote.
    Example 2:

    Input: senate = "RDD"
    Output: "Dire"
    Explanation:
    The first senator comes from Radiant and he can just ban the next senator's right in round 1.
    And the second senator can't exercise any rights anymore since his right has been banned.
    And the third senator comes from Dire and he can ban the first senator's right in round 1.
    And in round 2, the third senator can just announce the victory since he is the only guy in the senate who can vote.


    Constraints:

    n == senate.length
    1 <= n <= 104
    senate[i] is either 'R' or 'D'.

    Approach 1: using queue
    * inuition is to use queue to store the senators and iterate over the queue
    * while adding the senators we also count the number if senators in each party and keep iterating
    * until we have senators in both parties and get the cur senator from the queue and update the counts
    algo:
    * init queue of type Character, countR = 0, countD = 0 and also banR = 0, banD = 0
    * iterate over senate and add senates to queue and also count the senators in each party
    * while queue is not empty and countR > 0 and countD > 0
        * char c = queue.poll
        * if c == R then
            * if banR > 0 then decrement banR and decrement countR
            * else increment banD and add to queue
        * else if c == D then
            * if banD > 0 then decrement banD and decrement countD
            * else increment banR and add to queue

    * return "Radiant" if countR > 0 else "Dire"

    time & space:
    * n time and n space

    Approach 2: using 2 queues
    * intuition is to use 2 queues to store the senators in each party and iterate over the queue
    algo:
    * init queueR and queueD of type Integer to store the positions of senators of each party
    * iterate over the senate and add positions to respective queues
    * while queueR and queueD are not empty
        * int r = queueR.poll and int d = queueD.poll
        * if r < d then add r + n to queueR else add d + n to queueD (here we are adding n to the queue to
        * keep the order of senators in the queue important)
    * return "Radiant" if queueR is not empty else "Dire"
    time & space:
    * n time and n space

     */

    public String predictPartyVictory(String senate) {
        return predictPartyVictoryA1(senate);
    }

    public String predictPartyVictoryA2(String senate) {
        Queue<Integer> queueR = new ArrayDeque<>();
        Queue<Integer> queueD = new ArrayDeque<>();
        int n = senate.length();
        for(int i=0; i<n;i++){
            if(senate.charAt(i)=='R')queueR.offer(i);
            else queueD.offer(i);
        }
        while(!queueR.isEmpty() && !queueD.isEmpty()){
            int posR = queueR.poll(), posD = queueD.poll();
            if(posR<posD)queueR.offer(posR+n);
            else queueD.offer(posD+n);
        }
        return queueR.isEmpty()?"Dire":"Radiant";
    }

    public String predictPartyVictoryA1(String senate) {
        Queue<Character> queue = new ArrayDeque<>();
        int countR=0, countD = 0, banR=0, banD=0;
        for(char c: senate.toCharArray()){
            queue.offer(c);
            if(c=='R')countR++;
            else countD++;
        }
        while(countR>0 && countD>0){
            char c = queue.poll();
            if(c=='R'){
                if(banR > 0){
                    banR--;
                    countR--;
                }else{
                    banD++;
                    queue.offer(c);
                }
            }else{
                if(banD > 0){
                    banD--;
                    countD--;
                }else{
                    queue.offer(c);
                    banR++;
                }
            }
        }
        return countR>0?"Radiant":"Dire";
    }
}
