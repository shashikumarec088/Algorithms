package com.github.shashi.leetcode;
import java.util.*;
public class Problem273 {
    Map<Integer,String> unitMap = new HashMap<>();
    Map<Integer,String> tensMap = new HashMap<>();
    public String numberToWords(int num) {
        return getNum(num);
    }

    public String getNum(int num){
        unitMap.put(0,"Zero");
        unitMap.put(1,"One");
        unitMap.put(2,"Two");
        unitMap.put(3,"Three");
        unitMap.put(4,"Four");
        unitMap.put(5,"Five");
        unitMap.put(6,"Six");
        unitMap.put(7,"Seven");
        unitMap.put(8,"Eight");
        unitMap.put(9,"Nine");
        unitMap.put(10,"Ten");
        unitMap.put(11,"Eleven");
        unitMap.put(12,"Twelve");
        unitMap.put(13,"Thirteen");
        unitMap.put(14,"Fourteen");
        unitMap.put(15,"Fifteen");
        unitMap.put(16,"Sixteen");
        unitMap.put(17,"Seventeen");
        unitMap.put(18,"Eighteen");
        unitMap.put(19,"Nineteen");
        tensMap.put(2,"Twenty");
        tensMap.put(3,"Thirty");
        tensMap.put(4,"Forty");
        tensMap.put(5,"Fifty");
        tensMap.put(6,"Sixty");
        tensMap.put(7,"Seventy");
        tensMap.put(8,"Eighty");
        tensMap.put(9,"Ninety");
        return getMil(num);
    }

    public String getMil(int num){
        if(num==0) return unitMap.get(num);
        Map<Integer,String> map = new HashMap<>();
        map.put(0,"");
        map.put(1,"Thousand");
        map.put(2,"Million");
        map.put(3,"Billion");
        String s = "";
        int n = num, i =0;
        while(n>0){
            int huns = n%1000;
            if(huns>0){
                String s1 = getHun(huns);
                if(i>0){
                    s1 = s1 +" "+map.get(i);
                }
                if(s.length()>0)
                    s = s1 +" "+s;
                else s = s1;

            }
            n = n/1000;
            i++;
        }
        return s;
    }

    public StringBuilder appendSpace(StringBuilder sb){
        return sb.length()>0?sb.append(" "):sb;
    }

    public void appendNumber(StringBuilder sb, int num, Map<Integer,String> map){
        if(num>0){
            appendSpace(sb);
            sb.append(map.get(num));
        }
    }

    public String getHun(int number){
        StringBuilder sb = new StringBuilder();
        if(number>0){
            int hundreds = number/100;
            int rem = number%100;
            if(hundreds>0){
                appendNumber(sb,hundreds,unitMap);
                appendSpace(sb);
                sb.append("Hundred");
            }
            if(rem>0){
                if(rem<20){
                    appendNumber(sb,rem,unitMap);
                }else{
                    int tens = rem/10;
                    int units = rem%10;
                    if(tens>0){
                        appendNumber(sb,tens,tensMap);
                    }
                    appendNumber(sb,units,unitMap);
                }
            }
        }
        return sb.toString();
    }
}
