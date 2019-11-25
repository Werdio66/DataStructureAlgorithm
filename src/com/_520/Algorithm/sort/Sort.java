package com._520.Algorithm.sort;

import java.util.Arrays;

public class Sort {

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
        冒泡排序
     */
    public static void bubbleSort(int[] arr) {
        // 标识这一轮是否交换过
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换过，证明没有排好序
                    flag = true;
                    swap(arr, j, j + 1);
                }
            }
//            System.out.println("第" + (i + 1) + "次排序");
//            System.out.println(Arrays.toString(arr));
            if (!flag){
                // 没有交换过，已经排好序了
                break;
            }else {
                flag = false;   // 重置flag
            }
        }
    }

    /*
        选择排序
     */
    public static void selectSort(int[] arr){

        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;          // 最小值下标
            int value = arr[i];     // 最小值
            for (int j = i + 1; j < arr.length; j++) {
                if (value > arr[j]){    // 如果当前值不是最小值，就更新 index 和 value
                    index = j;
                    value = arr[j];
                }
            }
            // 把最小值和 i 下标的数交换
            arr[index] = arr[i];
            arr[i] = value;
//            System.out.println("第" + (i + 1) + "轮排序");
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void insertSort(int[] arr){

        // 从第2个数开始插入
        for (int i = 1; i < arr.length; i++) {
            // 第二个数是要插入的数组
            int insertValue = arr[i];
            // 要插入位置数字的下标
            int insertIndex = i - 1;
            // 插入的下标必须大于等于0，插入的值要比前一个位置的值小
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                // 把这个大的数后移
                arr[insertIndex + 1] = arr[insertIndex];
                // 往前继续找
                insertIndex--;
            }

            // 说明已经找到了对应的位置
            arr[insertIndex + 1] = insertValue;

//            System.out.println("第" + i + "轮");
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     *  希尔排序    交换法
     * @param arr   排序数组
     */
//    public static void shellSort(int[] arr){
//        int count = 0;
//        // 把数组分为gap组
//        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            //
//            for (int i = gap; i < arr.length; i++) {
//                // 为每组插入排序，每次都和前面已插入的比较
//                for (int j = i - gap; j >= 0; j -= gap) {
//                    if (arr[j] > arr[j + gap])
//                        swap(arr, j, j + gap);
//                }
//
//
//            }
////            System.out.println("第" + (++count) + "轮");
//////            System.out.println(Arrays.toString(arr));
//        }
//    }

    public static void shellSort(int[] arr){
//        int count = 0;
        // 把数组分为gap组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //
            for (int i = gap; i < arr.length; i++) {
                // 第二个数是要插入的数组
                int insertValue = arr[i];
                // 要插入位置数字的下标
                int insertIndex = i;
                // 后面的数小于前面的数时，移动位置
                while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]){
                    // 移动
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                // 找到了正确的位置
                arr[insertIndex] = insertValue;



            }
//            System.out.println("第" + (++count) + "轮");
//            System.out.println(Arrays.toString(arr));
        }
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];    // 基准值

        while (l < r){

            // 当左边的值小于等于基准值时，向右移动
            while (arr[l] < pivot)
                l++;
            // 当右边的值大于等于基准值时，向左移动
            while (arr[r] > pivot)
                r--;

            // 左边的已经小于等于基准值，右边的已经大于等于基准值
            if (l > r)
                break;
            // 交换
            swap(arr,l,r);
            // 交换后左边的值等于基准值，继续向右移动
            if (arr[l] == pivot)
                l++;
            // 交换后右边的值等于基准值，向左移动
            if (arr[r] == pivot)
                r--;
//            System.out.println("第一轮");
//            System.out.println(Arrays.toString(arr));
        }
        // 左递归
        if (left < r)
            quickSort(arr, left, r);
        // 右递归
        if (right > l)
            quickSort(arr,l,right);
    }
}
