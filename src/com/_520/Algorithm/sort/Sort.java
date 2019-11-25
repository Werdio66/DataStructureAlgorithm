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

}
