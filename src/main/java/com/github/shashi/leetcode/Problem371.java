package com.github.shashi.leetcode;

public class Problem371 {
    public int getSum(int a, int b) {
        StringBuilder sb = new StringBuilder();
        return getSumA3(a,b);
    }

    public int getSumA2(int a, int b){
        int x = Math.abs(a), y = Math.abs(b);
        if(x<y)return getSumA2(b,a);
        int sign = a>0?1:-1;
        if(a*b>=0){
            while(y!=0){
                int ans = x^y;
                int carry = (x&y) << 1;
                x =ans;
                y=carry;
            }
        }else{
            while(y!=0){
                int ans=x^y;
                int barrow = ((~x)&y) <<1;
                x =ans;
                y=barrow;
            }
        }
        return sign*x;
    }

    public int getSumA3(int a, int b){
        while(b!=0){
            int temp = (a&b)<<1;
            a = a^b;
            b = temp;
        }
        return a;
    }

    public int getSumA1(int a, int b){
        int neg=1;
        if(a<0 && b<0){
            a= a*-1;
            b = b*-1;
            neg=-1;
        }
        int ans = a^b;
        int and = a&b;
        while(and>0){
            and = and<<1;
            int temp = ans ^ and;
            and = ans & and;
            ans = temp;
        }
        return ans*neg;
    }
}