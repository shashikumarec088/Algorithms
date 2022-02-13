package com.github.shashi.leetcode;

public class Problem1089 {
    public void duplicateZeros(int[] arr) {
        duplicateZerosA2(arr);
    }

    public void duplicateZerosA2(int[] a){
        int n=a.length,z=0;
        for(int item: a)
            if(item==0)z++;
        int i=n-1,j=n+z-1;
        while(i>-1){
            if(a[i]!=0){
                if(j<n)a[j]=a[i];
            }else{
                if(j<n)a[j]=0;
                j--;
                if(j<n)a[j]=0;
            }
            j--;
            i--;
        }
    }

    public void duplicateZerosA1(int[] a){
        int n = a.length;
        int i=0;
        while(i<n){
            if(a[i]==0){
                int k = n-1;
                while(k>i){
                    a[k] = a[k-1];
                    k--;
                }
                i=i+2;
            }else i++;

        }
    }
}
