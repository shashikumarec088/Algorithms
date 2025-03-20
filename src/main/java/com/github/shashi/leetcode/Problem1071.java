package com.github.shashi.leetcode;

public class Problem1071 {
    /*
    1071. Greatest Common Divisor of Strings

    For two strings s and t, we say "t divides s" if and only if s = t + ... + t (t concatenated with itself 1 or more
    times)
    Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

    Example 1:

    Input: str1 = "ABCABC", str2 = "ABC"
    Output: "ABC"
    Example 2:

    Input: str1 = "ABABAB", str2 = "ABAB"
    Output: "AB"
    Example 3:

    Input: str1 = "LEET", str2 = "CODE"
    Output: ""
    Example 4:

    Input: str1 = "ABCDEF", str2 = "ABC"
    Output: ""

    Constraints:

    1 <= str1.length <= 1000
    1 <= str2.length <= 1000
    str1 and str2 consist of English uppercase letters.

    Approach 1: brute force
    * intuition is to consider the small string from both the strings, iterate from biggest length
    till 1 and check if the substring is divisor for both the strings, while checking we need to
    consider the substring from start to i
    algo:
    * m is length of s1 and n is length of s2
    * iterate from i= min(m,n) to 1
    * check if substring of length i is valid gcd if so return substring else empty string
    * to check valid substring first check if m%i==0 and n%i==0 if so then
    * get the substring from 0 to i as common
    * replace common in s1 and check if empty string is left if so return false else continue
    * replace common in s2 and check if empty string is left if so return false else continue

    time & space:
    *  it takes min(m,n) * (m+n)
    * here min(m,n) iterations we do and for reach iteration we replace substrings in both the strings
    which in worst case takes m+n time
    * it takes min(m,n) space for the substring

    Approach 2: gcd property
    * intuition is if there exists the common devisor string between two substrings then
    concatination of s1,s2 and s2,s1 will be same else their will not be common devisor.
    * if they are same then gcd of those 2 lengths will be the common substring

    algo:
    * s1 and s2 are two strings
    * check if s1+s2 is equal to s2+s1 if not return ""
    * find gcd of s1.length and s2.length
    * return substring of s1 from 0 to gcd
    * to find gcd of a, b
    * iterate until b is not 0
    * find a%b and make a=b and b=a%b
    * return a at the end

    time & space:
    * it takes m+n time and constant space

     */
    public String gcdOfStrings(String str1, String str2) {
        return gcdOfStringsA1(str1, str2);
    }

    public String gcdOfStringsA2(String s1, String s2) {
        if(!(s1+s2).equals(s2+s1))return "";
        int gcd = getGcd(s1.length(),s2.length());
        return s1.substring(0,gcd);
    }

    private int getGcd(int a, int b){
        while(b!=0){
            int temp = a%b;
            a=b;
            b=temp;
        }
        return a;
    }

    public String gcdOfStringsA1(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        for(int i=Math.min(m,n);i>=1;i--){
            if(isValid(str1,str2,i))
                return str1.substring(0,i);
        }
        return "";
    }

    private boolean isValid(String str1, String str2, int k) {
        int n = str1.length();
        int m = str2.length();
        if (n % k != 0 || m % k != 0) return false;
        String common = str1.substring(0, k);
        return str1.replace(common, "").isEmpty() && str2.replace(common, "").isEmpty();
    }
}
