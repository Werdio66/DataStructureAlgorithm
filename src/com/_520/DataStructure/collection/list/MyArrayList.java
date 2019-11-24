package com._520.DataStructure.collection.list;

// 简单模拟ArrayList
public class MyArrayList<T> {

    // 自定义类型数组
    private  T[] elementData = null;
    // 数组长度
    private int size = 0;

    // 根据索引查找数组中的元素
    public T getValue(int index){
        if (index >= size || index < 0){
            throw new RuntimeException("数组越界");
        }
        return elementData[index];
    }
    // 查找指定元素的下标
    public int getIndex(T elementDataNum){
        for (int i = 0; i < size; i++) {
            if (elementData[i] == elementDataNum)
                return i;
        }
        return -1;
    }
    // 修改指定下标元素的值
    public void replaceByIndex(int index, T elementDataNum){
        elementData[index] = elementDataNum;
    }

    // 修改指定元素
    public void replaceByValue(T oldValue, T newValue){
        for (int index = 0; index < size; index++) {
            if (elementData[index] == oldValue)
                elementData[index] = newValue;
        }
    }
    // 通过下标删除元素
    public void remove(int index){
        // 数组长度减1
        T[] copyArray = (T[]) new Object[size - 1];
        if (index >= size || index < 0){
            System.out.println("下标错误！");
            return;
        }
//        for (int i = index; i < size - 1; i++) {
//            elementData[i] = elementData[i + 1];
//        }
        // 复制索引前面的数
        System.arraycopy(elementData,0,copyArray,0,index);
        // 复制索引后的
        System.arraycopy(elementData,index+1,copyArray,index,size-index-1);
        // 更新数组
        elementData = copyArray;
        // 更新size
        size--;
    }

    // 添加数据
    public void add(T elementDataNum) {
        // 如果是第一个，就为数组初始化，长度为1
        if (size == 0){
            elementData = (T[]) new Object[1];
        }
        // 复制原数组，长度加1
        T[] copyArray = (T[]) new Object[size + 1];
        System.arraycopy(elementData,0,copyArray,0,size);
        // 更新数组
        elementData = copyArray;
        // 把新添加的数放入数组
        elementData[size] = elementDataNum;
        // 更新size
        size++;
    }

    // 遍历数组
    public void print(){
        //
        if (elementData == null){
            System.out.println("null");
            return;
        }
        if (size == 0){
            System.out.println("[]");
            return;
        }
        // 用来存储对象
        StringBuilder str = new StringBuilder(size * 3 + 1);
        str.append("[");
        for (int index = 0; index < elementData.length; index++) {
            str.append(elementData[index]);
            if (index != elementData.length - 1){
                str.append(", ");
            }else
                str.append("]");
        }
        System.out.println(str);
    }
}
