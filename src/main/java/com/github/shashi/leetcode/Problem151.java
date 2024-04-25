package com.github.shashi.leetcode;

public class Problem151 {
    /*
    Reverse Words in a String
    Given an input string s, reverse the order of the words.
    A word is defined as a sequence of non-space characters. The words in s will be separated by at
    least one space.
    Return a string of the words in reverse order concatenated by a single space.
    Note that s may contain leading or trailing spaces or multiple spaces between two words.
    The returned string should only have a single space separating the words. Do not include
    any extra spaces.

    Example 1:
    Input: s = "the sky is blue"
    Output: "blue is sky the"

    Example 2:
    Input: s = "  hello world  "
    Output: "world hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.

    Example 3:
    Input: s = "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed
    string.

    Constraints:
    1 <= s.length <= 104
    s contains English letters (upper-case and lower-case), digits, and spaces ' '.
    There is at least one word in s.

    Follow-up: If the string data type is mutable in your language, can you solve it in-place
    with O(1) extra space?

    Approach 1:
    * intuition is to reverse each word first in the string and at the end reverse the complete string
    this will make each word in the string to reverse its position.
    algo:
    * have the string start and end index trim the starting and ending spaces. have empty string builder
    * iterate from start position to end position, keep adding char to sb and when we encounter the
    space and previous char is not space reverse the word in sb then continue with next word.
    * store the start position of next word which helps in reversing the word in the string
    * at the end reverse the last word and then reverse the complete  string sb to move the words.
    * return sb
    time & space:
    * space is o(n) and time is o(n)

    Approach 2:
    * intuition is to split the string with spaces(\\s+) and iterate over the strings from last
    and form the string
    algo:
    * create an empty stringBuilder sb and split the input spring on \\s+ and iterate each string from
    end
    * check if prev char at sb is not space and sb length is > 0 then append the space and the current
    word then move to next word.
    * remember to consider world only if it is not empty

     */
    public static void main(String[] args) {
        Problem151 problem151 = new Problem151();
        String s = "  hello world  ";
        String s1 = problem151.reverseWordsA1(s);

        System.out.println(s1);
    }

    public String reverseWordsA2(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split("\\s+");
        for(int i=strs.length-1; i>=0; i--){
            if(strs[i].length()>0){
                if(sb.length()>0 && sb.charAt(sb.length()-1)!=' ')
                    sb.append(' ');
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int st, int en){
        while(st<en){
            char temp = sb.charAt(st);
            sb.setCharAt(st,sb.charAt(en));
            sb.setCharAt(en,temp);
            st++;
            en--;
        }
    }

    public String reverseWordsA1(String s) {
        int st=0, en=s.length()-1, prev=0;
        while(st<en && s.charAt(st)==' ')st++;
        while(en>st && s.charAt(en)==' ')en--;
        StringBuilder sb = new StringBuilder();
        for(int i=st; i<=en; i++){
            char c = s.charAt(i);
            if(c == ' ' && sb.charAt(sb.length()-1)!=' '){
                reverse(sb,prev,sb.length()-1);
                sb.append(' ');
                prev = sb.length();
            }else if(c !=' ')sb.append(c);
        }
        reverse(sb,prev,sb.length()-1);
        reverse(sb,0,sb.length()-1);
        return sb.toString();
    }
}
