package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem230 {
    /*
    Kth Smallest Element in a BST
    Given the root of a binary search tree, and an integer k, return the kth smallest value (1-indexed) of all the values
    of the nodes in the tree.
    Example 1:
    Input: root = [3,1,4,null,2], k = 1
    Output: 1
    Example 2:
    Input: root = [5,3,6,2,4,null,null,1], k = 3
    Output: 3
    Constraints:
    The number of nodes in the tree is n.
    1 <= k <= n <= 104
    0 <= Node.val <= 104

    Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth
    smallest frequently, how would you optimize?

    approach 1: recursion with array
    * intuition is to traverse in recursive inorder way and add to list and get the kth element by index
    algo:
    * initialize arrayList of integers call recursion with root, list
    * base case for recursion is to check if root is null then return,
    * call recursion on root.left along with list
    * add root.val to list then call recursion on root.right
    * once recursion completes from main method retunr list.ge(k-1)
    time & space:
    * n time & n space to store all elements

    approach 2: iterative inorder
    * intuition is to traverse the tree iteratively by dec k and return the value when k=0
    algo:
    * initialize empty stack of treeNodes iterate until stack is empty or root is not null
    * check if root is not null if so add to stack and make root = root.left
    * else pop top node from stack, dec k, if k is 0 return root.val else make root=root.right
    * return -1 at the end
    time & space:
    * h+k time and h space

    follow up: without optimization the time for inset and getting the kth smallest is 2h+k if we need
    to optimize then we need have datastructure consisting of BST plus the DLL, dll will store the elements
    in the ascending order so that we can insert and get the element in h+k time

    Approach 3: moris inorder traversal
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

    public int kthSmallest(TreeNode root, int k) {
        return kthSmallestA2(root,k);
    }

    public int kthSmallestA3(TreeNode root, int k) {
        while(root!=null){
            if(root.left==null){
                k--;
                if(k==0)return root.val;
                root=root.right;
            }else{
                TreeNode left=root.left;
                while(left.right!=null && left.right!=root)
                    left=left.right;
                if(left.right==null){
                    left.right=root;
                    root=root.left;
                }else{
                    k--;
                    if(k==0)return root.val;
                    root=root.right;
                    left.right=null;
                }
            }
        }
        return 0;
    }

    public int kthSmallestA2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                k--;
                if(k==0)return root.val;
                root = root.right;
            }
        }
        return -1;
    }

    public int kthSmallestA1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        rec(root,list);
        return list.get(k-1);
    }

    public void rec(TreeNode root, List<Integer> list){
        if(root==null)return;
        rec(root.left,list);
        list.add(root.val);
        rec(root.right,list);
    }
}
