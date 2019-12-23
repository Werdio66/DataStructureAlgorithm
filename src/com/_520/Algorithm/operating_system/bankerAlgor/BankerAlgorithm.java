package com._520.Algorithm.operating_system.bankerAlgor;

import java.util.*;

public class BankerAlgorithm {
    // 资源总数
    private static final int[] total = {10, 5, 7};
    // 所有进程
    private static Map<String, Process> processes = new HashMap<>();

    public static void main(String[] args) {
        init();
    }

    // 初始化list
    private static void init() {
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("1.资源分配      2.安全序列     3.请求资源      4.退出");
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
        }
    }

    /**
     *  请求
     * @param processName       发出请求的进程名称
     * @param request           请求的资源数组
     */
    private static void request(String processName, int[] request) {
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
        System.out.println("进程名  " + "  Max  " + "  Allocation  " + "  Need  ");
        System.out.println("     " + "  A  B  C  " + "  A  B  C  " + "  A  B  C");
        for (Map.Entry<String, Process> map: processes.entrySet()) {
            map.getValue().printProcess();
        }
        System.out.println("Available " + Arrays.toString(getAvailable(processes)));


    }
    //控制台输入数组信息
    private static int[] inputArray(Scanner sc) {
        int[] array = new int[total.length];
        for (int i = 0; i < total.length; i++) {
            array[i] = sc.nextInt();
        }
        return array;
    }

    private static void showSafeSerial(){
        List<Process> list = getSafeSerial(processes);

        if (list == null){
            System.out.println("找不到安全序列，不能分配！");
        }else {
            System.out.println("进程名  " + "  Work  " + "    Need    " + "  Allocation  " + "  WorkAndAllocation  " + "  Finish");
            System.out.println("     " + "   A  B  C " + "   A  B  C  " + "   A  B  C    " + "      A  B  C    ");
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
            if (process == null){
                return null;
            }else {
                process.setWork(getAvailable(map));
                process.setWorkAndAllocation(getWorkAndAllocation(process.getWork(), process.getAllocation()));
                process.setFinish(true);
                safeProcess.add(process);
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
    private static int[] getWorkAndAllocation(int[] work, int[] allocation) {
        int[] arr = new int[work.length];

        for (int i = 0; i < work.length; i++) {
            arr[i] = work[i] + allocation[i];
        }
        return arr;
    }

    // 获取剩余的资源数
    private static int[] getAvailable(Map<String, Process> processes) {
        int[] available = Arrays.copyOf(total, total.length);

        for (Map.Entry<String, Process> map : processes.entrySet()){
            int[] allocation = map.getValue().getAllocation();
            for (int j = 0; j < available.length; j++) {
                available[j] -= allocation[j];
            }
        }
        return available;
    }


}
