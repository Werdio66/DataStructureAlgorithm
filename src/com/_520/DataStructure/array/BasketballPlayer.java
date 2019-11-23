package com._520.DataStructure.array;

// 安排篮球员上场
public class BasketballPlayer {

    // 存放球衣号码
    private static Integer[] player = null;
    // 球员数
    private static int size = 0;
    // 初始化
    // 初始容量为5的线性列表,准备用来存储场上的5个球衣号码.
    public void init(int initialCapacity){
        player = new Integer[initialCapacity];
    }

    // 查询指定位置的球员的球衣号码是多少.查询索引位置为2的球衣号码是:33.
    public Integer getPlayer(int index){
        if (index >= size || index < 0){
            throw new RuntimeException("数组越界");
        }
        return player[index];
    }
    // 根据球衣号码查询该球员在场上的索引位置. 44球衣号的球员在场上的索引位置是:3.
    public int getIndex(Integer playerNum){
        for (int i = 0; i < size; i++) {
            if (player[i] == playerNum)
                return i;
        }
        return -1;
    }
    // 替换场上索引位置为2的球员,替换之后该位置的球衣编号为333. 333把33替换了.
    public void replaceByIndex(int index, Integer playerNum){
        player[index] = playerNum;
    }

    // 替换球衣号码为22的球员,替换之后为222.
    public void replaceByPlayerNum(Integer oldNum, Integer newNum){
        for (int index = 0; index < size; index++) {
            if (player[index] == oldNum)
                player[index] = newNum;
        }
    }
    // 把场上索引位置为2的球衣罚下场(注意:罚下,没有补位.).
    public void remove(int index){
        // 数组长度减1
        Integer[] copyArray = new Integer[size - 1];
        if (index >= size || index < 0){
            System.out.println("下标错误！");
            return;
        }
//        for (int i = index; i < size - 1; i++) {
//            player[i] = player[i + 1];
//        }
        // 复制索引前面的数
        System.arraycopy(player,0,copyArray,0,index);
        // 复制索引后的
        System.arraycopy(player,index+1,copyArray,index,size-index-1);
        // 更新数组
        player = copyArray;
        // 更新size
        size--;
    }

    // 安排5个球员上场:[11,22,33,44,55].
    public void add(Integer playerNum) {
        // 如果是第一个，就为数组初始化，长度为1
        if (size == 0){
            player = new Integer[1];
        }
        // 复制原数组，长度加1
        Integer[] copyArray = new Integer[size + 1];
        System.arraycopy(player,0,copyArray,0,size);
        // 更新数组
        player = copyArray;
        // 把新添加的数放入数组
        player[size] = playerNum;
        // 更新size
        size++;
    }

    // 按照球员在场上的位置,打印出球衣号码,打印风格:[11,22,33,44,55].
    public void print(){
        if (player == null){
            System.out.println("null");
            return;
        }
        if (size == 0){
            System.out.println("[]");
            return;
        }

        StringBuilder str = new StringBuilder(size * 3 + 1);
        str.append("[");
        for (int index = 0; index < player.length; index++) {
            str.append(player[index]);
            if (index != player.length - 1){
                str.append(", ");
            }else
                str.append("]");
        }
        System.out.println(str);
    }
}
