package com.github.shashi.leetcode;

public class Problem1089 {
    public void duplicateZeros(int[] arr) {
        duplicateZerosA2(arr);
    }

    /*
    in this first count the number of zeros until index of elements plus number of
    zeros is n-1, in 1 case then sum will be greater than n-1 ex: [1,0,0,1] for
    this to handle we should not add the extra zeros until count+i becomes <= n-1

    once you have the index from which you can shift to last, add extra zero
    when current element is zero and count+index is <= n-1 else do not add extra
    zero.
     */
    public void duplicateZerosA3(int[] nums) {
        int i=0, count=0, n = nums.length;
        while(i<n){
            if(nums[i]==0)count++;
            if(count+i>=n-1)break;
            i++;
        }
        int k=n-1;
        while(k>-1){
            nums[k] = nums[i];
            k--;
            // (count+i) <=n-1 to handle case where last element is zero but we
            // can not accommodate extra zero ex : [1001] in this case count+i is 4
            // which is equal to length in this case we should ignore the duplicate zero
            if((count+i) <=n-1 && nums[i]==0 && k>-1){
                nums[k] = nums[i];
                k--;
            }
            i--;
        }

    }

    public static void main(String[] args) {
        int[] nums = {8,4,5,0,0,0,0,7};
        int i=0, count=0, n = nums.length;
        while(i<n){
            if(nums[i]==0)count++;
            if(count+i>=n-1)break;
            i++;
        }
        int k=n-1;
        while(k>-1){
            nums[k] = nums[i];
            k--;
            if(nums[i]==0 && k>-1){
                nums[k] = nums[i];
                k--;
            }
            i--;
        }

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
