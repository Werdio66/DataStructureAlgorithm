package com._520.Algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 插值查找
 * 相当于对二分查找的优化
 */
public class InsertSerach {

    public static void main(String[] args) {
        int[] arr = {1,3,4,5,6,6,6,6,6,7,8,9};
        System.out.println(insertSerach1(arr, 0, arr.length - 1, 1));
        insertSerach2(arr,0,arr.length - 1,6).forEach(System.out::println);
    }
    private static int insertSerach1(int[] arr, int left, int right, int value){
        System.out.println("insertSerach1...");
        // 没有找到
        if (left > right || value < arr[left] || value > arr[right])
            return -1;
        // 自适应
        int mid = left - (left - right) * (value - arr[left]) / (arr[right] - arr[left]);
        if (value > arr[mid])   // 在右边查找
            return insertSerach1(arr,mid + 1,right,value);
        else if (value < arr[mid])// 在左边查找
            return insertSerach1(arr,left,mid - 1,value);
        else // 中间的值就是要查找的值
            return mid;
    }



    private static List<Integer> insertSerach2(int[] arr, int left, int right, int value){
        System.out.println("insertSerach2...");
        // 没有找到
        if (left > right || value < arr[left] || value > arr[right])
            return new ArrayList<>();
        int mid = left - (left - right) * (value - arr[left]) / arr[right] - arr[left];
        if (value > arr[mid])   // 在右边查找
            return insertSerach2(arr,mid + 1,right,value);
        else if (value < arr[mid])// 在左边查找
            return insertSerach2(arr,left,mid - 1,value);
        else { // 中间的值就是要查找的值
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
