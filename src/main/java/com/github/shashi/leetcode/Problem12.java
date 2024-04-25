package com.github.shashi.leetcode;
import java.util.*;
public class Problem12 {
    /*
    Integer to Roman
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

    Symbol       Value
    I             1
    V             5
    X             10
    L             50
    C             100
    D             500
    M             1000
    For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII,
    which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
    Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
    Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
    The same principle applies to the number nine, which is written as IX.
    There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.
    Given an integer, convert it to a roman numeral.

    Example 1:
    Input: num = 3
    Output: "III"
    Explanation: 3 is represented as 3 ones.
    Example 2:
    Input: num = 58
    Output: "LVIII"
    Explanation: L = 50, V = 5, III = 3.
    Example 3:
    Input: num = 1994
    Output: "MCMXCIV"
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

    Constraints:
    1 <= num <= 3999

    Approach 1:
    * intuition is to have all the 13 roman numbers and keep building from top to bottom by subtracting from number until
    number becomes smaller than the current symbol value
    algo:
    * have a map in desc order for key, value pairs, iterate over each element
    * for each symbol iterate over the number until num >=symbol if true append the symbol to res update the
    number else move to next symbol.
    * stop when the number < any digit.
    time & space:
    * it takes constant time and space as the input is limited to 3999

    Approach 2:
    * intuition is to process for symbols in desc order also keep the allowed difference for each symbol for example
    for synbol M (1000) allowed difference is C(100) because for any number from 999 to 900 we append (CM) similary
    for D(500) allowed difference is C(100), for C (100) allowed difference is X (10).
    * with this we can process the number in desc order by dividing the number to get the quetient and remainder and
    append symbol q times and append difference symbol and symbol if remainder is within limit.
    algo:
    * create arrays for symbols, numbers, diffsymbols and diffnumbers
    * iterate symbols in desc, find q and rem, iterate until q> 0 keep appending the symbol and subtract the symbol
    value from number
    * after that check of num-rem is within allowed limit if so append the diffSymbol and symbol and update number
    by subtracting the number and adding the diff
    * repeat the iteration for next symbol until number becomes 0.
    time & space:
    * it takes constant time and space as the input is limited to 3999
     */

    public static void main(String[] args) {
       String s = "  hello world  ";
       String[] s1 = s.split("\\s+");
       StringBuilder sb = new StringBuilder();

    }
    public String intToRoman(int num) {
        return intToRomanA2(num);
    }

    public String intToRomanA2(int num) {
        char[] symbols = {'M', 'D', 'C', 'L', 'X',
                'V','I'};
        int[] digits = {1000,500,100,50,10,5,1};
        int[] diffs = {100,100,10,10,1,1,0};
        char[] diffSymbols = {'C','C','X','X','I','I','I'};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<digits.length; i++){
            if(num>0){
                int q = num/digits[i];
                int rem = num%digits[i];
                while(q>0){
                    sb.append(symbols[i]);
                    num = num-digits[i];
                    q--;
                }
                if(digits[i]-rem <= diffs[i]){
                    sb.append(diffSymbols[i]);
                    sb.append(symbols[i]);
                    num = rem - digits[i]+diffs[i];
                }
            }else break;
        }
        return sb.toString();
    }

    public String intToRomanA1(int num){
        String[] symbols = new String[]{"M","CM","D","CD",
                "C","XC","L","XL",
                "X","IX","V","IV","I"};
        int[] values = new int[]{1000,900,500,400,100,90,50,40,
                10,9,5,4,1};
        int n = num;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<symbols.length;i++){
            while(n>= values[i]){
                sb.append(symbols[i]);
                n = n - values[i];
            }
        }
        return sb.toString();
    }


}
