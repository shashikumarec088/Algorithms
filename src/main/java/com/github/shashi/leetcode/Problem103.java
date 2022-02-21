package com.github.shashi.leetcode;
import java.util.*;

public class Problem103 {

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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return zigzagLevelOrderA4(root);
    }

    public List<List<Integer>> zigzagLevelOrderA4(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        dfs(root,result,0);
        return result;
    }

    public void dfs(TreeNode root, List<List<Integer>> result, int level){
        if(root==null)return;
        if(result.size()==level)
            result.add(new LinkedList<>());
        if(level%2==0)
            result.get(level).add(root.val);
        else result.get(level).add(0,root.val);
        if(root.left!=null)dfs(root.left,result,level+1);
        if(root.right!=null)dfs(root.right,result,level+1);
    }

    public List<List<Integer>> zigzagLevelOrderA3(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        boolean isLeft = true;
        LinkedList<Integer> list = new LinkedList<>();
        while(!queue.isEmpty()){
            TreeNode cur = queue.pollFirst();
            if(cur != null){
                if(isLeft)list.add(cur.val);
                else list.addFirst(cur.val);
                if(cur.left!=null)
                    queue.add(cur.left);
                if(cur.right!=null)
                    queue.add(cur.right);
            }
            else{
                result.add(list);
                list = new LinkedList<>();
                isLeft = !isLeft;
                if(queue.size()>0)
                    queue.add(null);
            }
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrderA2(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null)return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeft = true;
        while(!queue.isEmpty()){
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for(int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(isLeft)list.add(node.val);
                else list.addFirst(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            isLeft = !isLeft;
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> zigzagLevelOrderA1(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        Deque<TreeNode> s1 = new LinkedList<>();
        Deque<TreeNode> s2 = new LinkedList<>();
        s1.push(root);
        boolean even = true;
        while(!s1.isEmpty() || !s2.isEmpty()){
            List<Integer> list = new ArrayList<>();
            if(even){
                int size = s1.size();
                for(int i=0; i<size;i++){
                    TreeNode node = s1.pop();
                    list.add(node.val);
                    if(node.left != null)
                        s2.push(node.left);
                    if(node.right != null)
                        s2.push(node.right);
                }
            }
            else{
                int size = s2.size();
                for(int i=0; i<size;i++){
                    TreeNode node = s2.pop();
                    list.add(node.val);
                    if(node.right != null)
                        s1.push(node.right);
                    if(node.left != null)
                        s1.push(node.left);
                }
            }
            even = !even;
            result.add(list);
        }
        return result;
    }
}
