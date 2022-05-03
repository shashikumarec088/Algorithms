package com.github.shashi.leetcode;
import java.util.*;
public class Problem128 {
    class UnionFind{
        int[] root, rank,size;
        int n;
        UnionFind(int n){
            this.n = n;
            root = new int[n];
            rank = new int[n];
            size = new int[n];
            for(int i=0; i<n; i++){
                root[i]=i;
                rank[i]=1;
                size[i]=1;
            }
        }

        public int find(int x){
            if(x==root[x])return x;
            return root[x]=find(root[x]);
        }

        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if(rx!=ry){
                if(rank[ry]>rank[rx]){
                    root[rx]=ry;
                    size[ry]+=size[rx];
                }
                else if(rank[rx]>rank[ry]){
                    root[ry]=rx;
                    size[rx]+= size[ry];
                }else{
                    root[rx]=ry;
                    size[ry]+=size[rx];
                    rank[ry]++;
                }
            }
        }

        public int getLongestSequence(){
            int max=0;
            for(int i=0; i<n; i++)
                if(root[i]==i && size[i]>max)
                    max=size[i];
            return max;
        }
    }
    public int longestConsecutive(int[] nums) {
        return longestConsecutiveA6(nums);
    }

    public int longestConsecutiveA6(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();
        int n=nums.length;
        UnionFind uf = new UnionFind(n);
        for(int i=0; i<n; i++){
            if(map.containsKey(nums[i]))
                continue;
            if(map.containsKey(nums[i]-1))
                uf.union(i,map.get(nums[i]-1));
            if(map.containsKey(nums[i]+1))
                uf.union(i,map.get(nums[i]+1));
            map.put(nums[i],i);

        }
        return uf.getLongestSequence();
    }

    public int longestConsecutiveA5(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums)set.add(num);
        int max=0,n=nums.length;
        if(n<2)return n;
        for(int num : set){
            if(!set.contains(num-1)){
                int cur=1;
                while(set.contains(num+1)){
                    cur++;
                    num++;
                }
                max = Math.max(max,cur);
            }
        }
        return max;
    }

    public int longestConsecutiveA4(int[] nums){
        Arrays.sort(nums);
        int max=1, n=nums.length,current=1;
        if(n<2)return n;
        for(int i=1; i<n; i++){
            if(nums[i-1] != nums[i]){
                if(nums[i-1]+1==nums[i])
                    current++;
                else{
                    max = Math.max(max,current);
                    current=1;
                }
            }
        }
        return Math.max(max,current);
    }

    public int longestConsecutiveA3(int[] nums){
        Arrays.sort(nums);
        int max=0,n=nums.length,i=0,j=1,dups=0;
        if(n<2)return n;
        for(;j<n;j++){
            if(nums[j-1]+1==nums[j])
                continue;
            if(nums[j-1]==nums[j]){
                dups++;
                continue;
            }
            max = Math.max(j-i-dups,max);
            dups=0;
            i=j;
        }
        max = Math.max(j-i-dups,max);
        return max;
    }

    public int longestConsecutiveA2(int[] nums){
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for(int num: nums)set.add(num);
        for(int num: nums){
            int currentStreak=1;
            while(set.contains(num+1)){
                num++;
                currentStreak++;
            }
            max = Math.max(max,currentStreak);
        }
        return max;
    }

    public int longestConsecutiveA1(int[] nums){
        int max = 0;
        for(int num: nums){
            int currentStreak=1;
            while(numContains(nums,num+1)){
                num++;
                currentStreak++;
            }
            max = Math.max(max,currentStreak);
        }
        return max;
    }

    public boolean numContains(int[] nums,int num){
        for(int num1 : nums)
            if(num1==num)return true;
        return false;
    }
}
