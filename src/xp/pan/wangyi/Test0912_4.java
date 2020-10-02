package xp.pan.wangyi;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Test0912_4 {

    private static int MAXN = 20007;
    private static Edge[] head = new Edge[MAXN];
    private static boolean[] used = new boolean[MAXN];
    private static int[] match = new int[MAXN];
    private static int M, F;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        M = in.nextLine().split(" ").length;
        F = in.nextLine().split(" ").length;
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            addEdge(from + 1, to + 1);
        }
        System.out.println(binaryMatch());

    }

    private static int binaryMatch() {
        Arrays.fill(match, 0);
        int res = 0;
        for (int i = 1; i <= M + F; i++) {
            if (match[i] == 0) {
                Arrays.fill(used, false);
                if (dfs(i)) {
                    res++;
                }
            }
        }
        return res;
    }

    private static boolean dfs(int i) {
        used[i] = true;
        for (Edge e = head[i]; e != null; e = e.next) {
            int w = match[e.to];
            if (!used[w] && (w == 0 || dfs(w))) {
                match[i] = e.to;
                match[e.to] = i;
                return true;
            }
        }
        return false;
    }

    private static void addEdge(int from, int to) {
        Edge next = head[from];
        head[from] = new Edge(from, to, next);
    }

    static class Edge{
        int from, to;
        Edge next;
        public Edge(int from, int to, Edge next) {
            this.from = from;
            this.to = to;
            this.next = next;
        }
    }

}
