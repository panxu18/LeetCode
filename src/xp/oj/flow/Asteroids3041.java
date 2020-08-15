package xp.oj.flow;

import java.io.*;
import java.util.Arrays;

/**
 * 二分图最小顶点覆盖
 *
 * 问题描述
 * 有一个N*N的网格，网格中有一些障碍物，每次选择一行或者一列清除该行该列的所有障碍物。至少要选择多少障碍物。
 * 问题分析
 * 两种方向，一是将行和列分别于障碍物建边，然后选择最少的行和列是所有的障碍物都被覆盖，这就成了一个集合覆盖问题，
 * 没办法求解。第二就是将障碍物作为该行和该列的一条边，问题就变成了二分图的最小顶点覆盖。
 */
public class Asteroids3041 {

    public static void main(String[] args) throws IOException {
        new Asteroids3041().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 505;
    int MAXM = 10005;
    int INF = 1000000007;
    int N, M;
    Edge[] head = new Edge[MAXN<<1];
    long ans;
    private void solve() throws IOException {
        N = (int) readDouble();
        M = (int) readDouble();
        for (int i = 0; i < M; i++) {
            int r = (int) readDouble();
            int c = (int) readDouble();
            head[r] = new Edge(c+N, head[r]);
        }

        out.println(binaryMatch());
        out.flush();

    }

    boolean[] vis = new boolean[MAXN<<1];
    int[] match = new int[MAXN<<1];
    int binaryMatch() {
        Arrays.fill(match, 0);
        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (match[i] == 0){
                Arrays.fill(vis, false);
                if(dfs(i)){
                    res++;
                }
            }
        }
        return res;
    }

    private boolean dfs(int i) {
        vis[i] = true;
        for (Edge e = head[i]; e != null; e = e.next){
            int w = match[e.to];
            if (!vis[w] && (w == 0 || dfs(w))) {
                match[i] = e.to;
                match[e.to] = i;
                return true;
            }
        }
        return false;
    }

    class Edge{
        int to;
        Edge next;
        Edge(int to, Edge next){
            this.to = to;
            this.next = next;
        }
    }
}
