package com.github.shashi.leetcode;

public class Problem58 {
    /*
    Length of Last Word
    Given a string s consisting of words and spaces, return the length of the last word in the string.
    A word is a maximal
    substring
     consisting of non-space characters only.

    Example 1:
    Input: s = "Hello World"
    Output: 5
    Explanation: The last word is "World" with length 5.

    Example 2:
    Input: s = "   fly me   to   the moon  "
    Output: 4
    Explanation: The last word is "moon" with length 4.

    Example 3:
    Input: s = "luffy is still joyboy"
    Output: 6
    Explanation: The last word is "joyboy" with length 6.

    Constraints:
    1 <= s.length <= 104
    s consists of only English letters and spaces ' '.
    There will be at least one word in s.

    Approach 1:
    * intuition is to iterate from the end and stop when we find the first space and return the length
    algo:
    * keep end = n-1 and dec until the last element is space
    * then have count=0, start iterating from end and inc count if not space else return count
    time & space:
    * time is o(n) and no extra space
     */
    public int lengthOfLastWord(String s) {
        return lengthOfLastWordA1(s);
    }

    public int lengthOfLastWordA1(String s) {
        int end = s.length()-1, count=0;
        while(end>=0 && s.charAt(end)==' ')end--;
        while(end>=0 && s.charAt(end)!=' '){
            count++;
            end--;
        }
        return count;
    }
}