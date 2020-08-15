package xp.oj.poj;

import java.io.*;
import java.util.Arrays;

public class GirlsAndBoys1466 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new GirlsAndBoys1466().solve();
    }

    int MAXN = 505;
    int N, M, K;
    Edge[] head = new Edge[MAXN];
    int[] match = new int[MAXN];
    boolean[] vis = new boolean[MAXN];

    private void solve() throws IOException {
        in.whitespaceChars(':'+0,':'+1);
        in.whitespaceChars('('+0, '('+1);
        in.whitespaceChars(')'+0, ')'+1);
        while (in.nextToken()!=StreamTokenizer.TT_EOF){
            N = (int) in.nval;
            caseInit();
            for (int i = 0; i < N; i++) {
                read();
                int num = read();
                for (int j = 0; j < num; j++) {
                    head[i] = new Edge(read(), head[i]);
                }
            }
            out.println(N - biGraphMatch());
        }
        out.flush();
    }

    private int biGraphMatch() {
        int res = 0;
        Arrays.fill(match, -1);
        for (int i = 0; i < N; i++) {
            if (match[i] == -1){
                Arrays.fill(vis, false);
                if (find(i)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean find(int v) {
        vis[v] = true;
        for (Edge e = head[v]; e != null; e = e.next) {
            int cp = match[e.to];
            if (cp == -1 || !vis[cp] && find(cp)) {
                match[v] = e.to;
                match[e.to] = v;
                return true;
            }
        }
        return false;
    }

    private void caseInit() {
        Arrays.fill(head, null);
    }

    private class Edge {
        int to;
        Edge next;

        public Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }
}
