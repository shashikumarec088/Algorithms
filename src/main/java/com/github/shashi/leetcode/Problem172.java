package com.github.shashi.leetcode;

import java.math.BigInteger;

public class    Problem172 {
    /*
    Factorial Trailing Zeroes
    Given an integer n, return the number of trailing zeroes in n!.
    Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

    Example 1:
    Input: n = 3
    Output: 0
    Explanation: 3! = 6, no trailing zero.
    Example 2:
    Input: n = 5
    Output: 1
    Explanation: 5! = 120, one trailing zero.
    Example 3:
    Input: n = 0
    Output: 0
    Constraints:
    0 <= n <= 104
    Follow up: Could you write a solution that works in logarithmic time complexity?

    Approach 1: bf approach TLE
    * intuition is to calculate the factorial of given number and then count the number of zeros at the end.
    We need to use BigInteger since int does not fit ans for bigger numbers.
    algo:
    * import java.math.BigInteger. check if n is 0 then return 0.
    * init fact of type BigInteger and init BigInteger.ONE
    * iterate from i=1 to <= n, for each iteration compute fact = fact.multiply(BigInteger.valueOf(i))
    * init count=0, iterate until fact.mod(BigInteger.TEN) == BigInteger.ZERO inc count
    * make fact = fact.divide(BigInteger.TEN)
    * return count at the end
    time & space:
    * it takes nlogn time calculation involves approximating multiplication of 2 numbers takes log x log y, on
    deriving we get the n log n approximation

    Approach 2: optimized n time, counting factors of 5.
    * each zero at the end represents the multiplication by 10, to multiply 2 numbers we need to multiply all their
    factors ex : 15 * 30 = 450 => 15 = 5*3 and 30 = 2 * 3 * 5.
    * if we look at the factors of 2 numbers to determine the zeros at end we need to find the pairs of (2,5)
    in above example we have 1 pair. if we correlate this to factorial
    * in factorial we multiply all the numbers from 1 to n. we need to find the factors of each number from 1 to n
    and find the 5, 2 pairs.
    * but if we look closely we no need to consider 2s ex: for 5 factors are 2, 2,2  3 2 1. number of 2s will always
    be greater than 5 so if we look for number of 5 factors across numbers that is enough
    * to find 5 factors across numbers we need to interate each number until number%5 == 0 and count 5s and make num=
    num/5. this is to count the multiple factors for numbers like 25 which is 5 * 5 so there are 2 factors of 5 we
    need to count twice
    * to count 5s if we start from 5 and inc by 5 each step that is enough since we only need to find the factors
    of 5
    algo:
    * init count=0, iterate from i=5 i<=n and i=i+5 in steps of 5
    * for each number init cur=i, iterate until cur%5==0
    * inc count and make cur=cur/5;
    * return count at the end
    time & space:
    * it takes n time since we traverse across all numbers

    Approach 3: counting the factors of 5 effeciently
    * if we look the initial intuition for each value of i from 1 to n we were counting numbers which are factors
    of 5 if we ignore the duplicates part (ex 25 we counted twice, 125 we counted 3 times)
    * so we are basically calulating n/5 and combining with duplicates part which we get by diving n/25 when n
    is 25 and if n is 125 then we need to consider n/5+n/25+n/125
    * if we see the above pattern we continue till the value of pow is > n
    algo:
    * init pow =5, count=0 and iterate until  n >= pow
    * for each iteration we make count += n/pow and make pow= pow *5, we are increating by powers of 5
    * return count at the end
    time & space:
    * it takes log n time since we conider only powers of 5 from 5 till pow <=n and const space

     */

    public int trailingZeroes(int n) {
        return trailingZeroesA3(n);
    }

    public int trailingZeroesA1(int n) {
        if(n==0)return 0;
        BigInteger fact =BigInteger.ONE;
        for(int i=2; i<=n;i++)
            fact =fact.multiply(BigInteger.valueOf(i));
        int count=0;
        while(fact.mod(BigInteger.TEN).equals(BigInteger.ZERO)){
            count++;
            fact =fact.divide(BigInteger.TEN);
        }
        return count;
    }

    public int trailingZeroesA2(int n) {
        int count=0;
        for(int i=5; i<=n; i++){
            int cur=i;
            while(cur%5==0){
                count++;
                cur = cur/5;
            }
        }
        return count;
    }

    public int trailingZeroesA3(int n) {
        int count=0;
        long pow=5;
        while(n>=pow){
            count+=n/pow;
            pow= pow*5;
        }
        return count;
    }

    public int trailingZeroesA4(int n) {
        int count=0;
        while(n>0){
            count+=n/5;
            n=n/5;
        }
        return count;
    }
}
