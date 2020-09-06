package xp.pan.tenxun;

import java.util.Scanner;

/**
 * 深搜
 * 使用邻接表构无向图，使用两条有向边表示
 */
public class Test0906_2 {
    private static int N, M;
    private static Edge[] head = new Edge[100010];
    private static boolean visited[] = new boolean[100010];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        // 构图
        for (int i = 0; i < M; i++) {
            int x = in.nextInt();
            int pre = in.nextInt();
            for (int j = 1; j < x; j++) {
                int cur = in.nextInt();
                addEdges(pre, cur);
                pre = cur;
            }
        }
        // dfs
        int ans = dfs(0);
        System.out.println(ans);
    }

    private static int dfs(int u) {
        if (visited[u]) {
            return 0;
        }
        visited[u] = true;
        int res = 1;
        for (Edge e = head[u]; e != null; e = e.next) {
            res += dfs(e.to);
        }
        return res;
    }

    private static void addEdges(int from, int to) {
        head[from] = new Edge(from, to, head[from]);
        head[to] = new Edge(to, from, head[to]);
    }

    static class Edge {
        int from, to;
        Edge next;

        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }
    }
}
