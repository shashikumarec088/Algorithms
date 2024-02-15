package com.github.shashi.leetcode;

public class Problem67 {
    public static void main(String[] args) {
        Problem67 problem67 = new Problem67();
        problem67.addBinary("11","1");
    }
    public String addBinary(String a, String b) {
        return addBinaryA1(a,b);
    }

    public String addBinaryA1(String a, String b) {
        Integer.parseInt(a,2);
        int l1=a.length()-1, l2 = b.length()-1;
        boolean rem=false, sum=false;
        StringBuilder sb = new StringBuilder();
        while(l1>=0 && l2>=0){
            boolean a1 = a.charAt(l1)=='1'?true:false;
            boolean a2 = b.charAt(l2)=='1'?true:false;
            sum = rem ^ (a1 ^ a2);
            rem = (a1 && a2) || (rem && (a1 ^ a2));
            sb.append(sum?"1":"0");
            l1--;
            l2--;
        }
        String maxStr = a.length() > b.length() ? a:b;
        int i = l1>=0 ?l1:l2;
        while(i>=0){
            boolean a1 = maxStr.charAt(i)=='1'?true:false;
            sum = rem ^ a1;
            sb.append(sum?"1":"0");
            rem = rem && a1;
            i--;
        }
        if(rem) sb.append('1');
        return sb.reverse().toString();
    }
}