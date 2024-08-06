package com.github.shashi.leetcode;

public class Problem69 {
    /*
    Sqrt(x)
    Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned
    integer should be non-negative as well.
    You must not use any built-in exponent function or operator.
    For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.

    Example 1:
    Input: x = 4
    Output: 2
    Explanation: The square root of 4 is 2, so we return 2.
    Example 2:
    Input: x = 8
    Output: 2
    Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.

    Constraints:
    0 <= x <= 231 - 1

    Approach 1:
    * intuition is for any number x the sqrt will be <= x/2. since we are expected to find the integer sqrt
    we need to find a between 2, x/2 such that a^2 <= x <= (a+1)^2. this we can do it by doing binary search between
    the range 2, x/2 both inclusive. at the end we consider the r if exact match is not found because we are expected
    to round off the sqrt to lower integer.
    algo:
    * check if x<2 if so return x, init l=2, r=x/2
    * iterate until l<=r, find m = l+((r-l)/2
    * if (long) m*m > x then make r=m-1;
    * if m*m < x then make l=m+1 else return m
    * at the end return r
    time & space:
    * it takes log n time and constant space

    Approach 2: recursion
    * intuition is for any number x the sqrt is 2 * sqrt(x/4) and the base case is when x<2 return x. One thing
    to note is when for x when x is 2 the rec returns 0 but the ans is 1 this is handled by considering the
    left and right bound and checking if right * right is <= x then we consider right lese left.
    algo:
    * call rec with x, check base case if x<2 then return x
    * init left = 2 * rec(x/4)
    * right = left+1
    * return if right * right is <= x then right else left
    time & space:
    * log n time and log n space for rec stack
     */
    public int mySqrt(int x) {
        return mySqrtA2(x);
    }

    public int mySqrtA2(int x) {
        if(x< 2)return x;
        int left = mySqrtA2(x/4)*2;
        int right = left+1;
        return (long)right*right > x?left:right;
    }

    public int mySqrtA1(int x) {
        if(x<=1)return x;
        int l=2, r = x/2;
        while(l<=r){
            int mid = l+(r-l)/2;
            long res = (long)mid*mid;
            if(res==x)return mid;
            else if(res <x) l = mid+1;
            else r = mid-1;
        }
        return r;
    }
}