package xp.tiaozhanchengxujingsai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DNARepair {

    public static void main(String[] args) {
        try {
            new DNARepair().solve();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void solve() throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Trie trie = new Trie(10000);
        for (int i = 0; i <= n; i++) {
            trie.insert(bf.readLine());
        }
        System.out.println("init ok");
    }


    /**
     * 计算kmp的next数组
     * @param str 匹配字符串
     * @return
     */
    int[] computNext(String str) {
        char[] arr = str.toCharArray();
        int[] next = new int[arr.length];

        next[0] = -1;
        int j = -1; // 最大前缀索引
        for (int i = 0; i < arr.length - 1; i++) {
            if (j == -1 || arr[i] == arr[j]){
                //如果arr[i]h和next[i]相同，那么i+1位的前缀长度增加
                if (arr[++i] == arr[++j]) {
                    // 前缀最后一位相同，肯定不会匹配上，直接寻找下一个匹配位置
                    next[i] = next[j];
                }else {
                    next[i] = j;
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * 计算状态转移数组，长度为i的前缀增加一个字符之后的最大前缀长度
     * 从状态i添加一个字符之后转移到下一个状态,
     *
     * @param str 匹配字符串
     * @return 原始长度长度从0到长度为n-1,转移后的长度为0到n
     */
    int[][] computStatus(String str) {
        int[] next = computNext(str);
        int[][] nextStatus = new int[next.length][next.length];

        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            nextStatus[i][arr[i]] = i + 1;
            for (int j = next[i]; j != -1 ; j++) {
                if (nextStatus[i][arr[j]] == 0)
                    nextStatus[i][arr[j]] = j + 1;
            }
        }
        return nextStatus;
    }

    class Trie{
        Node[] nodes;
        Node root;
        int cnt;

        public Trie(int n) {
            nodes = new Node[n];
            root = new Node('0');
        }

        void insert(String str) {
            char[] arr = str.toCharArray();
            Node t = root;
            for (char c :
                    arr) {
                if (t.sons[c] == null){
                    Node node = new Node(c);
                    nodes[cnt] = node;
                    node.id = cnt++;
                    t.sons[c] = node;
                }
                t = t.sons[c];
            }
//            t.flag = false; //
        }

        /**
         * 1、构建fail指针
         * 2、补边
         *
         */
        void buildFail() {
            root.fail = root;
            Queue<Node> queue = new LinkedList<Node>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                Node p = queue.poll();
                // 遍历所有子节点
                for (int i = 0; i < p.sons.length; i++) {
                    if (p.sons[i] == null) {
                        // 空节点补边
                        if (p == root) p.sons[i] = p;
                        else p.sons[i] = p.fail.sons[i];
                    } else {
                        if (p == root) p.sons[i].fail = p;
                        else p.sons[i].fail = p.fail.sons[i];
                        queue.offer(p.sons[i]);
                    }
                }
            }
        }



        class Node {
            Node[] sons;
            Node fail;
            int id;
            boolean flag;
            char val;

            public Node(char v) {
                sons = new Node[128];
                fail = null;
                flag = true;
                val = v;
            }
        }
    }


}
