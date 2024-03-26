package com.github.shashi.leetcode;

public class Problem117 {
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

    public Node connectA2(Node root){
        if(root==null)return root;
        rec(root);
        return root;
    }

    /*
     * in the recursion we are traversing the right part before the left part
     * this is to make sure that there is an link at the same level so that
     *  we will be able to traverse to the next node in same level, since rec
     * is dfs it will try to access the next node of the right part if we traverse
     * the left part first so to address this we need to traverse the right part
     * before the left part. findNext is used to handle the null children at the
     * same level
     */
    public void rec(Node root){
        if(root==null)return;
        if(root.left!=null){
            if(root.right !=null)
                root.left.next = root.right;
            else root.left.next = findNext(root);
        }
        if(root.right !=null){
            root.right.next = findNext(root);
        }
        rec(root.right);
        rec(root.left);
    }

    public Node findNext(Node root){
        Node cur = root.next;
        while(cur != null){
            if(cur.left != null)return cur.left;
            if(cur.right!=null)return cur.right;
            cur = cur.next;
        }
        return null;
    }

    public Node connectA1(Node root) {
        if(root==null)return root;
        Node node = root;
        while(node.left != null || node.right != null){
            Node level = node, prev=null;
            while(level != null){
                if(level.left!=null){
                    if(prev !=null){
                        prev.next=level.left;
                        prev=level.left;
                    }else prev = level.left;
                }
                if(level.right != null){
                    if(prev != null){
                        prev.next = level.right;
                        prev = level.right;
                    }else prev = level.right;
                }
                level = level.next;

            }


            node = node.left!=null?node.left:node.right;
            while(node.left == null && node.right==null && node.next != null)
                node=node.next;
        }
        return root;
    }
}