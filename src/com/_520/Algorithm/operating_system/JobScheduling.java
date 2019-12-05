package com._520.Algorithm.operating_system;

import java.util.*;

public class JobScheduling {
    // 用于存放所有的pcb
    private static List<PCB> allPcb = new ArrayList<>();
    // 存放已经进入队列的pcb
    private static List<PCB> enterPcb = new LinkedList<>();
    // 存放已经处理过的pcb
    private static List<PCB> dealPcb = new ArrayList<>();


    /**
     *  初始化
     */
    private static void init(){
        PCB pcb1 = new PCB("a",0,5);
        PCB pcb2 = new PCB("b",1,4);
        PCB pcb3 = new PCB("c",3,3);
        PCB pcb4 = new PCB("d",4,8);
        PCB pcb5 = new PCB("e",6,2);
        allPcb.add(pcb1);
        allPcb.add(pcb2);
        allPcb.add(pcb3);
        allPcb.add(pcb4);
        allPcb.add(pcb5);
        allPcb.sort(new ArrivedSort());
    }
    /**
     *  先来先服务
     */
    private static void FCFS() {
        PcbQueue queue = new PcbQueue();
        queue.enQueue();
        while (!isFinish()){
            queue.deQueue();
            queue.enQueue();
        }
        queue.print();
    }

    /**
     *  短作业
     */
    private static void SJF(){
        PcbQueue queue = new PcbQueue();
        allPcb.sort(new ServiceSort());
        queue.enQueue();
        while (!isFinish()){
            queue.deQueue();
            queue.enQueue();
        }
        queue.print();
    }

    /**
     *  高响应比优先调度
     */
    private static void HRRN(){
        PcbQueue queue = new PcbQueue();
        allPcb.sort(new ResponseRatio());
        queue.enQueue();
        while (!isFinish()){
            queue.deQueue();

            queue.enQueue();
        }
        queue.print();
    }

    /**
     * 时间片轮转
     * @param timeSlice     时间片
     */
    private static void RR(int timeSlice){
        PcbQueue queue = new PcbQueue();
        queue.enQueue();
        while (!isFinish()){
            queue.deQueue(timeSlice);
            if (enterPcb.size() < allPcb.size())
                queue.enQueue();
        }
        queue.print();

    }
    public static void main(String[] args) {
        init();
//        System.out.println("先到先服务");
//        FCFS();
//        System.out.println("短作业");
//        SJF();
//        System.out.println("时间片轮转");
        RR(2);
    }

    private static boolean isFinish(){
        return dealPcb.size() == allPcb.size();
    }

    static class PcbQueue{
        // 记录当前遍历的是哪个pcb
        private int count = 0;
        // 记录当前时间
        private int nowTime = 0;
        // 当前执行的进程
        private PCB nowPrecess = null;
        // 入队，
        public void enQueue(){
            while (count < allPcb.size() && enterPcb.size() == 0){

                while (allPcb.get(count).arrivedTime > nowTime){
                    nowTime++;
                }

                // 将符合数据的额所有pcb加入enterPcb中
                if (allPcb.get(count).arrivedTime <= nowTime){
                    enterPcb.add(allPcb.get(count));
                    count++;
                }else {
                    break;
                }
            }
        }

        // 出队，为当前进程设置相关的字段值
        public void deQueue(int timeSlice){
            // 当前进程为第一个进入队列的
            if (enterPcb.size() == 0){
                return;
            }
            nowPrecess = enterPcb.remove(0);
            nowPrecess.timeSlice -= timeSlice;
            if (nowPrecess.timeSlice <= 0){

                // 计算开始时间
                nowPrecess.startTime = nowPrecess.arrivedTime;
                // 计算结束时间
                nowPrecess.finshTime = nowTime + timeSlice;
                // 计算周转时间
                nowPrecess.roundTime = nowPrecess.finshTime - nowPrecess.arrivedTime;
                // 计算平均周转时间
                nowPrecess.aveRoundTime = (double) nowPrecess.roundTime / nowPrecess.doTime;
                // 获得结束时间，即当前时间，方便判断剩下的进程是否已到达

                //经处理过数据后加入dealPcb容器
                dealPcb.add(nowPrecess);
            }else {
                nowTime += timeSlice;
                enterPcb.add(nowPrecess);
            }

        }
        // 出队，为当前进程设置相关的字段值
        public void deQueue(){
            // 当前进程为第一个进入队列的
            if (enterPcb.size() == 0){
                return;
            }
//            for (int i = 0; i < allPcb.size(); i++) {
//                // 计算等待时间
//                allPcb.get(i).waitTime = nowTime - allPcb.get(i).arrivedTime > 0 ? nowTime - allPcb.get(i).arrivedTime : 0;
//                // 计算响应比
//                allPcb.get(i).ratio = (allPcb.get(i).waitTime + allPcb.get(i).doTime) / allPcb.get(i).doTime;
//                allPcb.sort(new ResponseRatio());
//                System.out.println(allPcb.get(i).waitTime);
//            }
            nowPrecess = enterPcb.remove(0);
            // 计算开始时间，即为上一个进程的结束时间
            nowPrecess.startTime = nowTime;
        // 计算结束时间，该进程开始时间+服务时间
            nowPrecess.finshTime = nowPrecess.startTime + nowPrecess.doTime;
            // 计算周转时间
            nowPrecess.roundTime = nowPrecess.finshTime - nowPrecess.arrivedTime;
            // 计算平均周转时间
            nowPrecess.aveRoundTime = (double) nowPrecess.roundTime / nowPrecess.doTime;
            // 获得结束时间，即当前时间，方便判断剩下的进程是否已到达
            nowTime = nowPrecess.finshTime;
            //经处理过数据后加入dealPcb容器
            dealPcb.add(nowPrecess);
        }
        public void print(){
            System.out.println("进程名 到达时间  服务时间   开始时间  完成时间  周转时间  带权周转时间  响应比");
            for (PCB pcb:dealPcb
                 ) {
                System.out.println(pcb.name + "        " + pcb.arrivedTime + "         " + pcb.doTime + "        " +
                        pcb.startTime + "        " + pcb.finshTime + "         " +
                        pcb.roundTime + "          " + pcb.aveRoundTime +"       " + pcb.ratio);
            }

            dealPcb.clear();
        }
    }
}

/**
 *  先来先服务的比较器
 */
class ArrivedSort implements Comparator<PCB>{
    @Override
    public int compare(PCB o1, PCB o2) {
        if (o1.arrivedTime == o2.arrivedTime)
            return o1.doTime - o2.doTime;
        else
            return o1.arrivedTime - o2.arrivedTime;
    }
}

/**
 *  短作业的比较器
 */
class ServiceSort implements Comparator<PCB>{

    @Override
    public int compare(PCB o1, PCB o2) {

        return o1.doTime - o2.doTime;
    }
}

/**
 *  响应比的比较器
 */
class ResponseRatio implements Comparator<PCB>{

    @Override
    public int compare(PCB o1, PCB o2) {
//        if (o1.waitTime == o2.waitTime){
//            return o1.doTime - o2.doTime;
//        }
//        if (o1.doTime == o2.doTime)
//            return o2.waitTime - o1.waitTime;
        if (o1.ratio - o2.ratio > 0)
            return 1;
       else if (o1.ratio - o2.ratio == 0)
            return 0;
        else
            return -1;
    }
}