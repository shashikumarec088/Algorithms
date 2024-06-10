package com.github.shashi.leetcode;

public class Problem427 {
    /*
    Construct Quad Tree
    Given a n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
    Return the root of the Quad-Tree representing grid.
    A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has
    two attributes:
    val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can
    assign the val to True or False when isLeaf is False, and both are accepted in the answer.
    isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;
    }
    We can construct a Quad-Tree from a two-dimensional area using the following steps:
    If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid
    and set the four children to Null and stop.If the current grid has different values, set isLeaf to False and set val
    to any value and divide the current grid into four sub-grids as shown in the photo. Recurse for each of the children
    with the proper sub-grid. If you want to know more about the Quad-Tree, you can refer to the wiki.
    Quad-Tree format:
    You don't need to read this section for solving the problem. This is only if you want to understand the output format
    here. The output represents the serialized format of a Quad-Tree using level order traversal, where null signifies a
    path terminator where no node exists below.
    It is very similar to the serialization of the binary tree. The only difference is that the node is represented as a
    list [isLeaf, val].
    If the value of isLeaf or val is True we represent it as 1 in the list [isLeaf, val] and if the value of isLeaf or
    val is False we represent it as 0.
    Example 1:
    Input: grid = [[0,1],[1,0]]
    Output: [[0,1],[1,0],[1,1],[1,1],[1,0]]
    Explanation: The explanation of this example is shown below:
    Notice that 0 represents False and 1 represents True in the photo representing the Quad-Tree.
    Example 2:

    Input: grid = [[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,1],[1,1,1,1,0,0,0,0],
    [1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0],[1,1,1,1,0,0,0,0]]
    Output: [[0,1],[1,1],[0,1],[1,1],[1,0],null,null,null,null,[1,0],[1,0],[1,1],[1,1]]
    Explanation: All values in the grid are not the same. We divide the grid into four sub-grids.
    The topLeft, bottomLeft and bottomRight each has the same value.
    The topRight have different values so we divide it into 4 sub-grids where each has the same value.
    Explanation is shown in the photo below:
    Constraints:
    n == grid.length == grid[i].length
    n == 2x where 0 <= x <= 6

    Approach 1: recursion
    * Intuition is to perform what is asked in the problem. From the problem it states that the grid is devided
    into 4 subparts again and again which indicates recursive nature of the problem,
    matrix can be represented with two coordinates and length, using this information we can find all corners of
    the square, if all elements in grid are same then it is the leaf node in that case we can create a node with
    leaf set to true and return. if not we need to create the root node and recursively build the tree for all
    the 4 subparts of the tree.
    algo:
    * iterate over the grid and check if all elements are same if so crate the node with value set to true if
    grid has 1s else false and leaf set to true and return the node.
    * if not then create the root node with value false and leaf false
    * call recursion to get the topLeft with x1,y1 and length l/2
    * call recursion to get the topRight with x1, y1+l/2 and length l/2
    * call recursion to get the bottomLeft with x1+l/2,y1 and length l/2
    * call recursion to get the bottomRight with x1+l/2, y1+l/2 and length l/2;
    * return root at the end
    * in isSame we iterate from i=x1 to x1+l and j=y1 to y1+l, if grid[i][j] != grid[x1][y1] then we return false
    * we return true at the end indicating all the values are same
    time & space:
    * at each level of recursion we visit N^2 nodes and this happens at logn level so over all time complexity is
      n^2 logn and space is log n for extra stack, we did not count the space for tree

    Approach 2: recursion with optimization
    * in the approach 1 we check the values of grid repeatedly at each level which is making time as n^2 log n. This
    can be reduced by first making the recursive calls for all 4 children till length reaches 1 and then based on
    the value of the child nodes we can decide if we need to consider these as child nodes or should be combined
    as one left node. this depends on value returned by leaf nodes, if all children are leaf and have same value
    then we can return the leaf node with same value else we return the node with all 4 children. In this approach
    only once we check for each cell.
    algo:
    * if length is 1 then we return node with leaf set to true and value set to true if value at grid[x1][y1] is true
    else false
    * we recursively call the function with updated values of x1, y1 to get 4 children
    * topLeft = rec(x1,y1,l/2) , topRight = rec(x1,y1+l/2,l/2) bottomLeft = rec(x1+l/2,y1,l/2),
    bottomRight = rec(x1+l/2,y1+l/2,l/2)
    * if all children have same value and all are leaf nodes then create the new node with leaf set to true and
    value same as children value and return
    * else create a non leaf node with all four children considered and return it.
    time & space:
    * it takes N^2 time and log n  space
     */

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public Node construct(int[][] grid) {
        return constructA2(grid);
    }

    public Node constructA2(int[][] grid) {
        return rec2(grid,0,0,grid.length);
    }

    public Node rec2(int[][] grid, int x1, int y1, int l){
        if(l==1)
            return new Node(grid[x1][y1]==1,true);
        Node topLeft = rec2(grid,x1,y1,l/2);
        Node topRight = rec2(grid,x1,y1+l/2,l/2);
        Node bottomLeft = rec2(grid,x1+l/2,y1,l/2);
        Node bottomRight = rec2(grid,x1+l/2,y1+l/2,l/2);
        if(topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val &&
                bottomLeft.val == bottomRight.val){
            return new Node(topLeft.val,true);
        }
        return new Node(false,false,topLeft,topRight,bottomLeft,bottomRight);
    }

    public Node constructA1(int[][] grid) {
        return rec(grid,0,0,grid.length);
    }

    public Node rec(int[][] grid, int x1, int y1, int l){
        if(isSame   (grid,x1,y1,l))
            return new Node(grid[x1][y1]==1,true);
        Node root = new Node(false,false);
        root.topLeft = rec(grid,x1,y1,l/2);
        root.topRight = rec(grid,x1,y1+l/2,l/2);
        root.bottomLeft = rec(grid,x1+l/2,y1,l/2);
        root.bottomRight = rec(grid,x1+l/2,y1+l/2,l/2);
        return root;
    }

    public boolean isSame(int[][] grid, int x1, int y1, int l){
        for(int i=x1; i<x1+l;i++)
            for(int j=y1; j<y1+l; j++)
                if(grid[i][j]!=grid[x1][y1])return false;
        return true;
    }
}
