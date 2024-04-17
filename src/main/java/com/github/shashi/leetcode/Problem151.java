package com.github.shashi.leetcode;

public class Problem151 {
    public static void main(String[] args) {
        Problem151 problem151 = new Problem151();
        String s = "  hello world  ";
        String s1 = problem151.reverseWordsA2(s);

        System.out.println(s1);
    }
    /*
    adjust the trailing and starting spaces and traverse from end
    start reversing the string
     */
    public String reverseWordsA2(String s) {
        StringBuilder sb = new StringBuilder();
        int prev=0,n=s.length(), st=0,e=s.length()-1;
        while(st<n && s.charAt(st)==' ')st++;
        while(e>0 && s.charAt(e)==' ')e--;
        prev=0;
        for(int i=e; i>=st; i--){
            char c = s.charAt(i);
            if(c==' ' && sb.charAt(sb.length()-1)!=' '){
                reverseSb(sb,prev,sb.length()-1);
                sb.append(' ');
                prev = sb.length();
            }
            else if(c !=' ') sb.append(c);
        }
        reverseSb(sb,prev,sb.length()-1);
        return sb.toString();
    }

    private void reverseSb(StringBuilder sb, int s, int e){
        while(s<e){
            char temp = sb.charAt(s);
            sb.setCharAt(s,sb.charAt(e));
            sb.setCharAt(e,temp);
            s++;
            e--;
        }
    }
}
