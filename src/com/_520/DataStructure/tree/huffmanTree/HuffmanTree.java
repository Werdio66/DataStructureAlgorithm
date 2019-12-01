package com._520.DataStructure.tree.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  哈夫曼树
 */
public class HuffmanTree{

    static class Node implements Comparable<Node>{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" + "value=" + value + '}';
        }

        @Override
        public int compareTo(Node o) {
            // 从小到大排序
            return this.value - o.value;
        }
        public void prevOrder(){
            System.out.println(this);
            if (this.left != null)
                this.left.prevOrder();
            if (this.right != null)
                this.right.prevOrder();
        }
    }

    private static void prevOrder(Node root){
        if (root != null)
            root.prevOrder();
        else
            System.out.println("不能遍历");
    }
    /**
     * 将一个数组构建成一个哈夫曼树
     *
     * @param arr   构建的数组
     * @return      返回构建好树的根结点
     */
    private static Node creatHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<>();
        // 将arr中的数存到集合中
        for (int temp : arr
        ) {
            nodes.add(new Node(temp));
        }
        //
        while (nodes.size() > 1){
            // 对集合中的进行排序
            Collections.sort(nodes);
//            System.out.println("排序后：");
            System.out.println(nodes);
            // 取出最小的结点做为左结点
            Node leftNode = nodes.remove(0);
            // 取出第二小的做为右结点
            Node rightNode = nodes.remove(0);
            // 构建父结点，值为左右子结点值的和
            Node parent = new Node(leftNode.value + rightNode.value);
            // 将当前结点的左结点指向左结点
            parent.left = leftNode;
            parent.right = rightNode;
            // 将父结点加到list中
            nodes.add(parent);
//            System.out.println("合并俩个子节点：");
//            System.out.println(nodes);
        }
        return nodes.get(0);
    }
    public static void main(String[] args) {
        int[] arr = {2,34,6,9};
        Node root = creatHuffmanTree(arr);
        System.out.println("root = " + root);
        root.prevOrder();
    }
}


