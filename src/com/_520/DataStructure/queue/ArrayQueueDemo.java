package com._520.DataStructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {


    public static void main(String[] args) {

        String key = "";
        boolean bool = true;
        ArrayQueue queue = new ArrayQueue(4);
        while (bool){
            System.out.println("list   查看队列中的所有元素");
            System.out.println("get   获取第一个元素");
            System.out.println("add   添加元素元素");
            System.out.println("head   查看头部元素元素");
            System.out.println("exit   退出");

            Scanner scanner = new Scanner(System.in);
            key = scanner.next();

            switch (key){
                case "l":
                    try {
                        queue.listQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "g":
                    try {
                        System.out.println(queue.getQueue());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "a":
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case "h":
                    System.out.println(queue.headQueue());
                    break;
                case "e":bool = false;
                    break;
                default:break;

            }
        }

        System.out.println("程序退出");
    }
}
class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize){
        this.maxSize = maxSize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public boolean isFull(){
        return rear == maxSize - 1;
    }

    public void addQueue(int value){
        if (isFull())
            throw new RuntimeException("队列满了");

        arr[++rear] = value;
    }

    public int getQueue(){
        if (isEmpty())
            throw new RuntimeException("队列为空");

        front++;
        return arr[front];
    }

    public void listQueue(){
        if (isEmpty())
            throw new RuntimeException("队列为空");

        for (int value:arr
             ) {
            System.out.println(value);
        }
    }

    public int headQueue(){
        if (isEmpty())
            throw new RuntimeException("队列为空");
        return arr[front + 1];
    }

}