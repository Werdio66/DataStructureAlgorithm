package com._520.DataStructure.tree.avl;

public class AVLTreeDemo {

    public static void main(String[] args) {
        int[] arr = {10, 12, 8, 9, 7, 6};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.midOrder();
        System.out.println(avlTree.root.getHeight());
        System.out.println(avlTree.root.left.getHeight());
        System.out.println(avlTree.root.right.getHeight());
    }
    // 平衡二叉树
    static class AVLTree {
        Node root;

        public Node serach(int value) {
            if (root == null)
                return null;

            return root.serach(value);
        }

        public Node serachparent(int value) {
            if (root == null) {
                return null;
            }
            return root.serachParent(value);
        }


        /**
         * 查找右结点中的值最小的子结点
         *
         * @param node 要查找的结点
         * @return 最小值
         */
        private int findMinValueInRightNode(Node node) {
            Node target = node;
            // 查找最小的结点
            while (target.left != null) {
                target = target.left;
            }
            // 拿到最小的值
            int value = target.value;
            // 删除指定值的结点
            deleteNode(value);
            //
            return value;
        }

        // 删除指定值的结点
        public void deleteNode(int value) {
            // 拿到要删除的结点
            Node targetNode = serach(value);
            if (targetNode == null) {
                System.out.println("没有找到要删除的结点");
                return;
            }
            // 如果当前结点是根结点，且这颗树中只有这一个结点
            if (root.left == null && root.right == null) {
                // 删除这个结点
                root = null;
                return;
            }
            // 拿到删除结点的父结点
            Node parentNode = serachparent(value);
            // 说明要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 说明要删除的结点是父结点的左结点
                if (parentNode.left.value == targetNode.value) {
                    // 删除结点
                    parentNode.left = null;
                }
                // 说明要删除的结点是右结点
                if (parentNode.right.value == targetNode.value) {
                    // 删除结点
                    parentNode.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 要删除的结点有俩个子结点
                // 找到删除结点右子树中的最小值赋给当前要删除的结点
                targetNode.value = findMinValueInRightNode(targetNode.right);

            } else { // 删除的结点有一个子结点

                if (targetNode.left != null) {   // 要删除的结点有一个左子结点
                    // 说明当前要删除的结点不是根结点
                    if (parentNode != null) {
                        // 要删除的结点是它的父结点的左结点
                        if (parentNode.left.value == targetNode.value) {
                            parentNode.left = targetNode.left;
                        } else { // 要删除结点的是它的父结点的右子结点
                            parentNode.right = targetNode.left;
                        }
                    } else { // 说明当前要删除的结点是根结点
                        root = targetNode.left;
                    }

                } else {     // 删除的结点有一个右子结点
                    if (parentNode != null) {
                        // 要删除的结点是它的父结点的左结点
                        if (parentNode.left.value == targetNode.value) {
                            parentNode.left = targetNode.right;
                        } else { // 要删除结点的是它的父结点的右子结点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }

                }
            }
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

        // 左旋
        public void leftRotate(){
            // 新创建一个结点，值为根结点
            Node newNode = new Node(this.value);
            // 将新结点的左结点指向当前结点的左结点
            newNode.left = this.left;
            // 新结点的右结点指向当前结点的右结点的左结点
            newNode.right = this.right.left;
            // 将当前结点的值设为当前结点右结点的值
            this.value = this.right.value;
            // 当前结点的左结点指向新的结点
            this.left = newNode;
            // 当前结点的右结点指向当前结点的右结点的右结点
            this.right = this.right.right;
        }

        // 右旋
        public void rightRotate(){
            Node newNode = new Node(this.value);
            newNode.left = this.left.right;
            newNode.right = this.right;
            this.value = this.left.value;
            this.right = newNode;
            this.left = this.left.left;
        }

        // 返回指定结点的高度
        public int getHeight(){
            return Math.max(this.left == null ? 0 : this.left.getHeight(),
                    this.right == null ? 0 : this.right.getHeight()) + 1;
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
            if (value < this.value) {
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

            // 当右子树的高度 - 左子树的高度 > 1，就左旋
            if (this.right != null && this.left != null && right.getHeight() - left.getHeight() > 1){
                leftRotate();
            }
            // 当左子树的高度 - 右子树的高度 > 1，就右旋
            if (this.right != null && this.left != null && this.left.getHeight() - this.right.getHeight() > 1){
                rightRotate();
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
