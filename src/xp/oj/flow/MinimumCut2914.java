package xp.oj.flow;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

/**
 * 无向图最小割
 *
 * 问题描述
 * 对于给定的n个顶点的无向图，每两个顶点之间可能会存在多条边，计算最少去掉多少条边能使图不连通。
 * 问题分析
 * 两个顶点建立一条权为两点之间边的数量边，然后计算该图的全局最小割。无向图中因为没有源点和汇点，所以这里需要枚举源点和
 * 汇点计算最小割。stoer-wagner算法是计算无向图最小割的一种算法，算法的思路用集合A表示一个割的点集，依次选择到该集合边
 * 权最大的顶点加入到集合A中。把最后一个加入集合的点的割最小，因此最后一个加入的点作为汇点t，在t之前加入集合的任意一点
 * 作为源点s。此时可以分为两种情况：
 * 1、s-t cut是全局最小割
 * 2、s-t cut不是全局最小割，可以将s和t合并成一个顶点
 * 经过N-1次合并之后得到的最小割一定是全局最小割。
 */
public class MinimumCut2914 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new MinimumCut2914().solve();
    }

    int MAXN = 505;
    int N, M;
    int[][] grap = new int[MAXN][MAXN];
    int[] w = new int[MAXN];
    int[] v = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    private void solve() throws IOException {
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            N = (int) in.nval;
            M = read();
            for (int i = 0; i < MAXN; i++) {
                Arrays.fill(grap[i], 0);
            }
            for (int i = 0; i < M; i++) {
                int u = read();
                int v = read();
                int d = read();
                grap[u][v] += d;
                grap[v][u] += d;
            }
            out.println(stoerWagner(N));
        }
        out.flush();
    }

    private int stoerWagner(int n) {

        int minCut = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            v[i] = i;
        }
        while (n>1){
            Arrays.fill(vis, false);
            Arrays.fill(w, 0);
            int pre = 0;
            for (int i = 1; i < n; i++) {
                // 选择边权最大的顶点加入到集合中
                int k = -1;
                for (int j = 1; j < n; j++) {
                    if (!vis[v[j]]) {
                        // 计算j到集合中所有点的边权和，pre是上一个加入的点
                        w[v[j]] += grap[v[pre]][v[j]];
                        if (k == -1 || w[v[j]] > w[v[k]]) {
                            k = j;
                        }
                    }
                }
                vis[v[k]] = true;
                if (i == n - 1){
                    int s = v[pre], t = v[k];
                    minCut = min(minCut, w[t]);
                    // 把顶点t合并到顶点s
                    for (int j = 0; j < n; j++) {
                        grap[s][v[j]] += grap[v[j]][t];
                        grap[v[j]][s] += grap[v[j]][t];
                    }
                    v[k] = v[--n]; // 删除顶点t
                }
                pre = k;
            }
        }
        return minCut;
    }
}
