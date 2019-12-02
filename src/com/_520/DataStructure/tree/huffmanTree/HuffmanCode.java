package com._520.DataStructure.tree.huffmanTree;

import java.util.*;

public class HuffmanCode {
    // 用于存放对应字符的哈夫曼编码
    private static Map<Byte,String> hafumanCodes = new HashMap<>();

    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] bytes = hafumanCodesBytes(str);
        String string = byteToBitString(bytes);
        deCode(string);
    }

    // 解码

    /**
     * 1.根据压缩后的byte数组转换为原来的二进制码
     * @param b     压缩后的byte数组
     * @return      解压后的二进制数
     */
    private static String byteToBitString(byte[] b){
        // 用于存放解压后的二进制编码
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int temp = b[i];
            if (i != b.length - 1)
                temp |= 256;
            // 将int类型的数字转换为对应的二进制数
            String string = Integer.toBinaryString(temp);
            if (i != b.length - 1){
                // 如果不是最后一个字符就返回八位

                stringBuilder.append(string.substring(string.length() - 8));
            }else {
                // 返回最后一个字符的二进制长度
                stringBuilder.append(string);
            }
        }
        return stringBuilder.toString();
    }
    // 2.根据存放字符和对应哈夫曼编码的map，返回原来的字符串
    private static void deCode(String str){
        System.out.println("计算的二进制码：" + str);
        // 将二进制字符串转换为byte数组，便于遍历
//        byte[] bytes = str.getBytes();
        // 存放指定编码对应的字符
        Map<String,Byte> map = new HashMap<>();
        // 将原来存放指定字符对应的编码逆转
        for (Map.Entry<Byte,String> entry:hafumanCodes.entrySet()
             ) {
            map.put(entry.getValue(),entry.getKey());
        }
        System.out.println("指定编码对应的字符" + map);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        // 遍历byte数组
        for (int i = 0; i < str.length(); ) {

            String key = null;
            while (map.get(key) == null && index < str.length()){
                index++;
                key = str.substring(i, index);
//                System.out.print("key ---> " + key + " ");
            }
//            System.out.println();
            byte by = map.get(key);
//            System.out.println(by + " ---> " + (char) by + " ");
            stringBuilder.append((char)by);
            i = index;
        }
        System.out.println(stringBuilder.toString());
    }
    /**
     *  封装了字符串的压缩过程
     * @param str       要压缩的字符串
     * @return         压缩后的byte数组
     */
    private static byte[] hafumanCodesBytes(String str){
        // 得到每个字符的个数
        Map<Byte, Integer> bytes = getBytes(str);
        // 生成对应的哈夫曼树
        Node root = creatHaffmanTree(bytes);
        // 得到每个字符的唯一编码，存入hafumanCodes中
        getCodes(root,"",new StringBuilder());

        return hafumanCodesBytes(str,hafumanCodes);
    }

    /**
     *  将字符串转换为byte数组
     * @param str           转换的字符串
     * @param map           存放每种字符对应的哈夫曼码
     * @return              byte数组
     */
    private static byte[] hafumanCodesBytes(String str, Map<Byte, String> map) {
        // 得到字符串对应的字节数组
        byte[] bytes = str.getBytes();
        // 用来存放编码后的二进制码
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历数组
        for (byte key:bytes
             ) {
            // 把byte数组中的字符拼接成二进制码
            stringBuilder.append(map.get(key));
        }
        System.out.println("原来的二进制码：" + stringBuilder.toString());
        // 将字符串的二进制码转换为byte数组
        // 1.计算字符串的长度
        int len = (stringBuilder.length() + 7) / 8;
        // 用来存放转换后的编码
        byte[] hafumanCodesBytes = new byte[len];
        int index = 0;
        // 2.每次截取八个存入字符数组中
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String string = "";
            // 截取八个
            if (i + 8 < stringBuilder.length()){
                string = stringBuilder.substring(i, i + 8);
            }else {     // 字符串的剩余不足八个字符
                // 从第i个字符截取到最后
                string = stringBuilder.substring(i);
            }
            // 将这八个字符串转化为二进制对应的十进制数（除了第一位符号位） (byte)Integer.parseInt(string,2)
            // 负数在计算机中是以补码表示的 10101000（补码）要转换为对应的原码（11011000） =  -88
            // byte 强制转换是把数字转换为字节类型
//            System.out.println(string + "--" + (byte)Integer.parseInt(string,2));
            hafumanCodesBytes[index] = (byte) Integer.parseInt(string,2);
            index++;
        }
        return hafumanCodesBytes;
    }
    /**
     * 生成哈夫曼编码
     *
     * @param node     哈夫曼树
     * @param str      当前的状态
     */
    private static void getCodes(Node node, String str,StringBuilder stringBuilder) {
        // 拼接哈夫曼编码
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //
        stringBuilder1.append(str);
        if (node != null){
            if (node.value == null){    // 当前结点不是叶子结点就递归
                // 左结点拼接 0
                getCodes(node.left,"0",stringBuilder1);
                // 右结点拼接 1
                getCodes(node.right,"1",stringBuilder1);
            }else {     // 当前结点是叶子结点
                // 将当前结点的值和编码存入map中
                hafumanCodes.put(node.value,stringBuilder1.toString());
            }
        }
    }
    /**
     *  前序遍历
     * @param root      根结点
     */
    private static void prevOrder(Node root){
        if (root != null){
            root.prevOrder();
        }else
            System.out.println("没有内容");
    }

    /**
     *  把指定字符串分解，并计算每个字符出现的次数
     * @param str       分解的字符串
     * @return          返回一个存放字符和对应个数的map
     */
    private static Map<Byte,Integer> getBytes(String str){
        byte[] bytes = str.getBytes();
        // 存储每个字符出现的次数
        Map<Byte,Integer> map = new HashMap<>();
        for (byte c:bytes
             ) {
            if (!map.containsKey(c)){   // map的不存在这个字符
                map.put(c,1);
            }else {     // map中存在这个字符
                map.put(c,map.get(c) + 1);
            }
        }
        return map;
    }

    /**
     * 构建哈夫曼树
     *
     * @param map 存放每个字符出现的次数
     * @return 哈夫曼树的根结点
     */
    private static Node creatHaffmanTree(Map<Byte, Integer> map) {
        // 存放每个结点
        List<Node> nodes = new ArrayList<>();
        //  遍历map，并把每个元素加到list中
        for (Map.Entry<Byte,Integer> entry:map.entrySet()
             ) {
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        while (nodes.size() > 1) {
            // 对nodes进行排序
            Collections.sort(nodes);
            // 取出前俩个最小的数据
            Node leftNode = nodes.remove(0);
            Node rightNode = nodes.remove(0);
            // 构建父结点
            Node parent = new Node(leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 把构建的父结点加到集合中
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    /**
     *  树的结点
     */
    static class Node implements Comparable<Node>{
        Byte value;         // 存放的值
        Integer weight;          // 权重
        Node left;
        Node right;

        public Node(byte value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public Node(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", weight=" + weight +
                    '}';
        }

        private void prevOrder() {
            System.out.println(this);
            if (this.left != null)
                this.left.prevOrder();
            if (this.right != null)
                this.right.prevOrder();
        }

        @Override
        public int compareTo(Node o) {
            // 从小到大排序
            return this.weight - o.weight;
        }
    }
}
