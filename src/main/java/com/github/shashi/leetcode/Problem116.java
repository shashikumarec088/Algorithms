package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.*;

public class Problem116 {
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
    public static void main(String[] args) {
        Problem116 problem119 = new Problem116();
        problem119.connect(null);
    }

    public Node connect(Node root) {
        if(root == null) return root;
        rec1(root);
        return root;
    }

    /*
    * intuition behind the approach is very similar to iterative solution
    * finst check if the root is null which is the base case, else
    * if the node has left then connect its next to right and if it has the right
    * connect ot its next left to it
    *
     */
    public void rec1(Node root){
        if(root == null) return;
        if(root.left != null){
            root.left.next = root.right;
            if(root.next != null)
                root.right.next = root.next.left;
        }
        rec1(root.left);
        rec1(root.right);
    }

    /*
     * intuition is take left and right subarray,
     * connect the left and right, then traverse the left subarray and
     * right subarray and also traverse right part of left subarray and
     * left part of right subarray
     */
    public void rec2(Node left, Node right){
        if(left==null)return;
        left.next = right;
        rec2(left.left,left.right);
        rec2(left.right,right.left);
        rec2(right.left,right.right);
    }

    public Node connectLeft(Node root){
        Node leftMost = root;
        while(leftMost.left != null){
            Node head = leftMost;
            while(head != null){
                head.left.next = head.right;
                if(head.next != null)
                    head.right.next = head.next.left;
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    public void connectBFS(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int n = queue.size();
            Node prev = null;
            for(int i=0; i<n;i++){
                Node cur = queue.poll();
                if(prev != null)
                    prev.next = cur;
                prev = cur;
                if(cur.left != null)
                    queue.add(cur.left);
                if(cur.right != null)
                    queue.add(cur.right);
            }
        }
    }
}
