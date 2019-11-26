package com._520.DataStructure.hashtable;

import java.util.Scanner;

/**
 *  模拟hashtable实现对用户的CRUD
 */
public class HashTableDemo {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(6);
        String key = "";        // 接收用户输入
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("add 添加数据");
            System.out.println("list 查询数据");
            System.out.println("exit 退出");

            System.out.println("请输入指令：");
            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入用户id");
                    int id = scanner.nextInt();
                    System.out.println("请输入用户name");
                    String name = scanner.next();
                    hashTable.add(new Employee(id,name));
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "exit":
                    System.out.println("退出");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    // 存储多条链表
    static class HashTable{
        EmployeeLinkedList[] employeeLinkedLists;
        private int size;       // 一共多少条链表
        public HashTable(int size){
            this.size = size;
            employeeLinkedLists = new EmployeeLinkedList[size];
            // 初始化每条链表
            for (int i = 0; i < size; i++) {
                employeeLinkedLists[i] = new EmployeeLinkedList();
            }
        }

        public void add(Employee employee){
            // 得到插入哪条链表的id
            int id = hashFunction(employee.id);
            employeeLinkedLists[id].add(employee);
        }

        public void list(){
            for (int i = 0; i < size; i++) {
                employeeLinkedLists[i].list(i + 1);
            }
        }
        // 哈希函数
        private int hashFunction(int id){
            return id % size;
        }
    }
    // 存储员工的链表
    static class EmployeeLinkedList {
        Employee head;  // 链表的头指针，默认为空

        // 增加员工
        public void add(Employee employee) {
            // 如果链表为空，就把员工给head
            if (head == null){
                head = employee;
                return;
            }

            // 不为空
            // 临时指针，指向第一个
            Employee courentEmp = head;
            while (courentEmp.next != null){
                // 遍历找到最后一个结点
                courentEmp = courentEmp.next;
            }
            // 把新的员工加到后面
            courentEmp.next = employee;
        }

        // 查看所有的员工
        public void list(int id){
            if (head == null){
                System.out.println("第" + (id + 1) + "条链表为空");
                return;
            }
            System.out.print("第"+(id + 1)+"条链表的数据为 ");
            // 临时指针，指向第一个
            Employee courentEmp = head;
            while (courentEmp != null){

                System.out.print(" --> id = " + courentEmp.id + " name = " + courentEmp.name);
                // 遍历找到最后一个结点
                courentEmp = courentEmp.next;
            }
            System.out.println();
        }
    }
    // 员工类
    static class Employee{
        int id;
        String name;
        Employee next;      // 指向下一个员工
        public Employee(int id, String name){
            this.id = id;
            this.name = name;
        }
    }
}
