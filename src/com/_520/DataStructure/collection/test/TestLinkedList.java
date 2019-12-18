package com._520.DataStructure.collection.test;

import com._520.collection.list.MyLinkedList;

public class TestLinkedList {
    public static void main(String[] args) {

        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.addLast(5);
        linkedList.addLast(1);
        linkedList.addLast(3);
        linkedList.addLast(6);
        linkedList.addLast(2);
        linkedList.addLast(9);
        linkedList.print();
        System.out.println(linkedList.size());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println("---------------------------------");
        linkedList.addFirst(4);
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        linkedList.addFirst(7);
        linkedList.addFirst(5);
        linkedList.print();
        System.out.println(linkedList.size());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
    }
}
