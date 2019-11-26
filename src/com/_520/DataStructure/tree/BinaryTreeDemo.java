package com._520.DataStructure.tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        TreeNode node1 = new TreeNode(1,"露露");
        TreeNode node2 = new TreeNode(2,"小法");
        TreeNode node3 = new TreeNode(3,"火男");
        TreeNode node4 = new TreeNode(4,"亚索");

        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        node3.setLeftNode(node4);

        binaryTree.prevOnder(node1);
        System.out.println("---------------------------");
        binaryTree.midOnder(node1);
        System.out.println("---------------------------");
        binaryTree.lastOnder(node1);
    }
    static class BinaryTree{

        // 前序遍历
        public void prevOnder(TreeNode root){
            if (root != null)
                root.prevOnder(root);
        }

        // 中序遍历
        public void midOnder(TreeNode root){
            if (root != null)
                root.midOnder(root);
        }

        // 后序遍历
        public void lastOnder(TreeNode root){
            if (root != null)
                root.lastOnder(root);
        }
    }
     static class TreeNode{
         private int id;
         private String name;
         private TreeNode leftNode;
         private TreeNode rightNode;

         // 前序遍历
         public void prevOnder(TreeNode node){
             System.out.println("id = " + node.id + " name = " + node.name);
             if (node.leftNode != null)
                 prevOnder(node.leftNode);
             if (node.rightNode != null)
                 prevOnder(node.rightNode);
         }

         // 中序遍历
         public void midOnder(TreeNode node){
             if (node.leftNode != null) {
                 midOnder(node.leftNode);
             }
             System.out.println("id = " + node.id + " name = " + node.name);
             if (node.rightNode != null)
                 midOnder(node.rightNode);
         }

         // 后序遍历
         public void lastOnder(TreeNode node){
             if (node.leftNode != null) {
                 lastOnder(node.leftNode);
             }
             if (node.rightNode != null) {
                 lastOnder(node.rightNode);
             }
             System.out.println("id = " + node.id + " name = " + node.name);
         }




         public TreeNode(int id, String name) {
             this.id = id;
             this.name = name;
         }

         public int getId() {
             return id;
         }

         public void setId(int id) {
             this.id = id;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public TreeNode getLeftNode() {
             return leftNode;
         }

         public void setLeftNode(TreeNode leftNode) {
             this.leftNode = leftNode;
         }

         public TreeNode getRightNode() {
             return rightNode;
         }

         public void setRightNode(TreeNode rightNode) {
             this.rightNode = rightNode;
         }
     }
}
