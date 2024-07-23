package com.github.shashi.leetcode;

public class Problem64 {
    /*
    Minimum Path Sum
    Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the
    sum of all numbers along its path.
    Example 1:
    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
    Example 2:
    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12
    Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    0 <= grid[i][j] <= 200

    Approach 1: bf
    * intuition is we are asked to find the minimum path sum which has the relation for any position
    the pathsum will be grid[i][j]+ min(pathsum(i+1,j),pathsum(i,j+1))
    * this can be solved recursively
    algo:
    * we will have recursion function with grid,i,j
    * base case is when i==m-1 and j==n-1 return grid[i][j]
    * if i>=m or j>=n return int max
    * return grid[i][j]+min(rec(grid,i+1,j),rec(grid,i,j+1))
    time & space:
    * it takes 2^(m+n) since their are m+n

    Approach 2: dp with memoization
    * intuition is same as approach 1 but we can avoid the duplicate calculations by using the 2d array cache
    * here for the m-1, n-1 then min sum is grid[m-1][n-1] for (m-2,n-1) it will be min (m-1,n-1)+grid[m-2][n-1]
    this indicates that at any point we will have the min path sum for the position from the bottom right.
    * we can store the result in dp array after we get the pathsum for each r,j
    algo:
    * create a global variable of type Integer and size m,n where m is rows and n is cols of grid, make m, n also
    global
    * call rec with grid,0,0
    * int rec check if r==m-1 and c==n-1 if so return grid[r][c]
    * iif r>=m || c>=n return int max value as default
    * update dp[r][c] with grid[r][c]+min(rec(grid,r+1,c),rec(grid,r,c+1))
    * return dp[r][c]
    time & space:
    * it takes m*n time and m*n space

    Approach 3: dp with tabulation
    * intuition is same as approach 1 but using tabulation method we start from bottom right and travel to
    top left, when we are at bottom right the min value is grid position value, for the last row the
    min value will be sum of grid value and the min path sum at i,j+1. for the last column the min
    value will be sum of grid value and min path sum at i+1,j. for remaining positions the min
    path sum will be sum of grid value and min(dp[i+1][j],dp[i][j+1])
    algo:
    * init m as rows and n as cols and create dp of ints of size m,n
    * iterate from i=m-1 to 0 and j=n-1 to 0
    * if i=m-1 and j=n-1 then dp[i][j]= grid[i][j]
    * else if i=m-1 then dp[i][j]= grid[i][j] + dp[i][j+1]
    * else if j=n-1 then dp[i][j]= grid[i][j] + dp[i+1][j]
    * else dp[i][j]= grid[i][j] + min(dp[i+1][j]+dp[i][j+1])
    * return value at dp[0][0]
    time & space
    * it take m*n time and n*m space for dp array

    Approach 4: dp with 1d array for storing down row min path sums
    * intuition is at any point we access the min path sums of row below or elements right side that
    too we process from right to left and from bottom to top, so if we can store only the last
    processed row results then it is enough to compute the cur row results
    * consider we have dp 1d array of size n, when r=m-1,c=n-1 then our min path sum is grid[i][j] store in dp[n-1]
    * when we are at the last row the min path sum at j can be stores in dp[j] = grid[i][j]+dp[j+1]
    * when we are at last column dp[j] = grip[i][j]+dp[j]
    * else dp[j] = grid[i][j]+ Math.min(dp[j],dp[j+1])
    * return dp[0] at then end
    time & space:
    * it takes m*n time and n space

    Approach 5: dp without using extra array we can use the last row of input
    * intuition is same as above
    lago:
    * make dp = grid[m-1] rest is same as above
    time & space:
    * it takes m*n time and const space but it modifies the input
     */

    public int minPathSum(int[][] grid) {
        return minPathSumA5(grid);
    }

    public int minPathSumA5(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[] dp = grid[m-1];
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(i==m-1 && j==n-1)dp[j]=grid[i][j];
                else if(i==m-1)dp[j]=grid[i][j]+dp[j+1];
                else if(j==n-1)dp[j]=grid[i][j]+dp[j];
                else dp[j]=grid[i][j]+Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }

    public int minPathSumA4(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[] dp = new int[n];
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(i==m-1 && j==n-1)dp[j]=grid[i][j];
                else if(i==m-1)dp[j]=grid[i][j]+dp[j+1];
                else if(j==n-1)dp[j]=grid[i][j]+dp[j];
                else dp[j]=grid[i][j]+Math.min(dp[j],dp[j+1]);
            }
        }
        return dp[0];
    }

    public int minPathSumA3(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i=m-1; i>=0; i--){
            for(int j=n-1; j>=0; j--){
                if(i==m-1 && j==n-1)dp[i][j]=grid[i][j];
                else if(i==m-1)dp[i][j]=grid[i][j]+dp[i][j+1];
                else if(j==n-1)dp[i][j]=grid[i][j]+dp[i+1][j];
                else dp[i][j]=grid[i][j]+Math.min(dp[i+1][j],dp[i][j+1]);
            }
        }
        return dp[0][0];
    }

    Integer[][] cache;
    int m,n;

    public int minPathSumA2(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        cache = new Integer[m][n];
        return rec2(grid,0,0);
    }

    public int rec2(int[][] grid,int r,int c){
        if(r==m-1 && c==n-1)return grid[r][c];
        if(r==m || c == n)return Integer.MAX_VALUE;
        if(cache[r][c]!=null)return cache[r][c];
        cache[r][c]=grid[r][c]+Math.min(rec2(grid,r+1,c),rec2(grid,r,c+1));
        return cache[r][c];
    }


    public int minPathSumA1(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        return rec(grid,0,0);
    }

    public int rec(int[][] grid,int r,int c){
        if(r==m-1 && c==n-1)return grid[r][c];
        if(r==m || c == n)return Integer.MAX_VALUE;
        return grid[r][c]+Math.min(rec(grid,r+1,c),rec(grid,r,c+1));
    }
}
