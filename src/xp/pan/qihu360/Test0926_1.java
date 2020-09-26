package xp.pan.qihu360;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Test0926_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        S = in.nextInt();
        T = in.nextInt();
        for (int i = 0; i < M; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            head[u] = new Edge(u, v, w, head[u]);
            head[v] = new Edge(v, u, w, head[v]);
        }
        bfs();
        System.out.println(maxWeights[T]);
    }
    private static int N, M, S, T;
    private static boolean[] inQueue = new boolean[100007];
    private static Edge[] head = new Edge[100007];
    private static int[] maxWeights = new int[100007];

    private static void bfs() {
        Queue<Integer> que = new LinkedList<>();
        Arrays.fill(maxWeights, 1000000007);
        que.offer(S);
        maxWeights[S] = 0;
        inQueue[S] = true;
        while (!que.isEmpty()) {
            int cur = que.poll();
            inQueue[cur] = false;
            for (Edge e = head[cur]; e != null; e = e.next) {
                int max = Math.max(maxWeights[cur], e.dis);
                if (max < maxWeights[e.to]) {
                    maxWeights[e.to] = max;
                    if (!inQueue[e.to]) {
                        que.add(e.to);
                    }
                }
            }
        }
    }

    static class Edge {
        int from, to;
        int dis;
        Edge next;

        public Edge(int from, int to, int dis, Edge next) {
            this.from = from;
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
