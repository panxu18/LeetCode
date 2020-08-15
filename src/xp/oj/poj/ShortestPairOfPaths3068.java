package xp.oj.poj;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Math.min;

public class ShortestPairOfPaths3068 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new ShortestPairOfPaths3068().solve();
    }

    int MAXN = 150;
    int N, M, K;
    int cnt = 0;
    Edge[] head = new Edge[MAXN];
    int source, sink;
    int ans;
    private void solve() throws IOException {
        int cases = 0;
        while ((N = read()) != 0){
            cases++;
            M = read();
            Arrays.fill(head, null);
            ans = 0;
            source = 0;
            sink = (N-1)<<1|1;
            for (int i = 0; i < M; i++) {
                addBiEdge(read()<<1|1, read()<<1, 1, read());
            }
            addBiEdge(0, 1, 2, 0);
            for (int i = 1; i +1 < N; i++) {
                addBiEdge(i<<1, i<<1|1, 1, 0);
            }
            addBiEdge((N-1)<<1, (N-1)<<1|1, 2, 0);
            if (minCostMaxFlow() >= 2) {
                out.printf("Instance #%d: %d\n", cases, ans);
            } else {
                out.printf("Instance #%d: Not possible\n", cases);
            }
        }
        out.flush();
    }

    boolean[] used = new boolean[MAXN];
    boolean[] inq = new boolean[MAXN];
    long[] dis = new long[MAXN];
    Edge[] cur = new Edge[MAXN];
    int INF = 1000000007;

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


    private void addBiEdge(int u, int v, int cap, int dis) {
        head[u] = new Edge(v, cap, dis, head[u], null);
        head[v] = new Edge(u, 0, -dis, head[v], head[u]);
        head[u].reEdge = head[v];
    }

    private class Edge{
        int to, cap, dis;
        Edge next, reEdge;

        public Edge(int to, int cap, int dis, Edge next, Edge reEdge) {
            this.to = to;
            this.cap = cap;
            this.dis = dis;
            this.next = next;
            this.reEdge = reEdge;
        }
    }
}
