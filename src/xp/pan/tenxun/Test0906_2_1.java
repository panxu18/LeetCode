package xp.pan.tenxun;

import java.util.Scanner;


public class Test0906_2_1 {

    static class Edge {
        int u, v;
        Edge next;

        public Edge(int u, int v, Edge next) {
            this.u = u;
            this.v = v;
            this.next = next;
        }
    }

    private static int MAXN = 100005;
    private static Edge[] head = new Edge[MAXN];
    private static boolean used[] = new boolean[MAXN];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int k = in.nextInt();
            int first = in.nextInt();
            int next = -1;
            for (int j = 1; j < k; j++) {
                next = in.nextInt();
                head[first] = new Edge(first, next, head[first]);
                head[next] = new Edge(next, first, head[next]);
                first = next;
            }
        }
        System.out.println(dfs(0));
    }

    private static int dfs(int root) {
        used[root] = true;
        int res = 1;
        for (Edge e = head[root]; e != null; e = e.next) {
            if (!used[e.v]) {
                res += dfs(e.v);
            }
        }
        return res;
    }
}
