package xp.oj.flow;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

/**
 * 最大流
 * 问题描述
 * 每头牛都有喜欢的食物和饮料，现在有一些食物和饮料分配给这些牛，最多有多少头牛可以得到喜欢的食物和饮料。
 * 问题分析
 * 这里有三种实体无法实现二分图，如果把食物和饮料作为一个实体，那么会有重复使用的可能。如果每头牛都只有一种喜欢的食物
 * 和饮料，则可以将牛作为食物和饮料的边，然后计算二分图的最小顶点覆盖。但是这里也没有这个条件，为了限制每头牛只能选择一
 * 对食物和饮料，可以通过流量控制。把食物和饮料的边拆分成食物和牛，牛和饮料的连接，并且使用两个点表示牛，它们之间的流量
 * 限制为1（这里用只有一条边表示）。
 */
public class Dining3281 {
    public static void main(String[] args) throws IOException {
        new Dining3281().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }

    int MAXN = 105;
    int MAXM = 10005;
    int INF = 1000000007;
    int N, F, D;
    Edge[] head = new Edge[MAXN<<2];
    int foodOffset, drinkOffset, cowLeftOffset, cowRightOffset;
    int sindex, tindex;
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        F = (int) readDouble();
        D = (int) readDouble();
        foodOffset = -1;
        drinkOffset = foodOffset + F;
        cowLeftOffset = drinkOffset + D;
        cowRightOffset = cowLeftOffset + N;
        sindex = cowRightOffset + N + 1;
        tindex = sindex + 1;
        for (int i = 1; i <= N; i++) {
            addEdges(cowLeftOffset + i, cowRightOffset + i);
            int fn = (int) readDouble();
            int dn = (int) readDouble();
            for (int j = 0; j < fn; j++) {
                // 食物-牛
                int f = (int) readDouble();
                addEdges(foodOffset + f, cowLeftOffset + i);
            }
            for (int j = 0; j < dn; j++) {
                int d = (int) readDouble();
                addEdges(cowRightOffset + i, drinkOffset + d);
            }
        }
        for (int i = 1; i <= F; i++) {
            addEdges(sindex, foodOffset+i);
        }
        for (int i = 1; i <= D; i++) {
            addEdges(drinkOffset+i, tindex);
        }
        ek();
        out.println(ans);
        out.flush();

    }

    boolean[] used = new boolean[MAXN<<2];

    long[] flow = new long[MAXN<<2];
    Edge[] pre = new Edge[MAXN<<2];
    private void ek(){
        ans = 0;
        while (true){
            Arrays.fill(flow, 0);
            Arrays.fill(pre, null);
            LinkedList<Integer> que = new LinkedList<Integer>();
            que.offer(sindex);
            flow[sindex] = INF;
            while (!que.isEmpty()) {
                int f = que.poll();
                if (f == tindex) {
                    break;
                }
                for (Edge e = head[f]; e != null; e = e.next) {
                    if (flow[e.to] == 0 && e.cap > 0) {
                        flow[e.to] = min(e.cap, flow[f]);
                        pre[e.to] = e.rEdge;
                        que.offer(e.to);
                    }
                }
            }

            if (flow[tindex] == 0) {
                return;
            }
            ans += flow[tindex];
            Edge preEdge = pre[tindex];
            while (preEdge!= null) {
                preEdge.cap += flow[tindex];
                preEdge.rEdge.cap -= flow[tindex];
                preEdge = pre[preEdge.to];
            }
        }
    }

    private void addEdges(int a, int b) {
        head[a] = new Edge(b, 1, head[a], null);
        head[b] = new Edge(a, 0, head[b], head[a]);
        head[a].rEdge = head[b];
    }

    class Edge {
        int to;
        long cap;
        Edge next, rEdge;
        Edge(int to, long cap, Edge next, Edge rEdge){
            this.cap = cap;
            this.to = to;
            this.next = next;
            this.rEdge = rEdge;
        }
    }
}
