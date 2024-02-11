package com.github.shashi.leetcode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Problem973 {
    public static void main(String[] args) {
        Problem973 problem973 = new Problem973();
        int[][] points = {{1,3},{-2,2}};
        problem973.kClosestA21(points,1);
    }
    private int[][] points;
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
         return kClosestA3(points,k);
    }

    public int[][] kClosestA21(int[][] points, int k){
        int n = points.length;
        int l = 0, r = n-1;
        quickSelect(points, l, r, k);
        return Arrays.copyOfRange(points,0,k);
    }

    public void quickSelect(int[][] points, int l, int r, int k){
        if(l>=r) return;
        int pi = new Random().nextInt(r-l);
        pi = partition(points,l,r,pi);
        int ll = pi-l+1;
        if(k < ll){
            quickSelect(points, l, pi-1,k);
        }
        else if(k > ll){
            quickSelect(points,pi+1,r, k - ll);
        }
    }

    public int partition(int[][] points, int l, int r, int pi){
        int pivot = dist(points,pi);
        int si = l;
        swap(points,pi,r);
        for(int i=l; i<r; i++){
            if(dist(points,i) < dist(points,si)){
                swap(points,i,si);
                si++;
            }
        }
        swap(points,r,si);
        return si;
    }

    public int dist(int[][] points, int i){
        return points[i][0]*points[i][0] + points[i][1]*points[i][1];
    }

    public void swap(int[][] points, int i, int j){
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public int[][] kClosestA4(int[][] points, int k){
        this.points = points;
        quickSelect(0, points.length-1,k-1);
        return Arrays.copyOf(points,k);
    }

    public int quickSelect(int start, int end, int k){
        if(start==end)return start;
        int pivot = start+ new Random().nextInt(end-start);

        pivot = partition(start, end, pivot);
        if(pivot==k)return k;
        else if(pivot<k)return quickSelect(pivot+1,end,k);
        else return quickSelect(start,pivot-1,k);
    }

    public int partition(int start, int end, int pivot){
        int index = start;
        swap(pivot,end);
        for(int i=start; i<end; i++){
            if(distance(i)<distance(end)){
                swap(i,index);
                index++;
            }
        }
        swap(index,end);
        return index;
    }

    public int distance(int i){
        return points[i][0]*points[i][0]+points[i][1]*points[i][1];
    }

    public void swap(int i, int j){
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    public int[][] kClosestA2(int[][] points, int k){
        Arrays.sort(points,(a,b)->a[0]*a[0]+a[1]*a[1]-b[0]*b[0]-b[1]*b[1]);
        return Arrays.copyOf(points,k);
    }

    public int[][] kClosestA3(int[][] points, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        int i=0;
        for(int[] point : points){
            int distance = point[0]*point[0]+point[1]*point[1];
            if(pq.size()>=k){
                if(distance<pq.peek()[0]){
                    pq.poll();
                    pq.add(new int[]{distance,i});
                }
            }else pq.add(new int[]{distance,i});
            i++;
        }
        int n = pq.size();
        int[][] result = new int[n][];
        for(i=0; i<n;i++)result[i]=points[pq.poll()[1]];
        return result;
    }

    /*public int[][] kClosestA3(int[][] points, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b)->b[0]-a[0]);
        for(int i=0; i<points.length; i++){
            int dist = points[i][0]*points[i][0]+points[i][1]*points[i][1];
            if(pq.size()>=k){
                if(dist<pq.peek()[0]){
                    pq.poll();
                    pq.add(new int[]{dist,i});
                }
            }else pq.add(new int[]{dist,i});
        }
        int[][] result = new int[k][];
        for(int i=0; i<k; i++)result[i]=points[pq.poll()[1]];
        return result;
    }*/
    public int[][] kClosestA1(int[][] points, int k){
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b)->{
                    double a1 =Math.sqrt(b[0]*b[0]+b[1]*b[1]);
                    double a2 = Math.sqrt(a[0]*a[0]+a[1]*a[1]);
                    if(a1==a2)return 0;
                    if(a1<a2)return -1;
                    return 1;
                });
        for(int[] point: points){
            pq.add(point);
            if(pq.size()>k)pq.poll();
        }
        int n = pq.size();
        int[][] result = new int[n][];
        for(int i=0; i<n;i++)result[i]=pq.poll();
        return result;
    }
}