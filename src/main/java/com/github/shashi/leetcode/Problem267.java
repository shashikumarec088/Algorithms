package com.github.shashi.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Problem267 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public String serialize2(TreeNode root){
        StringBuilder sb = new StringBuilder();
        serRec(root,sb);
        return sb.toString();
    }

    private void serRec(TreeNode root, StringBuilder sb){
        if(root==null){
            sb.append("n,");
            return;
        }
        sb.append(root.val+",");
        serRec(root.left,sb);
        serRec(root.right,sb);
    }

    public TreeNode deserialize2(String s){
        Queue<String> queue = new LinkedList<>(Arrays.asList(s.split(",")));
        return desRec(queue);
    }

    private TreeNode desRec(Queue<String> queue){
        if(queue.isEmpty())return null;
        String s = queue.poll();
        if("n".equals(s))return null;
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = desRec(queue);
        root.right = desRec(queue);
        return root;
    }

    public String serialize(TreeNode root) {
        if(root==null)return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node ==null){
                sb.append("n,");
            }else{
                sb.append(node.val+",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String sb) {
        if(sb.length()==0)return null;
        String[] s = sb.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(s[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for(int i=1; i<s.length-1;i++){
            TreeNode parent = queue.poll();
            if(!"n".equals(s[i])){
                TreeNode left = new TreeNode(Integer.valueOf(s[i]));
                parent.left = left;
                queue.offer(left);
            }
            if(!"n".equals(s[++i])){
                TreeNode right = new TreeNode(Integer.valueOf(s[i]));
                parent.right = right;
                queue.offer(right);
            }
        }
        return root;
    }

    // Encodes a tree to a single string.
    public String serialize1(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root==null)return sb.toString();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            boolean flag=false;
            for(int i=0; i<size;i++){
                TreeNode node = queue.poll();
                if(node==null)sb.append("#,");
                else sb.append(node.val+",");
                if(node !=null){
                    if(node.left != null || node.right !=null)flag=true;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }else {
                    queue.offer(null);
                    queue.offer(null);
                }
            }
            if(!flag)break;
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize1(String sb) {
        if("".equals(sb))return null;

        String[] s = sb.split(",");
        int i=0, level=0, n=s.length;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root=null;
        while(i<n){
            if(level==0){
                root = new TreeNode(Integer.valueOf(s[0]));
                queue.offer(root);
            }else{
                for(int j=i; j< i+Math.pow(2,level);j=j+2){
                    TreeNode p = queue.poll();
                    TreeNode l=null,r=null;
                    if(p != null){
                        l = "#".equals(s[j])?null:new TreeNode(Integer.valueOf(s[j]));
                        p.left = l;
                        r = "#".equals(s[j+1])?null:new TreeNode(Integer.valueOf(s[j+1]));
                        p.right = r;

                    }
                    queue.offer(l);
                    queue.offer(r);
                }
            }
            i= i+ (int)Math.pow(2,level);
            level++;
        }
        return root;

    }
}