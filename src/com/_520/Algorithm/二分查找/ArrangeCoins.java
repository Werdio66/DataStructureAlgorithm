package com._520.Algorithm.二分查找;

/**
 * 第 i 行摆 i 个，统计能够摆的行数。
 * 返回 h 而不是 l，因为摆的硬币最后一行不能算进去。
 *
 * @author Werdio丶
 * @since 2020-07-02 07:35:13
 */
public class ArrangeCoins {

    public int arrangeCoins(int n){
        int level = 1;

        while (n > 0){
            n -= level;
            level++;
        }

        return n == 0 ? level - 1 : level - 2;
    }

    public static void main(String[] args) {
        ArrangeCoins a = new ArrangeCoins();
        System.out.println(a.arrangeCoins(4));
    }
}
