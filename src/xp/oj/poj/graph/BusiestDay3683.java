package xp.oj.poj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * 强连通分量，2SAT问题
 *
 * 问题描述
 * 有一个小镇上只有一个牧师。这个小镇上有一个传说，在九月一日结婚的人会受到爱神的保佑，但是要牧师举办一个仪式。
 * 这个仪式要么在婚礼刚刚开始的时候举行，要么举行完婚礼正好结束。
 * 现在已知有n场婚礼，告诉你每一场的开始和结束时间，以及举行仪式所需要的时间。问牧师能否参加所有的婚礼，
 * 如果能则输出一种方案。
 *
 * 问题分析
 * 对任意两场婚礼，可以有4种安排，分别判断它们之间的约束关系，构建一个选择图。分解强连通分量，判断是否可解。
 *
 *
 * 2-SAT问题：n个布尔型的变量，给出m个约束条件，约束条件例如：A，B不能同时为真，A，B必须同时为真等。
 * 对于a|b就有~a->b &~b->a，也就是存在约束条件，a为假时b为真，b为假时a为真。
 * 通过约束条件建图之后，如果存在以下几种情况：
 * x，~x没有边相连，没有约束x和~x的关系，但是最终也只能选择一个
 * x->~x，x为真时~x必须为真，所以x只能为假
 * ~x->x，~x为真时x必须为真，所以x只能为真
 * x->~x,~x->x，无解。
 * 因此进行强连通分量之后，如果x和~x在同一个强连通分量中，则无解。如果节点压缩之后，在DAG中排在后面的情况为真。
 */
public class BusiestDay3683 {

    static int[] S;
    static int[] T;
    static int[][] graph;
    static int[][] regraph;
    static boolean[] used;
    static int[] cmp;
    static int[] seq;
    static int cnt;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        // 处理输入
        n = Integer.parseInt(bf.readLine());
        S = new int[2 * n];
        T = new int[2 * n];
        used = new boolean[2 * n];
        cmp = new int[2 * n];
        seq = new int[2 * n];
        for (int i = 0; i < n; i++) {
            String[] str = bf.readLine().split(" ");
            int start =  time2Int(str[0]);
            int end = time2Int(str[1]);
            int p = time2Int(str[2]);
            S[i] = start;
            T[i] = start + p;
            S[i + n] = end - p;
            T[i + n] = end;
        }

        // 构图
        graph = new int[2 * n][2 * n + 1];
        regraph = new int[2 * n][2 * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (covered(i, j)) {
                    addEdge(i, j+n);
                    addEdge(j, i+n);
                }
                if (covered(i, j + n)) {
                    addEdge(i, j);
                    addEdge(j + n, i+n);
                }
                if (covered(i + n, j)) {
                    addEdge(i + n, j+n);
                    addEdge(j, i);
                }
                if (covered(i + n, j + n)) {
                    addEdge(i + n, j);
                    addEdge(j + n, i);
                }
            }
        }

        // 强连通分量分解
        ssc();

        // 判断是否有解
        for (int i = 0; i < n; i++) {
            if (cmp[i] == cmp[i + n]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        for (int i = 0; i < n; i++) {
            if (cmp[i] > cmp[i + n]) {
                System.out.printf("%02d:%02d %02d:%02d\n",S[i] / 60, S[i] % 60, T[i] / 60, T[i] % 60);
            } else {
                System.out.printf("%02d:%02d %02d:%02d\n",S[i + n] / 60, S[i + n] % 60, T[i + n] / 60, T[i + n] % 60);
            }
        }
    }

    static boolean covered(int i, int j) {
        return Math.min(T[i], T[j]) > Math.max(S[i], S[j]);
    }

    private static void addEdge(int f, int t) {
        int index = ++graph[f][0];
        graph[f][index] = t;
        index = ++regraph[t][0];
        regraph[t][index] = f;
    }

    private static void ssc() {

        // 正向dfs
        for (int i = 0; i < 2*n; i++) {
            if (!used[i]) {
                dfs(i);
            }
        }

        // 反向dfs
        Arrays.fill(used, false);
        int k = 0;
        for (int i = 2 *n - 1; i >= 0 ; i--) {
            if (!used[seq[i]]) {
                redfs(seq[i], k++);
            }
        }

    }

    private static int dfs(int v) {
        used[v] = true;
        int res = 1;
        for (int i = 1; i <= graph[v][0]; i++) {
            if (!used[graph[v][i]]) {
                res += dfs(graph[v][i]);
            }
        }
        seq[cnt++] = v;
        return res;
    }

    private static int redfs(int v, int k) {
        used[v] = true;
        int res = 1;
        for (int i = 1; i <= regraph[v][0]; i++) {
            if (!used[regraph[v][i]]) {
                res += redfs(regraph[v][i], k);
            }
        }
        cmp[v] = k;
        return res;
    }

    private static int time2Int(String str) {
        int res = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(str, ":"); stringTokenizer.hasMoreTokens(); ) {
            res = res * 60 + Integer.parseInt(stringTokenizer.nextToken());

        }
        return res;
    }
}
