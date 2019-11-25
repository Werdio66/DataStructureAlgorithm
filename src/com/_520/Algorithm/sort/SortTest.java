package com._520.Algorithm.sort;


public class SortTest {

    public static void main(String[] args) {
        int[] arr = new int[100000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*1000000);
        }

        long begin = System.currentTimeMillis();
        Sort.bubbleSort(arr);
        System.out.println("一共用了" + (System.currentTimeMillis() - begin) + "毫秒");
    }
}
