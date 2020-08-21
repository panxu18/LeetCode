package xp.oj.flow;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

/**
 * 最小费用流，坐标离散化
 *
 * 问题描述
 * 给N个带权的开区间，i号区间覆盖（ai,bi),权重为wi，先要从中选取一些区间，要求任意点都不被覆盖超过K次，计算最大权重和。
 * 问题分析
 * 把区间看成流量为1的流，当区间不重合时，这两个流是同一个流量为1的流。因此如果出现流量为K的流就说明有区间重叠了K次。
 * 因此问题变为计算求最大流量为K时的最大权重。
 * 因为区间范围比较大，所以需要离散化坐标。
 */
public class Intervals3680 {

    public static void main(String[] args) throws IOException {
        new Intervals3680().solve();
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

    int N,T, K;
    int[] arr = new int[MAXN];
    int cnt = 0;

    Range[] ranges = new Range[MAXN];

    private void solve() throws IOException {
        T = (int) readDouble();
        for (int t = 0; t < T; t++) {
            N = (int)readDouble();
            K = (int) readDouble();
            cnt = 0;
            for (int i = 0; i < N; i++) {
                int x = (int) readDouble();
                int y = (int) readDouble();
                int cost = (int) readDouble();
                ranges[i] = new Range(x, y, cost);
                arr[cnt++] = x;
                arr[cnt++] = y;
            }
            Arrays.sort(arr, 0, cnt);
            m = unique(arr, 0, cnt);
            source = m;
            sink = source + 1;
            ans = 0;
            Arrays.fill(head, null);
            addEdge(source, 0, K, 0);
            addEdge(m-1, sink, K, 0);
            for (int i = 0; i + 1 < m; i++) {
                addEdge(i, i+1, K, 0);
            }
            for (int i = 0; i < N; i++) {
                int u = Arrays.binarySearch(arr,0, m, ranges[i].x);
                int v = Arrays.binarySearch(arr,0, m, ranges[i].y);

                addEdge(v, u, 1, ranges[i].cost);
                addEdge(source, v, 1, 0);
                addEdge(u, sink, 1, 0);
                ans -= ranges[i].cost;
            }
            minCostMaxFlow();
            out.println(-ans);
        }

        out.flush();

    }


    class Range{
        int x, y, cost;

        public Range(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    private int unique(int[] arr, int s, int t) {
        int head = s, tail = s;
        for (int i = s; i < t; i++) {
            if (head == tail || arr[tail-1] != arr[i]){
                arr[tail++] = arr[i];
            }
        }
        return tail;
    }


    // 以下为zkw费用流模板
    static int MAXN = 500;
    int INF = 1000000007;
    long ans;
    int source, sink;
    boolean[] path = new boolean[MAXN];
    boolean[] used = new boolean[MAXN];
    long[] dis = new long[MAXN];
    Edge[] head = new Edge[MAXN];
    long[] slack = new long[MAXN];
    Edge[] cur = new Edge[MAXN];
    int m = 0; // 节点数量


    private void minCostMaxFlow() {
        Arrays.fill(dis, 0);
        Arrays.fill(slack,INF);
        while (true){
            System.arraycopy(head, 0, cur, 0, MAXN);
            while (true){
                Arrays.fill(path, false);
                long f = aug(source, INF);
                if (f <= 0) {
                    break;
                }
                ans+=dis[source]*f;
            }
            if (!update()){
                break;
            }
        }
    }

    private boolean update(){
        long d = INF;
        for (int i = 0; i < m+2; i++) {
            if (!path[i]) {
                d = min(d, slack[i]);
                slack[i] = INF;
            }
        }
        if (d == INF) {
            return false;
        }
        for (int i = 0; i < m+2; i++) {
            if (path[i]){
                dis[i] += d;
            }
        }
        return true;
    }


    // 在最短路上进行多路增广
    private long aug(int v, long cap) {
        path[v] = true; // 搜索路径
        if (v == sink){
            return cap;
        }
        used[v] = true; // dfs标记
        int f = 0;
        for (Edge e = cur[v]; e != null; e = e.next){
            cur[v] = e;
            if (!used[e.to] && e.cap > 0 ){
                long t =  dis[e.to] + e.dis - dis[v];
                if (t == 0){
                    long d = aug(e.to, min(cap-f, e.cap));
                    if (d > 0){
                        e.cap -= d;
                        e.rEdge.cap += d;
                        f += d;
                        if (f == cap){
                            used[v] = false;
                            break;
                        }
                    }
                } else {
                    slack[e.to] = min(slack[e.to], t);
                }
            }
        }
        used[v] = false;
        return f;
    }

    private void addEdge(int u, int v, long cap, long cost) {
        head[u] = new Edge(u, v, cap, cost, head[u], null);
        head[v] = new Edge(v, u, 0, -cost, head[v], head[u]);
        head[u].rEdge = head[v];
    }

    class Edge{
        int from, to;
        long cap, dis;
        Edge next, rEdge;

        public Edge(int from, int to, long cap, long dis, Edge next, Edge rEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.dis = dis;
            this.next = next;
            this.rEdge = rEdge;
        }
    }
}
