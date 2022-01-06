package com.github.shashi.leetcode;
import java.util.*;
public class Problem17 {
    public List<String> letterCombinations(String digits) {
        return lettersComb(digits);
    }

    public List<String> lettersComb(String digits){
        List<String> results = new ArrayList<>();
        if(digits.length()==0)return results;
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        backTrack(digits, results, map,new StringBuilder(),0);
        return results;
    }

    public void backTrack(String digits,List<String> results,
                          Map<Character,String> map,StringBuilder sb, int index){
        if(sb.length()==digits.length())
            results.add(sb.toString());
        else{
            String newStr = map.get(digits.charAt(index));
            for(char c : newStr.toCharArray()){
                sb.append(c);
                backTrack(digits,results,map,sb,index+1);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
