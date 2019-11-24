package com._520.Algorithm.recursive;

public class Maze {

    /**
     *  判断是否可达
     * @param map       地图
     * @param i         起始位置
     * @param j         起始位置
     * @return          可以到达返回 true 不能返回 false
     */
    private static boolean canGet(int[][] map, int i, int j){
        //
        if (map[6][5] == 2)
            return true;
        else {
            // 当前位置没有走过
            if (map[i][j] == 0){
                // 默认为可以走通
                map[i][j] = 2;
                // 分不同的测量走  下 右 左 上
                if (canGet(map, i + 1, j)){ // 向下走
                    return true;
                }else if (canGet(map, i, j + 1)){   // 向右走
                    return true;
                }else if (canGet(map, i - 1, j)) {  // 向左走
                    return true;
                }else if (canGet(map, i, j + 1)){   // 向上走
                    return true;
                }else { // 都走不通
                    map[i][j] = 3;
                    return false;
                }

            }else { // 当前map[i][j] = 1   2   3
                return false;
            }
        }
    }

    // 遍历二维数组
    private static void print(int[][] arr){
        for (int[] array:arr
             ) {
            for (int date:array
                 ) {
                System.out.print(date + "  ");
            }

            System.out.println();
        }
    }
    public static void main(String[] args) {
        // 地图
        int[][] map = new int[8][7];

        // 初始化地图四周为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 初始化地图四周为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 设置障碍
        map[3][4] = 1;
        map[5][5] = 1;
        map[2][1] = 1;
        map[3][5] = 1;
        map[5][2] = 1;
        map[5][4] = 1;
        System.out.println("初始地图");
        print(map);

        // 从指定位置开始走
        canGet(map, 1, 1);

        System.out.println("走过的地图");
        print(map);
    }
}
