package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        return spiralOrderA2(matrix);
    }

    public List<Integer> spiralOrderA2(int[][] matrix){
        int VISIT = 101;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int r=0, c=0, dir=0, changeCount=0, rows=matrix.length, cols=matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(matrix[0][0]);
        matrix[0][0] = VISIT;
        while(changeCount<2){
            while(r+dirs[dir][0]>=0 &&
                    r+dirs[dir][0] < rows &&
                    c+dirs[dir][1] >= 0 &&
                    c+dirs[dir][1] < cols &&
                    matrix[r+dirs[dir][0]][c+dirs[dir][1]] != VISIT
            ){
                r = r+dirs[dir][0];
                c = c+dirs[dir][1];
                list.add(matrix[r][c]);
                changeCount = 0;
                matrix[r][c] = VISIT;
            }
            changeCount++;
            dir = (dir+1)%4;
        }
        return list;
    }

    public List<Integer> spiralOrderA1(int[][] matrix) {
        int rt = 0, rb = matrix.length-1, cl=0, cr=matrix[0].length-1;
        ArrayList<Integer> res = new ArrayList<>();
        boolean flag = false;
        while(rt <= rb && cl <= cr){
            int r = rt, c = cl;
            while(c <= cr && cl <= cr){
                res.add(matrix[r][c]);
                c++;
            }
            if(rt == rb)break;
            rt++;
            r = rt;
            c=cr;
            while(r<=rb && rt <= rb){
                res.add(matrix[r][c]);
                r++;
            }
            if(cl == cr)break;
            cr--;
            r=rb;
            c=cr;
            while(c>=cl && cr >= cl){
                res.add(matrix[r][c]);
                c--;
            }
            rb--;
            r =rb;
            c=cl;
            while(r>=rt && rb >=rt){
                res.add(matrix[r][c]);
                r--;
            }
            cl++;
        }
        return res;
    }
}
