package com.github.shashi.leetcode;

import java.math.BigInteger;

public class Problem67 {
    /*
    Add Binary
    Given two binary strings a and b, return their sum as a binary string.
    Example 1:
    Input: a = "11", b = "1"
    Output: "100"
    Example 2:
    Input: a = "1010", b = "1011"
    Output: "10101"
    Constraints:
    1 <= a.length, b.length <= 104
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself.

    Approach 1:
    * intuition is to do what is said to do using bit manipulation, consider from the end, make sure first string
    we are processing has length >= 2nd string, at each index inc sum if it is 1 and also add the carry, if
    sum if odd append 1 else 0 and make carry as sum/2
    algo:
    * init n= a.length and m=b.length check if n<m then call same method by swapping strings
    * init stringBuilder sb and j=m-1, carry=0
    * iterate from i=n-1 to i=0 for each iteration, check if char at i in a is 1 if so inc carry
    * check if j is >-1 and char at j is 1 if so inc carry and dec j
    * append carry%2 to sb and make carry = carry/2;
    * at the end if carry>0 append 1 to sb
    * reverse sb and return toString()
    time & space:
    * max(m,n) time and max(m,n) space

    Approach 2:
    * intuition is to use the bit manipulation technique, for 2 numbers the sum without carry will be xor of 2
    numbers and carry for each bit will be and of two numbers leftshifted by 1 bit. if keep doing this by considering
    ans without carry as first element and carry as second element until carry becomes zero
    algo:
    * create biInteger to handle bigger integers for a as x,b as y, 0 as z and for ans and carry
    * iterate until y is not equal to z compare using compareTo method
    * make ans= x.xor(y) and carry as x.and(y) shiftedLeft by 1 bit
    * make x=ans and y=carry
    * return x by converting into string(2)
    time & space:
    * takes m+n time and max(m,n) space
     */
    public static void main(String[] args) {
        Problem67 problem67 = new Problem67();
        problem67.addBinary("11","1");
    }
    public String addBinary(String a, String b) {
        return addBinaryA1(a,b);
    }

    public String addBinaryA2(String a, String b) {
        BigInteger x = new BigInteger(a,2);
        BigInteger y = new BigInteger(b,2);
        BigInteger z = new BigInteger("0",2);
        BigInteger ans,carry;
        while(y.compareTo(z)!=0){
            ans = x.xor(y);
            carry=x.and(y).shiftLeft(1);
            x=ans;
            y=carry;
        }
        return x.toString(2);
    }

    public String addBinaryA1(String a, String b) {
        int n = a.length(), m=b.length();
        if(n<m)return addBinaryA2(b,a);
        StringBuilder sb = new StringBuilder();
        int carry=0,j=m-1;
        for(int i=n-1; i>=0;i--){
            if(a.charAt(i)=='1')carry++;
            if(j>-1 && b.charAt(j--)=='1')carry++;
            sb.append(carry%2);
            carry/=2;
        }
        if(carry>0)sb.append(carry);
        return sb.reverse().toString();
    }
}