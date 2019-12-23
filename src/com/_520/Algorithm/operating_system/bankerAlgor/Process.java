package com._520.Algorithm.operating_system.bankerAlgor;

import java.util.Arrays;

public class Process {
    private String name;                // 进程名称
    private int[] max;                  // 最大需求
    private int[] allocation;           // 当前已分配资源数
    private int[] need;                 // 需求数
    private int[] work;                 // 可分配资源数
    private int[] workAndAllocation;    // 当前进程释放资源后的资源总数
    private boolean isFinish = false;   // 是否有足够的资源分配给进程


    public Process(String name, int[] max, int[] allocation) {
        this.name = name;
        this.max = max;
        this.allocation = allocation;
        need = new int[max.length];
        for (int i = 0; i < max.length; i++) {
            need[i] = max[i] - allocation[i];
        }
    }

    public void printProcess(){
        System.out.println(name + "    " + Arrays.toString(max) + "  " + Arrays.toString(allocation) + "  "
                + Arrays.toString(need));
    }

    public void printSafeProcess(){
        System.out.println(name + "   " + Arrays.toString(work) + "    " + Arrays.toString(need) + "   "
                + Arrays.toString(allocation) + "       " + Arrays.toString(workAndAllocation) + "         " + isFinish);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getMax() {
        return max;
    }

    public void setMax(int[] max) {
        this.max = max;
    }

    public int[] getAllocation() {
        return allocation;
    }

    public void setAllocation(int[] allocation) {
        this.allocation = allocation;
    }

    public int[] getNeed() {
        return need;
    }

    public void setNeed(int[] need) {
        this.need = need;
    }

    public int[] getWork() {
        return work;
    }

    public void setWork(int[] work) {
        this.work = work;
    }

    public int[] getWorkAndAllocation() {
        return workAndAllocation;
    }

    public void setWorkAndAllocation(int[] workAndAllocation) {
        this.workAndAllocation = workAndAllocation;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
