package xp.pan.bianlifeng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 有向图判断是否有环
 */
public class Test0918_2 {
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
        int cnt = 0;
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            String u = next();
            String v = next();
            Edge pre = head.get(u);
            head.put(u, new Edge(u,v, pre));
        }
        for (String s : head.keySet()) {
            if (dfs(s)) {
                System.out.println("Y");
                return;
            }
        }
        System.out.println("N");
    }

    private static boolean dfs(String u) {
        if (headUsed.contains(u)) {
            return false;
        }
        headUsed.add(u);
        for (Edge e = head.get(u); e != null; e = e.next) {
            if (headUsed.contains(e.to)) {
                return true;
            }
            if (dfs(e.to)) {
                return true;
            }
        }
        return false;
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
