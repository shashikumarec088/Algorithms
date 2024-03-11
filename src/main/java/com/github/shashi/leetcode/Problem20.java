package com.github.shashi.leetcode;
import java.util.*;
public class Problem20 {
    public boolean isValid(String s) {
        return isValidA2(s);
    }

    public boolean isValidA2(String s){
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        char[] sr = s.toCharArray();
        return rec(sr,sr.length,map);
    }

    public boolean rec(char[] sr, int n, Map<Character,Character> map){
        if(n==0) return true;
        if(n%2 != 0) return false;
        if(sr[0]==')'||sr[0]==']'||sr[0]=='}') return false;
        char closing = map.get(sr[0]);
        int i, count = 0;
        for(i = 1; i<n; i++){
            if(sr[i] == sr[0])
                count++;
            else if(sr[i] == closing){
                if(count == 0)
                    break;
                count--;
            }
        }
        if(i==n) return false;
        if(i==1) return rec(Arrays.copyOfRange(sr,2,n),n-2,map);
        return rec(Arrays.copyOfRange(sr,1,i),i-1,map) &&
                rec(Arrays.copyOfRange(sr,i+1,n),n-i-1,map);
    }

    public boolean isValidA3(String s) {
        Map<Character,Character> open = new HashMap<>();
        Map<Character,Character> close = new HashMap<>();
        Stack<Character> stack = new Stack<>();
        open.put('(',')');
        open.put('[',']');
        open.put('{','}');
        close.put(')','(');
        close.put(']','[');
        close.put('}','{');
        int n = s.length();
        for(int i=0; i<n; i++){
            Character c = s.charAt(i);
            if(open.containsKey(c))stack.push(c);
            else if(!stack.isEmpty() && stack.peek().equals(close.get(c))){
                stack.pop();
            } else return false;
        }
        if(stack.isEmpty())return true;
        return false;

    }

    public boolean isValidA1(String s){
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put(']','[');
        map.put('}','{');
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if(!stack.isEmpty() && stack.peek().equals(map.get(c)))
                    stack.pop();
                else return false;
            }
            else stack.push(c);
        }
        return stack.isEmpty()?true:false;
    }
}
