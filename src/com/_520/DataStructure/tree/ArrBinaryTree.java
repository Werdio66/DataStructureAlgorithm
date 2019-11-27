package com._520.DataStructure.tree;

/**
 * 顺序二叉树
 */
public class ArrBinaryTree {
    public static void main(String[] args) {
        int[] arr =new int[]{1,2,3,4,5};
        ArrayTree arrayTree = new ArrayTree(arr);
//        arrayTree.prevOrder();
//        arrayTree.midOrder();
        arrayTree.lastOrder();
    }


    static class ArrayTree{
        int[] arr;

        public ArrayTree(int[] arr){
            this.arr = arr;
        }

        public void prevOrder(){
            this.prevOrder(0);
        }

        public void midOrder(){
            this.midOrder(0);
        }

        public void lastOrder(){
            this.lastOrder(0);
        }
        private void prevOrder(int index){
            // 输出当前结点值
            System.out.println(arr[index]);

            // 向左递归
            if ((index * 2 + 1) < arr.length){
                prevOrder(index * 2 + 1);
            }
            // 向右递归
            if ((index * 2 + 2) < arr.length){
                prevOrder(index * 2 + 2);
            }
        }

        public void midOrder(int index){

            // 向左递归
            if ((index * 2 + 1) < arr.length){
                midOrder(index * 2 + 1);
            }
            // 输出当前结点值
            System.out.println(arr[index]);
            // 向右递归
            if ((index * 2 + 2) < arr.length){
                midOrder(index * 2 + 2);
            }
        }

        public void lastOrder(int index){

            // 向左递归
            if ((index * 2 + 1) < arr.length){
                lastOrder(index * 2 + 1);
            }
            // 向右递归
            if ((index * 2 + 2) < arr.length){
                lastOrder(index * 2 + 2);
            }
            // 输出当前结点值
            System.out.println(arr[index]);
        }

    }
}
