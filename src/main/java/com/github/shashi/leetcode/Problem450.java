package com.github.shashi.leetcode;

public class Problem450 {
    /*
    Delete Node in a BST
    Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node
    reference (possibly updated) of the BST.
    Basically, the deletion can be divided into two stages:
    Search for a node to remove.
    If the node is found, delete the node.
    Example 1:
    Input: root = [5,3,6,2,4,null,7], key = 3
    Output: [5,4,6,2,null,null,7]
    Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
    One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
    Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
    Example 2:
    Input: root = [5,3,6,2,4,null,7], key = 0
    Output: [5,3,6,2,4,null,7]
    Explanation: The tree does not contain a node with value = 0.
    Example 3:
    Input: root = [], key = 0
    Output: []
    Constraints:

    The number of nodes in the tree is in the range [0, 104].
    -105 <= Node.val <= 105
    Each node has a unique value.
    root is a valid binary search tree.
    -105 <= key <= 105
    Follow up: Could you solve it with time complexity O(height of tree)?

    Approach 1: recursive solution
    * intuition is if the node to be deleted has left then we find the predecessor and copy its value to
    node and then we delete the predecessor else if node has right then we find successor and copy value
    to the node and deleter the successor
    algo:
    * in delete check if root is null  then return
    * if root val is < target then root.right = rec(root.right,target)
    * if root val is > target then root.left = rec(root.left,target)
    * if root is the value that need tobe deleted then
    * we check if it has no children then we make root as null
    * check if it has one child then return that child
    * if it has both child then find the successor by considering the right child and finding the
    leftmost child, we make its value as successor value and assign root.right = rec(successor,target)
    * return root at the end

    time & space:
    log n time and log n space in average

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

    public TreeNode deleteNode(TreeNode root, int key) {
        return deleteNodeA1(root,key);
    }

    public TreeNode deleteNodeA1(TreeNode root, int key) {
        if(root==null)return null;
        if(root.val < key){
            root.right = deleteNodeA1(root.right,key);
        }else if(root.val > key)
            root.left = deleteNodeA1(root.left,key);
        else{
            if(root.left==null && root.right==null)root=null;
            else if(root.left !=null){
                root.val = predecessor(root);
                root.left = deleteNodeA1(root.left,root.val);
            }else{
                root.val = successor(root);
                root.right = deleteNodeA1(root.right,root.val);
            }
        }
        return root;
    }

    private int predecessor(TreeNode root){
        root=root.left;
        while(root.right !=null)root=root.right;
        return root.val;
    }
    private int successor(TreeNode root){
        root=root.right;
        while(root.left !=null)root=root.left;
        return root.val;
    }
}
