package com.github.shashi.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Problem149 {
    /*
    Max Points on a Line
    Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number
    of points that lie on the same straight line.

    Example 1:
    Input: points = [[1,1],[2,2],[3,3]]
    Output: 3
    Example 2:

    Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
    Constraints:
    1 <= points.length <= 300
    points[i].length == 2
    -104 <= xi, yi <= 104
    All the points are unique.

    Approach 1: iterative approach with slopes
    * intuition is to find the slope between specific point to all the points and group the points with same slope
    and get the max slope count among these and update the max, this we need to do for all the points
    * we no need to consider the previous points since those were covered when previous points were considered as
    starting points.
    * to get the max count for each slope instead of computing the slope in floating point which will lead to
    precision issues we need to compute the gcd of difference in x and y points and get the gcd of dx and dy
    * then we can divide the dx and dy by gcd to get the same slope in form of dy/dx and use this as key for
    count the points with same slope
    * once we compute the slope and update the count then we can update the max count with starting point i
    * when 2 points are same we need to count those separately as overlaps and also when x axis is same then
    we need to count those as vertical points
    * the max will be among the sum of max slope count plus overlaps plus 1 or overlaps plus vertical plus 1
    * we update the max with these values if the latest value is greate than existing value of max.
    algo:
    * init n as number of points, maxPoint =1
    * iterate from i=0 to n, init map of type string, int, cur=0, vertical=0,overalp=0
    * compute the slopes from i to all other points from j=i+1 to n
    * for each value of j consider p1 = points[i] and p2 = points[j]
    * find dx = p1[0]-p2[0] and dy=p1[1]-p2[1]
    * check if dx and dy are 0 if so then inc overlap, else if dx==0 then inc vertical
    * else get the gcd of dx, dy and devide dx and dy by gcd
    * create slope key with dy +"/"+dx and update slope count in map by inc by 1
    * update cur as max of cur, map.get(slope)
    * once we are done with computing slopes for all other points update maxPoint
    * with max(maxPoint, cur+overlap+1) and then max(maxPoint,overlap+vertical+1)
    * return maxPoint at the end
    * to compute gcd we use euclidian algorithm
    * iterate until b is not 0, store b in temp make b = a%b and a = temp
    * return a at the end.
    time & space:
    * it takes n2 time and n space
     */

    public int maxPoints(int[][] points) {
        return maxPointsA1(points);
    }

    public int maxPointsA1(int[][] points) {
        int n= points.length;
        if(n<=2)return n;
        int maxPoints=1;
        for(int i=0; i<n; i++){
            Map<String,Integer> map = new HashMap<>();
            int cur=0, vertical=0,overlap=0;
            for(int j=i+1;j<n;j++){
                int[] p1 = points[i],p2 = points[j];
                int dx = p1[0]-p2[0] , dy = p1[1]-p2[1];
                if(dx==0 && dy==0)overlap++;
                else if(dx==0)vertical++;
                else{
                    int gcd = getGcd(dx,dy);
                    dx/=gcd;
                    dy/=gcd;
                    String slope = dy+"/"+dx;
                    map.put(slope,map.getOrDefault(slope,0)+1);
                    cur = Math.max(cur,map.get(slope));
                }
            }
            maxPoints = Math.max(maxPoints,cur+overlap+1);
            maxPoints = Math.max(maxPoints,vertical+overlap+1);
        }
        return maxPoints;
    }

    public int getGcd(int a, int b){
        while(b!=0){
            int temp=b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
