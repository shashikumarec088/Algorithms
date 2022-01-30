package com.github.shashi.leetcode;
import java.util.*;
public class Problem12 {
    public String intToRoman(int num) {
        return intToRomanA2(num);
    }

    public String intToRomanA2(int num){
        String[] symbols = new String[]{"M","CM","D","CD",
                "C","XC","L","XL",
                "X","IX","V","IV","I"};
        int[] values = new int[]{1000,900,500,400,100,90,50,40,
                10,9,5,4,1};
        int n = num;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<symbols.length;i++){
            while(n>= values[i]){
                sb.append(symbols[i]);
                n = n - values[i];
            }
        }
        return sb.toString();
    }

    public String intToRomanSol(int n){
        Map<Integer,Character> map = new HashMap<>();
        map.put(1000,'M');
        map.put(500,'D');
        map.put(100,'C');
        map.put(50,'L');
        map.put(10,'X');
        map.put(5,'V');
        map.put(1,'I');

        List<int[]> orderList = new ArrayList<>();
        orderList.add(new int[]{1000,100});
        orderList.add(new int[]{500,100});
        orderList.add(new int[]{100,10});
        orderList.add(new int[]{50,10});
        orderList.add(new int[]{10,1});
        orderList.add(new int[]{5,1});
        orderList.add(new int[]{1,0});
        int input = n;
        StringBuilder sb = new StringBuilder();
        for(int[] pos : orderList){
            int num = pos[0];
            int pDiff = pos[1];

            if(input>0){
                int q = input/num;
                int r = input % num;
                for(int i=0; i<q; i++)
                    sb.append(map.get(num));
                if(r>0){
                    if((num-r)<=pDiff){
                        sb.append(map.get(pDiff));
                        sb.append(map.get(num));
                        r = r - (num-pDiff);
                    }
                }
                input = r;
            }else break;
        }
        return sb.toString();
    }
}
