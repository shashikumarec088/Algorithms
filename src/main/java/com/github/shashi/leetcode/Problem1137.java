package com.github.shashi.leetcode;
import java.util.*;
public class Problem1137 {
    Map<Integer,Integer> map = new HashMap<>();
    public int tribonacci(int n) {
        return tribDpItr(n);
    }

    public int tribDpItr(int n){
        int x=0,y=1,z=1,result=0;
        if(n==0)return 0;
        if(n<3) return 1;
        for(int i=3; i<=n;i++){
            result = x+y+z;
            x=y;
            y=z;
            z=result;
        }
        return result;
    }

    public int tribDp(int n){
        if(n==0) return 0;
        if(n<3) return 1;
        if(!map.containsKey(n))
            map.put(n,tribDp(n-1)+tribDp(n-2)+tribDp(n-3));
        return map.get(n);
    }
}
