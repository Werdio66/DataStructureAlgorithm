package com._520.Algorithm.recursive;

/**
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 */
public class LongestUnivaluePath {

    private int max = 0;
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        // 遍历坐结点
        if (root.left != null){
            aroundTree(root.left);

        }
        // 遍历右结点
        if (root.right != null)
            aroundTree(root.right);

        return max;
    }

    private void aroundTree(TreeNode root) {

        TreeNode left = root.left;
        TreeNode right = root.right;


    }

    public static void main(String[] args) {

    }
}
