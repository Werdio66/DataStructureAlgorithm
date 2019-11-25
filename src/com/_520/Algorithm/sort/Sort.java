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

}
