package com.github.shashi.leetcode;

public class Problem345 {
    /*
    345. Reverse Vowels of a String
    Given a string s, reverse only all the vowels in the string and return it.

    The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.



    Example 1:

    Input: s = "IceCreAm"

    Output: "AceCreIm"

    Explanation:

    The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

    Example 2:

    Input: s = "leetcode"

    Output: "leotcede"



    Constraints:

    1 <= s.length <= 3 * 105
    s consist of printable ASCII characters.

    Approach 1: using 2 pointers
    * intuition is to use 2 pointers one from start and one from end, iterate until start < end
    * iterate start until ovel and < end
    * iterate end until ovel and > start
    * swap the characters at start and end

    algo:
    * init start=0 and end=n-1, convert string to array
    * iterate until start < end
    * iterate until start < end and char at start is not oval
    * iterate until end > start and char at end is not oval
    * swap the characters at start and end
    * increment start and decrement end
    * return the string
    * to check if char is oval or not compare against a,e,i,o,u,A,I,O,U,E

    time & space:
    * n time and n space
     */

    public String reverseVowels(String s) {
        return reverseVowelsA1(s);
    }

    private boolean isOval(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    private void swap(char[] arr, int st, int end){
        char temp = arr[st];
        arr[st] = arr[end];
        arr[end] = temp;
    }

    public String reverseVowelsA1(String s) {
        int start = 0, end = s.length()-1;
        char[] arr = s.toCharArray();
        while(start < end){
            while(start < end && !isOval(arr[start]))
                start++;
            while(end > start && !isOval(arr[end]))
                end--;
            if(start<end)
                swap(arr,start,end);
            start++;
            end--;
        }
        return new String(arr);
    }
}
