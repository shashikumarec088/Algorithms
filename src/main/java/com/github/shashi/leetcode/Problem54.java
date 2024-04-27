package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem54 {
    /*
    Spiral Matrix
    Given an m x n matrix, return all elements of the matrix in spiral order.
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]

    Constraints:
        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100

    approach 1:
        * intuition is to travel along the metrics and adjust the bounds accordingly
        algo:
        * initially bounds are rs, re, cs and ce
        * iterate until rs <= re and cs <=ce
            * first process 1st row from cs to ce
            *  then last column from rs+1 (because we processed rs in previous row) to re
            * then process last row from ce-1 to cs desc (we do not consider ce because
            it is processed in previous column) Note: we should not process this row
            if rs and re are same else we end up processing twice the same row
            * finally process 1st column from re-1 to rs+1 desc (rs is ignored because it
            is considered when processing 1st row Note: we should not process this column
            if cs == ce else we end up processing the already processed column
            * done with last layer now adjust bounds rs++, re--, cs++, ce-- then continue
            with processing the inner layer

    approach 2:
        * intuition is to process the matrix spirally by starting at the first element
        * have the condition to terminate processing when there is 2 continuous direction changes
        which means we have visited all the elements
        * since we are moving along the matrix spirally  we need to change direction when we hit the
        bounds or when we see that the element is already visited, to track the visited elements,
        we can use the existing matrix by updating the element with constant 101
        (values will be within -100 to 100)
        * have the directions defined as [0,1][1,0][0,-1][-1,0] initially we are in east direction
        and initially no dir change
        * start the matrix traversal   first visit the 1st node, r=0, c=0 then start the traversal
        using while loop which checks on number of direction changes > 1
        * update the r and c based on direction values, then check if they are within bounds and not
        visited if so visit the element and reset the change direction since we are visited the element
        * if the bound is reached and change direction and inc change dir variable
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        return spiralOrderA1(matrix);
    }

    public List<Integer> spiralOrderA2(int[][] mat){
        int r=0, c=0,d=0, chdir=0, VISIT=101, m = mat.length,n=mat[0].length;
        List<Integer> res = new ArrayList<>();
        res.add(mat[r][c]);
        mat[r][c] = VISIT;
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        while(chdir<2){
            int nr = r + dir[d][0];
            int nc = c + dir[d][1];
            while(nr>=0 && nr<m && nc >=0 && nc < n &&
                    mat[nr][nc]!= VISIT){
                r = nr;
                c = nc;
                res.add(mat[r][c]);
                mat[r][c] = VISIT;
                nr = r + dir[d][0];
                nc = c + dir[d][1];
                chdir=0;
            }
            d = (d+1)%4;
            chdir++;
        }
        return res;
    }

    public List<Integer> spiralOrderA1(int[][] mat){
        int m = mat.length, n = mat[0].length;
        int rs=0, re=m-1, cs=0, ce=n-1;
        List<Integer> res = new ArrayList<>();
        while(rs<=re && cs <= ce){
            for(int i=cs; i<=ce; i++)
                res.add(mat[rs][i]);
            for(int i=rs+1; i<=re; i++)
                res.add(mat[i][ce]);
            if(rs != re){
                for(int i=ce-1; i>=cs; i--)
                    res.add(mat[re][i]);
            }
            if(cs != ce){
                for(int i=re-1; i>rs; i--)
                    res.add(mat[i][cs]);
            }
            rs++;
            cs++;
            re--;
            ce--;
        }
        return res;
    }


}
