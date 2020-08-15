package xp.oj;

import java.util.Scanner;

public class AnniversaryParty {

    public static void main(String[] args) {
//        new AnniversaryParty().solve();

        double interval = 500;
        int a = 800;
        System.out.println(a/interval);
    }

    int[] award;
    Edge[] head;
    int[][] dp; // 第i个顶点选或者不选
    private void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        init(n);
        for (int i = 1; i <= n; i++) {
            award[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int s = in.nextInt();
            int f = in.nextInt();
            addEdge(s,f);
        }
        dfs(1, 0); // 任意顶点开始都可以
        System.out.println(Math.max(dp[1][0], dp[1][1]));
        
    }

    private void dfs(int i, int f) {
        dp[i][1] = award[i];
        for (Edge e = head[i]; e != null; e = e.next) {
            if (e.to == f) continue;
            dfs(e.to, i);
            dp[i][0] += Math.max(dp[e.to][0], dp[e.to][1]);
            dp[i][1] += dp[e.to][0];
        }
    }

    private void addEdge(int s, int f) {
        head[s] = new Edge(head[s], f);
        head[f] = new Edge(head[f], s);
    }

    private void init(int n) {
        award = new int[n + 1];
        head = new Edge[n + 1];
        dp = new int[n + 1][2];

    }

    class Edge{
        Edge next;
        int to;

        public Edge(Edge next, int to) {
            this.next = next;
            this.to = to;
        }
    }
}
