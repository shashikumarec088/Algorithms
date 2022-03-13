package com.github.shashi.leetcode;
import java.util.*;
public class Problem422 {
    public boolean validWordSquare(List<String> words) {
        StringBuilder sb = new StringBuilder();
        return validWordSquareA1(words);
    }

    public boolean validWordSquareA1(List<String> words){
        int n = words.size();
        for(int r=0; r<n; r++){
            for(int c = 0; c<words.get(r).length(); c++){
                if(c>=n || words.get(c).length()<=r ||
                        words.get(r).charAt(c)!=words.get(c).charAt(r))
                    return false;
            }
        }
        return true;
    }
}