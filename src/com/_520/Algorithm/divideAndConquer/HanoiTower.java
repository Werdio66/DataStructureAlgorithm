package com._520.Algorithm.divideAndConquer;

public class HanoiTower {
    private static int count = 0;
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
        System.out.println(count);
    }

    private static void hanoiTower(int number, char a, char b, char c){
        count++;
        if (number == 1)
            System.out.println("第" + number + "个盘子从 " + a + " --> " + c);
        else {
            // 将所有的盘子分为俩个，最底下的，剩余其他的
            hanoiTower(number - 1, a, c, b);
            // 将最底下的从a移动到c
            System.out.println("第" + number + "个盘子从 " + a + " --> " + c);
            // 将b中的都移动到c
            hanoiTower(number - 1, b, a, c);
        }
    }
}
