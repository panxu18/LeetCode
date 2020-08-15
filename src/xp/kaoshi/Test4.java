package xp.kaoshi;

import java.io.*;
import java.util.Arrays;

public class Test4 {

    StreamTokenizer in = new StreamTokenizer(
            new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Test4().solve();
    }

    int INF = 1000000007;
    int N, M, K;
    Edge[] edges = new Edge[3005];
    int ans = INF;
    private void solve() throws IOException {
        N = read();
        M = read();
        for (int i = 0; i < M; i++) {
            edges[i] = new Edge(read(), read(), read());
        }
        Arrays.sort(edges, 0, M);
        for (int i = 0; i < M-N+1; i++) {
            ans = Math.min(kruskal(i), ans);
        }
        out.println(ans);
        out.flush();
    }

    private int kruskal(int start) {
        init();
        int max = -INF;
        int min = INF;
        for (int i = start; i < M; i++) {
            Edge e = edges[i];
            if (!isSame(e.from, e.to)) {
                union(e.from, e.to);
                max = Math.max(max, e.dis);
                min = Math.min(min, e.dis);
            }
        }
        return max - min;
    }


    private void union(int from, int to) {
        int px = find(from);
        int py = find(to);
        if (px == py) {
            return;
        }
        if (rank[px] < rank[py]){
            par[px] = py;
        } else {
            par[py] = px;
            if (rank[px] == rank[py]) {
                rank[py]++;
            }
        }
    }

    private boolean isSame(int from, int to) {
        return find(from) == find(to);
    }

    private int find(int x) {
        return par[x] == x ? x
                : (par[x] = find(par[x]));
    }

    int[] par = new int[1005];
    int[] rank = new int[1005];

    private void init() {
        for (int i = 1; i <= N; i++) {
            par[i] = i;
        }
        Arrays.fill(rank,0);
    }

    class Edge implements Comparable<Edge>{
        int from;
        int to;
        int dis;
        Edge(int f, int t, int d) {
            this.from = f;
            this.to = t;
            this.dis = d;
        }

        @Override
        public int compareTo(Edge o) {
            return dis - o.dis;
        }
    }
}
