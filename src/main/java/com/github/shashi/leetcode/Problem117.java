package com.github.shashi.leetcode;

public class Problem117 {
    /*
        Populating Next Right Pointers in Each Node II
    Given a binary tree

    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
    Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should
    be set to NULL.

    Initially, all next pointers are set to NULL.
    Example 1:
    Input: root = [1,2,3,4,5,null,7]
    Output: [1,#,2,3,#,4,5,7,#]
    Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its
    next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers,
    with '#' signifying the end of each level.
    Example 2:

    Input: root = []
    Output: []
    Constraints:
    The number of nodes in the tree is in the range [0, 6000].
    -100 <= Node.val <= 100

    Follow-up:
    You may only use constant extra space.
    The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.

    Approach 1: BFS
    * intuition is to connect the child nodes of current level and move to the next level. start from root,
    connect its children then move current level first child.
    algo:
    * initialize node=root, traverse until the node is null
    * at each iteration, make cur=node and prev= null, traverse the cur level until it is null
    * check if cur has left then
        -> if prev exists then make prev.next = cur.left;
        -> make prev = cur.left;
    * check if cur has right then
        -> if prev exists then make prev.next = cur.right
        -> make prev = cur.right;
    * make cur = cur.next
    * once processed all the nodes in cur level
    * iterate over the node until we find the node which has child ie make node = node.next until node has no
    children and node.next exists
    * move the node to next level is if has left then node.left else node.right
    * return the root at the end
    time & space:
    * takes n time and 1 space

    Approach 2: recursion
    * intuition is to connect the root node children then recursively connect the right subtree and then
    the left subtree
    * here we need to connect the right subtree before left subtree since while connecting the left subtree
    it uses the links connected by right subtree at same level.
    algo:
    * if root is null then return
    * if root has left
        -> if right exists then make root.left.next = root.right;
        -> else get the next node by calling getNext(root)
    * if root has right
        -> connect the next by calling getNext(root)
    * then call recursively on root.right and then on root.left
    * int getNext method we make cur =root.next
    * iterate until cur is not null
    * check if cur has left then return left if it has right then return right else make cur = cur.next
    * return null at the end
    time & space:
    * n time and n space for recursion stack
     */
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        return connectA2(root);
    }

    public Node connectA2(Node root) {
        rec(root);
        return root;
    }

    public void rec(Node root){
        if(root==null)return;
        if(root.left!=null){
            if(root.right!=null)
                root.left.next = root.right;
            else root.left.next = getNext(root);
        }
        if(root.right!=null)
            root.right.next = getNext(root);
        rec(root.right);
        rec(root.left);
    }

    public Node getNext(Node node){
        Node cur = node.next;
        while(cur !=null ){
            if(cur.left!=null) return cur.left;
            else if(cur.right !=null)return cur.right;
            else cur = cur.next;
        }
        return null;
    }
    public Node connectA1(Node root) {
        if(root==null)return null;
        Node node = root;
        while(node !=null){
            Node cur = node,prev=null;
            while(cur!=null){
                if(cur.left !=null){
                    if(prev!=null)
                        prev.next = cur.left;
                    prev = cur.left;
                }
                if(cur.right !=null){
                    if(prev!=null)
                        prev.next = cur.right;
                    prev = cur.right;
                }
                cur = cur.next;
            }
            while(node.left==null && node.right==null && node.next!=null)
                node = node.next;
            node = node.left!=null?node.left:node.right;
        }
        return root;
    }
}