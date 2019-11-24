package com._520.Algorithm.recursive;

public class StringDemo {


    /**
     *  字符串匹配(暴力法)
     * @param dadStr        指定字符串
     * @param subStr        包含的字串
     * @return              包含 字串第一个下标      不包含 -1
     */
    private static int contains(String dadStr, String subStr){

        for (int i = 0; i < dadStr.length() - subStr.length() + 1; i++) {
            int k = i;
            int j = 0;
            for (;j < subStr.length();) {
                if (dadStr.charAt(k) == subStr.charAt(j)){
                    k++;
                    j++;
                }else {
                    break;
                }
                if (dadStr.charAt(k) == subStr.charAt(subStr.length() - 1))
                    return k - subStr.length() + 1;
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        String str = "abdfcvghi";
        String str2 = "dfcvg";
        System.out.println(contains(str,str2));
    }
}
