package com._520.Algorithm.kmp;

import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "BBBCC";
        String str2 = "ABCDABCDABD";
        int[] next = partialMatch(str2);
        System.out.println(Arrays.toString(next));
        int index = kmpSerach(str1, str2, next);
//        int index = bruteForce(str1, str2);
        System.out.println(index);
    }

    private static int kmpSerach(String str1, String str2, int[] next){
        for (int i = 0,j = 0; i < str1.length(); i++) {

            // 当前匹配不相等时，就从子串的第next[j - 1] 开始对比，直到从子串的第一个字符开始
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            // 当前匹配字符相等就继续往后匹配
           if (str1.charAt(i) == str2.charAt(j)){
               j++;
           }
            // 匹配完了
           if (j == str2.length())
               return i - j + 1;
        }
        return -1;
    }
    private static int[] partialMatch(String subStr) {
        // 部分匹配表
        int[] next = new int[subStr.length()];
        // 一个字符时为0
        next[0] = 0;

        for (int i = 1,j = 0; i < subStr.length(); i++) {

            while (j > 0 && subStr.charAt(i) != subStr.charAt(j))
//                j = 0;    ?? 是不是也可以
                j = next[j - 1];
            if (subStr.charAt(i) == subStr.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    // 暴力匹配
    private static int bruteForce(String str1, String str2){
        for (int i = 0, j = 0; i < str1.length();) {
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
                i++;
            }else {
                i = i - j + 1;
                j = 0;
            }
            if (j == str2.length())
                return i - j;
        }
        return -1;
    }
}
