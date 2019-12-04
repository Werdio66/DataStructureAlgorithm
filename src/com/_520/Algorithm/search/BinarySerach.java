package com._520.Algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySerach {

    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,6,6,6,6,7,8,9};
        System.out.println(binarySerach1(arr, 0, arr.length - 1, 1));
        binarySerach2(arr,0,arr.length - 1,6).forEach(System.out::println);
        System.out.println("index = " + binatySerach(arr, 9));
    }
    /**
     * 二分查找一个下标，递归
     *
     * @param arr           查找的数组
     * @param left          左索引
     * @param right         右索引
     * @param value         查找的值
     */
    private static int binarySerach1(int[] arr, int left, int right, int value) {
        System.out.println("binarySerach1..");
        // 没有找到
        if (left > right)
            return -1;
        int mid = left - (left - right) / 2;
        if (value > arr[mid])   // 在右边查找
            return binarySerach1(arr,mid + 1,right,value);
        else if (value < arr[mid])// 在左边查找
            return binarySerach1(arr,left,mid - 1,value);
        else // 中间的值就是要查找的值
            return mid;
    }

    /**
     *  二分查找，非递归
     * @param arr       数组
     * @param target    查找的值
     * @return          下标，-1
     */
    private static int binatySerach(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right){
            int mid = (left + right) / 2;
            if (arr[mid] == target)
                return mid;
            else if (arr[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        return -1;
    }
    /**
     *  二分查找所有相同元素的下标
     * @param arr           数组
     * @param left          左下标
     * @param right         右下标
     * @param value         查找的值
     * @return              查找到，返回所有的下标，没有返回空集
     */
    private static List<Integer> binarySerach2(int[] arr, int left, int right, int value) {
        // 没有找到
        if (left > right)
            return new ArrayList<>();
        int mid = (left + right) / 2;
        if (value > arr[mid])   // 在右边查找
            return binarySerach2(arr,mid + 1,right,value);
        else if (value < arr[mid])// 在左边查找
            return binarySerach2(arr,left,mid - 1,value);
        else {// 找到了要查找的值
            int index = mid - 1;
            List<Integer> list = new ArrayList<>();
            while (index >= 0 && arr[index] == arr[mid]){   // 查找左边相同的数
                list.add(index);
                index--;
            }
            list.add(mid);
            index = mid + 1;
            while (index < arr.length && arr[index] == arr[mid]){   // 查找右边相同的数
                list.add(index);
                index++;
            }
            
            return list;
        }

    }
}
