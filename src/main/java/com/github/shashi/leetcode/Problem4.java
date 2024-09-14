package com.github.shashi.leetcode;

public class Problem4 {
    /*
    Median of Two Sorted Arrays
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays
    The overall run time complexity should be O(log (m+n)).
    Example 1:
    Input: nums1 = [1,3], nums2 = [2]
    Output: 2.00000
    Explanation: merged array = [1,2,3] and median is 2.
    Example 2:
    Input: nums1 = [1,2], nums2 = [3,4]
    Output: 2.50000
    Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
    Constraints:
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -106 <= nums1[i], nums2[i] <= 106

    Approach 1: bf mergesort
    * intuition is to merge both the arrays in asc order and return n/2th element if odd else avg of n/2-1 and n/2
    element
    algo:
    * m=nums1.length, n=nums2.length init out array of length m+n, init l1=0,l2=0, i=0
    * iterate until l1<m && l2<n at each iteration
    * check if nums1[l1] <nums2[l2] if so add nums1[l1] to out[i] and inc l1 else nums2[l2] to out of i and inc l2
    * inc i
    * at the end iterate until l1<m and add to out[i] and inc i,l1
    * iterate until l2<n and add it to out[i] inc i,l2;
    * make mid=(m+n)/2
    * if (m+n)%2 == 0 then return (out[mid-1]+out[mid])/2.0
    * else return out[mid]
    time & space:
    * takes over all (m+n) time and (m+n) space

    Approach 2: getting the mid element without actually creating the extra array for out
    * intuition is to have the 2 pointers l1, l2, iterate from i=0 to (m+n)/2-1
    * at each iterate update l1 or l2 based on which one is smaller, at the end if sum is odd then return
    element getting the mid at next position else by taking the avg of min at mid and prev position
    algo:
    * init global variables l1, l2, find total = (m+n) and temp=0
    * iterate i from 0 to <total/2-1 and for each iteration call getMin which takes nums1 and nums2 and assign to temp
    * at the end if total%2 ==1 then return getMin(nums1,nums2)
    * else return avg of temp,getMin(nums1,nums2) this is to handle even number of elements
    time & space:
    * takes m+n time and constanct space

    Approach 3: binary search with rec
    * intuition is since both the arrays are sorted, we need to find the mid element of the merged list or mid and
    previous element if total elements are even. to do that we can consider the mid of both the arrays and check
    if number of elements on the left side of both the mids are < target position if so we can discard the left
    part of A or B else right part of A or B
    * if amid+bmid<k , to decide on which array left part to discard, we can check if av(mid val of a) is <
    bv(mid val of b) if so we can discard the left part of A else left part of b
    * similarly if amid+bmid>k, to decide on which array right part to discard, we can check if av > bv
    if so we can discard the A right part else B.
    algo:
    * create global variables n1, n2 of type integer array, assing nums1, nums2 to those, let total=m+n
    where m = n1.length and n=n2.length
    * if total is odd then return rec of 0,m-1,0,n-1,total/2
    * else return avg of rec of 0,m-1,0,n-1,total/2-1 and rec of 0,m-1,0,n-1,total/2
    * in rec we take start index of n1, n2 and end index of n1,n2 if as,ae,bs,be, kth element
    * base case is if either of the array is empty then we return if as>ae then we return n2[k-as]
    * if bs>be then we return n1[k-bs]
    * compute mid of A, B am=(as+ae)/2, bm=(bs+be)/2, and av=n1[am] and bv=n2[bm]
    * check if am+bm < k  if so
        * check if av<bv if so return rec(am+1,ae,bs,be,k) else rec(as,ae,bm+1,be)
    * else ie am +bm >=k
        * check if av>bv if so return rec(as,am-1,bs,be) else rec(as,ae,bs,bm-1)
    time & space:
    * it takes log m + log n time and log m + log n space as we need to cut both arrays in worst case.

    Approach 4: binary search on min length array
    * intuition is to partition both the arrays such that the number of elements on left parts are <=
    right parts and then check if edge elements are in proper order, ie aleft<=bright and bleft<=aright
    where aleft is left part of array a and aright is right part of a similary bleft and bright are for
    array b
    * to parition the array at right position of smaller array 1 we need to do the binary search and
    get the amid and find the position of array b such that number of elements in left part and right
    parts are same  partA = (l+r)/2 then partb = (m+n+1)/2-partA , we added 1 to handle the odd elements
    cas and also partA and partB position belongs to right part of the array
    * then we get the elements at 4 boundaries and check if they are satisfying the condition aleft<=bright
    and bleft<=aright if so we found the right boundaries else we check if aleft > bright then we consider
    the left part of array a ie we make r=partA-1 else we consider right part of array ie l=partA+1
    algo:
    * init m=nums1.length and n = nums2.length, l=0, r=m (remember this r=m not m-1) this is because on the
    left part all the elements from A can be there when all elements in A < B this will hold true.
    * if m>then we call the same method with nums2,nums1 this is to make sure that we always consider the
    smallest array for binary search so time complexity is reduced
    * iterate loop until l<=r
    * find partA= (l+r)/2 and partB = (m+n+1)/2-partA
    * find values at 4 edges
    * aleft = if partA is 0 then set to Integer.MIN_VALUE else to nums1[partA-1]
    * aright = if partA=m then Integer.MAX_VALUE else nums1[partA]
    * bleft = if partB is 0 then set to Integer.MIN_VALUE else to nums2[partB-1]
    * bright = if partB=n then Integer.MAX_VALUE else nums2[partB]
    * check if aleft<=bright and bleft<=aright if so we paritioned both arrays at right position
        * if (m+n)%2==0 then our median is avg of max(aleft,bleft)and min(aright,bright)
        * else max(aleft,bleft)
    * if aleft > bright then make r=partA-1
    * else make l=partA+1
    time & space:
    * it take log(min(m,n)) time and constant space
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMedianSortedArraysA1(nums1,nums2);
    }

    public static void main(String[] args) {
        int[] nums1= {1,3};
        int[] nums2 = {2};
        Problem4 problem4 = new Problem4();
        problem4.findMedianSortedArraysA1(nums1,nums2);
    }

    public double findMedianSortedArraysA2(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length)return findMedianSortedArraysA2(nums2,nums1);
        int m=nums1.length, n=nums2.length;
        int l=0, r=m;
        while(l<=r){
            int partA = (l+r)/2;
            int partB = (m+n+1)/2-partA;
            int maxleftA = partA==0?Integer.MIN_VALUE:nums1[partA-1];
            int minrightA = partA==m?Integer.MAX_VALUE:nums1[partA];
            int maxleftB = partB==0?Integer.MIN_VALUE:nums2[partB-1];
            int minrightB = partB==n?Integer.MAX_VALUE:nums2[partB];

            if(maxleftA>minrightB)r=partA-1;
            else if(maxleftB>minrightA)l=partA+1;
            else{
                if((m+n)%2==0)
                    return (Math.max(maxleftA,maxleftB)+
                            Math.min(minrightA,minrightB))/(double)2.0;
                else return Math.max(maxleftA,maxleftB);
            }
        }
        return 0.0;
    }
    int[] n1,n2;
    public double findMedianSortedArraysA3(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length;
        n1=nums1;
        n2=nums2;
        if((m+n)%2==0)return (double)(rec(0,m-1,0,n-1,(m+n)/2-1)+
                rec(0,m-1,0,n-1,(m+n)/2))/2.0;
        else return (double)rec(0,m-1,0,n-1,(m+n)/2);
    }

    public int rec(int as,int ae,int bs,int be, int k){
        if(as>ae)return n2[k-as];
        if(bs>be)return n1[k-bs];
        int am=(as+ae)/2, bm=(bs+be)/2;
        int av=n1[am], bv=n2[bm];
        if((am+bm)<k){
            if(av<bv)
                return rec(am+1,ae,bs,be,k);
            else return rec(as,ae,bm+1,be,k);
        }else{
            if(av>bv)
                return rec(as,am-1,bs,be,k);
            else return rec(as,ae,bs,bm-1,k);
        }
    }

    int l1=0,l2=0;
    public double findMedianSortedArraysA4(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length,temp=0;
        for(int i=0; i< (m+n)/2;i++)
            temp=getMin(nums1,nums2);
        if((m+n)%2==0)return (double)(temp+getMin(nums1,nums2))/2.0;
        else return (double)getMin(nums1,nums2);
    }

    public int getMin(int[] nums1, int[] nums2){
        if(l1<nums1.length && l2<nums2.length)
            return nums1[l1]<nums2[l2]?nums1[l1++]:nums2[l2++];
        else if(l1<nums1.length)return nums1[l1++];
        else if(l2<nums2.length)return nums2[l2++];
        else return -1;
    }

    public double findMedianSortedArraysA1(int[] nums1, int[] nums2) {
        int m=nums1.length,n=nums2.length,l1=0,l2=0,i=0;
        int[] out = new int[m+n];
        while(l1<m && l2<n){
            if(nums1[l1]<nums2[l2])
                out[i++]=nums1[l1++];
            else out[i++]=nums2[l2++];
        }
        while(l1<m)out[i++]=nums1[l1++];
        while(l2<n)out[i++]=nums2[l2++];
        int mid=(m+n)/2;
        if((m+n)%2==0)return (double)(out[mid-1]+out[mid])/2.0;
        else return (double)out[mid];
    }
}