package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GatesAndWalls {
    public static void main(String[] args) {
        GatesAndWalls gatesAndWalls = new GatesAndWalls();
        int [][] rooms = new int[][]{
                {2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}
        };
        gatesAndWalls.wallsAndGates(rooms);
    }
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final int WALL = -1;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[]{1,0},new int[]{-1,0},new int[]{0,1},new int[]{0,-1}
    );

    public void wallsAndGates(int[][] rooms){
        int m = rooms.length;;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for(int row = 0; row<m;row++){
            for(int col =0; col < n; col++){
                if(rooms[row][col] == EMPTY){
                    rooms[row][col] = distanceToNearestGate(rooms,
                            row,col);
                }
            }
        }
    }

    public int distanceToNearestGate(int[][] rooms, int startRow,
                                     int startCol){
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] distance = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow,startCol});
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];
            for(int[] direction : DIRECTIONS){
                int r = row +direction[0];
                int c = col + direction[1];
                if(r<0||c<0||r>=m||c>=n||rooms[r][c] == WALL
                || distance[r][c] != 0)
                    continue;
                distance[r][c] = distance[row][col]+1;
                if(rooms[r][c] == GATE)
                    return distance[r][c];
                queue.add(new int[]{r,c});
            }
        }
        return Integer.MAX_VALUE;
    }
}
