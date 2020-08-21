package xp.oj.flow;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

/**
 * 最大费用最大流
 *
 * 问题描述
 * 公司有N辆货车，现在A地和B地分别需要a,b车辆，已知每辆车去这两第能获得最大利润，计算如何分配能得到最大利润。
 * 问题分析
 * 使用最小费用最大流，将边权设为负值即可得到最大费用最大流。
 */
public class MaxCostMaxFlow {

    public static void main(String[] args) throws IOException {
        new MaxCostMaxFlow().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }


    int N;
    int A, B;
    int indexA, indexB;

    private void solve() throws IOException {
        N = readInt();
        A = readInt();
        B = readInt();
        Arrays.fill(head, null);
        indexA = N +1;
        indexB = N + 2;

        source = N +3;
        sink = N + 4;


        for (int i = 1; i <= N; i++) {
            int costA = readInt();
            addEdges(indexA, i, 1, -costA);
            int costB = readInt();
            addEdges(indexB, i, 1, -costB);
            addEdges(i, sink, 1, 0);
        }
        addEdges(source, indexA, A, 0);
        addEdges(source, indexB, B, 0);
        minCostMaxFlow();
        out.println(-ans);
        out.flush();

    }


    // 以下内容为spfa多路增广最小费用流模板
    int MAXN = 100009;
    Edge[] head = new Edge[MAXN];
    int source, sink;
    boolean[] used = new boolean[MAXN];
    boolean[] inq = new boolean[MAXN];
    long[] dis = new long[MAXN];
    Edge[] cur = new Edge[MAXN];
    int INF = 1000000007;
    int ans;

    private long minCostMaxFlow() {
        long flow = 0;
        ans = 0;
        System.arraycopy(head, 0, cur, 0, MAXN);
        while (spfa()){
            long f = aug(source, INF);
            ans += dis[source] * f;
            flow += f;
        }
        return flow;
    }

    // 在最短路上进行多路增广
    private long aug(int v, long cap) {
        if (v == sink){
            return cap;
        }
        used[v] = true;
        int f = 0;
        for (Edge e = cur[v]; e != null; cur[v] = e, e = e.next){
            if (!used[e.to] && e.cap > 0 && dis[v] == dis[e.to] + e.dis){
                long d = aug(e.to, min(cap-f, e.cap));
                e.cap -= d;
                e.reEdge.cap += d;
                f += d;
                if (f == cap){
                    used[v] = false;
                    return f;
                }
            }
        }
        used[v] = false;
        cur[v] = head[v];
        return f;
    }

    // 汇点到其他点的最小距离
    private boolean spfa() {
        Arrays.fill(inq, false);
        Arrays.fill(dis, INF);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(sink);
        inq[sink] = true;
        dis[sink] = 0;
        while (!que.isEmpty()){
            int cur = que.poll();
            inq[cur] = false;
            for (Edge e = head[cur]; e != null; e = e.next){
                if (e.reEdge.cap > 0 && dis[e.to] > dis[cur] - e.dis){
                    dis[e.to] = dis[cur] - e.dis;
                    if (!inq[e.to]){
                        inq[e.to] = true;
                        if (que.isEmpty() || dis[que.peek()] <= dis[e.to]){
                            que.offer(e.to);
                        } else {
                            que.addFirst(e.to);
                        }
                    }
                }
            }
        }
        return dis[source] != INF;
    }


    private void addEdges(int u, int v, int cap, int dis) {
        head[u] = new Edge(u, v, cap, dis, head[u], null);
        head[v] = new Edge(v, u, 0, -dis, head[v], head[u]);
        head[u].reEdge = head[v];
    }

    private class Edge{
        int from, to, cap, dis;
        Edge next, reEdge;

        public Edge(int from, int to, int cap, int dis, Edge next, Edge reEdge) {
            this.from = from;
            this.to = to;
            this.cap = cap;
            this.dis = dis;
            this.next = next;
            this.reEdge = reEdge;
        }
    }
}
