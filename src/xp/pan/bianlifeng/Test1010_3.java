package xp.pan.bianlifeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;

public class Test1010_3 {
    private static StreamTokenizer in = null;
    static {
        in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        in.whitespaceChars('-', '-' + 1);
        in.whitespaceChars('>', '>' + 1);
    }

    private static int nextInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    private static String next() throws IOException {
        in.nextToken();
        return in.sval;
    }

    private static final int MAXN = 10007;
    private static HashMap<String, Edge> head = new HashMap<>();
    private static HashSet<String> headUsed = new HashSet<>();
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        String root = next();
        int cnt = 0;
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            String u = next();
            String v = next();
            Edge pre = head.get(u);
            head.put(u, new Edge(u,v, pre));
        }
        System.out.println(dfs(root) - 1);

    }

    private static int dfs(String u) {
        headUsed.add(u);
        int cnt = 1;
        for (Edge e = head.get(u); e != null; e = e.next) {
            if (!headUsed.contains(e.to)) {
                cnt += dfs(e.to);
            }
        }
        return cnt;
    }

    private static class Edge {
        String from, to;
        Edge next;

        public Edge( String from, String to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }
    }
}
