package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem118 {
    public List<List<Integer>> generate(int numRows) {
        return generateA1(numRows);
    }

    public List<List<Integer>> generateA1(int numRows) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<numRows; i++){
            list.add(new ArrayList<>());
            for(int j=0; j<i+1; j++){
                if(j==0 || j==i)
                    list.get(i).add(1);
                else
                    list.get(i).add(list.get(i-1).get(j-1)+list.get(i-1).get(j));
            }
        }
        return list;
    }
}