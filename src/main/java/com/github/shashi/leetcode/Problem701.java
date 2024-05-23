package com.github.shashi.leetcode;

public class Problem701 {
    /*
    Insert into a Binary Search Tree
    You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of
    the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

    Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion.
    You can return any of them.

    Example 1:
    Input: root = [4,2,7,1,3], val = 5
    Output: [4,2,7,1,3,5]
    Example 2:
    Input: root = [40,20,60,10,30,50,70], val = 25
    Output: [40,20,60,10,30,50,70,null,null,25]
    Example 3:
    Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
    Output: [4,2,7,1,3,5]
    Constraints:
    The number of nodes in the tree will be in the range [0, 104].
    -108 <= Node.val <= 108
    All the values Node.val are unique.
    -108 <= val <= 108
    It's guaranteed that val does not exist in the original BST.

    approach 1: recursion
    * intuition is to do the recursive binary search until the root becomes null then return the new node with val value
    else keep propagating towards left if root.val > val else right
    algo:
    * call rec with root and val, if root is null create a node with val and return it
    * if root.val > val make root.left = rec root.left,val
    * else make root.right = rec root.right,val
    * return root at the end
    time & space:
    * log n time and log n space

    approach 2: iterative
    * intuition is to keep searching until node is null, at each search we compare root.val with val if it is > val then
    we search left side else right side when no child is found we add the new node
    algo:
    * initialize node = root and iterate until node is null
    * if node.val > val then check if node.left is null if so make node.left = new node and return root
    * else check if node.right is null if so then make node.right = new node and return root
    * at the end we need to return new node this is for the case when root is null
    time & space:
    * log n time and const space
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

    public TreeNode insertIntoBST(TreeNode root, int val) {
        return insertIntoBSTA2(root,val);
    }

    public TreeNode insertIntoBSTA2(TreeNode root, int val) {
        TreeNode node = root;
        while(node!=null){
            if(node.val > val){
                if(node.left==null){
                    node.left = new TreeNode(val);
                    return root;
                }
                node = node.left;
            }else{
                if(node.right==null){
                    node.right = new TreeNode(val);
                    return root;
                }
                node = node.right;
            }
        }
        return new TreeNode(val);
    }

    public TreeNode insertIntoBSTA1(TreeNode root, int val) {
        if(root==null)return new TreeNode(val);
        if(root.val<val)root.right=insertIntoBSTA1(root.right,val);
        else root.left=insertIntoBSTA1(root.left,val);
        return root;
    }
}
