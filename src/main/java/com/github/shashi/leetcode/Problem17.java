package com.github.shashi.leetcode;
import java.util.*;
public class Problem17 {
    Map<Integer,List<String>> map = new HashMap<>();
    Map<Character,String> map2 = new HashMap<>();
    public List<String> letterCombinations(String digits) {
        return letterCombinationsA3(digits);
    }

    public List<String> letterCombinationsA3(String digits){
        map2.put('2',"abc");
        map2.put('3',"def");
        map2.put('4',"ghi");
        map2.put('5',"jkl");
        map2.put('6',"mno");
        map2.put('7',"pqrs");
        map2.put('8',"tuv");
        map2.put('9',"wxyz");

        LinkedList<String> result = new LinkedList<>();
        if(digits.length()==0)return result;
        result.add("");
        while(result.peekFirst().length()!=digits.length()){
            String element = result.pollFirst();
            for(char c : map2.get(digits.charAt(element.length())).toCharArray())
                result.addLast(element+c);
        }
        return result;
    }

    public List<String> letterCombinationsA2(String digits){
        map2.put('2',"abc");
        map2.put('3',"def");
        map2.put('4',"ghi");
        map2.put('5',"jkl");
        map2.put('6',"mno");
        map2.put('7',"pqrs");
        map2.put('8',"tuv");
        map2.put('9',"wxyz");
        List<String> result = new ArrayList<>();
        if(digits.length()==0)return result;
        return backTrack(0, digits, new StringBuilder(),result);
    }

    public List<String> backTrack(int i, String digits, StringBuilder sb,
                                  List<String> result){
        if(i==digits.length()){
            result.add(sb.toString());
            return result;
        }
        for(char c: map2.get(digits.charAt(i)).toCharArray()){
            sb.append(c);
            backTrack(i+1,digits, sb, result);
            sb.deleteCharAt(sb.length()-1);
        }
        return result;
    }

    public List<String> letterCombinationsA1(String digits){
        map.put(2,Arrays.asList("a","b","c"));
        map.put(3,Arrays.asList("d","e","f"));
        map.put(4,Arrays.asList("g","h","i"));
        map.put(5,Arrays.asList("j","k","l"));
        map.put(6,Arrays.asList("m","n","o"));
        map.put(7,Arrays.asList("p","q","r","s"));
        map.put(8,Arrays.asList("t","u","v"));
        map.put(9,Arrays.asList("w","x","y","z"));
        if(digits.length()==0)return new ArrayList<>();
        return rec(Integer.parseInt(digits));
    }

    public List<String> rec(Integer num){
        if(num<10)return map.get(num);
        List<String> last = map.get(num%10);
        List<String> ans = new ArrayList<>();
        List<String> rList = rec(num/10);
        for(String s : rList)
            for(String s2 : last)
                ans.add(s.concat(s2));
        return ans;
    }
}
