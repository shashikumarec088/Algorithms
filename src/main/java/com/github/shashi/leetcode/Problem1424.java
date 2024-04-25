package com.github.shashi.leetcode;

import java.util.*;

public class Problem1424 {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        return findDiagonalOrderA2(nums);
    }


    /*
        intuition is to use the bfs approach moving left and right
        we add the next level neighbors for each position,
        when we are at 0th column we add the down element and right
        element if they exist, else we add only right element if they
        exist
    */
    public int[] findDiagonalOrderA2(List<List<Integer>> nums) {
        List<Integer> res = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            res.add(nums.get(pos[0]).get(pos[1]));
            if(pos[1]==0 && pos[0]+1 < nums.size())
                queue.offer(new int[]{pos[0]+1,pos[1]});
            if(pos[1]+1 < nums.get(pos[0]).size())
                queue.offer(new int[]{pos[0],pos[1]+1});
        }

        int[] ans = new int[res.size()];
        for(int i=0; i< res.size(); i++){
            ans[i] = res.get(i);
        }

        return ans;
    }

    /*
        inuition is to use the sum of i and j always same
        for all the elements in same diagonal, here
        we are traversing from 1st row to last row, elements
        in array will be in reverse order, so while adding to
        final ans we take elements in reverse order, we
        can also traverse from last row and move upwards
        in that case we will have elements in right order
        we no need to add elements from end in that case
    */
    public int[] findDiagonalOrderA1(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int count = 0, k=0;
        for(int i=0; i<nums.size(); i++){
            for(int j=0; j< nums.get(i).size(); j++){
                if(!map.containsKey(i+j))
                    map.put(i+j,new ArrayList<>());
                map.get(i+j).add(nums.get(i).get(j));
                count++;
            }
        }
        int[] res = new int[count];
        for(int i=0; i< map.size(); i++){
            for(int j = map.get(i).size()-1; j>=0; j--)
                res[k++] = map.get(i).get(j);
        }

        return res;
    }
}