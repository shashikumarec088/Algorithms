package com.github.shashi.leetcode;
import java.util.*;
public class Problem40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        return combinationSum2A2(candidates,target);
    }

    public List<List<Integer>> combinationSum2A2(int[] cand, int tar){
        Map<Integer,Integer> map = new HashMap<>();
        for(int item: cand)
            map.put(item,map.getOrDefault(item,0)+1);
        List<List<Integer>> result = new ArrayList<>();
        List<int[]> list = new ArrayList<>();
        for(int key : map.keySet())
            list.add(new int[]{key,map.get(key)});
        rec2(list, tar, result, new LinkedList<>(),0);
        return result;
    }

    public void rec2(List<int[]> list, int tar, List<List<Integer>> result, LinkedList<Integer>
            cur, int start){
        if(tar<0)return;
        if(tar==0){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i=start; i<list.size();i++){
            int[] item = list.get(i);
            if(item[1]<=0)continue;
            item[1]--;
            cur.add(item[0]);
            rec2(list,tar-item[0],result,cur,i);
            cur.removeLast();
            item[1]++;
        }
    }

    public List<List<Integer>> combinationSum2A1(int[] cand, int tar){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(cand);
        rec(cand,tar,result,new LinkedList<>(),0);
        return result;
    }

    public void rec(int[] cand, int tar, List<List<Integer>> result,
                    LinkedList<Integer> cur, int start){
        if(tar<0)return;
        if(tar==0){
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i=start; i<cand.length;i++){
            if(i>start && cand[i]==cand[i-1])continue;
            cur.add(cand[i]);
            rec(cand,tar-cand[i],result,cur,i+1);
            cur.removeLast();
        }
    }
}