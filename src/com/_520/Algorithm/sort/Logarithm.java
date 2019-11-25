package com._520.Algorithm.sort;

import java.util.Arrays;

/**
 *  对数器
 */
public class Logarithm {

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

    /**
     *  对数器
     * @param arr       要排序的数组
     * @return          排序没有问题返回 true 否则返回 false
     */
    private static boolean logarithm(int[] arr){
        // 复制要排序的数组
        int[] copyArr = Arrays.copyOf(arr,arr.length);

        // 使用jdk自带的排序
        Arrays.sort(copyArr);

        // 使用自己写的排序对数组排序
        Sort.bubbleSort(arr);

        // 挨个检查
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != copyArr[i])   // 有一个不相等，返回false
                return false;
        }

        return true;
    }

    public static void main(String[] args) {


        System.out.println(logarithm(getRandomArray(100000)));
//        System.out.println(Arrays.toString(arr));

    }
}
