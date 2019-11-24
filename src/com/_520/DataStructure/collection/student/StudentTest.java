package com._520.DataStructure.collection.student;

import com._520.DataStructure.collection.list.MyArrayList;

public class StudentTest {
    public static void main(String[] args) {
        Student student = new Student();
        Student student1 = new Student();
        Student student2 = new Student();
        MyArrayList<Student> studentList = new MyArrayList<>();
        student.setName("张三");
        student.setAge(34);
        student.setGrade(44);
        student.setClassNum("class 05");
        student1.setName("李四");
        student1.setAge(24);
        student1.setGrade(12);
        student1.setClassNum("class 05");
        student2.setName("王五");
        student2.setAge(26);
        student2.setGrade(77);
        student2.setClassNum("class 04");
        studentList.add(student);
        studentList.add(student1);
        studentList.add(student2);
        studentList.print();

        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(26);
        student3.setGrade(88);
        student3.setClassNum("class 04");
        studentList.replaceByValue(student2,student3);
        studentList.print();

    }
}
