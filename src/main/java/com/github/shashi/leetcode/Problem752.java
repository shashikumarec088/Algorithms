package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem752 {
    public int openLock(String[] deadends, String target) {
        return openLockA1(deadends,target);
    }

    public int openLockA1(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> deads = new HashSet<>();
        for(String s : deadends)deads.add(s);
        int count=0;
        if(deads.contains("0000"))return -1;
        queue.add("0000");
        visited.add("0000");
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size;i++){
                String s = queue.poll();
                if(s.equals(target))return count;
                for(int j=0; j<4;j++){
                    Integer val = Integer.valueOf(String.valueOf(s.charAt(j)));
                    int n1 = (val+1)%10;
                    int n2 = val-1;
                    n2 = n2<0?n2+10:n2;
                    String k1 = s.substring(0,j)+String.valueOf(n1)+s.substring(j+1);
                    String k2 = s.substring(0,j)+String.valueOf(n2)+s.substring(j+1);
                    if(!visited.contains(k1) &&
                            !deads.contains(k1)){queue.offer(k1);
                        visited.add(k1);
                    }
                    if(!visited.contains(k2) &&
                            !deads.contains(k2)){queue.offer(k2);
                        visited.add(k2);
                    }

                }
            }
            count++;
        }
        return -1;
    }
}