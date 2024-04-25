package com.github.shashi.leetcode;
import java.util.*;
public class Problem98 {
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
        return isValidBSTA4(root);
    }

    /*
        intuition is to use the inorder traversal, for each node
        in inorder traversal we visit left node, then current then right
        we can use the iterative inorder traversal to traverse the tree
        and keep updating the previous value and compare current element
        value with the previous element value
    */
    public boolean isValidBSTA4(TreeNode root){
        if(root == null)return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root,prev1=null;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(prev1!=null && cur.val<=prev1.val)
                return false;
            prev1 = cur;
            cur = cur.right;
        }
        return true;
    }

    /*
       intuition is to use the recursive in order traversal
       the main thing is to update the prev value and
       when current value is less then prev return false,
       if left subtree is not valid then return false,
       do the same for right subtree
   */
    public boolean isValidBSTA3(TreeNode root){
        if(root == null) return true;
        if(!isValidBSTA3(root.left))return false;
        if(prev!= null && root.val<=prev.val) return false;
        prev = root;
        return isValidBSTA3(root.right);
    }


    class Record{
        Integer left;
        Integer right;
        TreeNode root;
        Record(Integer left,TreeNode root, Integer right){
            this.left = left;
            this.right = right;
            this.root = root;
        }
    }


    /*
         intuition is to check if left and right sub tree are bst
         and if value at current element > left bound and < right bound;
         at each element when traversing left subtree we update the right
         bound to current element value since all the elements in left subtree
         should be less than root values, similarly when traversing the right
         sub tree we update the left bound value since all the elements in the
         right subtree should be greater than the root element value
     */
    public boolean isValidBSTA1(TreeNode root){
        return rec(null,root,null);
    }

    public boolean rec(Integer left, TreeNode root, Integer right){
        if(root == null) return true;
        boolean lc = left == null?true: root.val>left;
        boolean rc = right == null?true: root.val<right;
        return lc && rc && rec(left,root.left,root.val)
                && rec(root.val, root.right, right);
    }
}
