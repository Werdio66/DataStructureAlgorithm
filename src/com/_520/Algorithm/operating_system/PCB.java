package com._520.Algorithm.operating_system;

public class PCB {
    public String name;                 // 进程标识符
    public int arrivedTime;             // 到达时间
    public int doTime;                  // 服务时间
    public int startTime;               // 开始时间
    public int priority;                // 优先级
    public int timeSlice;               // 时间片

    public PCB(String name, int arrivedTime, int doTime) {
        this.name = name;
        this.arrivedTime = arrivedTime;
        this.doTime = doTime;
    }
}
