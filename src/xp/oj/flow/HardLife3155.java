package xp.oj.flow;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

/**
 * 最大密度子图, 最小割
 *
 * 问题描述
 * 给一个图，选择其中若干个顶点以及它们之间的边作为一个子图，使这个子图中边的数量/顶点数量最大。
 * 问题分析
 * 该问题是计算最大密度子图，首先最大化D=|E|/|V|可以看出是一个01规划问题，通过枚举D计算最大值的D，问题在于如何判断对于
 * 一个给定的d是否存在|E|-D*|V|>0，即max{|E|-D*|V|}>0。假设存在一个顶点集合V，那么其中边的数量应该等于V中顶点所有的边
 * 减去V和V的补给V'之间的边，从这个角度来看，应该是可以利用最小割解决。转化为最小割的形式之后的表达式为：
 *
 * 前面一部分可以看做是选择顶点的代价，所以应该表示为顶点到汇点t的边。后面一部分表示为两集合之间的边，通常这一部分会分
 * 成两部分写即V->V'的边和V'->V的边，看起来是重复计算了，实际上在计算最大流时，最多只会选择其中一条边。这里没有表示不选
 * 的代价，因为不选的代价为0，但是建图时容量为0的边导致无法增广，所以这里考虑对不选时的边添加一个较大的初始容量，
 * 在计算最小割之后需要将这一部分初始容量减掉。
 */
public class HardLife3155 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new HardLife3155().solve();
    }

    int MAXN = 105;
    int MAXM = 1005;
    int N, M;
    Edge[] head = new Edge[MAXN];
    int[] dig = new int[MAXN];
    int source, sink;
    boolean[] vis = new boolean[MAXN];
    int[] U = new int[MAXM];
    int[] V = new int[MAXM];
    private void solve() throws IOException {
        N = read();
        M = read();
        if (M == 0){
            out.println("1");
            out.println("1");
            out.flush();
            return;
        }
        source = N + 1;
        sink = source + 1;
        for (int i = 0; i < M; i++) {
            U[i] = read();
            V[i] = read();
            dig[U[i]]++;
            dig[V[i]]++;
        }
        doSolve();
        out.println(cnt-1);
        for (int i = 1; i < cnt; i++) {
            out.println(ans[i]);
        }
        out.flush();
    }

    private void doSolve() {
        double lb = 0, ub = 100;
        // double类型有效位不超过15位，精度不能太高
        for (int i = 0; i < 20; i++) {
            double mid = (lb+ub)/2;
            constructGrap(mid);
            if (N * M - isap() > 0){
                lb = mid;
            } else {
                ub = mid;
            }
        }
        constructGrap(lb);
        isap();
        Arrays.fill(vis, false);
        dfs(source);
        Arrays.sort(ans, 1, cnt);
    }

    int[] ans = new int[MAXN];
    int cnt = 0;
    private void dfs(int v) {
        vis[v] = true;
        ans[cnt++] = v;
        for (Edge e = head[v]; e != null; e = e.next) {
            if (e.cap > 0 && !vis[e.to]){
                dfs(e.to);
            }
        }
    }


    private void constructGrap(double d){
        Arrays.fill(head, null);
        for (int i = 0; i < M; i++) {
            addBiEdge(U[i], V[i], 1);
            addBiEdge(V[i], U[i], 1);
        }
        for (int i = 1; i <= N; i++) {
            addBiEdge(source, i, M);
            addBiEdge(i, sink, M - dig[i] + 2*d);
        }
    }

    private void addBiEdge(int u, int v, double cap) {
        head[u] = new Edge(u, v, cap, head[u], null);
        head[v] = new Edge(v, u, 0, head[v], head[u]);
        head[u].reEdge = head[v];
    }

    int[] level = new int[MAXN];
    Edge[] cur = new Edge[MAXN];
    int[] gap = new int[MAXN];
    boolean hasGap;

    private double isap() {
        double res = 0;
        bfs();
        System.arraycopy(head, 0, cur, 0, MAXN);
        hasGap = false;
        while (!hasGap && level[source] <= N+2){
            res += aug(source, Integer.MAX_VALUE);
        }
        return res;
    }

    private double aug(int v, double cap) {
        if (v == sink) {
            return cap;
        }
        double flow = 0;
        for (Edge e = cur[v]; e != null; e = e.next){
            cur[v] = e;
            if (level[e.to] +1 == level[v] && e.cap > 0){
                double d = aug(e.to, min(cap-flow, e.cap));
                if (d > 0){
                    e.cap -= d;
                    e.reEdge.cap += d;
                    flow+=d;
                    if (flow == cap) {
                        return cap;
                    }
                }
            }
        }
        // 当前点增广路径处理完毕
        if (--gap[level[v]] == 0) {
            hasGap = true; // 断层
        }
        gap[++level[v]]++;
        cur[v] = head[v];
        return flow;
    }

    private void bfs() {
        Arrays.fill(level, 0);
        Arrays.fill(gap, 0);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(sink);
        gap[1] = level[sink] = 1;
        while (!que.isEmpty()){
            int f = que.poll();
            for (Edge e = head[f]; e != null; e = e.next){
                if (level[e.to] == 0 && e.reEdge.cap > 0) {
                    level[e.to] = level[f]+1;
                    gap[level[e.to]]++;
                    que.offer(e.to);
                }
            }
        }
    }

    class Edge{
        int from, to;
        double cap;
        Edge next, reEdge;

        public Edge(int from, int to, double cap, Edge next, Edge reEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.next = next;
            this.reEdge = reEdge;
        }
    }
}
