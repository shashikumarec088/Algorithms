package com.github.shashi.leetcode;
import java.util.*;

public class Problem95 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static void main(String[] args) {
        Problem95 problem119 = new Problem95();
        System.out.println(problem119.generateTrees(3));
    }

        public List<TreeNode> generateTrees(int n) {
            return getTrees(n);
        }

        public List<TreeNode> getTrees(int n){
            if(n==0) new LinkedList<TreeNode>();
            return getbstsRec(1,n);
        }

        public LinkedList<TreeNode> getbstsRec(int start, int end){
            LinkedList<TreeNode> allTrees = new LinkedList<>();
            if(start>end){
                allTrees.add(null);
                return allTrees;
            }

            for(int i=start; i<= end; i++){
                LinkedList<TreeNode> left = getbstsRec(start,i-1);
                LinkedList<TreeNode> right = getbstsRec(i+1,end);
                for(TreeNode l : left){
                    for(TreeNode r: right){
                        TreeNode ith = new TreeNode(i);
                        ith.left = l;
                        ith.right = r;
                        allTrees.add(ith);
                    }
                }
            }
            return allTrees;
        }
}
