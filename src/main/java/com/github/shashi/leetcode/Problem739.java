package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem739  {
    /*
    739. Daily Temperatures

    Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.

    Example 1:

    Input: temperatures = [73,74,75,71,69,72,76,73]
    Output: [1,1,4,2,1,1,0,0]
    Example 2:

    Input: temperatures = [30,40,50,60]
    Output: [1,1,1,0]
    Example 3:

    Input: temperatures = [30,60,90]
    Output: [1,1,0]


    Constraints:

    1 <= temperatures.length <= 105
    30 <= temperatures[i] <= 100

    Approach 1: brute force
    * intuition is to iterate over the array and for each element iterate over the rest of the array to find the next
    warmer day
    algo:
    * iterate over the array from 0 to n-1
    * for each element iterate from i+1 to n-1 and check if element at j is > element at i if so then store j-i in
    result array and break
    * return result array
    time & space:
    * n^2 time and n space

    Approach 2: using stack
    * intuition is to use stack to store the index of the elements and iterate over the array and for each element
    check if it is greater than the element at top of the stack if so then pop the element and store the difference
    between the index and the popped element in the result array
    algo:
    * create stack of type Integer
    * iterate over the array from 0 to n-1
    * for each element check if stack is not empty and element at top of stack is < element at i if so then pop the
    element and store i-top in result array
    * push the index to stack
    * return result array
    time & space:
    * n time and n space, initially it looks like there are 2 loops then it should be n2 but in reality it is n+n=2n
    because each element is pushed and popped only once

    Approach 3: using single pass
    * intuition is to iterate over the array from end and keep track of the hottest day and for each element check if
    it is greater than the hottest day if so then store the difference between the index and the hottest day in the
    result array
    algo:
    * iterate over the array from n-1 to 0
    * for each element check if it is >= hottest day if so then store 0 in result array and update the hottest day
    * else store the difference between the index and the hottest day in the result array
    * return result array
    time & space:
    * n time and n space it looks like there are 2 loops then it should be n2 but in reality it is n+n=2n
    because array positions are visited only once

     */
    public int[] dailyTemperatures(int[] temperatures) {
        return dailyTemperaturesA3(temperatures);
    }

    public int[] dailyTemperaturesA3(int[] temp) {
        int n = temp.length;
        int[] result = new int[n];
        int hot = 0;
        for(int i=n-1; i>-1; i--){
            if(temp[i] >= hot ){
                hot = temp[i];
                continue;
            }
            int day = 1;
            while(temp[i+day]<= temp[i]){
                day+= result[i+day];
            }
            result[i]=day;
        }
        return result;
    }

    public int[] dailyTemperaturesA2(int[] temp) {
        Stack<Integer> stack = new Stack<>();
        int n = temp.length;
        int[] result = new int[n];
        for(int cur =0; cur<n; cur++){
            while(!stack.isEmpty() && temp[stack.peek()]<temp[cur]){
                int t = stack.pop();
                result[t] = cur-t;
            }
            stack.push(cur);
        }
        return result;
    }

    public int[] dailyTemperaturesA1(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(temperatures[j]>temperatures[i]){
                    result[i] = j-i;
                    break;
                }

            }
        }
        return result;
    }

}