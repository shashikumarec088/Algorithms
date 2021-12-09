package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem119 {
    public static void main(String[] args) {
        Problem119 problem119 = new Problem119();
        System.out.println(problem119.getPascal(4));
    }

    public List<Integer> getPascal(int n){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        for(int i=0; i<n;i++){
            for(int j=i; j>0; j--)
                list.set(j,list.get(j)+list.get(j-1));
            list.add(1);
        }
        return list;
    }
}
