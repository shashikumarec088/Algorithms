package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Problem286 {
    public static void main(String[] args) {
        String s = "1234";
        System.out.println(s.substring(0,1));
        Set<String> str = new HashSet<>();
        System.out.println(Integer.valueOf(String.valueOf(s.charAt(0))));

    }
    public void wallsAndGates(int[][] rooms) {
        wallsAndGatesA1(rooms);
    }

    public void wallsAndGatesA1(int[][] rooms) {
        int r = rooms.length, c = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                if(rooms[i][j]==0)
                    queue.offer(new int[]{i,j});
            }
        }
        int dist=1;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0; i<size;i++){
                int[] pos = queue.poll();
                for(int[] dir : dirs){
                    int r1 = pos[0]+dir[0], c1 = pos[1]+dir[1];
                    if(r1>=0 && r1 <r && c1>=0 && c1<c &&
                            rooms[r1][c1] == 2147483647){
                        rooms[r1][c1] = dist;
                        queue.offer(new int[]{r1,c1});
                    }
                }
            }
            dist++;
        }
    }
}