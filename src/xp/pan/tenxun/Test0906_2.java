package xp.pan.tenxun;

import java.util.Scanner;

/**
 * 深搜
 * 使用邻接表构无向图，使用两条有向边表示
 */
public class Test0906_2 {

    static class Edge {
        int from, to;
        Edge next;

        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }

        private static void addEdges(int from, int to) {
            root[from] = new Edge(from, to, root[from]);
            root[to] = new Edge(to, from, root[to]);
        }
    }

    private static int n, m;
    private static boolean visited[] = new boolean[100000];
    private static Edge[] root = new Edge[100000];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int x = scan.nextInt();
            int curr = scan.nextInt();
            for (int j = 1; j < x; j++) {
                int next = scan.nextInt();
                Edge.addEdges(curr, next);
                curr = next;
            }
        }
        int res = deepFirstSerach(0);
        System.out.println(res);
    }
    private static int deepFirstSerach(int u) {
        if (visited[u]) {
            return 0;
        }
        visited[u] = true;
        int res = 1;
        for (Edge e = root[u]; e != null; e = e.next) {
            res += deepFirstSerach(e.to);
        }
        return res;
    }

}
