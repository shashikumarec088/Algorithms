package com.github.shashi.leetcode;
import java.util.*;
public class Problem297 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    String NULL="N";
    String SPLITTER=",";

    public String serialize(TreeNode root){
        StringBuilder sb = new StringBuilder();
        rec(root,sb);
        return sb.toString();
    }

    public void rec(TreeNode root, StringBuilder sb){
        if(root==null)sb.append(NULL).append(SPLITTER);
        else{
            sb.append(root.val).append(SPLITTER);
            rec(root.left,sb);
            rec(root.right,sb);
        }
    }

    public TreeNode deserialize(String s){
        Queue<String> queue = new LinkedList<>(Arrays.asList(s.split(SPLITTER)));
        return recD(queue);
    }

    public TreeNode recD(Queue<String> queue){
        String s = queue.poll();
        if(s.equals(NULL))return null;
        else{
            TreeNode root = new TreeNode(Integer.parseInt(s));
            root.left=recD(queue);
            root.right=recD(queue);
            return root;
        }
    }

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        if(root==null)return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node==null){
                sb.append("n ");
                continue;
            }
            sb.append(node.val+" ");
            queue.add(node.left);
            queue.add(node.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String data) {
        if(data==null||data.length()==0)return null;
        String[] arr = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        for(int i=1; i<arr.length; i++){
            TreeNode parent = queue.poll();
            if(!"n".equals(arr[i])){
                TreeNode node = new TreeNode(Integer.parseInt(arr[i]));
                parent.left = node;
                queue.add(node);
            }
            if(!"n".equals(arr[++i])){
                TreeNode node = new TreeNode(Integer.parseInt(arr[i]));
                parent.right = node;
                queue.add(node);
            }
        }
        return root;
    }
}