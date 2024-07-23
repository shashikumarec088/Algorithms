package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem530 {
    /*
    Minimum Absolute Difference in BST
    Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two
    different nodes in the tree.

    Example 1:
    Input: root = [4,2,6,1,3]
    Output: 1
    Example 2:
    Input: root = [1,0,48,null,null,12,49]
    Output: 1
    Constraints:
    The number of nodes in the tree is in the range [2, 104].
    0 <= Node.val <= 105

    Approach 1: iterative inorder traversal
    * intuition is to use the iterative inorder traversal and then at each iteration find the difference between the
    previous value and the current value and update the difference if it is lesser then current min
    algo:
    * init prev=null cur=root and empty stack of tree node and Integer min=null
    * iterate until either cur is not null or stack is not empty
    * if cur is not null add to stack and make cur=cur.left
    * else pop top element to cur and check if prev is not null, if so get the absolute difference and compare
    with min if it is lesser then update
    * make prev=cur and cur = cur.right and continue
    * return min at the end
    time & space:
    * n time and 1 space

    Approach 2: recursion
    * intuition is to do inorder recursion and update min whenever we visit the node
    algo:
    * have global variables for prev and min call recursion on root
    * if root is null then return
    * call recursion on root.left
    * if prev is not null then update min with difference between prev and cur when difference is lesser than min or
    when min is null
    * call recursion on root.right
    * return min from main method where we called recursion
    time & space:
    * takes n time and n space for recursion stack

    Approach 3: moris inorder traversal
    * intuition is to use the moris algo to traverse the tree and track the prev when we visit the node and update
    the abs min values
    algo:
    * int min= integer.max_value, prev=null iterate until root is not null,
    * if root.left is null then if prev is not null make min = min(min,abs(prev.val-cur.val)) make prev=root
    * make root= root.right
    * else make left = root.left and iterate until left.right is null or equal to root
    * if left.right is null then connect to root and make roo = root.left
    * else make left.right = null, prev is not null then make min = min(min,abs(prev.val-cur.val))
    *  make prev=root, root=root.right
    * return min at end
    time & space:
    * const space and n time
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

    public int getMinimumDifference(TreeNode root) {
        return getMinimumDifferenceA2(root);
    }

    Integer min=null;
    TreeNode prev=null;
    public int getMinimumDifferenceA2(TreeNode root) {
        rec(root);
        return min;
    }

    public void rec(TreeNode root){
        if(root==null)return;
        rec(root.left);
        if(prev !=null){
            int diff = Math.abs(prev.val-root.val);
            min= min==null?diff: Math.min(min,diff);
        }
        prev=root;
        rec(root.right);
    }

    public int getMinimumDifferenceA1(TreeNode root) {
        TreeNode cur=root,prev=null;
        Stack<TreeNode> stack = new Stack<>();
        Integer min=null;
        while(cur!=null || !stack.isEmpty()){
            if(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                if(prev !=null){
                    int diff = Math.abs(prev.val-cur.val);
                    min= min==null?diff: Math.min(min,diff);
                }
                prev = cur;
                cur = cur.right;
            }
        }
        return min;
    }

    public int getMinimumDifferenceA3(TreeNode root) {
        TreeNode prev=null,cur=root;
        int min = Integer.MAX_VALUE;
        while(cur!=null){
            if(cur.left ==null){
                if(prev!=null)
                    min = Math.min(Math.abs(prev.val-cur.val),min);
                prev = cur;
                cur = cur.right;
            }else{
                TreeNode left = cur.left;
                while(left.right!=null && left.right!=cur)
                    left = left.right;
                if(left.right == null){
                    left.right=cur;
                    cur = cur.left;
                }else{
                    left.right = null;
                    if(prev!=null)
                        min = Math.min(Math.abs(prev.val-cur.val),min);
                    prev=cur;
                    cur = cur.right;
                }
            }
        }
        return min;
    }
}
