package xp.oj.poj.graph;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 最长路径，差分系统， spaf
 *
 *  问题描述
 * 有N个区间<ai,bi>，在这些区间中选择一些点，要求每个区间至少选择ci个点，求最少选择多少个点。
 * 问题分析
 * 给问题可以变为一个差分约束问题，设si为[0,i]中至少选择的点数，那么根据题目要求对于区间<a,b>
 *     来说有sb-sa>=c，
 * 同时si+1 - si >=0, si+1 - si <= 1。构图后计算从起点到终点的最长路径。
 */
public class Intervals1201 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Intervals1201().solve();
    }

    int MAXN = 50005;
    int N, Q;
    Edge[] head = new Edge[MAXN];
    int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
    int[] dis = new int[MAXN];
    boolean[] inq = new boolean[MAXN];
    private void solve() throws IOException {
        N = read();
        for (int i = 0; i < N; i++) {
            int a = read();
            int b = read();
            int c = read();
            minValue = min(minValue, a);
            maxValue = max(maxValue, b+1);
            addEdge(a, b + 1, c);
        }
        for (int i = minValue; i <= maxValue; i++) {
            addEdge(i, i+1, 0);
            addEdge(i+1, i, -1);
        }
        spfa();
        out.println(dis[maxValue]);
        out.flush();
    }

    private void spfa() {
        Arrays.fill(dis, Integer.MIN_VALUE);
        Arrays.fill(inq, false);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.add(minValue);
        dis[minValue] = 0;
        inq[minValue] = true;
        while (!que.isEmpty()){
            int now = que.poll();
            inq[now] = false;
            for (Edge e = head[now]; e != null; e = e.next){
                int d = e.dis + dis[e.from];
                if (d > dis[e.to]){
                    dis[e.to] = d;
                    inq[e.to] = true;
                    if (!que.isEmpty() && d > que.peek()) {
                        que.addFirst(e.to);
                    } else {
                        que.addLast(e.to);
                    }
                }
            }
        }
    }

    private void addEdge(int u, int v, int dis){
        head[u] = new Edge(u, v, dis, head[u]);
    }

    class Edge{
        int from, to, dis;
        Edge next;

        public Edge(int from, int to, int dis, Edge next) {
            this.from = from;
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
