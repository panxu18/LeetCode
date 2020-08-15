package xp.oj.poj;

import java.io.*;
import java.util.Arrays;

public class MuddyFields2226 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new MuddyFields2226().solve();
    }

    int MAXN = 55;
    int N, M, K;
    int cnt = 0;
    int[][] map = new int[MAXN][MAXN];
    int[][] index = new int[MAXN][MAXN];
    Edge[] head = new Edge[MAXN * MAXN];
    private void solve() throws IOException {
        N = read();
        M = read();
        in.ordinaryChar('.');
        cnt = 0;
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            if (flag == true){
                cnt++;
                flag = true;
            }
            for (int j = 0; j < M; j++) {
                int c = in.nextToken();
                map[i][j] = c;
                if (c == '.' && flag){
                    cnt++;
                    flag = false;
                }
                if (c == '*') {
                    flag = true;
                    index[i][j] = cnt;
                }
            }
        }
        for (int i = 0; i < M; i++) {
            if (flag == true){
                cnt++;
                flag = true;
            }
            for (int j = 0; j < N;j++) {
                int c = map[j][i];
                if (c == '.' && flag){
                    cnt++;
                    flag = false;
                }
                if (c == '*') {
                    flag = true;
                    head[index[j][i]] = new Edge(cnt, head[index[j][i]]);
                }
            }
        }
        if (flag == true){
            cnt++;
        }
        out.println(biGraphMatch());
        out.flush();
    }

    int[] match = new int[MAXN * MAXN];
    boolean[] vis = new boolean[MAXN * MAXN];
    private int biGraphMatch() {
        int res = 0;
        Arrays.fill(match, -1);
        for (int i = 0; i < cnt; i++) {
            if (match[i] == -1){
                Arrays.fill(vis, false);
                if (find(i)){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean find(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next){
            int cp = match[e.to];
            if (cp == -1 || !vis[cp] && find(cp)) {
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private class Edge{
        int to;
        Edge next;

        public Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }
}
