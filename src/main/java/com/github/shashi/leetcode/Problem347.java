package com.github.shashi.leetcode;
import java.util.*;
public class Problem347 {
    Map<Integer,Integer> map = new HashMap<>();
    int[] array;
    public int[] topKFrequent(int[] nums, int k) {
        return topKFrequentA3(nums, k);
    }

    public static void main(String[] args) {
        topKFrequentA4(new int[]{1,1,1,2,2,3},2);
    }

    public static int[] topKFrequentA4(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int num : nums){
            int count = 1;
            if(map.containsKey(num)) count = map.get(num)+1;
            map.put(num,count);
        }

        // max heap with custom comparator
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(
                (a,b)->{
                    if(a[0]> b[0]) return -1;
                    else if(a[0] == b[0]) return 0;
                    else return 1;
                });
        for(int key: map.keySet()){
            pq.add(new int[]{map.get(key),key});
        }

        int[] result = new int[k];
        for(int i=0; i<k; i++)
            result[i] = pq.poll()[1];
        return result;

    }

    public int[] topKFrequentA3(int[] nums, int k){
        for(int num: nums){
            int count = map.getOrDefault(num, 0);
            map.put(num, count+1);
        }
        List<Integer>[] array = new ArrayList[nums.length+1];

        for(int key : map.keySet()){
            int freq = map.get(key);
            if(array[freq]==null)
                array[freq] = new ArrayList<>();
            array[freq].add(key);
        }
        int[] res = new int[k];
        int j=0;
        for(int i = array.length-1; i>-1 && j<k; i--){
            if(array[i]!=null)
                for(int element : array[i]){
                    if(j<k){
                        res[j]=element;
                        j++;
                    }else break;
                }
            if(j>=k)break;
        }
        return res;
    }

    public int[] topKFrequentA2(int[] nums, int k){
        for(int num: nums){
            int count = map.getOrDefault(num,0);
            map.put(num, count+1);
        }

        int n = map.size();
        array = new int[n];
        int i=0;
        for(int key: map.keySet()) array[i++] = key;
        quickSelect(0,n-1, n-k);
        return Arrays.copyOfRange(array,n-k,n);
    }

    public int quickSelect(int start, int end, int k){
        if(start==end)return array[start];
        int pivot = start + (new Random().nextInt(end-start));
        pivot = partition(start,end,pivot);
        if(k==pivot)return array[k];
        else if(k< pivot)return quickSelect(start, pivot-1,k);
        else return quickSelect(pivot+1,end,k);
    }

    public int partition(int start, int end, int pivotIndex){
        int pivot = array[pivotIndex];
        swap(pivotIndex,end);
        int index = start;
        for(int i=start; i<end; i++){
            if(map.get(array[i]) < map.get(pivot)){
                swap(i,index);
                index++;
            }
        }
        swap(index,end);
        return index;
    }

    public void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int[] topKFrequentA1(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            int count = map.getOrDefault(num,0);
            map.put(num,count+1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        for(Integer key : map.keySet()){
            int count = map.get(key);
            if(pq.size() == k){
                if(count > pq.peek()[0]){
                    pq.poll();
                    pq.add(new int[]{count,key});
                }
            }else pq.add(new int[]{count,key});
        }

        int[] result = new int[k];
        for(int i=0; i<k; i++)result[i] = pq.poll()[1];
        return result;
    }
}