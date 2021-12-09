package com.github.shashi.leetcode;

import java.util.*;

public class Problem50 {
    public static void main(String[] args) {
        Problem50 problem119 = new Problem50();
        System.out.println(problem119.myPow(2,1));
    }

    public double myPow(double x, int n) {
        return myPowItr(x,n);
    }
    public double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }
    public double myPowItr(double x, int n){
        long b =n;
        double a=x;
        if(n<0){
            b =b*-1;
            a = 1/a;
        }

        double ans = 1, current=a;
        for(long i=b; i>0; i/=2){
            if(i%2==1)ans = ans*current;
            current*=current;
        }
        return ans;
    }

    public double myPowRec(double x, int n){
        if(n==0) return 1;
        int p = n;
        double b = x;
        if(n<0){
            p = p*-1;
            b = 1/(double)x;
        }
        Map<Integer,Double> memo = new HashMap<>();
        return rec(b,p,memo);
    }

    public double rec(double x, int n,Map<Integer,Double> memo){
        if(memo.containsKey(n)) return memo.get(n);
        double result = 0;
        if(n==0)result=1;
        else{
            result= rec(x,n/2,memo);
            result*=result;
            if(n%2!=0)result*=x;
        }
        memo.put(n,result);
        return result;
    }
}
