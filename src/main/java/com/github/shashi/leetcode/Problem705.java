package com.github.shashi.leetcode;
import java.util.*;
public class Problem705  {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    class BST{
        TreeNode root;

        public TreeNode search(TreeNode root, int key){
            if(root==null|| root.val==key)return root;
            if(key>root.val)return search(root.right,key);
            else return search(root.left,key);
        }

        public TreeNode insert(TreeNode root, int key){
            if(root==null)return new TreeNode(key);
            if(key>root.val)
                root.right = insert(root.right,key);
            else if(root.val==key)return root;
            else root.left = insert(root.left, key);
            return root;
        }

        public TreeNode successor(TreeNode root){
            TreeNode cur = root;
            while(cur !=null && cur.left!=null)
                cur = cur.left;
            return cur;
        }

        public TreeNode predecessor(TreeNode root){
            TreeNode cur = root;
            while(cur != null && cur.right != null)
                cur = cur.right;
            return cur;
        }

        public TreeNode delete(TreeNode root, int key){
            if(root==null)return root;
            if(key>root.val)
                root.right = delete(root.right,key);
            else if(key<root.val)
                root.left = delete(root.left,key);
            else{
                if(root.left==null && root.right==null)
                    root=null;
                else if(root.right!=null){
                    root.val = successor(root.right).val;
                    root.right = delete(root.right,root.val);
                }
                else{
                    root.val = predecessor(root.left).val;
                    root.left = delete(root.left,root.val);
                }
            }
            return root;
        }
    }

    class Bucket{
        BST bst;

        Bucket(){
            bst = new BST();
        }

        public void insert(int key){
            bst.root = bst.insert(bst.root,key);
        }

        public void delete(int key){
            bst.root = bst.delete(bst.root,key);
        }

        public boolean exists(int key){
            return bst.search(bst.root,key)!=null;
        }
    }
    int n = 10000;
    LinkedList<Integer>[] buckets = new LinkedList[n];
    Bucket[] buckets1 = new Bucket[n];
    public Problem705() {
        for(int i=0; i<n;i++)
            buckets1[i] = new Bucket();

    }

    private int hashCode(int key){
        return key%n;
    }

    public void add(int key) {
        int code = hashCode(key);
        Bucket  slot= buckets1[code];
        slot.insert(key);

    }

    public void remove(int key) {
        int code = hashCode(key);
        Bucket  slot= buckets1[code];
        slot.delete(key);
    }

    public boolean contains(int key) {
        int code = hashCode(key);
        return buckets1[code].exists(key);
    }

    public void add1(int key) {
        int code = hashCode(key);
        LinkedList<Integer> list = buckets[code];
        if(list==null){
            list = new LinkedList<>();
            buckets[code]=list;
        }
        if(!contains(key))list.add(key);
    }

    public void remove1(int key) {
        int code = hashCode(key);
        LinkedList<Integer> list = buckets[code];
        if(list!=null)list.removeFirstOccurrence(key);
    }

    public boolean contains1(int key) {
        int code = hashCode(key);
        LinkedList<Integer> list = buckets[code];
        if(list!=null)return list.contains(key);
        return false;
    }
}