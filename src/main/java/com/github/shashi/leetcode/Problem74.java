package com.github.shashi.leetcode;

public class Problem74 {
    /*
    Search a 2D Matrix
    You are given an m x n integer matrix matrix with the following two properties:
    Each row is sorted in non-decreasing order.
    The first integer of each row is greater than the last integer of the previous row.
    Given an integer target, return true if target is in matrix or false otherwise.
    You must write a solution in O(log(m * n)) time complexity.

    Example 1:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true
    Example 2:
    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    Output: false
    Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104

    Approach 1: binary search
    * intuition is to do the binary search in 2d sorted matrix, since all the rows in matrix are sorted and
    start element of each row is > prev row end element we can apply normal binary search, considering 0 to
    m*n-1, to get the r,c given any number we can use r = num/n and c = num%n.
    algo:
    * init m=matrix.length, n = matrix[0].length, l=0, h=m*n-1
    *iterate using while loop until l<=h, find mid using mid=l+(r-l)/2
    * find r = mid/n, c=mid%n, if matrix[r][c]==target then return true, if it is less make l=mid+1
    else r=mid-1
    time & space:
    * it takes log(m*n) time and const space
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        return searchMatrixA1(matrix,target);
    }

    public boolean searchMatrixA1(int[][] matrix, int target) {
        int m=matrix.length, n = matrix[0].length;
        int l=0, h=m*n-1;
        while(l<=h){
            int mid = (l+h)/2;
            int r=mid/n,c=mid%n;
            if(matrix[r][c]==target)return true;
            else if(matrix[r][c]<target)l=mid+1;
            else h=mid-1;
        }
        return false;
    }
}
