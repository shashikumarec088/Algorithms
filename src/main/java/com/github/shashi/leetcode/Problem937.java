package com.github.shashi.leetcode;
import java.util.*;
public class Problem937 {
    public String[] reorderLogFiles(String[] logs) {
        return reOrderLogFilesA1(logs);
    }

    static class LogComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            String[] s1Arr = s1.split(" ",2);
            String[] s2Arr = s2.split(" ",2);
            boolean s1Digit = Character.isDigit(s1Arr[1].charAt(0));
            boolean s2Digit = Character.isDigit(s2Arr[1].charAt(0));;
            if(!s1Digit && !s2Digit){
                int compareResult = s1Arr[1].compareTo(s2Arr[1]);
                return compareResult == 0 ? s1Arr[0].compareTo(s2Arr[0]): compareResult;
            }
            else if(!s1Digit || !s2Digit)
                return s1Digit ? 1:-1;
            else return 0;
        }
    }

    public String[] reOrderLogFilesA1(String[] logs){
        LogComparator lc = new LogComparator();
        Arrays.sort(logs,lc);
        return logs;
    }
}
