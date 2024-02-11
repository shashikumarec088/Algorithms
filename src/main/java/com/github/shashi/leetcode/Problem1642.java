package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problem1642 {

    public static void main(String[] args) {
        Problem1642 prom = new Problem1642();
        int[] heights = {4,12,2,7,3,18,20,3,19};
        int res = prom.furthestBuildingA5(heights,10,2);
    }

    public int furthestBuildingA5(int[] heights, int bricks, int ladders){
        int l=0, hi=heights.length -1;
        ArrayList<int[]> diffs = new ArrayList<>();
        for(int i=0; i<hi; i++){
            int diff = heights[i+1] - heights[i];
            if(diff>0)diffs.add(new int[]{diff,i+1});
        }
        Collections.sort(diffs, (a,b)->a[0]-b[0]);
        while(l<hi){
            int mid = l +(hi-l+1)/2;
            if(isReachable(heights, diffs, mid, bricks, ladders))
                l = mid;
            else hi=mid-1;
        }
        return l;
    }

    public boolean isReachable(int[] heights, ArrayList<int[]> diffs, int mid, int bricks, int ladders){
        for(int[] diff: diffs){
            if(diff[1] > mid) continue;
            if(bricks >= diff[0]) bricks -= diff[0];
            else if(ladders>0) ladders--;
            else return false;
        }
        return true;
    }

    public int furthestBuildingA4(int[] heights, int bricks, int ladders){
        int l=0, hi = heights.length-1;
        while(l < hi){
            int mid = l + (hi-l+1)/2;
            boolean reachable = isReachable(heights, mid, bricks, ladders);
            if(reachable) l=mid;
            else hi=mid-1;
        }
        return l;
    }

    public boolean isReachable(int[] heights, int mid, int bricks, int ladders){
        List<Integer> diffs = new ArrayList<>();
        for (int i=0; i< mid;i++){
            int diff = heights[i+1] - heights[i];
            if(diff<=0)continue;
            diffs.add(diff);
        }
        Collections.sort(diffs);
        for(int diff : diffs){
            if(diff <= bricks){
                bricks -= diff;
            }
            else if(ladders > 0){
                ladders--;
            }
            else return false;
        }
        return true;
    }

    public int furthestBuildingA3(int[] heights, int bricks,int ladders){
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)-> b-a);
        int n = heights.length;
        for(int i=0; i<n-1; i++){
            int diff = heights[i+1] - heights[i];
            if(diff <= 0) continue;
            bricks -= diff;
            heap.add(diff);
            if(bricks < 0 && ladders > 0){
                diff = heap.poll();
                bricks += diff;
                ladders--;
            }
            if(bricks < 0) return i;
        }
        return n-1;
    }

    public int furthestBuildingA2(int[] heights, int bricks,int ladders){
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = heights.length;
        for(int i=0; i<n-1; i++){
            int diff = heights[i+1] - heights[i];
            if(diff <= 0) continue;
            heap.add(diff);
            if(heap.size() > ladders){
                diff = heap.poll();
                bricks -= diff;
                if(bricks < 0) return i;
            }
        }
        return n-1;
    }

    public int furthestBuildingA1(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i=1; i<heights.length; i++){
            int diff = heights[i] - heights[i-1];
            if(diff >0){
                boolean usedLadders = false;
                if(ladders>0){
                    heap.add(diff);
                    ladders--;
                    usedLadders = true;
                }
                else if(heap.size()>0 && heap.peek() < diff){
                    int old = heap.poll();
                    heap.add(diff);
                    diff = old;
                }

                if(!usedLadders) bricks -= diff;
                if(bricks < 0)
                    return i-1;
            }
        }
        return heights.length-1;
    }
}
