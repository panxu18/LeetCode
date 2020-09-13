package xp.pan.didi;

import java.util.Arrays;
import java.util.Scanner;

public class Test0913_2 {

    private static int n, m, k;
    private static boolean visited[] = new boolean[107];
    private static Edge[] head = new Edge[107];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            Arrays.fill(head, null);
            Arrays.fill(visited, false);
            n = in.nextInt();
            m = in.nextInt();
            k = in.nextInt();
            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int d = in.nextInt();
                if (d <= k) {
                    Edge.addEdges(u, v);
                }
            }
            int res = deepFirstSerach(1);
            System.out.println(res == n ? "Yes" : "No");
        }
    }

    private static int deepFirstSerach(int u) {
        if (visited[u]) {
            return 0;
        }
        visited[u] = true;
        int res = 1;
        for (Edge e = head[u]; e != null; e = e.next) {
            res += deepFirstSerach(e.to);
        }
        return res;
    }

    static class Edge {
        int from, to;
        Edge next;

        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }

        private static void addEdges(int from, int to) {
            head[from] = new Edge(from, to, head[from]);
            head[to] = new Edge(to, from, head[to]);
        }
    }
}
