package com.github.shashi.leetcode;
import java.util.*;
public class Problem421 {
    public int findMaximumXOR(int[] nums) {
        return findMaximumXORA2(nums);
    }

    class Trie{
        Map<Character,Trie> children = new HashMap<>();
        Trie(){}
    }

    public int findMaximumXORA2(int[] nums){
        int maxNum = nums[0];
        for(int num: nums)maxNum = Math.max(num, maxNum);
        String maxStr = Integer.toBinaryString(maxNum);
        int maxRes = 0, l = maxStr.length(), n = nums.length;
        String[] nStrs = new String[n];
        for(int i=0; i<n; i++)
            nStrs[i] = Integer.toBinaryString((1<<l)|nums[i]).substring(1);
        Trie trie = new Trie();
        Trie cur = trie;
        for(String s : nStrs){
            cur = trie;
            Trie exorTrie = trie;
            int curRes = 0;
            for(char c : s.toCharArray()){
                char cc = c=='1'?'0':'1';
                if(!cur.children.containsKey(c))
                    cur.children.put(c,new Trie());
                cur = cur.children.get(c);

                if(exorTrie.children.containsKey(cc)){
                    curRes = (curRes<<1)|1;
                    exorTrie = exorTrie.children.get(cc);
                }else{
                    curRes = curRes<<1;
                    exorTrie = exorTrie.children.get(c);
                }

            }
            maxRes = Math.max(maxRes,curRes);
        }
        return maxRes;
    }

    public int findMaximumXORA1(int[] nums){
        int maxNum =nums[0];
        for(int num : nums)maxNum = Math.max(num,maxNum);
        int maxRes =0, l=Integer.toBinaryString(maxNum).length();

        for(int i=l-1; i>-1; i--){
            maxRes = maxRes << 1;
            int curXor = maxRes | 1;
            Set<Integer> prefixes = new HashSet<>();
            for(int num : nums)prefixes.add(num>>i);
            for(int prefix : prefixes)
                if(prefixes.contains(prefix^curXor)){
                    maxRes = curXor;
                    break;
                }
        }
        return maxRes;
    }
}