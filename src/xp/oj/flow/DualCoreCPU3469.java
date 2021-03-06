package xp.oj.flow;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

/**
 * 最小割
 * 问题描述
 * 双核cpu核A和核B，有一批任务，将每个任务在不同cpu上运算的时间不同，有M个任务之间需要通信，如果两个任务在同一个核上执行没有通信开销，否则会有一定的开销。计算如何安排这批任务使运算时间最短。
 * 问题分析
 * 建图使用最小割算法。
 * 注意跨cpu的两个任务之间需要建两条边，也就是往返都有容量。
 */
public class DualCoreCPU3469 {
    public static void main(String[] args) throws IOException{
        new DualCoreCPU3469().solve();
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

    int MAXN = 20010;
    int INF = 1000000007;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int sindex, tindex;
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        M = (int) readDouble();
        sindex = N +1;
        tindex = N + 2;
        for (int i = 1; i <= N; i++) {
            int c = (int) readDouble();
            addEdges(sindex, i, c);
            c = (int) readDouble();
            addEdges(i, tindex, c);
        }
        for (int i = 0; i < M; i++) {
            int u = (int) readDouble();
            int v = (int) readDouble();
            int c = (int) readDouble();
            addEdges(u,v,c);
            addEdges(v,u,c);
        }
        isap();
        out.println(ans);
        out.flush();

    }

    int[] level = new int[MAXN];
    Edge[] cur = new Edge[MAXN];
    int[] gap = new int[MAXN];
    boolean hasGap;
    private void isap() {
        ans = 0;
        bfs();
        System.arraycopy(head, 0, cur, 0, MAXN);
        while (!hasGap && level[sindex] <= N+2){
            ans += dfs(sindex, INF);
        }
    }

    private long dfs(int v, long cap) {
        if (v == tindex) {
            return cap;
        }
        long flow = 0;
        for (Edge e = cur[v]; e != null; e = e.next){
            cur[v] = e;
            if (level[e.to] +1 == level[v] && e.cap > 0){
                long d = dfs(e.to, min(cap-flow, e.cap));
                if (d > 0){
                    e.cap -= d;
                    e.rEdge.cap += d;
                    flow+=d;
                    if (flow == cap) {
                        return cap;
                    }
                }
            }
        }
        // 当前点增广路径处理完毕
        if (--gap[level[v]] == 0) {
            hasGap = true; // 断
        }
        gap[++level[v]]++;
        cur[v] = head[v];
        return flow;
    }

    private void bfs() {
        Arrays.fill(level, 0);
        Arrays.fill(gap, 0);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(tindex);
        gap[1] = level[tindex] = 1;
        while (!que.isEmpty()){
            int f = que.poll();
            for (Edge e = head[f]; e != null; e = e.next){
                if (level[e.to] == 0) {
                    level[e.to] = level[f]+1;
                    gap[level[e.to]]++;
                    que.offer(e.to);
                }
            }
        }
    }


    private void addEdges(int u, int v, int c) {
        head[u] = new Edge(v, c, head[u], null);
        head[v] = new Edge(u, 0, head[v], head[u]);
        head[u].rEdge = head[v];

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
