package com._520.Algorithm.recursive;

public class Test2 {

    private static class Node{
        int val;
        Node next;
        private Node(int val){
            this.val = val;
        }
    }

    // 反转链表
    private static Node recursiveNode(Node head) {
        // 如果为空表或者最后一个结点就结束
        if (head == null || head.next == null)
            return head;

        Node newNode = recursiveNode(head.next);
        Node t1 = head.next;
        t1.next = head;
        head.next = null;
        return newNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        node1.next = new Node(2);
        node1.next.next = new Node(3);
        node1.next.next.next = new Node(4);
        Node recursiveNode = recursiveNode(node1);
        while (recursiveNode != null){
            System.out.println(recursiveNode.val);
            recursiveNode = recursiveNode.next;
        }
    }
}
