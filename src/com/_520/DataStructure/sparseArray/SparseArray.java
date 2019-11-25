package com._520.DataStructure.sparseArray;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SparseArray {

    // 创建的二维数组
    private static int[][] chessArray1 = new int[11][11];

    // 遍历二维数组
    private static void print(int[][] arr){
        for (int[] i:arr
             ) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print("\t" + i[j]);
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        // 原始二维数组
        print(chessArray1);

        // 记录有效数字个数
        int count = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j < chessArray1.length; j++) {
                if (chessArray1[i][j] != 0)
                    count++;
            }
        }

        // 初始化稀疏数组
        int[][] spraseArray = new int[count + 1][3];
        spraseArray[0][0] = chessArray1.length;
        spraseArray[0][1] = chessArray1.length;
        spraseArray[0][2] = count;

        // 为稀疏数组赋值
        count = 0;
        for (int i = 0; i < chessArray1.length; i++) {
            for (int j = 0; j < chessArray1.length; j++) {
                if (chessArray1[i][j] != 0){
                    count++;
                    spraseArray[count][0] = i;
                    spraseArray[count][1] = j;
                    spraseArray[count][2] = chessArray1[i][j];
                }
            }
        }
        // 将稀疏数组存放到文件中
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("/f:/date.txt");
            for (int[] arr:spraseArray
            ) {
                for (int date:arr
                ) {
                    fileWriter.write(date);
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 遍历稀疏数组
        System.out.println("稀疏数组");
        print(spraseArray);

        // 将稀疏数组从文件中取出

        int[][] sparseArray1 = new int[spraseArray.length][3];
        FileReader fileReader = null;

        int date = -1;
        int m = 0;
        int j = 0;
        try {
            fileReader = new FileReader("/f:/date.txt");
            while ((date = fileReader.read()) != -1){
                if (j == 3){
                    j = 0;
                    m++;
                }
//                System.out.println("date = " + date);
                sparseArray1[m][j] = date;
                j++;

            }

            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出从文件中取出的数据
        System.out.println("从文件中取出的稀疏数组");
        print(sparseArray1);
        // 将稀疏数组转换为二维数组
        int[][] chessArray2 = new int[sparseArray1[0][0]][sparseArray1[0][0]];

        // 为新二维数组赋值
        for (int i = 1; i < sparseArray1.length; i++) {
            chessArray2[sparseArray1[i][0]][sparseArray1[i][1]] = sparseArray1[i][2];
        }

        // 转换后
        System.out.println("恢复后的二维数组");
        print(chessArray2);
    }
}
