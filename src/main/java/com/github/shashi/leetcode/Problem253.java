package com.github.shashi.leetcode;
import java.util.*;
public class Problem253 {
    public int minMeetingRooms(int[][] intervals) {
        return minMeetingRoomsA3(intervals);
    }

    public int minMeetingRoomsA3(int[][] intervals){
        int n = intervals.length;
        List<Integer> l = new ArrayList<>();
        int[] sp = new int[n];
        int[] ep = new int[n];
        int i=0;
        for(int[] interval : intervals){
            sp[i] = interval[0];
            ep[i++] = interval[1];
        }
        Arrays.sort(sp);
        Arrays.sort(ep);
        int count=0;
        int s=0, e=0;
        while(s<n){
            if(sp[s]<ep[e])
                count++;
            else e++;
            s++;
        }
        return count;
    }

    public int minMeetingRoomsA2(int[][] intervals){
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,b)->a-b);
        int i = 0;
        while(i<intervals.length){
            int[] interval = intervals[i];
            if(!pq2.isEmpty() && pq2.peek()<=interval[0]){
                pq2.poll();
                pq2.add(interval[1]);
            }
            else pq2.add(interval[1]);
            i++;
        }
        return pq2.size();
    }

    public int minMeetingRoomsA1(int[][] intervals){
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((a,b)->a[0]-b[0]);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((a,b)->a-b);
        for(int[] interval: intervals)
            pq1.add(interval);
        while(!pq1.isEmpty()){
            int[] interval = pq1.poll();
            if(!pq2.isEmpty() && pq2.peek()<=interval[0]){
                pq2.poll();
                pq2.add(interval[1]);
            }
            else pq2.add(interval[1]);
        }
        return pq2.size();
    }
}