package com.github.shashi.leetcode;

public class Problem1295 {
    public int findNumbers(int[] nums) {
        return findNumbersA2(nums);
    }

    public int findNumbersA2(int[] numsArr){
        int count = 0;
        for(int nums : numsArr)
            if((nums >=10 && nums<100) ||
                    (nums >=1000 && nums<10000) ||
                    (nums >=100000 && nums<1000000)
            )
                count++;
        return count;
    }

    public int findNumbersA1(int[] nums){
        int count = 0;
        for(int item : nums)
            if(countNums(item)%2==0)count++;
        return count;
    }

    public int countNums(int num){
        int count =0;
        while(num>9){
            count++;
            num = num/10;
        }
        count++;
        return count;
    }
}
