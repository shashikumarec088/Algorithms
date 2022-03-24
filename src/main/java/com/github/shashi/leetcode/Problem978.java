package com.github.shashi.leetcode;

public class Problem978 {
    public static void main(String[] args) {
        Problem978 problem978 = new Problem978();
        int[] input = {9,4,2,10,7,8,8,1,9};
        System.out.println(problem978.maxTurbulenceSize(input));
    }
    public int maxTurbulenceSize(int[] arr) {
        return maxTurbulenceSizeA2(arr);
    }

    public int maxTurbulenceSizeA3(int[] arr){
        // dp approach
        int n=arr.length, len=0;
        int[][] states= new int[n][2];

        for(int i=1; i<n; i++){
            if(arr[i-1]< arr[i]){
                states[i][0] = states[i-1][1]+1;
                len = Math.max(states[i][0],len);
            }
            else if(arr[i-1] > arr[i]){
                states[i][1] = states[i-1][0]+1;
                len = Math.max(states[i][1],len);
            }
        }
        return len+1;
    }

    public int maxTurbulenceSizeA2(int[] arr){
        int start=0, ans=1,n=arr.length;
        for(int i=1; i<n; i++){
            int c = Integer.compare(arr[i-1],arr[i]);
            if(c==0)
                start=i;
            else if(i==n-1 || c*Integer.compare(arr[i],arr[i+1])!=-1){
                ans = Math.max(ans,i-start+1);
                start=i;
            }
        }
        return ans;
    }

    public int maxTurbulenceSizeA1(int[] arr){
        int n=arr.length, minc=1;
        int maxc=minc, res=minc;
        for(int j=1; j<n;j++){
            // we need to swap as we need alternating comparision
            int temp = minc;
            minc = maxc;
            maxc = temp;
            if(arr[j] < arr[j-1]){
                minc++;
                res = Math.max(minc, res);
            }else minc = 1;
            if(arr[j] > arr[j-1]){
                maxc++;
                res = Math.max(maxc, res);
            }else maxc = 1;
        }
        return res;
    }
}