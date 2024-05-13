package com.github.shashi.leetcode;

import java.util.Stack;

public class Problem129 {
    /*
    Sum Root to Leaf Numbers
    You are given the root of a binary tree containing digits from 0 to 9 only.

    Each root-to-leaf path in the tree represents a number.

    For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
    Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.

    A leaf node is a node with no children.
    Example 1:
    Input: root = [1,2,3]
    Output: 25
    Explanation:
    The root-to-leaf path 1->2 represents the number 12.
    The root-to-leaf path 1->3 represents the number 13.
    Therefore, sum = 12 + 13 = 25.
    Example 2:
    Input: root = [4,9,0,5,1]
    Output: 1026
    Explanation:
    The root-to-leaf path 4->9->5 represents the number 495.
    The root-to-leaf path 4->9->1 represents the number 491.
    The root-to-leaf path 4->0 represents the number 40.
    Therefore, sum = 495 + 491 + 40 = 1026.
    Constraints:
    The number of nodes in the tree is in the range [1, 1000].
    0 <= Node.val <= 9
    The depth of the tree will not exceed 10.

    Approach 1: iterative pre order traversal
    * intuition is to traverse the tree in preorder manner and add the value to sum when node has no children
    algo:
    * create 2 stacks nodeStack of type TreeNode and valStack to hold values
    * initialize sum=0, push root to nodeStack and 0 to valStack
    * iterate until stack is empty
    * get node by poping nodeStack and val from valStack
    * calculate the latest val = 10*val + root.val
    * if root has no children add val to sum
    * if root has right child add the child and val to stacks
    * if root has left child add the child and val to stacks
    * return sum at the end
    time & space:
    * takes n time and n space

    Approach 2: recursion
    * intuition is to do the preorder recursion and add the value when root has no children
    algo:
    * in recursion pass root, int val defaults to 0
    * if root is null then return 0
    * compute val = val * 10+root.val
    * if root has no children then return val
    * call rec with root.left and val
    * call rec with root.right and val
    * return the sum of 2 recursion calls
    time & space:
    * takes n time and n space
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
    public int sumNumbers(TreeNode root) {
        return sumNumbersA1(root);
    }

    int total=0;
    public int sumNumbersA2(TreeNode root) {
        return rec(root,0);
    }

    public int rec(TreeNode root,int val){
        if(root==null)return 0 ;
        val = val*10+root.val;
        if(root.left==null && root.right==null)return val;
        return rec(root.left,val) + rec(root.right,val);
    }

    public int sumNumbersA1(TreeNode root) {
        if(root==null)return 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();
        nodeStack.push(root);
        valStack.push(0);
        int sum=0;
        while(!nodeStack.isEmpty()){
            TreeNode node = nodeStack.pop();
            int val =valStack.pop();
            val = val*10 + node.val;
            if(node.left==null && node.right==null)
                sum += val;
            if(node.right!=null){
                nodeStack.push(node.right);
                valStack.push(val);
            }
            if(node.left!=null){
                nodeStack.push(node.left);
                valStack.push(val);
            }
        }
        return sum;
    }
}
