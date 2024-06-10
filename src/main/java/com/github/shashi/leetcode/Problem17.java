package com.github.shashi.leetcode;
import java.util.*;
public class Problem17 {
    /*
    Letter Combinations of a Phone Number
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
    represent. Return the answer in any order.
    A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to
    any letters.

    Example 1:
    Input: digits = "23"
    Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
    Example 2:
    Input: digits = ""
    Output: []
    Example 3:
    Input: digits = "2"
    Output: ["a","b","c"]
    Constraints:
    0 <= digits.length <= 4
    digits[i] is a digit in the range ['2', '9'].

    Approach 1: backtracking rec
    * intuition is to enumerate all the combinations by keeping each position constant per digit and getting all
    the other combinations
    algo:
    * create a res list to store answers, map of char, list of char to get all the characters per digit
    * call rec with res,map, digits, new stringbuilder and 0 index
    * in rec base case is to check if sb length is equal to digits length if so we found the combination add
    it to result and return back.
    * iterate over all the characters in map for the digit at index and append the char to sb and call rec
    by inc index
    * after rec delete the last char from sb this is called backtracking.
    time & space:
    * if N is the length of digits then we iterate for all chars and for each char we traverse 4 power N combinations
    so over all time complexity is N*4^N. space is N for recursion stack output space is not included.
     */
    Map<Integer,List<String>> map = new HashMap<>();
    Map<Character,String> map2 = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        return letterCombinationsA1(digits);
    }


    public List<String> letterCombinationsA1(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length()==0)return res;
        Map<Character,List<Character>> map = new HashMap<>();
        map.put('2',Arrays.asList('a','b','c'));
        map.put('3',Arrays.asList('d','e','f'));
        map.put('4',Arrays.asList('g','h','i'));
        map.put('5',Arrays.asList('j','k','l'));
        map.put('6',Arrays.asList('m','n','o'));
        map.put('7',Arrays.asList('p','q','r','s'));
        map.put('8',Arrays.asList('t','u','v'));
        map.put('9',Arrays.asList('w','x','y','z'));
        rec(digits,map,res,0,new StringBuilder());
        return res;
    }

    public void rec(String digits, Map<Character,List<Character>> map, List<String> res,
                    int index, StringBuilder sb){
        if(digits.length()==index){
            res.add(sb.toString());
            return;
        }
        for(char c : map.get(digits.charAt(index))){
            sb.append(c);
            rec(digits, map, res, index+1,sb);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
