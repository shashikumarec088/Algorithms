package com.github.shashi.leetcode;

public class Problem222 {
    /*
    Count Complete Tree Nodes
    Given the root of a complete binary tree, return the number of the nodes in the tree.

    According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
    and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at
    the last level h.

    Design an algorithm that runs in less than O(n) time complexity.
    Example 1:
    Input: root = [1,2,3,4,5,6]
    Output: 6
    Example 2:
    Input: root = []
    Output: 0
    Example 3:
    Input: root = [1]
    Output: 1
    Constraints:

    The number of nodes in the tree is in the range [0, 5 * 104].
    0 <= Node.val <= 5 * 104
    The tree is guaranteed to be complete.

    Approach 1: recursion
    * intuition is to count the nodes with recursion
    algo:
    * check if root is null if so return 0
    * else return 1 + rec(root.left)+rec(root.right)
    time & space:
    * takes n time and n space for recursion

    Approach 2: binary search
    * since given tree is BST that means all the levels will be complete and the last level will be filled from left
    to right
    * we can find the depth and total nodes will be 2^d -1 + nodes in last level, since last level if filled from
    left to right we can use binary search to find the nodes in last level
    algo:
    * to find out d init d=0, cur = root iterate until cur has left and inc d
    * last level can have between 1 to 2^d nodes, indexed from l=0, r=2^d-1
    * do binary search to find the values at last level
    * mid = l+(r-l)/2
    * check if node exists at mid if so make l=mid+1 else r = mid-1
    * at the end return 2^d-1+ l nodes (here after binary search l will have 1 greater than node position ie if
    node present till 0th position then l will be 1 after binary search, even if we are initializing l with 0
    after binary search its value will be > 1 since it is guarenteed that atleat 1 node will be there at the
    last level)
    * to check if node exists it takes mid, root, d
    * we initialize l=0, r = 2^d-1, we iterate until the last level to get the node at given position
    * iterate from i=0 to i<d
    * find m = l+(r-l)/2
    * if mid <= m then we make r=m (node will be from l till m including m because we are checking <=)
    * and we move root one level down to left side since mid is within first half of the tree
    * if mid>m then we male l=m+1 and we move root to one level down towards right since mid lies in
    the second half of tree.
    * once we reached the last level we check if root is null if so then node does not exists at mid
    * else node exists
    time & space:
    * it takes d time to find the position of last node in last layer and at each step it takes d
    time to verify if node exists or not so overall d^2 time it takes
    * takes constant space

     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int countNodes(TreeNode root) {
        return countNodesA2(root);
    }

    public int countNodesA2(TreeNode root) {
        if(root==null)return 0;
        if(root.left==null) return 1;
        int d=0;
        TreeNode cur = root;
        while(cur.left!=null){
            d++;
            cur=cur.left;
        }
        int l=0, r = (int)Math.pow(2,d) -1;
        while(l<=r){
            int m = l+(r-l)/2;
            if(exists(m,root,d))l=m+1;
            else r = m-1;
        }
        return (int)Math.pow(2,d) -1 +l;
    }

    private boolean exists(int m, TreeNode root, int d){
        int l=0, r=(int)Math.pow(2,d)-1;
        for(int i=0; i<d;i++){
            int mid = l+(r-l)/2;
            if(m<=mid){
                root = root.left;
                r=mid;
            }else{
                root=root.right;
                l=mid+1;
            }
        }
        return root !=null;
    }

    public int countNodesA1(TreeNode root) {
        return root==null?0:(
                countNodesA1(root.left)+countNodesA1(root.right)+1
        );
    }
}
