package com.github.shashi.leetcode;
import java.util.*;
public class Problem98 {
    /*
    Validate Binary Search Tree
    Given the root of a binary tree, determine if it is a valid binary search tree (BST).
    A valid BST is defined as follows:
     of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.

    Example 1:
    Input: root = [2,1,3]
    Output: true
    Example 2:
    Input: root = [5,1,4,null,null,3,6]
    Output: false
    Explanation: The root node's value is 5 but its right child's value is 4.
    Constraints:
    The number of nodes in the tree is in the range [1, 104].
    -231 <= Node.val <= 231 - 1

    Approach 1: recursion
    * intuition is to compare the current node value with left and right bound if it is within the limit then
    we recursively call the same method on root.left by updating the right bound and we call on root.right by
    updating the left bound
    algo:
    * call the recursive function validate with left and right bounds as null
    * if root is null then return true
    * check if left bound is not null and root.val <= left bound then return false since it does not meet the constraints
    * check if right bound is not null and root.val >= right bound then return false
    * call validate with right bound = root.val and call validate with left bound=root.val
    time & space:
    * takes n time & n space for recursion stack

    Approach 2: iterative inorder traversal
    * intuition is to use the iterative inorder traversal and compare the each visiting node with the orevious visited
    node value if it is <= prev value then it is not the bst since in bst inorder traversal should be in sorted order
    algo:
    * initialize prev=null, cur=root, empty stack of type treeNode
    * iterate until cur is not null or stack is not empty
    * if cur is not null add to stack and make cur = cur.left
    * if cur is null then pop the top element from stack, if prev is not null then check if cur.val <= prev.val
    if so return false
    * make prev = cur and cur=cur.right
    * repeat until stak is empty and cur is null
    * return true at the end
    time & space:
    * n time and n space

    Approach 3: Moris inorder traversal
    * intuition is to use the moris inorder traversal to do the inorder traversal without using the stack
    algo:
    * initialize prev=null, cur=root
    * iterate until cur is not null
    * if cur.left is not null then find the inorder predecessor of cur by finding the rightmost node in the left subtree
    * if rightmost node is not null and rightmost.right is null then make rightmost.right=cur and cur=cur.left
    * else if rightmost.right is cur then make rightmost.right=null, if prev is not null and cur.val <= prev.val return false
    * make prev=cur and cur=cur.right
    * repeat until cur is null
    * return true at the end
    time & space:
    * n time and const space

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
    TreeNode prev;
    public boolean isValidBST(TreeNode root) {
        return isValidBSTA1(root);
    }

    public boolean isValidBSTA1(TreeNode root) {
        return rec(null,root,null);
    }

    public boolean isValidBSTA2(TreeNode root) {
        TreeNode cur=root,prev=null;
        Stack<TreeNode> stack = new Stack<>();
        while(cur!=null|| !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur= cur.left;
            }else{
                cur = stack.pop();
                if(prev!=null && cur.val<=prev.val)return false;
                prev = cur;
                cur=cur.right;
            }
        }
        return true;
    }

    public boolean isValidBSTA3(TreeNode root) {
        Integer prev=null;
        while(root!=null){
            if(root.left==null){
                if(prev!=null && root.val <= prev)return false;
                prev = root.val;
                root=root.right;
            }else{
                TreeNode left = root.left;
                while(left.right!=null && left.right!=root)
                    left=left.right;
                if(left.right==null){
                    left.right=root;
                    root=root.left;
                }else{
                    left.right=null;
                    if(prev!=null && root.val <= prev)return false;
                    prev = root.val;
                    root=root.right;
                }
            }
        }
        return true;
    }

    public boolean rec(Integer l, TreeNode root,Integer r){
        if(root==null)return true;
        if(l!=null && root.val <= l)return false;
        if(r != null && root.val >= r)return false;
        return rec(l,root.left,root.val) && rec(root.val,root.right,r);
    }
}
