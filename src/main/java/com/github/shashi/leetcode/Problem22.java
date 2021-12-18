package com.github.shashi.leetcode;
import java.util.*;
public class Problem22 {
    public List<String> generateParenthesis(int n) {
        return generateParenthesisBack(n);
    }

    public static void main(String[] args) {
        Problem22 p22 = new Problem22();
        p22.generateParenthesis(3);
    }

    public List<String> generateParenthesisBack(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open+1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close+1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public List<String> generateParenthesisBrut(int n){
        List<String> list = new ArrayList<>();
        rec(new char[2*n],0,list);
        return list;
    }

    public void rec(char[] cur, int pos, List<String> out){
        if(pos == cur.length){
            if(valid(cur))
                out.add(new String(cur));
        }else{
            cur[pos]='(';
            rec(cur,pos+1,out);
            cur[pos]=')';
            rec(cur,pos+1,out);
        }
    }

    public boolean valid(char[] cur){
        int bal=0;
        for(char c :cur){
            if(c == '(') bal++;
            else bal--;
            if(bal <0) return false;
        }
        return bal==0;
    }
}
