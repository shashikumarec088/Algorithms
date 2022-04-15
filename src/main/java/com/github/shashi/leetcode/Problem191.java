package com.github.shashi.leetcode;

public class Problem191 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        return hammingWeightA2(n);
    }

    public int hammingWeightA2(int n){
        int count=0,mask=1;
        for(int i=0; i<32; i++){
            if((n&mask) != 0)count++;
            mask<<=1;
        }
        return count;
    }

    public int hammingWeightA1(int n){
        int count=0,i=32;
        while(n!=0){
            if((n&1)==1)count++;
            n = n>>>1;
        }
        return count;
    }
}