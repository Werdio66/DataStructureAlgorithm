package com._520.Algorithm.sort;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortTest {

    /**
     *  生成一个指定大小的随机数组
     * @param size      数组的长度
     * @return          生成的数组
     */
    private static int[] getRandomArray(int size){
        int[] arr = new int[size];

        // 生成随机数组
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*1000000);
        }

        return arr;
    }

    private static int[] array = new int[]{1,3,2,4,7,5,6,9,8,0};

    @Test
    void testQuick(){
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        Sort.quickSort(array,0,array.length-1);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));

        int[] arr = getRandomArray(100000);
        long begin = System.currentTimeMillis();
        Sort.quickSort(arr,0,arr.length-1);    //
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }
    @Test
    void testShell(){
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        Sort.shellSort(array);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));

        int[] arr = getRandomArray(100000);
        long begin = System.currentTimeMillis();
        Sort.shellSort(arr);       // 交换法：1200      移动位置：24
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }
    @Test
    void testInsert(){
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        Sort.insertSort(array);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*1000000);
        }
        long begin = System.currentTimeMillis();
        Sort.insertSort(arr);       // 14848
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }



    @Test
    void testBubble(){
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*1000000);
        }
        long begin = System.currentTimeMillis();
        Sort.bubbleSort(arr);       // 22408
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }

    @Test
    void testSelect(){
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(array));
//        Sort.selectSort(array);
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(array));
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*1000000);
        }
        long begin = System.currentTimeMillis();
        Sort.selectSort(arr);       // 5382
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }
}
