package com.github.shashi.leetcode;

public class Problem63 {
    /*
    Unique Paths II
    You are given an m x n integer array grid. There is a robot initially located at the top-left corner
    (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    The robot can only move either down or right at any point in time.
    An obstacle and space are marked as 1 or 0 respectively in grid. A path that the robot takes cannot include
    any square that is an obstacle.
    Return the number of possible unique paths that the robot can take to reach the bottom-right corner.
    The testcases are generated so that the answer will be less than or equal to 2 * 109.
    Example 1:
    Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
    Output: 2
    Explanation: There is one obstacle in the middle of the 3x3 grid above.
    There are two ways to reach the bottom-right corner:
    1. Right -> Right -> Down -> Down
    2. Down -> Down -> Right -> Right
    Example 2:
    Input: obstacleGrid = [[0,1],[0,0]]
    Output: 1
    Constraints:
    m == obstacleGrid.length
    n == obstacleGrid[i].length
    1 <= m, n <= 100
    obstacleGrid[i][j] is 0 or 1.

    Approach 1: bf with recursion
    * intuition is to do what is asked for at any point the bot can move down and right which indicates that at
    any point we have 2 choices with to go down or to go right so unique paths at each position will be
    sum of the paths from down position and paths from right position.
    * this gives us with the relation for any position i,j = (i+1,j)+(i,j+1) this can be solved using the
    recursive approach
    * the base case is when we hit the boundaries or value at position is 1 which indicates their is an obstacle
    in that case we return 0
    * when we rech the bottom right and if value at position is 0 then we return 1 else 0 indicating if this
    path can be considered or not
    * at the end we call the recursion for 2 choices i+1,j and i,j+1 and return the sum.
    algo:
    * call recursion with grid,i,j,m,n.
    * check if i<0 or j<0 or i>=m or j>=n then return 0
    * check if grid[i][j]==1 is so return 0
    * check if i==m-1 and j==n-1 if so return 1 if grid[i][j]==0 else 1
    * return sum of rec(grid,i+1,j,m,n) and rec(grid,i,j+1,m,n)
    time & space:
    * time is 2^(m+n) and space is m+n which is depth of recursion stack

    Approach 2: recursion with memoization
    * intuition is same as approach 1 but in approach 1 we do lot of repeatetive calulations, which can be avoided
    by storing the results in the memo table.
    * at each step if the result is already present in the memo then return it else compute the result and store
    in the memo array and return the result.
    algo:
    * same as approach 1, init extra  2d global array of type Integer and size m,n
    * before calling the next recursions check if i,j is not null in memo if not return from memo
    * else call both recursions and store the summed result at memo of i,j then return result.
    time & space:
    * time m*n we visit each position once and m*n space for memo

    Approach 3: dp with tabulation
    * intuition is same as approach 2 but here we do it using tabulation and from top to bottom,
    we create the dp array of size m,n and iterate over the row and cols and if pos is 0,0
    then we set the value as 1 indicating it is reachable if row is 0 then we take the value from previous
    position in same row, if 0th column then we take the values from same position from previous row
    else it will be the sum of position above and before the current position ie i,j-1 and i-1,j
    algo:
    * m,n rows and cols, if value at 0,0 is 1 then return 0 indicating we cant proceed
    * create dp int array of size m,n
    * iterate i=0 to m and j=0 to n
    * if i,j==0 then set dp[i][j]=0
    * if value at i,j == 1 then set dp[i][j]=0
    * if i==0 then dp[i][j] = dp[i][j-1]
    * if j==0 then dp[i][j] = dp[i-1][j]
    * else dp[i][j] = dp[i][j-1]+dp[i-1][j]
    * return dp[m-1][n-1]
    time & space:
    * it takes mn time and space

    Approach 4: dp with 1d array
    * intuition is same approach 3 if we closely look at the problem if we know the state of previous row cur element
    and current row prev element then it is enough to get the cur position state sow now dp[i][j]=dp[i-1][j]+dp[i][j-1]
    becomes dp[j]=dp[j]+dp[j-1] where dp will be holding the prev row results when processing the cur row and also
    we start processing the results from left to right so we will have the current row results at prev position of
    current row.
    algo:
    * create dp array of type int and size n
    * rest of the logic same as approach 3 but in place of dp[i][j] we will have only j positions
    * when i=0,j=0 dp[j]=0, when i==0 dp[j]=dp[j-1], when j==0 then dp[j]=dp[j](prev row j position result)
    * else dp[j]=dp[j-1]+dp[j]
    * return dp[n-1] at the end.
    time & space:
    * mn time and n space

    Approach 5: dp with no extra space using the 1st row of input array
    * intuition and also space as approach 4 but dp = grid[0]
    time & space:
    * mn time and const space but it modifies the input
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        return uniquePathsWithObstaclesA5(obstacleGrid);
    }

    Integer[][] dp;
    public int uniquePathsWithObstaclesA2(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        dp = new Integer[m][n];
        return rec2(grid,0,0,m,n);
    }

    public int rec2(int[][] grid, int i, int j, int m, int n){
        if(i<0 || j<0 || i>=m || j>=n)return 0;
        if(grid[i][j]==1)return 0;
        if((i==(m-1)) && (j==(n-1)))return grid[i][j]==1?0:1;
        if(dp[i][j]!=null)return dp[i][j];
        dp[i][j]=rec2(grid,i+1,j,m,n)+rec2(grid,i,j+1,m,n);
        return dp[i][j];
    }

    public int uniquePathsWithObstaclesA1(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        return rec(grid,0,0,m,n);
    }

    public int rec(int[][] grid, int i, int j, int m, int n){
        if(i<0 || j<0 || i>=m || j>=n)return 0;
        if(grid[i][j]==1)return 0;
        if((i==(m-1)) && (j==(n-1)))return grid[i][j]==1?0:1;
        return rec(grid,i+1,j,m,n)+rec(grid,i,j+1,m,n);
    }

    public int uniquePathsWithObstaclesA3(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[][] dp = new int[m][n];
        if(grid[0][0]==1)return 0;
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                if(grid[i][j]==1)dp[i][j]=0;
                else if(i==0 && j==0)dp[i][j]=1;
                else if(i==0)dp[i][j]=dp[i][j-1];
                else if(j==0)dp[i][j]=dp[i-1][j];
                else dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public int uniquePathsWithObstaclesA4(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[] dp = new int[n];
        if(grid[0][0]==1)return 0;
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                if(grid[i][j]==1)dp[j]=0;
                else if(i==0 && j==0)dp[j]=1;
                else if(i==0)dp[j]=dp[j-1];
                else if(j==0)dp[j]=dp[j];
                else dp[j]=dp[j]+dp[j-1];
            }
        }
        return dp[n-1];
    }

    public int uniquePathsWithObstaclesA5(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[] dp = grid[0];
        if(grid[0][0]==1)return 0;
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                if(grid[i][j]==1)dp[j]=0;
                else if(i==0 && j==0)dp[j]=1;
                else if(i==0)dp[j]=dp[j-1];
                else if(j==0)dp[j]=dp[j];
                else dp[j]=dp[j]+dp[j-1];
            }
        }
        return dp[n-1];
    }

}
