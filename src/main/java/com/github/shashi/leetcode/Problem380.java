package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Problem380 {
    Map<Integer, Integer> map = new HashMap<>();
    ArrayList<Integer> list = new ArrayList<>();
    Random rand = new Random();

    public Problem380() {

    }

    public boolean insert(int val) {
        if(map.containsKey(val))return false;
        map.put(val,list.size());
        list.add(list.size(),val);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val))return false;
        int index = map.get(val);
        map.put(list.get(list.size()-1),index);
        list.set(index,list.get(list.size()-1));
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}