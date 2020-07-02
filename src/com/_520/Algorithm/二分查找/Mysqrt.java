package com._520.Algorithm.二分查找;

/**
 * 一个数 x 的开方 sqrt 一定在 0 \~ x 之间，并且满足 sqrt == x / sqrt 。
 * 可以利用二分查找在 0 \~ x 之间查找 sqrt。
 *
 * @author Werdio丶
 * @since 2020-07-02 06:50:24
 */
public class Mysqrt {

    public int mySqrt(int x) {
        if (x <= 1){
            return x;
        }
        int l = 1, r = x;

        while (l <= r){
            int mid = l + (r - l) >> 1;

            int sqrt = x / mid;
            if (mid == sqrt){
                return mid;
            }else if (sqrt < mid){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return r;
    }

    public int mySqrt1(int x) {
        if (x <= 1){
            return x;
        }
        int l = 1, r = x;

        while (l <= r){
            int mid = l + (r - l) / 2;

            if (x == mid * mid){
                return mid;
            }else if (x < mid * mid){
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }

        return r;
    }
    public static void main(String[] args) {
        Mysqrt m = new Mysqrt();
        System.out.println(m.mySqrt1(5));
    }
}
