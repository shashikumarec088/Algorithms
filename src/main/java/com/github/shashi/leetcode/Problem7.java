package com.github.shashi.leetcode;

public class Problem7 {
    public static void main(String[] args) {
        byte b = (byte)0xff;
        System.out.println(b>>>8);
    }
    public int reverse(int x) {
        return reverseA2(x);
    }

    public int reverseA2(int x){
        int y=0;
        while(x!=0){
            int rem=x%10;
            int newAns = y*10+rem;
            if((newAns-rem)/10 != y)return 0;
            y = newAns;
            x /=10;
        }
        return y;
    }

    public int reverseA1(int x){
        int y=0, flg = x<0?-1:1;
        while(x!=0){
            int rem = x%10;
            if(y> Integer.MAX_VALUE/10 || (y==Integer.MAX_VALUE/10 && rem>Integer.MAX_VALUE/10))return 0;
            if(y< Integer.MIN_VALUE/10 || (y==Integer.MIN_VALUE/10 && rem<Integer.MIN_VALUE/10))return 0;
            y=y*10;
            y+= x%10;
            x /=10;
        }
        return y;
    }
}