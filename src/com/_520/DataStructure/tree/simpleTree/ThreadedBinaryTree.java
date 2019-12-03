package com._520.DataStructure.tree.simpleTree;

public class ThreadedBinaryTree {

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1,"露露");
        TreeNode node1 = new TreeNode(2,"小法");
        TreeNode node2 = new TreeNode(3,"火男");
        TreeNode node3 = new TreeNode(4,"亚索");
        TreeNode node4 = new TreeNode(5,"剑圣");
        TreeNode node5 = new TreeNode(6,"龙王");
        root.setLeftNode(node1);
        root.setRightNode(node2);
        node1.setLeftNode(node3);
        node1.setRightNode(node4);
        node2.setLeftNode(node5);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);
        // 线索化
//        binaryTree.threadedBinaryTree();
        binaryTree.midThreadedBinaryTree(root);

        System.out.println("node4 前驱 = " + node4.leftNode);
        System.out.println("node4 后继 = " + node4.rightNode);
//        binaryTree.listThreaded();
    }
    static class BinaryTree{

        public void setRoot(TreeNode root) {
            this.root = root;
        }

        private TreeNode root;
        private TreeNode prev;

        public void threadedBinaryTree(){
            this.threadedBinaryTree(root);
        }

        // 中序遍历
        public void listThreaded(){
            TreeNode node = root;

            while (node != null){
                // 找到第一个为 1 的结点
                while (node != null && node.leftType == null){
                    node = node.leftNode;
                }
                // 输出当前结点
                System.out.println(node);
                // 判断当前结点有没有指向后继的结点
                if (node != null && node.rightType == 1){
                    node = node.rightNode;      // 拿到当前结点的后继结点
                    System.out.println(node);
                }
                if (node != null)
                    // 更新当前结点，继续往后遍历
                    node = node.rightNode;
            }

        }
        // 中序线索化二叉树
        private void threadedBinaryTree(TreeNode node){

            if (node == null)
                return;

            threadedBinaryTree(node.leftNode);

            if (node.leftNode == null) {
                // 将左结点指向前驱结点
                node.setLeftNode(prev);
                node.setLeftType(1);
            }
            if (prev != null && prev.rightNode == null){
                // 将右结点指向后继结点
                prev.setRightNode(node);
                prev.setRightType(1);
            }
            // 指向当前结点
            prev = node;

            threadedBinaryTree(node.rightNode);
        }

        // 前序线索化二叉树
        private void midThreadedBinaryTree(TreeNode node){

            if (node == null)
                return;
            if (node.leftNode == null) {
                // 将左结点指向前驱结点
                node.setLeftNode(prev);
                node.setLeftType(1);
            }
            if (prev != null && prev.rightNode == null && node.rightNode != null){
                // 将右结点指向后继结点
                prev.setRightNode(node.rightNode);
                prev.setRightType(1);
            }
            // 指向当前结点
            prev = node;
            // 遍历左子树
            if (node.leftType == null){
                midThreadedBinaryTree(node.leftNode);
//
            }
//            // 遍历右子树
            if (node.rightType == null)
            midThreadedBinaryTree(node.rightNode);
        }

    }
    static class TreeNode{
        private int id;
        private String name;
        private TreeNode leftNode;      // 左结点
        private TreeNode rightNode;     // 右结点

        private Integer leftType;       // 左边类型
        private Integer rightType;      // 右边类型




        public TreeNode(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", leftType=" + leftType +
                    ", rightType=" + rightType +
                    '}';
        }

        public Integer getLeftType() {
            return leftType;
        }

        public void setLeftType(Integer leftType) {
            this.leftType = leftType;
        }

        public Integer getRightType() {
            return rightType;
        }

        public void setRightType(Integer rightType) {
            this.rightType = rightType;
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
