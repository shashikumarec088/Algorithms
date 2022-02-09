package com.github.shashi.leetcode;
import java.util.*;
public class Problem819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        return mostUsed2(paragraph, banned);
    }

    public String mostUsed2(String p, String[] banned){
        Set<String> set = new HashSet<>();
        for(String str : banned)set.add(str);
        String ans = "";
        int max = 0;
        Map<String,Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        char[] pc = p.toCharArray();
        for(int i=0; i<pc.length;i++){
            if(Character.isLetter(pc[i])){
                sb.append(Character.toLowerCase(pc[i]));
                if(i != pc.length-1)
                    continue;
            }

            if(sb.length()>0){
                String s1 = sb.toString();
                if(!set.contains(s1)){
                    int count = map.getOrDefault(s1,0)+1;
                    map.put(s1,count);
                    if(max<count){
                        ans = s1;
                        max= count;
                    }
                }
            }
            sb = new StringBuilder();
        }
        return ans;
    }

    public String mostUsed(String paragraph1, String[] banned){
        Set<Character> punct = new HashSet<>();
        Map<String,Integer> map = new HashMap<>();
        punct.add('!');
        punct.add('?');
        punct.add('\'');
        punct.add(',');
        punct.add(';');
        punct.add('.');
        String paragraph = paragraph1.replaceAll("[^a-zA-Z0-9 ]"," ");
        String[] sr = paragraph.split("\\s+");
        Set<String> bans = new HashSet<>();
        int max = 0;
        String word = "";
        for(String str : banned)bans.add(str);
        int n = sr.length;
        for(int i=0; i<n; i++){
            String s = sr[i].trim().toLowerCase();
            if(!bans.contains(s) && s.length()>0){
                int count = map.getOrDefault(s,0)+1;
                map.put(s,count);
                if(count > max){
                    max = count;
                    word = s;
                }
            }
        }
        return word;
    }
}
