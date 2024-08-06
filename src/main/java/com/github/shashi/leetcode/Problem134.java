package com.github.shashi.leetcode;

public class Problem134 {
    /*
    Gas Station
    There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
    You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next
    (i + 1)th station. You begin the journey with an empty tank at one of the gas stations.
    Given two integer arrays gas and cost, return the starting gas station's index if you can travel around
    the circuit once in the clockwise direction, otherwise return -1. If there exists a solution,
    it is guaranteed to be unique

    Example 1:
    Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
    Output: 3
    Explanation:
    Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
    Travel to station 4. Your tank = 4 - 1 + 5 = 8
    Travel to station 0. Your tank = 8 - 2 + 1 = 7
    Travel to station 1. Your tank = 7 - 3 + 2 = 6
    Travel to station 2. Your tank = 6 - 4 + 3 = 5
    Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
    Therefore, return 3 as the starting index.
    Example 2:
    Input: gas = [2,3,4], cost = [3,4,3]
    Output: -1
    Explanation:
    You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
    Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
    Travel to station 0. Your tank = 4 - 3 + 2 = 3
    Travel to station 1. Your tank = 3 - 3 + 3 = 3
    You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
    Therefore, you can't travel around the circuit once no matter where you start.
    Constraints:
    n == gas.length == cost.length
    1 <= n <= 105
    0 <= gas[i], cost[i] <= 104

    Approach 1:
    * intuition is to start from each gas station and check if we are able to cover all the gas station
    if so then return the starting station as result
    algo:
    * iterate i =0 to n  where n is number of stations
    * initialize fuel=0, j=i, stations covered count=0 and iterate until count<n
    * for each value of j fuel = fuel[i] - cost[i] , check if fuel is still +ve if not break the loop
    and start over from next value of i
    * count is n then return the current value of i as result
    time & space:
    * n2 time complexity as we have 2 loops and no extra space is needed

    Approach 2:
    * intuition is that if their exists a path then sum of all the gains will be >= 0 else there will not
    be circular path. if path exists, then starting from 0 at any point gain drops below 0 then any point
    between these is not a valid start point, then start over from the next station as start point
    algo:
    * first check if path exists by summing all the gains and checking if >=0 if so then continue else return -1
    * start from i=0 to n start summing the gain whenever gain goes below 0 update the start point to next pointer
    and reset the gain
    * at the end whatever is the start point we got will be the actual start point.
    time & space:
    * n time and 0 space
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        return canCompleteCircuitA2(gas,cost);
    }
    public int canCompleteCircuitA2(int[] gas, int[] cost) {
        int n = gas.length, gain=0, start=0;
        for(int i=0; i<n;i++)
            gain+=gas[i]-cost[i];
        if(gain<0)return -1;
        gain=0;
        for(int i=0; i<n; i++){
            gain += gas[i] - cost[i];
            if(gain<0){
                gain=0;
                start = i+1;
            }
        }
        return start;
    }



    public int canCompleteCircuitA1(int[] gas, int[] cost) {
        int n = gas.length;
        for(int i=0; i<n; i++){
            int count=0, j=i,fuel=0;
            while(count<n){
                fuel += gas[j] - cost[j];
                if(fuel<0)break;
                count++;
                j++;
                j = j%n;
            }
            if(count==n)return i;
        }
        return -1;
    }
}