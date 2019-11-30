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
            // 第二个数是要插入的数字
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

            // 当左边的值小于基准值时，向右移动
            while (arr[l] < pivot)
                l++;
            // 当右边的值大于基准值时，向左移动
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

    /**
     *  合并排序
     * @param arr           排序数组
     * @param left          分解的左索引
     * @param right         分解的右索引
     * @param temp          中间数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            // 递归左边
            mergeSort(arr, left, mid, temp);
            // 递归右边
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }
    /**
     *  合并
     * @param arr           合并的数组
     * @param left          左边的初始索引
     * @param mid           右边的初始索引
     * @param right         数组的最右边位置
     * @param temp          中间数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;           // 左边索引
        int j = mid + 1;        // 右边索引
        int t = 0;              // 中间数组的索引

        // 一边没有遍历完就一直执行
        while (i <= mid && j <= right){

            if (arr[i] <= arr[j]){ // 左边的小，把左边的给中间数组
                temp[t] = arr[i];
                i++;
                t++;
            }else { // 右边的小，把右边的给中间数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 如果左边的没有完，就把左边的都给中间数组
        while (i <= mid)
            temp[t++] = arr[i++];
        // 如果右边的没有完，就把右边的都给中间数组
        while (j <= right)
            temp[t++] = arr[j++];

        // 把temp数组中的数据拷回arr
        t = 0;
        int tempLeft = left;
//        System.out.println("tempLeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right)
            arr[tempLeft++] = temp[t++];
    }

    /**
     *  基数排序：刚开始按个位从小到大排序，然后十位，百位···
     *  以空间换时间  空间O（n^2）
     * @param arr   排序数组
     */
    public static void radixSort(int[] arr){

        int max = arr[0];
        // 获取数组中最大的元素
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        // 计算数组中最大元素的位数
        int maxLength = (max + "").length();
        // 定义十个桶，每个桶的大小是排序数组的长度
        int[][] bucket = new int[10][arr.length];
        // 记录每个桶中的有效长度
        int[] bucketLength = new int[10];

        //--------------- 将数据放入桶中 -----------------------
        // 循环每一位数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 计算每位的数       第一次是个位
                int digit = arr[j] / n % 10;
                // 将arr[j] 加到对应桶（bucket[digit]）的对应位置上（bucketLength[digit]）
                bucket[digit][bucketLength[digit]] = arr[j];
                // 每个桶中加入一个数据就加一
                bucketLength[digit]++;
            }

            //--------------- 将数据从桶中取出来 -----------------------
            // 记录每个桶中数的下标
            int index = 0;
            // 遍历每个桶
            for (int j = 0; j < bucket.length; j++) {
                // 如果这个桶中有数据
                if (bucketLength[j] != 0) {
                    // 将桶中的数据放入原数组中
                    for (int k = 0; k < bucketLength[j]; k++) {
                        // 将第j的桶中的第 k 个数放入原数组
                        arr[index++] = bucket[j][k];
                    }
                }

                // 每一轮完后要把当前桶的长度置为 0，清空所有的桶
                bucketLength[j] = 0;
            }
//            System.out.println("第" +(i + 1)+"次");
//            System.out.println(Arrays.toString(arr));

        }
    }

    public static void heapSort(int[] arr){

    }

    /**
     * 调整数组为大顶堆或者小顶堆
     * @param arr       要调整的数组
     * @param i         调整的非叶子节点
     * @param length    数组长度
     */
    private static void adjustSort(int[] arr,int i,int length){
        // 记录当前结点的值
        int value = arr[i];

        for (int j = 2 * i + 1; j < arr.length; j = 2 * j + 1) {
            if (j + 1 < arr.length && arr[j] > arr[j + 1]){
                j++;
            }

            if (arr[j] > value){
                arr[i] = arr[j];
            }else {
                break;
            }
        }

        arr[i] = value;


    }
}
