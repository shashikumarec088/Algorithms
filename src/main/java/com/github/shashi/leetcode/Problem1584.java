package com.github.shashi.leetcode;

import java.util.PriorityQueue;

public class Problem1584 {
    public int minCostConnectPointsPrism(int[][] points){
        if(points == null || points.length == 0) return 0;
        int n=points.length, ans =0, count =0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->a[0]-b[0]);
        int[] pt0 = points[0];
        for(int i=1; i<points.length; i++){
            int[] pt1 = points[i];
            int cost = Math.abs(pt0[0] - pt1[0])+ Math.abs(pt0[1] - pt1[1]);
            pq.offer(new int[]{cost,i});
        }
        boolean[] visited = new boolean[n];
        visited[0]=true;
        while(pq.size() > 0 && count< n-1){
            int[] pt = pq.poll();
            if(!visited[pt[1]]){
                visited[pt[1]] = true;
                int[] pt2 = points[pt[1]];
                ans += pt[0];
                for(int i = 0; i < n; i++){
                    if(!visited[i]){
                        int[] pt3 = points[i];
                        int cost = Math.abs(pt2[0] - pt3[0])+ Math.abs(pt2[1] - pt3[1]);
                        pq.offer(new int[]{cost,i});
                    }
                }
                count++;
            }
        }
        return ans;
    }
    public int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        PriorityQueue<Edge> pq = new PriorityQueue<>((x, y) -> x.cost - y.cost);
        boolean[] visited = new boolean[size];
        int result = 0;
        int count = size - 1;
        // Add all edges from points[0] vertexs
        int[] coordinate1 = points[0];
        for (int j = 1; j < size; j++) {
            // Calculate the distance between two coordinates.
            int[] coordinate2 = points[j];
            int cost = Math.abs(coordinate1[0] - coordinate2[0]) +
                    Math.abs(coordinate1[1] - coordinate2[1]);
            Edge edge = new Edge(0, j, cost);
            pq.add(edge);
        }
        visited[0] = true;

        while (!pq.isEmpty() && count > 0) {
            Edge edge = pq.poll();
            int point1 = edge.point1;
            int point2 = edge.point2;
            int cost = edge.cost;
            if (!visited[point2]) {
                result += cost;
                visited[point2] = true;
                for (int j = 0; j < size; j++) {
                    if (!visited[j]) {
                        int distance = Math.abs(points[point2][0] - points[j][0]) +
                                Math.abs(points[point2][1] - points[j][1]);
                        pq.add(new Edge(point2, j, distance));
                    }
                }
                count--;
            }
        }
        return result;
    }
    class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }


    }
}
