package com.github.shashi.leetcode;

import java.util.*;

public class Problem50 {
    /*
    Pow(x, n)
    Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
    Example 1:
    Input: x = 2.00000, n = 10
    Output: 1024.00000
    Example 2:
    Input: x = 2.10000, n = 3
    Output: 9.26100
    Example 3:
    Input: x = 2.00000, n = -2
    Output: 0.25000
    Explanation: 2-2 = 1/22 = 1/4 = 0.25
    Constraints:
    -100.0 < x < 100.0
    -231 <= n <= 231-1
    n is an integer.
    Either x is not zero or n > 0.
    -104 <= xn <= 104

    Approach 1: recursion
    * intuition is to use the recursion with binary exponentiation, where we multiply x by itself and reduce the
    power by half. we need to handle the case when n is odd, we need to multiply the x to the result. when n is
    2^-31 we need to use the long else we end with overflowing when we multiply with -1 when n is -v1.
    algo:
    * call recursion with x, and type casting n to long
    * if n==0 then return 1, if n<0 then make x = 1/x and n = -1*n;
    * if n%2== 0 then return rec(x*x,n/2) else return x*rec(x*x,n/2)
    time & space:
    * it takes log n time and long n space for recursion stack.

    Approach 2: iterative approach
    * intuition is to convert above recursion into iteration, for that we will have the res=1,prod and we start iterating
    from i=n when i is odd we muntiply the current prod to res, we make prod=prod*prod and reduce the iby i/2.
    algo:
    * cast n to long to handle the overflow case, if n<0 make x=1/x and n=-1*n
    * init ans=1, prod=x iterate i=n to >0 reduce i by i/2 after each iteration
    * if i%2 ==1 multiply ans with prod this is to handle the odd powers
    * make prod= prod*prod
    * return ans at the end.
    time & space:
    * it takes log n time and space
     */
    public static void main(String[] args) {
        Problem50 problem119 = new Problem50();
        System.out.println(problem119.myPow(2,1));
    }

    public double myPow(double x, int n) {
        return myPowA1(x,n);
    }
    public double myPowA1(double x, int n) {
        return rec(x,(long)n);
    }

    public double myPowA2(double x, int n) {
        long n1 = (long)n;
        if(n<0){
            x = 1/x;
            n1 = -1*n1;
        }
        double ans=1, prod=x;
        for(long i=n1; i>0; i=i/2){
            if(i%2==1)ans=ans*prod;
            prod = prod*prod;
        }
        return ans;
    }

    public double rec(double x, long n) {
        if(n==0)return 1.0;
        if(n<0){
            return 1.0/rec(x,-1*n);
        }
        if(n%2==1) return x*rec(x*x,n/2);
        else return rec(x*x,n/2);
    }


}
