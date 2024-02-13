package com.github.shashi.leetcode;

public class Problem66 {
    public static void main(String[] args) {
        int[] digits = {9};
        Problem66 problem66 = new Problem66();
        int[] ans = problem66.plusOne(digits);
    }
    public int[] plusOne(int[] digits) {
        boolean flag = false;
        int n = digits.length;
        for(int i=n-1; i>=0; i--){
            if(digits[i] +1 < 10){
                digits[i]+=1;
                return digits;
            }else{
                digits[i]=0;
            }
            if(i==0) flag=true;
        }
        if(flag){
            int[] nDigits = new int[digits.length+1];
            nDigits[0]=1;
            for(int i=0; i<digits.length;i++)
                nDigits[i+1] = digits[i];
            return nDigits;
        }
        return digits;
    }
}
