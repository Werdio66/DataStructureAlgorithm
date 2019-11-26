package com._520.Algorithm.search;

import java.util.Arrays;

/**
 *  斐波那契查找（黄金分割）
 */
public class FibonacciSearch {
    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,6,6,6,6,7,8,9};
        System.out.println(fibonacciSerach(arr,8));
    }

    /**
     *  创建一个指定大小的斐波那契数列
     * @param size      斐波那契长度
     * @return          斐波那契数列
     */
    private static int[] fibonacci(int size){
        int[] arr = new int[size];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < size; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }

        return arr;
    }

    /**
     *  查找指定元素在数组中的下标
     * @param arr       数组
     * @param value     指定的值
     * @return          返回下标，找不到返回 -1
     */
    private static int fibonacciSerach(int[] arr, int value){

        if (value < arr[0] || value > arr[arr.length - 1])
            return -1;
        int left = 0;
        int right = arr.length - 1;
        int k = 0;      // 在斐波那契数列中的位置
        int[] fib = fibonacci(10);      // 拿到一个斐波那契数列
        //
        while (right > fib[k] - 1)
            k++;
        int mid = 0;       // 中值

        // 构造新的满足斐波那契数列的数组
        int[] temp = Arrays.copyOf(arr, fib[k]);
        // 将多余的位置赋值为 最后一个数的值
        for (int i = right + 1; i < fib[k]; i++) {
            temp[i] = temp[right];
        }
        while (left <= right){
            mid = left + fib[k - 1] - 1;        //
            if (value > temp[mid]){
                left = mid + 1;
                k -= 1;
            }else if (value < temp[mid]){
                right = mid - 1;
                k -= 2;
            }else {
                if (mid <= right)
                    return mid;
                else
                    return right;
            }
        }


        return -1;
    }


}
