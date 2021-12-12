package com.github.shashi.leetcode;

import java.util.HashSet;
import java.util.Set;


public class Problem489 {
    public static void main(String[] args) {
        Problem489 problem119 = new Problem489();
    }

    /*public void cleanRoom(Robot robot) {
        cleanRm(robot,0,0,0,new HashSet<String>());
    }

    public void cleanRm(Robot robot, int r, int c,
                        int or, Set<String> seen){
        seen.add(r+" "+c);
        robot.clean();
        int[][] dirs = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
        for(int i=0; i<4;i++){
            int nOr = (or+i)%4;
            int nr = r+dirs[nOr][0];
            int nc = c+dirs[nOr][1];
            if(!seen.contains(nr+" "+nc) && robot.move()){
                cleanRm(robot,nr,nc,nOr,seen);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
        }
    }*/
}
