package com.github.shashi.leetcode;

public class Problem221 {
    /*
    221. Maximal Square
    Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return
    its area.
    Example 1:
    Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
    Output: 4
    Example 2:
    Input: matrix = [["0","1"],["1","0"]]
    Output: 1
    Example 3:
    Input: matrix = [["0"]]
    Output: 0
    Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 300
    matrix[i][j] is '0' or '1'.

    Approach 1: brute force approach
    * intuition is considering all the possible squares starting from each cell and updating the max square length
    for each square that we find staring from that cell.
    algo:
    * init max =0, m is rows and n is cols
    * iterate over i=0 to m and j=0 to n, if cell has 1 at that position, then we start considering all the sq starting
    at that posision
    * to do that we init sqln=1 and isSqr=true
    * iterate until i+sqln is < m and j+sqln < n and isSqr is true
    * we iterate over the next row of cur row starting from k=j to j+sqln and validate if any cell is not 1
    if so then we make isSqr false
    * we iterate over the next col of cur col starting from k=i to i+sqln and validate if any cell is not 1
    if so then we make isSqr false
    * after that if isSqr is true then we inc sqln
    * once isSqr becomes false then we update the max with sqln if it is greater than max
    * return max*max at the end
    time & space:
    * it take m*n iterations and for each iteration we check m*n cells in worst case so total (m*n)^2 is the
    time and takes constant space

    Approach 2: 2D dp
    * intuition is that if we look at bf approach we are repeating the computations for each cell, instead
    if we can store the maxlengths of each cell before and above the cur cell then we can compute the maxlength
    of the cur cell, it dp[i][j]= min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1 when the cur cell is 1, this reduces
    the time complexity to mn
    algo:
    * init m rows, n cols max=0 and create dp int 2d array of size m+1, n+1
    * iterate from i=1 to i<=m and j=1; j<=n if i-1,j-1 is 1 then
    * dp[i][j] = min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])
    * update the max with dp[i][j] if it is > max
    * return max*max at the end
    time & space:
    * it takes mn time and mn space

    Approach 3: 1D dp
    * intuition is same approach 2 but if we look the relation we need only the prev value of current row
    and the prev element pf prev row and cur element of prev row, but when we are computing the current element
    value the prev element value needed for next element will be over written, if we hold this value in seperate
    variable then we can use 1d array to store the results.
    * this can be done by storing the prev row cur element value in temp variable and updating the prev variable
    with this value once we are done with using the prev variable for computing the current element
    algo:
    * init m, n as rows and cols and 1d dp array of size n+1, max=0,prev=0
    * iterate i=1 to <=m and j=1 to <=n make temp=dp[j]
    * if mat[i-1][j-1] ==1 then compute dp[j]=min(dp[j],dp[j-1],prev)
    * else make dp[j]=0
    * make prev=temp
    * update max with max of max,dp[j]
    * return max*max at the end
    time & space:
    * mn time and n space

    Approach 4: dp with recursion
    * intuition is same as approach 2, but using the recursion approach we define the global memo array of
    Integers of 2D. and we reach the end of the matrix and start building the solution from their till we
    reach the start of the matrix
    * we compute the max length of cur position by recursively knowing the length of next, down and diagonal
    elements
    * then updating the cur element length as min of those 3 plus 1 if cur position in matrix is 1
    algo:
    * init global variables for m,n, max,mat,memo where dp is 2d integer array of size m+1, n+1
    where m,n are rows and cols respectively
    * cal rec with 0,0
    * in rec first check the base case where i>=m or j>=n return 0
    * check if memo[i][j] is not null if not then return value from it
    * get down by calling rec with i+1,j, right with i,j+1 and diag with i+1,j+1
    * if the mat[i][j]==1 then update the memo[i][j] with min of down,right,diagonal plus 1 else 0
    * return memo[i][j]
    time & space:
    * it takes mn time and mn space
     */

    public int maximalSquare(char[][] matrix) {
        return maximalSquareA3(matrix);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
    }

    int m,n,max;
    Integer[][] memo;
    char[][] mat;
    public int maximalSquareA4(char[][] mat) {
        m=mat.length;
        n=mat[0].length;
        max=0;
        memo = new Integer[m+1][n+1];
        this.mat=mat;
        rec(0,0);
        return max*max;
    }

    public int rec(int i, int j){
        if(i>=m || j>=n)return 0;
        if(memo[i][j]!=null)return memo[i][j];
        int down = rec(i+1,j),right=rec(i,j+1),diag=rec(i+1,j+1);
        if(mat[i][j]=='1'){
            memo[i][j]=Math.min(Math.min(right,down),diag)+1;
            max=Math.max(max,memo[i][j]);
        }else memo[i][j]=0;
        return memo[i][j];
    }

    public int maximalSquareA3(char[][] mat) {
        int m=mat.length,n=mat[0].length,max=0,prev=0;
        int[] dp = new int[n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                int temp=dp[j];
                if(mat[i-1][j-1]=='1'){
                    dp[j]=Math.min(Math.min(dp[j-1],dp[j]),prev)+1;
                }else dp[j]=0;
                max = Math.max(dp[j],max);
                prev=temp;
            }
        }
        return max*max;
    }

    public int maximalSquareA2(char[][] mat) {
        int m=mat.length,n=mat[0].length,max=0;
        int[][] dp = new int[m+1][n+1];
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(mat[i-1][j-1]=='1'){
                    dp[i][j]=Math.min(Math.min(dp[i-1][j-1],dp[i-1][j]),dp[i][j-1])+1;
                }
                max = Math.max(dp[i][j],max);
            }
        }
        return max*max;
    }

    public int maximalSquareA1(char[][] mat) {
        int m=mat.length,n=mat[0].length,max=0;
        for(int i=0; i<m;i++){
            for(int j=0; j<n;j++){
                if(mat[i][j]=='1'){
                    int sqln=1;
                    boolean isSqr=true;
                    while((i+sqln)<m && (j+sqln)<n && isSqr){
                        for(int k=j; k<=j+sqln;k++){
                            if(mat[i+sqln][k]=='0')
                                isSqr=false;
                        }
                        for(int k=i; k<=i+sqln;k++){
                            if(mat[k][j+sqln]=='0')
                                isSqr=false;
                        }
                        if(isSqr)sqln++;
                    }
                    max=Math.max(sqln,max);
                }
            }
        }
        return max*max;
    }
}
