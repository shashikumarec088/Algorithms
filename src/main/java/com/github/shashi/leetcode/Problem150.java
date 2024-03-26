package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Problem150 {
    public int evalRPN(String[] tokens) {
        return evalRPNA1(tokens);
    }

    public int evalRPNA1(String[] tokens) {
        List<String> list = Arrays.asList("+","-","/","*");
        Stack<Integer> stack = new Stack<>();
        for(int i=0;  i<tokens.length; i++){
            String c = tokens[i];
            if(!list.contains(c))stack.push(Integer.valueOf(c));
            else{
                int b = stack.pop();
                int a = stack.pop();
                if(c.equals("+"))stack.push(a+b);
                else if(c.equals("-"))stack.push(a-b);
                else if(c.equals("/"))stack.push(a/b);
                else if(c.equals("*"))stack.push(a*b);
            }
        }
        return stack.pop();
    }
}