package com._520.Algorithm.operating_system;


import java.util.Random;

public class PCB implements Comparable<PCB> {
    public String name;                 // 进程标识符
    public int arrivedTime;             // 到达时间
    public int doTime;                  // 服务时间
    public int startTime;               // 开始时间
    public int finshTime;               // 完成时间
    public int priority;                // 优先级
    public int timeSlice;               // 时间片
    public int roundTime;               // 轮转时间
    public double aveRoundTime;         // 带权轮转时间
    public int waitTime;                // 等待时间
    public double ratio;                // 响应比

    public PCB(String name, int arrivedTime, int doTime) {
        this.name = name;
        this.arrivedTime = arrivedTime;
        this.doTime = doTime;
        this.timeSlice = doTime;
        this.priority = new Random().nextInt(8);
    }

    @Override
    public String toString() {
        return "PCB{" +
                "name='" + name + '\'' +
                ", arrivedTime=" + arrivedTime +
                ", doTime=" + doTime +
                ", startTime=" + startTime +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(PCB o) {
        if (this.arrivedTime == o.arrivedTime)
            return this.doTime - o.doTime;
        else
            return arrivedTime - o.arrivedTime;
    }

}
