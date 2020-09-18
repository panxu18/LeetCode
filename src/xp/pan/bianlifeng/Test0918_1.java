package xp.pan.bianlifeng;

import java.io.*;

/**
 * 无向图判断是否有环
 */
public class Test0918_1 {
    private static StreamTokenizer in = null;
    static {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        in.whitespaceChars('[', '[' + 1);
        in.whitespaceChars(']', ']' + 1);
        in.whitespaceChars(',', ',' + 1);
    }

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static final int MAXN = 10007;
    private static Edge[] head = new Edge[MAXN];
    private static boolean[] edgeUsed = new boolean[MAXN<<1];
    private static boolean[] headUsed = new boolean[MAXN];
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        int cnt = 0;
        while (in.nextToken() != StreamTokenizer.TT_EOF) {

            int u = (int) in.nval;
            int v = nextInt();
            head[u] = new Edge(cnt++, u, v, head[u]);
            head[v] = new Edge(cnt++, v, u, head[v]);
        }
        for (int i = 0; i < MAXN; i++) {
            if (head[i] != null && dfs(i)) {
                System.out.println("Y");
                return;
            }
        }
        System.out.println("N");
    }

    private static boolean dfs(int u) {
        headUsed[u] = true;
        for (Edge e = head[u]; e != null; e = e.next) {
            if (edgeUsed[e.id]) {
                continue;
            }
            edgeUsed[e.id] = edgeUsed[e.id^1] = true;

            if (headUsed[e.to]) {
                return true;
            }

            if (dfs(e.to)) {
                return true;
            }
        }
        return false;
    }

    private static class Edge {
        int id;
        int from, to;
        Edge next;

        public Edge(int id, int from, int to, Edge next) {
            this.id = id;
            this.from = from;
            this.to = to;
            this.next = next;
        }
    }
}
