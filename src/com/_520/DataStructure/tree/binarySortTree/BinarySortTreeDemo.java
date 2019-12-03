package com._520.DataStructure.tree.binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        binarySortTree.midOrder();

    }

    static class BinarySortTree{
        Node root;

        public Node serach(int value){
            if (root == null)
                return null;

            return root.serach(value);
        }

        public Node serachparent(int value){
            if (root == null){
                return null;
            }
            return root.serachParent(value);
        }
        // 增加结点
        public void add(Node node){
            if (root == null){
                root = node;
            }else {
                root.add(node);
            }
        }
        // 中序遍历
        public void midOrder(){
            if (root == null){
                System.out.println("当前树为空");
            }else {
                root.midOrder();
            }
        }
    }
    static class Node{
        int value;
        Node left;
        Node right;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

        public Node(int value) {
            this.value = value;
        }

        // 删除指定值的结点
        public void delete(int value) {
            // 当前结点就是要删除的结点
            if (this.value == value){

            }
        }

        // 查找要删除结点的父结点
        public Node serachParent(int value){
            // 如果当前结点的左结点或者右结点是要删除的值，当前结点就是删除结点的父结点
            if ((this.left != null && this.left.value == value)
                    || (this.right != null && this.right.value == value)){
                return this;
            }else {
                // 删除的值大于当前结点值，向右递归
                if (this.right != null && value > this.value){
                    return this.right.serachParent(value);
                    // 删除的值小于当前结点值，向左递归
                }else if (this.left != null && value < this.value){
                    return this.left.serachParent(value);
                }else {
                    // 没有找到
                    return null;
                }
            }

        }

        // 查找要删除的结点
        public Node serach(int value) {

            if (this.value == value) {
                return this;
            }
            if (this.value < value) {
                if (this.left != null) {
                    return this.left.serach(value);
                }
                return null;
            } else {
                if (this.right != null) {
                    return this.right.serach(value);
                }
                return null;
            }

        }
        // 创建二叉排序树
        public void add(Node node){
            // 新加结点的值大于当前结点
            if (node.value > this.value){
                // 当前结点的右结点为空，就直接加在当前结点的后面
                if (this.right == null){
                    this.right = node;
                }else {
                    // 向右递归
                    this.right.add(node);
                }
            }else { // 新加结点的值小于等于当前结点
                // 当前结点的左结点为空，直接加在当前结点的后面
                if (this.left == null){
                    this.left = node;
                }else
                    // 向左递归
                    this.left.add(node);
            }
        }
        // 中序遍历
        public void midOrder(){
            if (this.left != null){
                this.left.midOrder();
            }
            System.out.println(this);
            if (this.right != null){
                this.right.midOrder();
            }
        }
    }
}
