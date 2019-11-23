package com._520.Algorithm.recursive;

/**
 *  合并俩个有序链表
 */
public class Test3 {

    private static class Node{
        int val;
        Node next;
        private Node(int val){
            this.val = val;
        }
    }

    private static Node mergeNode(Node node1, Node node2){
        if (node1 == null)
            return node2;
        if (node2 == null)
            return node1;

        if (node1.val > node2.val) {
            // 因为node2的值小所以递归node2
            node2.next = mergeNode(node1, node2.next);
            // 返回小的
            return node2;
        }else {
            node1.next = mergeNode(node1.next,node2);
            return node1;
        }
    }


    public static void main(String[] args) {

        Node node1 = new Node(1);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(7);

        Node node2 = new Node(2);
        node2.next = new Node(4);
        node2.next.next = new Node(6);
        node2.next.next.next = new Node(8);

        Node node = mergeNode(node1, node2);

        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
