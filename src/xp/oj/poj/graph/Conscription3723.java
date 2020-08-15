package xp.oj.poj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 最小生成树
 *
 * 问题描述
 * windy要组建一支军队，召集了N个女孩和M个男孩，每个人要付10000RMB，但是如果一个女孩和一个男孩有关系d的，且已经付给了
 * 其中一个人的钱，那么就可以付给另一个人10000-d元，求windy最少要付多少钱。
 * 问题分析
 * 如果所有人之间没有关系，那么花费为(N + m)*10000。如果考虑这些人之间的关系，那么关系为d就可以减少d开销，如果一个男孩
 * 和两个女都有关系，肯定选择较大的d。因此问题就是在图中计算最大树或者最大森林。
 * 注意oj输入的数据不规范，需要使用token分词输入。
 */
public class Conscription3723 {

    public static void main(String[] args) throws IOException {
        new Conscription3723().solve();
    }

    int T, N, M, R;
    Edge[] edges = new Edge[50005];
    StringTokenizer st = null;
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);

    // 输入不规范，只能使用token分割
    void solve() throws IOException {
        T = readInt();
        for (int t = 0; t < T; t++) {
            N = readInt();
            M = readInt();
            R = readInt();
            // 多组输入初始化
            Arrays.fill(edges, null);
            for (int r = 0; r < R; r++) {
                int x = readInt();
                int y = readInt();
                int d = readInt();
                edges[r] = new Edge(x, y + N, -d);
            }
            out.println((N + M) * 10000L + kruskal());
        }
        out.flush();
    }

    private long kruskal() {
        // 初始化并查集
        for (int i = 0; i < N + M; i++) {
            par[i] = i;
            rank[i] = 0;
        }
        Arrays.sort(edges, 0, R);
        int cnt = 0;
        long sum = 0;
        for (int i = 0; i < R; i++) {
            Edge e = edges[i];
            int x = e.from;
            int y = e.to;
            if (!same(x, y)) {
                union(x, y);
                sum += e.d;
                if (++cnt == N + M - 1) {
                    break;
                }
            }
        }
        return sum;
    }

    int[] par = new int[20005];
    int[] rank = new int[20005];

    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (py == px) {
            return;
        }
        if (rank[py] < rank[px]) {
            par[py] = px;
        } else {
            par[px] = py;
            if (rank[px] == rank[py]){
                rank[py]++;
            }
        }
    }

    private int find(int x) {
        return par[x] == x ? x : (par[x] = find(par[x]));
    }

    private boolean same(int x, int y) {
        return find(x) == find(y);
    }

    class Edge implements Comparable<Edge>{
        int from;
        int to;
        long d;

        Edge(int from, int to, long d) {
            this.from = from;
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(Edge e) {
            return d < e.d ? -1 : (d == e.d ? 0 : 1);
        }
    }

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }
}
