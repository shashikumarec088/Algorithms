package com.github.shashi.leetcode;
import java.util.*;
public class Problem13 {
    /*
    Roman to Integer
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, 2 is written as II in Roman numeral, just two ones added together.
    12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

    Roman numerals are usually written largest to smallest from left to right. However,
    the numeral for four is not IIII. Instead, the number four is written as IV. Because
    the one is before the five we subtract it making four. The same principle applies to the number nine,
    which is written as IX. There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given a roman numeral, convert it to an integer.

    Example 1:
    Input: s = "III"
    Output: 3
    Explanation: III = 3.
    Example 2:
    Input: s = "LVIII"
    Output: 58
    Explanation: L = 50, V= 5, III = 3.
    Example 3:
    Input: s = "MCMXCIV"
    Output: 1994
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

    Constraints:
    1 <= s.length <= 15
    s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
    It is guaranteed that s is a valid roman numeral in the range [1, 3999].

    Approach 1:
    * intuition is to process the roman string from right to left and if we find that current char value is >= prev char
    value then we add it else we subtract it to the total value
    algo:
    * create a map to get the values from roman char to int value, initialize the prev val and sum to 0
    * start processing the string from end at each iteration check if value of cur char is >= prev then add to sum else sub
    from sum, repeat thin until all the characters are processed.
    time & space:
    * time digit length and space is constant

    Approach 2:
    * intuition is to process the string from start check if current element is smaller than next if so process 2
    else 1.
    algo:
    * create a map to get the values from roman char to int value, initialize the prev val and sum to 0
    * start processing the string from the start, check if current is < next if so process 2 elements
    add next element - cur element value to result.
    * if the cur element is >= next then add cur to sum and proceed.
    * process all the elements in the string and return result at the end.
    time & space:
    * time digit length and space is constant
     */

    public int romanToInt(String s) {
        return romanToIntA2(s);
    }


    public int romanToIntA2(String s) {
        int ans=0, n= s.length();
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int i=0;
        while(i<n){
            if(i+1<n && map.get(s.charAt(i))< map.get(s.charAt(i+1))){
                ans = ans+ map.get(s.charAt(i+1)) - map.get(s.charAt(i));
                i=i+2;
            }else ans+= map.get(s.charAt(i++));
        }
        return ans;
    }

    public int romanToIntA1(String s) {
        int ans=0, prev=0, n= s.length();
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        for(int i=n-1; i>=0; i--){
            int c = map.get(s.charAt(i));
            if(c < prev)ans -=c;
            else ans+=c;
            prev=c;
        }
        return ans;
    }
}