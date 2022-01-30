package com.github.shashi.leetcode;
import java.util.*;
public class Problem13 {
    public int romanToInt(String s) {
        return romanToIntA2(s);
    }

    public int romanToIntA2(String s){
        Map<String,Integer> map = new HashMap<>();
        map.put("M",1000);
        map.put("D",500);
        map.put("C",100);
        map.put("L",50);
        map.put("X",10);
        map.put("V",5);
        map.put("I",1);
        int i=0, sum=0,n=s.length();
        while(i<n){
            int curVal = map.get(s.substring(i,i+1));
            int nextVal=0;
            if(i+1<n)
                nextVal = map.get(s.substring(i+1,i+2));
            if(nextVal>curVal){
                sum+= nextVal-curVal;
                i+=2;
            }else{
                sum+= curVal;
                i+=1;
            }
        }
        return sum;
    }

    public int romanToIntA1(String s){
        String[] symbols = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX",
                "V","IV","I"};
        int[] values = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int index=0,result=0;
        for(int i=0; i<symbols.length;i++){
            while(index < s.length() && s.startsWith(symbols[i],index)){
                result += values[i];
                index += symbols[i].length();
            }
        }
        return result;
    }
}
