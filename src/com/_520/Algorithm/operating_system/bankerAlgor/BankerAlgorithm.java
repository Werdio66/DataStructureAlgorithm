package com._520.Algorithm.operating_system.bankerAlgor;

import java.util.*;

public class BankerAlgorithm {

    // 所有进程
    private static Map<String, Process> processes = new HashMap<>();
    // 存放资源
    private static Map<String, Integer> totalResources = new HashMap<>();
//    public static void main(String[] args) {
//        init1();
//        show();
//        showSafeSerial();
////        request("p3", new int[]{0, 1, 1});
//        request("p1", new int[]{1, 2, 2, 3});
//
////        show();
////        showSafeSerial();
////        request("p4", new int[]{3, 3, 0});
////        request("p0", new int[]{0, 2, 0});
////        show();
//    }

    public static void main(String[] args) {
        init();
    }
    // 初始化list
    private static void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入资源总数：");
        int sum = scanner.nextInt();
        for (int i = 0; i < sum; i++) {
            System.out.println("请输入第" + (i + 1) + "个资源名称");
            String name = scanner.next();
            System.out.println("请输入第" + (i + 1) + "个资源的总数");
            Integer num = scanner.nextInt();
            totalResources.put(name, num);
        }
        System.out.println("请输入进程数：");
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            System.out.println("请输入进程" + (i + 1) + "的名称");
            String name = scanner.next();
            System.out.println("请输入进程" + (i + 1) + "的最大资源数");
            int[] max = inputArray(scanner);
            System.out.println("请输入进程" + (i + 1) + "的已分配资源数");
            int[] allocation = inputArray(scanner);
            processes.put(name,new Process(name, max, allocation));
        }
        boolean flag = true;
        while (flag){
            System.out.println("1.查看资源分配情况      2.查看安全序列     3.请求资源      4.退出");
            int key = scanner.nextInt();
            switch (key){
                case 1:
                    show();
                    break;
                case 2:
                    showSafeSerial();
                    break;
                case 3:
                    System.out.println("请输入发出请求的进程：");
                    String name = scanner.next();
                    System.out.println("请输入请求的资源数：");
                    int[] req = inputArray(scanner);
                    request(name, req);
                    break;
                case 4:
                    flag = false;
                default:
                    break;

            }
            System.out.println("再见！");
        }
    }


    private static void init1(){
//        processes.put("p0", new Process("p0", new int[]{7, 5, 3}, new int[]{0, 1, 0}));
//        processes.put("p1", new Process("p1", new int[]{3, 2, 2}, new int[]{2, 0, 0}));
//        processes.put("p2", new Process("p2", new int[]{9, 0, 2}, new int[]{3, 0, 2}));
//        processes.put("p3", new Process("p3", new int[]{2, 2, 2}, new int[]{2, 1, 1}));
//        processes.put("p4", new Process("p4", new int[]{4, 3, 3}, new int[]{0, 0, 2}));
        processes.put("p0", new Process("p0", new int[]{7, 5, 3, 2}, new int[]{0, 1, 0, 1}));
        processes.put("p1", new Process("p1", new int[]{3, 2, 2, 3}, new int[]{2, 0, 0, 0}));
        processes.put("p2", new Process("p2", new int[]{9, 0, 2, 2}, new int[]{3, 0, 2, 1}));
        processes.put("p3", new Process("p3", new int[]{2, 2, 2, 0}, new int[]{2, 1, 1, 0}));
        processes.put("p4", new Process("p4", new int[]{4, 3, 3, 1}, new int[]{0, 0, 2, 0}));
        totalResources.put("A", 10);
        totalResources.put("B", 5);
        totalResources.put("C", 7);
        totalResources.put("D", 7);
    }
    /**
     *  请求
     * @param processName       发出请求的进程名称
     * @param request           请求的资源数组
     */
    private static void request(String processName, int[] request) {
        System.out.println(processName + " 请求资源数：" + Arrays.toString(request));
        // 剩余的资源总数
        int[] av = getAvailable(processes);
        // 判断请求资源是否大于剩余的资源总数
        for (int i = 0; i < request.length; i++) {
            if (request[i] > av[i]){
                System.out.println("剩余资源不能满足你的请求！");
                return;
            }
        }

        // 请求的进程
        Process current = processes.get(processName);
        // 已分配资源
        int[] al = current.getAllocation();
        // 需求
        int[] need = current.getNeed();
        // 判断请求资源是否大于需求
        for (int i = 0; i < need.length; i++) {
            if (request[i] > need[i]){
                System.out.println("请求数过多。");
                return;
            }
        }
        // 记录分配前的资源
        int[] oldAl = Arrays.copyOf(al, al.length);
        int[] oldNeed = Arrays.copyOf(need, need.length);
        // 分配资源
        for (int i = 0; i < al.length; i++) {
            al[i] += request[i];
            need[i] -= request[i];
        }
        // 判断是否存在安全序列
        if (getSafeSerial(processes) != null){
            System.out.println("存在安全序列，可以分配！");
            // 存在安全序列
            current.setAllocation(al);
            current.setNeed(need);
        }else {
            System.out.println("找不到安全序列，不能分配！");
            current.setAllocation(oldAl);
            current.setNeed(oldNeed);
        }
    }


    private static void show() {
        List<String> resourcesName = getResourcesName();
        System.out.println("进程名  " + "  Max  " + "  Allocation  " + "  Need  ");
        System.out.println("     " + " " + resourcesName + "  " + resourcesName + "  " + resourcesName);
        for (Map.Entry<String, Process> map: processes.entrySet()) {
            map.getValue().printProcess();
        }
        System.out.println("Available " + Arrays.toString(getAvailable(processes)));


    }
    //控制台输入数组信息
    private static int[] inputArray(Scanner sc) {
        int[] array = new int[totalResources.size()];
        for (int i = 0; i < totalResources.size(); i++) {
            array[i] = sc.nextInt();
        }
        return array;
    }

    private static List<String> getResourcesName(){
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Integer> map : totalResources.entrySet()){
            list.add(map.getKey());
        }
        return list;
    }
    private static void showSafeSerial(){
        List<Process> list = getSafeSerial(processes);
        List<String> resourcesName = getResourcesName();
        if (list == null){
            System.out.println("找不到安全序列，不能分配！");
        }else {
            System.out.println("进程名  " + "  Work  " + "    Need    " + "  Allocation  " + "  WorkAndAllocation  " + "  Finish");
            System.out.println("     " + resourcesName  + "    "+ resourcesName  + "   "+ resourcesName  + "       "+ resourcesName);
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).printSafeProcess();

                if (i == list.size() - 1) {
                    str.append(list.get(i).getName());
                }else {
                    str.append(list.get(i).getName()).append(" --> ");
                }
            }
            System.out.println("安全序列：" + str.toString());
        }
    }

    // 申请资源时的安全性检查
    private static List<Process> getSafeSerial(Map<String, Process> processMap) {

        Map<String, Process> map = new HashMap<>(processMap);
        // 存放安全序列
        List<Process> safeProcess = new ArrayList<>();
        while (!map.isEmpty()){
            Process process = findSafeProcess(map);
            // 没有找到符合的进程，说明不安全，直接返回
            if (process == null){
                return null;
            }else {// 找到符合的进程

                // 设置 Work
                process.setWork(getAvailable(map));
                // 计算 WorkAndAllocation
                process.setWorkAndAllocation(addTwoArrays(process.getWork(), process.getAllocation()));
                // 设置状态
                process.setFinish(true);
                // 把当前进程加到安全序列中
                safeProcess.add(process);
                // 删除当前进程，下次遍历的时候不需要判断这个进程
                map.remove(process.getName());
            }

        }

        return safeProcess;

    }

    // 寻找符合的进程
    private static Process findSafeProcess(Map<String, Process> processMap) {
        for (Map.Entry<String, Process> entry : processMap.entrySet()) {
            int[] av = getAvailable(processMap);
            Process p = entry.getValue();
            boolean flag = true;
            for (int i = 0; i < av.length; i++) {
                if (p.getNeed()[i] > av[i]){
                    flag = false;
                }
            }

            if (flag){
                return p;
            }
        }

        return null;
    }

    // 计算workAndAllocation
    private static int[] addTwoArrays(int[] arr1, int[] arr2) {
        int[] arr = new int[arr1.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr1[i] + arr2[i];
        }
        return arr;
    }

    // 获取剩余的资源数
    private static int[] getAvailable(Map<String, Process> processes) {
        int[] available = new int[totalResources.size()];
        initAv(available);
        for (Map.Entry<String, Process> map : processes.entrySet()){
            int[] allocation = map.getValue().getAllocation();
            for (int j = 0; j < available.length; j++) {
                available[j] -= allocation[j];
            }
        }
        return available;
    }

    // 将map中的值取出
    private static void initAv(int[] available) {
        int count = 0;
        for (Map.Entry<String, Integer> map : totalResources.entrySet()) {
            available[count++] = map.getValue();
        }
    }
}
