package com.github.shashi.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem426 {
    class Node {
        int val;
        Node left;
        Node right;

        Node(int x) {
            val = x;
        }
    }

    Node first=null;
    Node last=null;
    public Node treeToDoublyList(Node root) {
        return TreeToDLLMoris(root);
    }

    public Node TreeToDLLMoris(Node root){
        if(root==null) return root;
        Node head = new Node(1);
        Node last = head;
        Node cur = root;
        while(cur != null){
            if(cur.left ==null){
                last.right = cur;
                cur.left = last;
                last = cur;
                cur = cur.right;
            }else{
                Node pred = cur.left;
                while(pred.right != null && pred.right != cur)
                    pred = pred.right;
                if(pred.right==null){
                    pred.right = cur;
                    cur = cur.left;
                }else{
                    last.right = cur;
                    cur.left = last;
                    last = cur;
                    //pred.right = null;
                    cur = cur.right;
                }
            }
        }
        last.right = head.right;
        head.right.left = last;
        return head.right;
    }

    public Node TreeToDLLRec(Node root){
        if(root == null) return root;
        helper(root);
        last.right = first;
        first.left = last;
        return first;
    }

    public void helper(Node node){
        if(node !=null){
            helper(node.left);
            if(last !=null){
                last.right = node;
                node.left = last;
            }else first = node;
            last = node;
            helper(node.right);
        }
    }
}
