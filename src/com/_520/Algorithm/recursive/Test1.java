package com._520.Algorithm.recursive;

import java.util.HashMap;

public class Test1 {

    private static HashMap<Integer, Integer> map = new HashMap<>();

    // 递归求和
    private static int sum(int num){
        if (num == 1)
            return 1;

        return sum(num - 1) + num;
    }


    // 斐波那契数列第n项值
    private static int sum1(int num){

        map.put(num,-1);

        if (num <= 2)
            return 1;
        if (map.get(num) == -1){
            int value = sum1(num - 1) + sum1(num - 2);
            map.replace(num,-1,value);
            return value;
        }
        else
            return map.get(num);
    }


    // 小青蛙一次可以挑一个或者两个台阶，一共有多少跳法
    private static int f(int n){
        if (n <= 2)
            return n;


        return f(n - 1) + f(n - 2);
    }
    public static void main(String[] args) {
//        System.out.println(sum(10));
        System.out.println(sum1(5));
//        System.out.println(f(4));
    }
}
