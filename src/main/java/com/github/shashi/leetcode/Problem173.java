package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Problem173 {
    /*
    Binary Search Tree Iterator
    Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):

    BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of
    the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
    boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise
    returns false.
    int next() Moves the pointer to the right, then returns the number at the pointer.
    Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return
    the smallest element in the BST.

    You may assume that next() calls will always be valid. That is, there will be at least a next number in the
    in-order traversal when next() is called.
    Example 1:
    Input
    ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
    [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
    Output
    [null, 3, 7, true, 9, true, 15, true, 20, false]

    Explanation
    BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
    bSTIterator.next();    // return 3
    bSTIterator.next();    // return 7
    bSTIterator.hasNext(); // return True
    bSTIterator.next();    // return 9
    bSTIterator.hasNext(); // return True
    bSTIterator.next();    // return 15
    bSTIterator.hasNext(); // return True
    bSTIterator.next();    // return 20
    bSTIterator.hasNext(); // return False

    Constraints:
    The number of nodes in the tree is in the range [1, 105].
    0 <= Node.val <= 106
    At most 105 calls will be made to hasNext, and next.
    Follow up:
    Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory,
    where h is the height of the tree?

    Approach 1: doing the inorder traversal iterative
    * intuition is to modify the traditional inorder traversal to traverse next step when we call next method
    algo:
    * initialize cur= root, and empty stack in the constructor
    * in next method, check if either cur != null and stack is not empty if
    * iterate until cur is not null and add to stack
    * pop the top element in the stack consider its value, then make cur=cur.right
    * return the value
    * in has next method check if cur !=null or stack is not empty
    time & space:
    * takes ammortized 1 time and h space


    Approach 2:
    *intuition is to do the recursion and store the result in list then use the list to immetate iterator
    algo:
    * initialize the arrayList and index as -1
    * in constructor call recursive inorder traversal
    * in revursive inorder we check if root is null if so return
    * else we traverse the rec on root.left
    * then visit the cur node and then we call rec on root.right
    * in next we return array.get(++index)
    * in hasnext we check if index+1<array.size()
    time & space:
    * takes n space and 1 ammortized time for next and hasnext
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

    class BSTIteratorA2 {
        int index;
        List<Integer> list;

        public BSTIteratorA2(TreeNode root) {
            index = -1;
            list = new ArrayList<>();
            rec(root);
        }

        private void rec(TreeNode root){
            if(root==null)return;
            rec(root.left);
            list.add(root.val);
            rec(root.right);
        }

        public int next() {
            return list.get(++index);
        }

        public boolean hasNext() {
            return index+1 < list.size();
        }
    }
    class BSTIteratorA1 {
        TreeNode cur;
        Stack<TreeNode> stack;

        public BSTIteratorA1(TreeNode root) {
            cur = root;
            stack = new Stack<>();
        }

        public int next() {
            int val = -1;
            if(cur !=null || !stack.isEmpty()){
                while(cur !=null){
                    stack.push(cur);
                    cur= cur.left;
                }
                cur = stack.pop();
                val = cur.val;
                cur = cur.right;
            }
            return val;
        }

        public boolean hasNext() {
            return cur !=null || !stack.isEmpty();
        }
    }
}
