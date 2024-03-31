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

    /*
   intuition behind the below approach is for any tree
   the unique subtrees will be identical only thing is that we
   need to add the offset of current node values that is the reason
   we are using the clone with the right side tree
   refer to approach 3 in leetcode solution
   */
    public List<TreeNode> generateTreesA2(int n) {
        List<TreeNode>[] dp = new List[n+1];
        dp[0] = new ArrayList<>();
        if(n==0)return dp[0];
        dp[0].add(null);
        for(int i=1; i<=n;i++){
            dp[i] = new ArrayList<>();
            for(int j=1;j<=i;j++){
                for(TreeNode l : dp[j-1]){
                    for(TreeNode r:dp[i-j]){
                        TreeNode root = new TreeNode(j);
                        root.left = l;
                        root.right = clone(r,j);
                        dp[i].add(root);
                    }
                }
            }
        }
        return dp[n];
    }

    private TreeNode clone(TreeNode n, int offset){
        if(n==null)return null;
        TreeNode root = new TreeNode(n.val+offset);
        root.left = clone(n.left,offset);
        root.right = clone(n.right,offset);
        return root;
    }

    /*
    intuition behind the approach is we know in BST all the nodes in the left
    will be lesser and all the nodes in the right will be greater than root node,
    at each step we can build the subtrees for left and right and connect ot the root,
    since we are asked to get the unique combinations those combinations will have
    n roots starting from 1 so we loop through 1 to n in recursion and build the subtrees
    and then connect the left and right to get all combinations
     */
        public List<TreeNode> generateTrees(int n) {
            return getTrees(n);
        }

    Map<String,List<TreeNode>> memo = new HashMap<>();
        public List<TreeNode> getTrees(int n){
        return rec(1,n);
        }
        public List<TreeNode> rec(int s, int e){
        List<TreeNode> res = new ArrayList<>();
        if(s>e){
            res.add(null);
            return res;
        }
        String key = s+""+e;
        if(memo.containsKey(key))return memo.get(key);
        for(int i=s; i<=e; i++){
            List<TreeNode> ls = rec(s,i-1);
            List<TreeNode> rs = rec(i+1,e);
            for(TreeNode l : ls){
                for(TreeNode r : rs){
                    TreeNode root = new TreeNode(i);
                    root.left=l;
                    root.right=r;
                    res.add(root);
                }
            }
        }
        memo.put(key,res);
        return res;
        }
}
