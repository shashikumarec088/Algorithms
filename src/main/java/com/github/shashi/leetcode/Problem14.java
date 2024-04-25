package com.github.shashi.leetcode;

public class Problem14 {
    /*
    Longest Common Prefix
    Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".
    Example 1:
    Input: strs = ["flower","flow","flight"]
    Output: "fl"
    Example 2:
    Input: strs = ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    Constraints:
    1 <= strs.length <= 200
    0 <= strs[i].length <= 200
    strs[i] consists of only lowercase English letters.

    Approach 1:
    * intuition is to find the find the minlength among all strings and iterate over the length
    and check if current char is same as small string char if same continue else return string until
    current position
    algo:
    * have minLength as first string length and update it by iterating through all strings, also capture
    the small string, have empty string builder defined
    * iterate from i=0 till minLength and for each string check if char at i and small string char are
    same if not return string formed till this point else append current char to string
    * at the end return the formed string
    time & space:
    * time complexity is mn and space is o(1)

    Approach 2: (recursion)
    * intuition is to keep spitting the array into 2 parts until we have 1 element
    when we have 1 element each then we find the common prefix and we do this upwords untill
    all elements is covered;
    algo:
    * have the recursive function which takes strs, 0, n-1.
    * base case is when l=r then return left stirng, else find mid and call rec on left
    and call rec on right
    * return the common prefix from left and right
    * we use function to return the common prefix.
    time & space:
    * time is o(s) all chars in all strs

    Approach 3:(binary search)
    * intuition is to find the minLength among the words and use the binary search to find the prefixes
    algo:
    * find the minLength string and its length, start binary search with l=0, h=n-1
    * in each iteration check if all the words till mid are same if so keep l=mid+1 else h=mid-1
    and repeat the process
    * in the end return substring of length l;
    time & space:
    * time is mn where m is each string length and n is number of strings and we do not use any space
     */
    public String longestCommonPrefix(String[] strs) {
        return longestCommonPrefixA3(strs);
    }

    public static void main(String[] args) {

    }

    public String longestCommonPrefixA3(String[] strs) {
        int minLen = strs[0].length();
        String minStr = strs[0];
        for(String s: strs){
            if(s.length() < minLen){
                minLen = s.length();
                minStr = s;
            }
        }
        int l=0, h=minLen-1;
        while(l<=h){
            int mid = l+(h-l)/2;
            String midStr = minStr.substring(l,mid+1);
            boolean found = true;
            for(String str : strs){
                if(!str.startsWith(midStr,l)){
                    found = false;
                    break;
                }
            }
            if(found)l=mid+1;
            else h=mid-1;
        }
        return minStr.substring(0,l);
    }

    public String longestCommonPrefixA2(String[] strs) {
        return longestCommonPrefixRec(strs,0,strs.length-1);
    }

    public String longestCommonPrefixRec(String[] strs, int l, int h){
        if(l==h) return strs[l];
        else{
            int mid = (l+h)/2;
            String sLeft = longestCommonPrefixRec(strs,l,mid);
            String sRight = longestCommonPrefixRec(strs,mid+1,h);
            return commonPrefix(sLeft,sRight);
        }
    }

    public String commonPrefix(String s1, String s2){
        int n = Math.min(s1.length(),s2.length());
        for(int i=0; i<n; i++){
            if(s1.charAt(i) != s2.charAt(i))
                return s1.substring(0,i);
        }
        return s1.substring(0,n);
    }
    public String longestCommonPrefixA1(String[] strs) {
        if(strs.length==1)return strs[0];
        StringBuilder sb = new StringBuilder();
        int smallStrLength = strs[0].length(), i=0;
        String smallStr = strs[0];
        for(String s : strs){
            if(s.length()<smallStrLength){
                smallStrLength = s.length();
                smallStr = s;
            }
        }

        while(i<smallStrLength){
            for(String s : strs){
                if(s.charAt(i)!=smallStr.charAt(i))
                    return sb.toString();
            }
            sb.append(smallStr.charAt(i++));
        }
        return sb.toString();
    }
}