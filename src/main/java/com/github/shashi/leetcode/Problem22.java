package com.github.shashi.leetcode;
import java.util.*;
public class Problem22 {
    /*

    Generate Parentheses
    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    Example 1:
    Input: n = 3
    Output: ["((()))","(()())","(())()","()(())","()()()"]
    Example 2:
    Input: n = 1
    Output: ["()"]

    Constraints:
    1 <= n <= 8

    Approach 1: brute force approach
    * intuition is to generate all the possible combinations of length n and validate is it valid if so consider,
    to generate all the combinations we can use the queue initially by keeping the empty string and iterating until
    queue is empty and each time taking the string appending open bracket and pushing to queue and close bracket
    and pushing to queue.
    algo:
    * initialize the list of type string to store the results, and Queue of type string, add empty string to queue
    * iterate until queue is empty, at each iteration poll the string, check if its length is 2*n if so validate
    the string and add to res if valid
    * if its length is less than 2*n then append open bracket and add to queue, append closed bracket and add to queue
    * return res at the end
    * in validate function we iterate through each element and keep counting the number of open brackets we dec if we
    find the closed bracket
    * when left count reaches below 0 we return false indicating the string is not valid
    * at the end of left count is 0 we return true.
    time & space:
    * it takes 2^2n time total since we need to form the string of length 2n and each position can hold 2 values.
    max space taken by queue is 2^2n

    Approach 2: backtracking
    * the approach 1 is simple but inefficient since we generate all valid and invalid combinations.
    *in backtracking we build the valid candidates as we go.
    algo:
    * initialize res variable to hold the result, call backtrack with res, open,close as zeros, emtpy string
    builder and size n
    * in backtracking first we check if sb length is 2n if so we add to result and return
    * if open < n then we add open bracket to sb and call backtrack with open+1
    * we remove last char from sb after backtrack
    * if close < open append close bracket and call backtrack with close+1
    * we remove last char from sb after backtrack
    time & space:
    * it takes 4^n/ sqrt(n) this is based on catelian number time and n space for recursion stack.


     */
    public List<String> generateParenthesis(int n) {
        return generateParenthesisA2(n);
    }

    public List<String> generateParenthesisA2(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res,0,0,new StringBuilder(),n);
        return res;
    }

    public static void main(String[] args) {
        Problem22 problem22 = new Problem22();
        List<String> res = problem22.generateParenthesis(3);
    }

    public void backtrack(List<String> res, int open, int close,
                          StringBuilder sb, int n){
        if(sb.length()==2*n){
            res.add(sb.toString());
            return;
        }
        if(open< n){
            sb.append("(");
            backtrack(res,open+1,close,sb,n);
            sb.deleteCharAt(sb.length()-1);
        }
        if(close< open){
            sb.append(")");
            backtrack(res,open,close+1,sb,n);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public List<String> generateParenthesisA1(int n) {
        List<String> res = new LinkedList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        while(!queue.isEmpty()){
            String s = queue.poll();
            if(s.length()==2*n){
                if(validate(s))res.add(s);
            }else{
                queue.offer(s+"(");
                queue.offer(s+")");
            }
        }
        return res;
    }

    private boolean validate(String s){
        int left=0;
        for(char c : s.toCharArray()){
            if(c==')')left--;
            if(c=='(')left++;
            if(left<0)return false;
        }
        return left==0;
    }
}
