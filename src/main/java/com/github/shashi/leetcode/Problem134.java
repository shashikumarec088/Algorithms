package com.github.shashi.leetcode;

public class Problem134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        return canCompleteCircuitA2(gas,cost);
    }
    /*
        check if path exists it exists if total fual >= 0
        else no path. once we have the path then we can
        traverse through the gas stations summing the
        dela if it is < 0 then any station between start
        to that station can not have the start. then
        start from next station.
    */
    public int canCompleteCircuitA2(int[] gas, int[] cost) {
        int n = gas.length, total=0;
        // check if path exists
        for(int i=0; i<n; i++){
            total+= gas[i]-cost[i];
        }
        if(total <0)return -1;

        int surplus = 0, start=0;
        for(int i=0; i<n;i++){
            surplus+= gas[i]-cost[i];
            if(surplus<0){
                surplus=0;
                start=i+1;
            }
        }
        return start;
    }

    /*
        starting at any index if we travel across the gas stations
        and still we have 0 or more fuel then that is the path
        else no path exists we Time limit with this is is O(n2)
        solution
    */
    public int canCompleteCircuitA1(int[] gas, int[] cost) {
        int n = gas.length;
        for(int i=0; i<n; i++){
            int count=0, j=i, fuel=0;
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