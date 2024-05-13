package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem224 {
    /*
    Basic Calculator
    Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result
    of the evaluation.

    Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions,
    such as eval().

    Example 1:
    Input: s = "1 + 1"
    Output: 2
    Example 2:
    Input: s = " 2-1 + 2 "
    Output: 3
    Example 3:
    Input: s = "(1+(4+5+2)-3)+(6+8)"
    Output: 23

    Constraints:
    1 <= s.length <= 3 * 105
    s consists of digits, '+', '-', '(', ')', and ' '.
    s represents a valid expression.
    '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
    '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
    There will be no two consecutive operators in the input.
    Every number and running calculation will fit in a signed 32-bit integer.

    Approach 1:
    * intuition is to use the stack to evaluate the expression if we evaluate the expression from left to right
    we handle the cases wrongly for example (1-7+5) if we process this from left to right ans is -1 but if we process
    from right to left then ans is 11 which is wrong, hence while using stack we need to process in reverse order
    from right to lest. we need to evaluate the expression whenever we see the closing bracket till we see the
    opening bracket.
    algo:
    * create the empty stack of type object to hold both symbols, brackets and numbers, initialize cur=0, n=0 to hold
    the current digit and the position in n.
    * iterate from i=length-1 to 0, if the c is digit then compute the position as pow(10,n)*(c-0) and add this to cur
    * when c is not digit and not empty then push the cur number to stack and if c is opening bracket then process the
    values in stack until the closing bracket this is done in eval method.
    * remove the closing bracket from stack and push the eval result to stack. if c is symbol or closing bracket then
    push to stack and proceed.
    * at the end handle the case where expression has only numbers in that case n>0 then push cur to stack and eval the
    stack and return the result
    * in eval we check if stack is empty or top element is not integer if so then we push 0
    * we process until stack is not empty and top element is not closing bracket.
    * we pop the cur char from stack which will be either + or - based on that we add to the result
    * we return the result at the end
    time & space:
    we take n space and time
     */
    public int calculate(String s) {
        return calculateA1(s);
    }
    public int calculateA1(String s) {
        int cur=0,n=0,m=s.length();
        Stack<Object> stack = new Stack<>();
        for(int i=m-1; i>=0; i--){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                cur = (int)Math.pow(10,n)*(int)(c-'0')+cur;
                n++;
            }else if(c !=' '){
                if(n>0){
                    stack.push(cur);
                    cur=0;
                    n=0;
                }
                if(c =='('){
                    int res = eval(stack);
                    stack.pop();
                    stack.push(res);
                }else stack.push(c);
            }
        }
        if(n>0)stack.push(cur);
        return eval(stack);
    }

    private int eval(Stack<Object> stack){
        if(stack.isEmpty() || !(stack.peek() instanceof Integer))
            stack.push(0);
        int res = (int)stack.pop();
        while(!stack.isEmpty() && (char)stack.peek() !=')'){
            char c = (char)stack.pop();
            if(c=='+')res+=(int)stack.pop();
            else if(c=='-')res-=(int)stack.pop();
        }
        return res;
    }
}
