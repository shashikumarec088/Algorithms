package com.github.shashi.leetcode;

public class Problem779 {
    public int kthGrammar(int n, int k) {
        return kthGrammarA1(n,k);
    }

    public int kthGrammarA1(int n, int k) {
        return rec(n,k);
    }

    /*
    when we check the pattern we will be able to find the recurrence relation
    0
    01
    0110
    as f(n,k) = if(k%2==0) then if f(n-1,k/2)==0 then 1 else 0;
                else f(n-1,(k+1)/2)
     */
    public int rec(int n, int k) {
        if(n==1 || k==1)return 0;
        if(k%2==0){
            int temp = rec(n-1,k/2);
            if(temp==0)return 1;
            else return 0;
        }else{
            return rec(n-1,(k+1)/2);
        }
    }
}