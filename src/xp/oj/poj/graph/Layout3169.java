package xp.oj.poj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 差分约束，spfa算法
 * 问题描述
 * 一共有n头牛，有ml个关系好的牛的信息，有md个关系不好的牛的信息，对应输入的第一行的三个元素，接下来ml行，每行三个
 * 元素A,B,D，表示A牛和B牛相距不希望超过D，接下来md行，每行三个元素A,B,D表示A牛和B牛的相距至少要有D才行。求1号牛和n号牛
 * 的最大距离，如果距离无限大输出-2，如果无解输出-1。
 * 问题分析
 * 典型的差分约束系统，目标xn-x1最大值，即xn-x1<=D，因此约束为xi-xj<=d。根据传递性用边<a,b,c>表示b-a<=c，无路径可达表
 * 示没有约束，如果有负环表示约束之间有矛盾。
 */
public class Layout3169 {

    public static void main(String[] args) throws IOException {
        new Layout3169().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return Integer.parseInt(st.nextToken());
    }

    int N, ML, MD;
    Edge[] head = new Edge[1005];
    void solve() throws IOException {
        N = readInt();
        ML = readInt();
        MD = readInt();
        Arrays.fill(head, null);
        for (int i = 0; i < ML; i++) {
            int a = readInt();
            int b = readInt();
            long d = readInt();
            addEdge(a, b, d);
        }
        for (int i = 0; i < MD; i++) {
            int a = readInt();
            int b = readInt();
            long d = readInt();
            addEdge(b, a, -d);
        }
        // 补全约束关系
        for (int i = 1; i < N; i++) {
            addEdge(i +1, i, 0L);
        }
        out.println(spfa(1));
        out.flush();
    }

    long[] dis = new long[1005];
    boolean[] inq = new boolean[1005];
    int[] sum = new int[1005];
    private long spfa(int s) {

        LinkedList<Integer> que = new LinkedList<Integer>();
        Arrays.fill(dis, Long.MAX_VALUE);
        Arrays.fill(inq, false);
        Arrays.fill(sum, 0);
        que.offer(s);
        dis[s] = 0L;
        inq[s] = true;
        while (!que.isEmpty()) {
            int v = que.poll();
            inq[v] = false;
            for (Edge e = head[v]; e != null; e = e.next) {
                long d = dis[v] + e.dis;
                if (d < dis[e.to]) {
                    dis[e.to] = d;
                    if (!inq[e.to]) {
                        que.offer(e.to);
                        if (++sum[e.to] > N) {
                            return -1L;
                        }
                    }
                }
            }
        }
        return dis[N] == Long.MAX_VALUE ? -2 : dis[N];
    }


    private void addEdge(int a, int b, long d) {
        head[a] = new Edge(b, d, head[a]);
    }

    class Edge{
        int to;
        long dis;
        Edge next;

        Edge(int to, long dis, Edge next){
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
