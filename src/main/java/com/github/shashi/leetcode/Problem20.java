package com.github.shashi.leetcode;
import java.util.*;
public class Problem20 {
    /*
    Valid Parentheses
    Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

    An input string is valid if:
    Open brackets must be closed by the same type of brackets.
    Open brackets must be closed in the correct order.
    Every close bracket has a corresponding open bracket of the same type.

    Example 1:
    Input: s = "()"
    Output: true
    Example 2:
    Input: s = "()[]{}"
    Output: true
    Example 3:
    Input: s = "(]"
    Output: false
    Constraints:
    1 <= s.length <= 104
    s consists of parentheses only '()[]{}'.

    Approach 1:
    * intuition is to keep pushing the open brackets to stack and when we encounter the closing bracket,
    check if top element of stack is corresponding open bracket if not return false, else pop the element
    and proceed with next element, if stack is empty at the end then expression is valid
    algo:
    * create a stack of characters map with mapping between close to open brackets
    * iterate over the input string check if current element in map if so check if top of stack is
    corresponding open bracket
    * is so then pop the top element else return false
    * if not in map then keep adding to stack
    * at the end if stack is empty then return true else false
     */
    public boolean isValid(String s) {
        return isValidA1(s);
    }

    public boolean isValidA1(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put(')','(');
        map.put('}','{');
        map.put(']','[');
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                if(!stack.isEmpty() && stack.peek()==map.get(c))
                    stack.pop();
                else return false;
            }else stack.push(c);
        }
        return stack.isEmpty();
    }
}
