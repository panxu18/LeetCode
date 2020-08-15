package xp.oj.poj;

import java.io.*;
import java.util.Arrays;

public class Kindergarten3692 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Kindergarten3692().solve();
    }

    int MAXN = 505;
    int N, M, K;
    Edge[] head = new Edge[MAXN];
    int[] match = new int[MAXN];
    boolean[] vis = new boolean[MAXN];
    boolean[][] map = new boolean[MAXN][MAXN];

    private void solve() throws IOException {
        int cases = 0;
        while ((N=read())!= 0){
            M = read();
            K = read();
            cases++;
            caseInit();
            for (int i = 0; i < K; i++) {
                map[read()][read()] = true;
            }
            constructGrap();
            out.printf("Case %d: %d\n", cases, N+M-biGraphMatch());
        }
        out.flush();
    }

    private void constructGrap() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!map[i][j]){
                    head[i] = new Edge(j+N, head[i]);
                }
            }
        }
    }

    private int biGraphMatch() {
        int res = 0;
        Arrays.fill(match, -1);
        for (int i = 1; i <= N; i++) {
            if (match[i] == -1){
                Arrays.fill(vis, false);
                if (find(i)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean find(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next) {
            int cp = match[e.to];
            if (cp == -1 || !vis[cp] && find(cp)) {
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private void caseInit() {
        Arrays.fill(head, null);
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], false);
        }
    }

    private class Edge {
        int to;
        Edge next;

        public Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }
}
