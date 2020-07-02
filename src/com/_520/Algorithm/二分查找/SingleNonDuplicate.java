package com._520.Algorithm.二分查找;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * @author Werdio丶
 * @since 2020-07-02 09:35:57
 */
public class SingleNonDuplicate {

    public int singleNonDuplicate(int[] nums) {

        int l = 0, r = nums.length - 1;

        while (l < r){
            int mid = l + (r - l) / 2;
            // 保证在偶数位
            if (mid % 2 == 1){
                mid--;
            }
            if (nums[mid] == nums[mid + 1]){
                l = mid + 2;
            }else {
                r = mid;
            }
        }

        return nums[l];
    }


    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6};
        SingleNonDuplicate s = new SingleNonDuplicate();
        System.out.println(s.singleNonDuplicate(arr));
    }
}
