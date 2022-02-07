package com.github.shashi.leetcode;
import com.github.pedrovgs.pair.Pair;
public class Problem165 {
    public int compareVersion(String version1, String version2) {
        return compareVersion1(version1,version2);
    }

    public int compareVersion2(String v1, String v2){
        int p1=0, p2=0, n1=v1.length(), n2=v2.length();
        while(p1 < n1 || p2 < n2){
            Pair<Integer,Integer> a1 = get_next_chunk(v1,p1);
            Pair<Integer,Integer> a2 = get_next_chunk(v2,p2);
            if(a1.getValue() > a2.getValue()) return 1;
            if(a1.getValue() < a2.getValue()) return -1;
            p1 = a1.getKey();
            p2 = a2.getKey();
        }
        return 0;
    }

    public Pair<Integer,Integer> get_next_chunk(String v, int i){
        int n = v.length();
        int start = i;
        if(i>= n) return new Pair<>(i,0);
        while(start<n && v.charAt(start) != '.')
            start++;
        return new Pair<>(start+1,Integer.valueOf(v.substring(i,start)));
    }

    public static void main(String[] args) {
        Problem165 problem165 = new Problem165();
        int ans = problem165.compareVersion1("0.1","1.0");
        System.out.println(ans);
    }

    public int compareVersion1(String v1, String v2){
        String[] v1Arr = v1.split(".");
        String[] v2Arr = v2.split(".");
        int n1 = v1Arr.length, n2 = v2Arr.length;
        int m = Math.max(n1,n2);
        for(int i=0; i<m; i++){
            int a = i < n1 ? Integer.valueOf(v1Arr[i]):0;
            int b = i < n2 ? Integer.valueOf(v2Arr[i]):0;
            if(a>b) return 1;
            if(b>a) return -1;
        }
        return 0;
    }
}
