package com._520.Algorithm.recursive;

import java.util.Map;

/**
 * 8 皇后问题
 */
public class Queen {

    // 用来存储皇后在每一排的位置, 即第几列
    private int[] array = new int[9];
    private static int methodCount = 0;        // 计算一共有多少种解法
    private static int recursiveCount = 0;     // 计算一共判断了多少次冲突，即调用了多少次judge

    /**
     *  添加皇后
     * @param n     添加第几个皇后
     */
    private void cheek(int n){
        // 递归结束条件
        if (n == 9){    // 八个皇后都已经放好了
            print();
            return;
        }

        // 分别把皇后放在每一排的八个位置
        for (int i = 1; i <= 8; i++) {
            // 最开始把皇后放在每一排的第一列
            array[n] = i;
            if (judge(n)){
                // 如果可以放下， 就继续放下一个皇后
                cheek(n + 1);
            }
            // 如果不可以就把这个皇后放在这一排的 i + 1 位置上
        }
    }
    /**
     *  判断是否冲突
     * @param n     第几个皇后
     * @return      冲突就返回false  否则返回true
     */
    private boolean judge(int n){
        recursiveCount++;

        // 判断是否在同一列
        // 判断是否在同一斜线
        for (int i = 1; i < n; i++) {
            // 每增加一个皇后都要判断是否和前面的皇后有冲突
            // array[n] == array[i] 判断新加的皇后和之前的皇后是否在一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断新的皇后和之前的皇后是否在同一斜线
            //
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i]))
                return false;
        }

        return true;
    }

    private void print(){
        methodCount++;
        for (int i = 1; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.cheek(1);
        System.out.println("一共有 " + methodCount + "种解法");
        System.out.println("一共执行了 " + recursiveCount + "次");
    }
}
