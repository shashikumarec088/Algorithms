package com.github.shashi.leetcode;

public class Problem14 {
    public String longestCommonPrefix(String[] strs) {
        return longestCommonPrefixA3(strs);
    }

    /*
    intuition is to do the binary search using the minlength
    string, check for prefix match, at the end we use l+h/2
    because l might be 1 length over the common prefix.
     */
    public String longestCommonPrefixA3(String[] strs){
        if(strs.length==1)return strs[0];
        int n = strs[0].length();
        for(String str : strs)
            n = Math.min(n,str.length());
        int l=1, h=n;
        while(l<=h){
            int mid = (l+h)/2;
            if(isPrefix(strs,mid))
                l=mid+1;
            else h = mid-1;
        }
        return strs[0].substring(0,(l+h)/2);
    }

    public boolean isPrefix(String[] strs, int mid){
        String prefix = strs[0].substring(0,mid);
        for(int i=1; i<strs.length; i++)
            if(!strs[i].startsWith(prefix)) return false;
        return true;
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