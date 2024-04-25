package com.github.shashi.leetcode;

public class Problem48 {
    /*

        Rotate Image
        You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees
        (clockwise).

        You have to rotate the image in-place, which means you have to modify the input 2D matrix
        directly. DO NOT allocate another 2D matrix and do the rotation.

        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [[7,4,1],[8,5,2],[9,6,3]]

        Constraints:
            n == matrix.length == matrix[i].length
            1 <= n <= 20
            -1000 <= matrix[i][j] <= 1000


        Approach 1:
        * intuition is to rotate the matrix clock wise we need to swap the rows and then we need to
        swap the symmetric elements.
        * for swapping the rows have i=0, j=m-1 then swap rows and inc i and dec j
        * for swapping the symmetric element we need iterate for element above diagonal that is
        from i=0 to m and j=i+1 to n and swap i,j with j,i this will make sure all elements are
        swapped

        Approach 2:
        * intuition is to rotate the layer by layer, with in layer we rotate all elements then we move
        inwords
        * there will be total n/2 layers. in each layer the first element will be layer and last
        element will be n - layer - 1.
        * we need to rotate total i=layer to n-layer - 1, ex if n = 3 then i=0,1 since 2nd element will
        be swapped with 0th element.
        * we need to replace the 1st element in the row by the last element from the 1st column
        and 2nd element in the row by 2nd element from the last from the 1st column
        * similarly we need to replace the last element in 1st column from last element from the
        last row, 2nd element from last of 1st column with the 2nd element from the last of the last row
        * like wise for last element of the last column with first element from last column,
        2nd last element with the 2nd element from last column
        * similarly for last element of last column with last element of first row,
        2nd last element of last column with 2nd element from last of the 1st row.

        * observations, we have offset = i - first;
        keep the first element in temp variable temp = mat[first][i]
        mat[first][i] = mat[last-offset][first]
        mat[last-offset][first] = mat[last][last-offset]
        mat[last][last-offset] = mat[i][last];
        mat[i][last] = temp;

     */
    public void rotate(int[][] matrix) {
        rotateA2(matrix);
    }

    /*
        intuition is to rotate the matrix layer by layer
        there will be n/2 layers after each layer number of
        rows and columns reduces by 2 to form a ring, within
        each layer we start from first element replace it
        with the right element repeat it 4 time then for
        all elements in row
    */
    public void rotateA2(int[][] mat){
        int n = mat.length;
        // there will be n/2 layers in sq matrix
        for(int layer=0; layer < n/2; layer++){
            // in each layer we start with layer row and column element
            // last element will be out of n we need to remove layer
            // since indexes are 0 based so we remove 1 hence n - layer - 1
            int first = layer;
            int last = n-layer-1;
            // within layer we need to swap last -1 elements, we no need
            //to swap the last element since it gets swapped with first
            for(int i= first; i< last; i++){
                // this captures the shift from first position
                // when layer is 1 first positon is [1,1], but offset is 0
                // second position is [1,2] offset is 1 this is needed
                // when we are swapping the 2, 3 or next elements, for
                // first element the offset is 0, it increases as i increases
                int offset = i - first;
                // retain the first element
                int temp = mat[first][i];
                // first element is first row will be swapped with
                // last element in the first column here for i=0,
                // when i is 1 we swap the second element with the
                // second element from last position of row in first column
                // remember for first row we always swap elements from first
                // column and as colun indices increases the row position from
                // which we swap will reduce ex when n=4 [0,0]<-[3,0],[0,1]<-[2,0]
                mat[first][i] = mat[last-offset][first];
                // for first column we always swap from last row and as we
                // reduce the row value the column from which we swap also we reduce
                // ex n = 4, [3,0]<-[3,3],[2,0]<-[3,2],[1,0]<-[3,1]
                mat[last-offset][first] = mat[last][last-offset];
                // for the last row we always swap from last column here as
                // the column value reduces the row values increses by i
                // note the difference here for 1st row and last column
                // the indices values increases along with i so we use i directly
                mat[last][last-offset] = mat[i][last];
                // for the last column values will be swapped from first row
                // we retained the value in temp which is copied now,
                // as mentioned before for last column positions increase along
                // with i so we use i directly without offsets,
                mat[i][last] = temp;
            }
        }
    }

    /*
        intuition for matrix rotation in clock wise
        is to swap the rows and then swap the  symmetric elements,
        for anti clockwise swap the columns and swap symmetric elements
    */
    public void rotateA1(int[][] mat) {
        int n = mat.length;
        int i=0, j=n-1;
        // swap the rows for clockwise rotation, then elements above diagonals
        // for anti clockwise swap columns then elements above diagonals
        while(i<j){
            for(int k= 0; k<n; k++){
                int temp = mat[i][k];
                mat[i][k] = mat[j][k];
                mat[j][k] = temp;
            }
            i++;
            j--;
        }

        for(int r=0; r<n; r++){
            for(int c=r+1; c<n; c++){
                int temp = mat[r][c];
                mat[r][c] = mat[c][r];
                mat[c][r] = temp;
            }
        }
    }
}
