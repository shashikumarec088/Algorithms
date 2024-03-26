package com.github.shashi.leetcode;

public class Problem702 {
    static class ArrayReader{
        private int[] arr;
        ArrayReader(int[] arr){
            this.arr = arr;
        }
        public int get(int i){
            return i+1;
        }
    }

    public int searchA2(ArrayReader reader, int target) {
        if(reader.get(0)==Integer.MAX_VALUE)return -1;
        if(reader.get(0)==target)return 0;
        int l=0, r=1;
        while(reader.get(r) < target){
            l=r;
            r=2*r;
        }
        while(l<=r){
            int mid = l+(r-l)/2;
            if(reader.get(mid)==target)return mid;
            else if(reader.get(mid)<target) l=mid+1;
            else r = mid-1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,3,5,9,12};
        ArrayReader arrayReader = new ArrayReader(arr);
        Problem702 problem702 = new Problem702();
        problem702.search(arrayReader,9);

    }
    public int search(ArrayReader reader, int target) {
        return searchA1(reader,target);
    }

    public int searchA1(ArrayReader reader, int target) {
        if(reader.get(0)==Integer.MAX_VALUE)return -1;
        if(reader.get(0)==target)return 0;
        int l=0, r=1;
        while(reader.get(r)!=Integer.MAX_VALUE){
            l=r;
            r=2*r;
        }
        while(l<r){
            int mid = l+(r-l)/2;
            if(reader.get(mid)==Integer.MAX_VALUE){
                r=mid;
            }else{
                l=mid+1;
            }
        }
        r=l-1;
        l=0;
        while(l<=r){
            int mid = l+(r-l)/2;
            if(reader.get(mid)==target)return mid;
            else if(reader.get(mid)<target) l=mid+1;
            else r = mid-1;
        }
        return -1;
    }
}