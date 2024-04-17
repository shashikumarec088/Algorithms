package com.github.shashi.leetcode;
import java.util.*;
public class Problem12 {

    public static void main(String[] args) {
       String s = "  hello world  ";
       String[] s1 = s.split("\\s+");
       StringBuilder sb = new StringBuilder();

    }
    public String intToRoman(int num) {
        return intToRomanA3(num);
    }

    /*

    intutition is to have all the 13 roman numbers and keep
    building from top to bottom by subtracting from number until
    number becomes smaller than the current symbol value
     */
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

    /*
    intuition is to find the quetient and rem and check if
    the q > 0 and keep appending the symbol and if rem is within
    the limit then append the allowed difference
     */
    public String intToRomanA3(int num) {
        Map<Integer,Character> map = new HashMap<>();
        map.put(1000,'M');
        map.put(500,'D');
        map.put(100,'C');
        map.put(50,'L');
        map.put(10,'X');
        map.put(5,'V');
        map.put(1,'I');
        String[] symbols = {"M", "D", "C", "L",
                "X", "V","I"};
        int[] counts = {1000, 500, 100, 50,
                10, 5, 1};

        int[] diffs = {100, 100, 10, 10, 1,1, 0};
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<symbols.length; i++){
            int q = num/ counts[i];
            int r = num % counts[i];
            if(num>0){
                while(q>0){
                    sb.append(symbols[i]);
                    num -= counts[i];
                    q--;
                }

                if(counts[i] - r <= diffs[i]){
                    sb.append(map.get(diffs[i]));
                    sb.append(symbols[i]);
                    num = num - (counts[i] - diffs[i]);
                }
            } else break;
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
