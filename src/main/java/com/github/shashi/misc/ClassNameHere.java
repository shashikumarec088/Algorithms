package com.github.shashi.misc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

      public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    @Override
    public String toString(){
        return "{ val = "+val+", left = "+left+", right = "+right+", next = "+next+" }";
    }
};

class TreeNode {
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

public class ClassNameHere {

    private int in = 0;
    private int pre = 0;

    public static void main(String[] args) {



        ClassNameHere cl = new ClassNameHere();
        int [] in = new int[]{2,3,1,2,4,3};
        int [] post = new int[]{9,15,7,20,3};
        Node root = cl.constructTreeFromArray(new Integer[]{1,2,2,3,4,4,3});

         boolean s1 = cl.isSymmetricItr(root);
        System.out.println(s1);
        System.out.println(s1);
StringBuilder sb = new StringBuilder();


    }

    public boolean isSymmetricItr(Node root){
        if(root == null|| (root.left == null && root.right ==null))
            return true;
        if(root.left ==null|| root.right ==null)return false;
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.add(root.left);
        s2.add(root.right);
        while(!s1.isEmpty()&&!s2.isEmpty()){
            Node n1 = s1.pop();
            Node n2 = s2.pop();
            if(n1 == null && n2 == null) continue;
            if(n1 == null || n2 == null) return false;
            if(n1.val != n2.val) return false;
            s1.push(n1.right);
            s2.push(n2.left);
            s1.push(n1.left);
            s2.push(n2.right);
        }
        return true;
    }
    public StringBuilder trimSpaces(String str){
        int start =0, end = str.length()-1;
        while(start<end && str.charAt(start) == ' ')start++;
        while(end > start && str.charAt(end) == ' ')end--;
        StringBuilder sb = new StringBuilder();
        while(start <= end){
            char c = str.charAt(start);
            if(c != ' ')sb.append(c);
            else if( c == ' ' && str.charAt(start-1) != ' ') sb.append(c);
            start++;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int start , int end){
        while(start <= end){
            char c = sb.charAt(start);
            sb.setCharAt(start,sb.charAt(end));
            sb.setCharAt(end,c);
            start++;
            end--;
        }
    }

    public void reverseWord(StringBuilder sb){
        int start = 0, end = 0, n = sb.length();
        while(end < n){
            while(end < n && sb.charAt(end) != ' ')end++;
            reverse(sb,start,end-1);
            start = end+1;
            end = end+1;
        }
    }

    public String reverseString(String str){
        StringBuilder sb = trimSpaces(str);
        reverse(sb, 0, sb.length()-1);
        reverseWord(sb);
        return sb.toString();
    }

    public String reverseWords(String s) {
        return reverseString(s);
    }


    private Node constructTreeFromArray(Integer[] data){
        if(data.length == 0 || (data.length == 1 && data[0] == null)) return null;
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(data[0]);
        queue.add(root);
        Node current = null;
        int count = 0;
        for(int i=1; i<data.length;i++){
            Integer value = data[i];
            if(count == 0)
                current = queue.poll();
            Node node = null;
            if(value != null)
                node = new Node(value);
            if(count == 0){
                count++;
                current.left = node;
            }else{
                count =0;
                current.right = node;
            }
            if(node != null)
                queue.add(node);
        }
        return root;
    }

    private void connect2(Node root){
        Node leftMost = root;
        while(leftMost != null){
            Node current = leftMost;
            Node prev = null;
            leftMost = null;
            while(current != null){
                if(current.left != null){
                    if(prev != null){
                        prev.next = current.left;
                        if(leftMost.left == null && leftMost.right == null){
                            leftMost = prev.next;
                        }
                    }else{
                        leftMost = current.left;
                    }
                    prev = current.left;
                }
                if(current.right != null){
                    if(prev != null){
                        prev.next = current.right;
                        if(leftMost.left == null && leftMost.right == null){
                            leftMost = prev.next;
                        }

                    }else{
                        leftMost = current.right;
                    }
                    prev = current.right;
                }
                current = current.next;
                if(current == null)
                    prev = null;
            }
        }
    }
}