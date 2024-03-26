package com.github.shashi.leetcode;

public class Problem367 {
    public boolean isPerfectSquare(int num) {
        return isPerfectSquareA3(num);
    }

    // for any perfect squere the sum of first add numbers
    // will be equal to n
    public boolean isPerfectSquareA3(int n) {
        int i=1;
        while(n>0){
            n -=i;
            i+=2;
        }
        return n==0;
    }

    public boolean isPerfectSquareA2(int num) {
        if(num==1)return true;
        if(num<=3)return false;
        long l=2, r=num/2;
        while(l<=r){
            long mid = l + (r-l)/2;
            long ans = mid*mid;
            if(ans== num)return true;
            else if(ans < num)l=mid+1;
            else r=mid-1;
        }
        return false;
    }

    public boolean isPerfectSquareA1(int num) {

        int cur= 2, prev=2;
        while(cur < num){
            prev = cur;
            cur = cur*cur;
        }
        if(cur == num)return true;

        for(int i=prev; i<=num/2; i++){
            int x=0;
            while(num%i==0){
                num = num/i;
                x++;
            }
            if(x%2 != 0)return false;
        }
        return num==1;
    }
}