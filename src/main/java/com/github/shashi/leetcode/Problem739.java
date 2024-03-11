package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem739  {
    public int[] dailyTemperatures(int[] temperatures) {
        return dailyTemperaturesA4(temperatures);
    }

    public int[] dailyTemperaturesA4(int[] temp) {
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

    public int[] dailyTemperaturesA3(int[] temp) {
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

    public int[] dailyTemperaturesA2(int[] temperatures) {
        int n = temperatures.length;
        int[] cache = new int[101];
        int[] result = new int[n];
        for(int i=n-1; i>-1; i--){
            for(int j=temperatures[i]+1; j<101; j++){
                if(cache[j]!=0 && cache[j] > i){
                    if(result[i] == 0 || result[i] > (cache[j]-i))result[i] = cache[j]-i;
                }

            }
            cache[temperatures[i]]=i;
        }
        return result;
    }
}