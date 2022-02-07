package com.github.shashi.leetcode;
import java.util.*;
import com.github.pedrovgs.pair.Pair;
public class Problem76 {
    public String minWindow(String s, String t) {
        return minWindowOpt(s,t);
    }

    public String minWindowOpt(String s, String t){
        if(s.length()==0 || t.length()==0) return "";
        if(s.length() < t.length()) return "";
        Map<Character,Integer> tMap = new HashMap<>();
        for(char c : t.toCharArray()){
            int count = tMap.getOrDefault(c,0);
            tMap.put(c,count+1);
        }
        int required = tMap.size(),formed=0,n=s.length();
        List<Pair<Integer,Character>> filteredS = new ArrayList<>();
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            if(tMap.containsKey(c)){
                filteredS.add(new Pair<>(i,c));
            }
        }
        Map<Character,Integer> sMap = new HashMap<>();
        int l = 0;
        int r = l;
        int[] ans = new int[]{-1,0,0};
        while(r < filteredS.size()){
            char c = filteredS.get(r).getValue();

            int count = sMap.getOrDefault(c,0);
            sMap.put(c,count+1);
            if(tMap.get(c).intValue()== sMap.get(c).intValue())
                formed++;
            while(l<=r && formed==required){
                c = filteredS.get(l).getValue();
                int li = filteredS.get(l).getKey();
                int ri = filteredS.get(r).getKey();
                if(ans[0]==-1 || (ri-li+1)<ans[0]){
                    ans[0] = ri-li+1;
                    ans[1] = li;
                    ans[2] = ri;
                }
                count = sMap.get(c);
                sMap.put(c,count-1);
                if(sMap.get(c).intValue()<tMap.get(c).intValue())
                    formed--;
                l++;
            }
            r++;
        }
        return ans[0]==-1?"":s.substring(ans[1],ans[2]+1);
    }

    public String minWindowS1(String s, String t){
        int n=s.length(),l=0,r=0,formed=0;
        Map<Character,Integer> map = new HashMap<>();
        if(s.length()==0 || t.length()==0 ) return "";
        for(int i=0; i<t.length();i++){
            int c1 = map.getOrDefault(t.charAt(i),0);
            map.put(t.charAt(i),c1+1);
        }
        int required = map.size();
        Map<Character,Integer> dMap = new HashMap<>();
        int[] ans = new int[]{-1,0,0};
        while(r<n){
            char c2 = s.charAt(r);
            int cn = dMap.getOrDefault(c2,0);
            dMap.put(c2,cn+1);
            if(map.containsKey(c2) && dMap.get(c2).intValue()==map.get(c2).intValue())
                formed++;
            while(l<=r && formed == required){
                char lc = s.charAt(l);
                if(ans[0]==-1 || (r-l+1)<ans[0]){
                    ans[0] = r-l+1;
                    ans[1]= l;
                    ans[2] = r;
                }
                dMap.put(lc,dMap.get(lc)-1);
                if(map.containsKey(lc) && dMap.get(lc).intValue()<map.get(lc).intValue())
                    formed--;
                l++;
            }
            r++;
        }
        return ans[0]==-1 ? "" :s.substring(ans[1],ans[2]+1);
    }
}
