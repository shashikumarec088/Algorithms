package com.github.shashi.leetcode;

public class Problem125 {
    /*
        Valid Palindrome

        A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and
         removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric
         characters include letters and numbers.

        Given a string s, return true if it is a palindrome, or false otherwise.

        Example 1:

        Input: s = "A man, a plan, a canal: Panama"
        Output: true
        Explanation: "amanaplanacanalpanama" is a palindrome.
        Example 2:

        Input: s = "race a car"
        Output: false
        Explanation: "raceacar" is not a palindrome.

        Constraints:

        1 <= s.length <= 2 * 105
        s consists only of printable ASCII characters.

        Approach 1:
        * intuition is to use the 2 pointer approach and compare the characters
        * have i=0, j=n-1 if char at i or j not char then inc and continue else compare if not
        same return false
        * if we reach the end then we can return true, note to check if letteror digit
        we can use Character.isLetterOrDigit method and also to convert into lower case
        we can use Character.toLowerCase


        Approach 2:
        * intuition is to replace not alpha numberic characters and reverse and use string equals
        method it is very straight forward solution.
        * we can use regex to replace "[^A-Za-z0-9]"
        * note we should not use string buffer for comparision we will get invalid results


     */
    public boolean isPalindrome(String s) {
        return isPalindromeA2(s);
    }

    public boolean isPalindromeA2(String s) {
        String s1 = s.replaceAll("[^A-Za-z0-9]","").toLowerCase();
        return s1.equals(new StringBuilder(s1).reverse().toString());
    }

    public boolean isPalindromeA1(String s) {
        int n = s.length();
        int i=0, j=n-1;
        while(i<j){
            char c1 = Character.toLowerCase(s.charAt(i));
            char c2 = Character.toLowerCase(s.charAt(j));;
            if(!Character.isLetterOrDigit(c1)){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(c2)){
                j--;
                continue;
            }
            if(c1!=c2)return false;
            i++;
            j--;
        }
        return true;
    }
}