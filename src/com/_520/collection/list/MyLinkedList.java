package com._520.collection.list;

// 模拟LinkedList
public class MyLinkedList<T> {

    private int size = 0;   // 结点个数

    private Node<T> first = null;     // 指向首结点

    private Node<T> last = null;      // 指向尾结点

    // 结点对象
    private class Node<T>{
        Node<T> next;
        Node<T> prev;
        T element;

        private Node(Node<T> next, Node<T> prev, T element){
            this.next = next;
            this.prev = prev;
            this.element = element;
        }
    }

    public int size(){
        return size;
    }
    // 尾部添加数据
    public void addLast(T t){
        linkLast(t);
    }

    // 头部添加数据
    public void addFirst(T t){
        linkFirst(t);
    }

    // 在尾部添加
    private void linkLast(T t) {
        Node<T> node = last;
        Node<T> newNode = new Node<>(null,last,t);
        if (node == null){
            first = newNode;
        } else {
            node.next = newNode;
        }
        last = newNode;
        size++;
    }

    // 在头部添加
    private void linkFirst(T t) {
        Node<T> node = first;
        Node<T> newNode = new Node<>(first,null,t);
        if (node == null){
            last = newNode;
        } else {
            node.prev = newNode;
        }
        first = newNode;
        size++;
    }
    // 遍历所有结点
    public void print(){
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 用来存储对象
        StringBuilder str = new StringBuilder(size * 3 + 1);
        str.append("[");
        Node<T> node = first;
        while (node.next != null){
            str.append(node.element);
            str.append(", ");
            node = node.next;
        }
        str.append(node.element);
        str.append("]");
        System.out.println(str);
    }

    // 获取第一个结点
    public T getFirst(){
        return first.element;
    }

    // 获取最后一个结点
    public T getLast(){
        return last.element;
    }
}
