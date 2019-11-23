package com._520.Algorithm.recursive;

import java.util.HashMap;

public class Tribonacci {

    private static HashMap<Integer, Integer> map = new HashMap<>();

    private int tribonacci(int n) {
        map.put(n,-1);
        if (n == 0)
            return 0;
        if (n == 1 || n == 2)
            return 1;

        if (map.get(n) == -1){
            int value = tribonacci(n - 1) + tribonacci(n - 2) + tribonacci(n - 3);
            map.replace(n,-1,value);
            return value;
        }else
            return map.get(n);
    }

    private static int f(int n){
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int f1 = 0;
        int f2 = 1;
        int f3 = 1;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = f1 + f2 + f3;
            f1 = f2;
            f2 = f3;
            f3 = sum;
        }

        return sum;
    }
    public static void main(String[] args) {
        System.out.println(f(25));
    }
}
