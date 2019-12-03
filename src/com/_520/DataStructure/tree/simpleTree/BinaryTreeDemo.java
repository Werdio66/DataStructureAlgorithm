package com._520.DataStructure.tree.simpleTree;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        TreeNode node1 = new TreeNode(1,"露露");
        TreeNode node2 = new TreeNode(2,"小法");
        TreeNode node3 = new TreeNode(3,"火男");
        TreeNode node4 = new TreeNode(4,"亚索");

        binaryTree.setRoot(node1);
        node1.setLeftNode(node2);
        node1.setRightNode(node3);
        node3.setLeftNode(node4);

        binaryTree.prevOnder(node1);
        System.out.println("---------------------------");
        binaryTree.midOnder(node1);
        System.out.println("---------------------------");
        binaryTree.lastOnder(node1);

        binaryTree.prevOnderSerach(node1, 4);
        binaryTree.midOnderSerach(node1, 5);
        binaryTree.deleteNode(4);
        binaryTree.prevOnder(node1);
    }
    static class BinaryTree{

        public void setRoot(TreeNode root) {
            this.root = root;
        }

        private TreeNode root;

        public void deleteNode(int id){
            if (root.id == id){
                root = null;
            }else {
                root.deleteNode(id);
            }
        }

        // 前序遍历
        public void prevOnder(TreeNode root){
            if (root != null)
                root.prevOnder(root);
        }

        // 前序遍历
        public void prevOnderSerach(TreeNode root,int id){
            TreeNode node = null;
            if (root != null) {
                node = root.prevOnderSerach(root, id);
            }
            if (node == null){
                System.out.println("没有找到id为" + id + "员工");
            }
            else
                System.out.println(node.id + "  " + node.name);
        }
        // 中序遍历
        public void midOnder(TreeNode root){
            if (root != null)
                root.midOnder(root);
        }

        // 前序遍历
        public void midOnderSerach(TreeNode root,int id){
            TreeNode node = null;
            if (root != null) {
                node = root.midOnderSerach(root, id);
            }
            if (node == null){
                System.out.println("没有找到id为" + id + "员工");
            }
            else
                System.out.println(node.id + "  " + node.name);
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

         // 前序遍历
         public TreeNode prevOnderSerach(TreeNode node, int id){
             // 如果找到就返回
             if (id == node.id)
                 return node;
             else {
                 // 用来接收当前遍历结果
                 TreeNode node1 = null;
                 if (node.leftNode != null)
                     node1 = prevOnderSerach(node.leftNode,id);
                 if (node1 != null)     // 遍历左子树的时候已经找到了，直接返回
                     return node1;
                 if (node.rightNode != null)    // 没有找到遍历右子树
                     node1 =  prevOnderSerach(node.rightNode,id);
                 // 不论找没找到，最后都需返回结点
                 return node1;
             }
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

         // 前序遍历
         public TreeNode midOnderSerach(TreeNode node, int id){
             // 用来接收当前遍历结果
             TreeNode node1 = null;
             if (node.leftNode != null)
                 node1 = midOnderSerach(node.leftNode,id);

//             if (node1 != null)     // 遍历左子树的时候已经找到了，直接返回
//                 return node1;

             // 如果没有找到就和当前结点比较，相等就返回
             if (id == node.id) {
                 return node;
             }

             if (node.rightNode != null)    // 没有找到遍历右子树
                 node1 =  midOnderSerach(node.rightNode,id);
             // 不论找没找到，最后都需返回结点
             return node1;

         }

         public void deleteNode(int id){
             if (this.leftNode != null && this.leftNode.id == id){
                 this.leftNode = null;
                 return;
             }

             if (this.rightNode != null && this.rightNode.id == id){
                 this.rightNode = null;
                 return;
             }

             if (this.leftNode != null)
                 this.leftNode.deleteNode(id);

             if (this.rightNode != null)
                 this.rightNode.deleteNode(id);
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
