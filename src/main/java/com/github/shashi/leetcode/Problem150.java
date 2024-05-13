package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Problem150 {
    /*
        Evaluate Reverse Polish Notation
        You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

        Evaluate the expression. Return an integer that represents the value of the expression.

        Note that:

        The valid operators are '+', '-', '*', and '/'.
        Each operand may be an integer or another expression.
        The division between two integers always truncates toward zero.
        There will not be any division by zero.
        The input represents a valid arithmetic expression in a reverse polish notation.
        The answer and all the intermediate calculations can be represented in a 32-bit integer.

        Example 1:
        Input: tokens = ["2","1","+","3","*"]
        Output: 9
        Explanation: ((2 + 1) * 3) = 9
        Example 2:
        Input: tokens = ["4","13","5","/","+"]
        Output: 6
        Explanation: (4 + (13 / 5)) = 6
        Example 3:
        Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        Output: 22
        Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
        = ((10 * (6 / (12 * -11))) + 17) + 5
        = ((10 * (6 / -132)) + 17) + 5
        = ((10 * 0) + 17) + 5
        = (0 + 17) + 5
        = 17 + 5
        = 22
        Constraints:
        1 <= tokens.length <= 104
        tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].

        Approach 1:
        * intuition is to keep pushing the digits to stack and when current string is symbol the pup last 2
        elements perform operation and push the result, return last element in stack
        algo:
        * create the symbols set and empty stack
        * iterate over all strings if string is symbol pop b = first element a = 2nd element in stack
        * perform operation based on symbol then add result to stack
        * return top element in stack at the end.
        time & space:
        n time and n space
     */
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