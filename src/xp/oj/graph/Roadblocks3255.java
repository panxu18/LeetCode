package xp.oj.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * K最短路，dijkstra, A*算法
 *
 * 问题描述
 * 在图中查找从1到N的第k短路径。
 * 问题分析
 * A*算法可以用来搜索所有到达t的路径，在这个问题中因为估计函数就是到达目标的距离，所以按照估价函数由小到大搜索会依
 * 次得到第K最短路径。
 * 假如估计的距离等于最短路径中所在位置到终点的距离，那么便会按照最短路径行走（未卜先知走的当然是最短路线，
 * 而且不会碰壁效率超高）；假如估计值要小于实际值，效率比较低，但会找到最优解；而估计值大于实际值，
 * 会导致找到的解不是最优解。
 */
public class Roadblocks3255 {
    public static void main(String[] args) throws IOException {
        new Roadblocks3255().solve();
    }

    int N, R;
    Edge[] head = new Edge[5005];
    long[] dis = new long[5005];
    long[] dis2 = new long[5005];

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String[] str = null;
        str = in.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        R = Integer.parseInt(str[1]);
        //数据初始化
        Arrays.fill(head, 1, N + 1, null);
        Arrays.fill(dis, 1, N + 1, Long.MAX_VALUE);
        Arrays.fill(dis2, 1, N + 1, Long.MAX_VALUE);

        for (int i = 0; i < R; i++) {
            str = in.readLine().split(" ");
            int a = Integer.parseInt(str[0]);
            int b = Integer.parseInt(str[1]);
            int d = Integer.parseInt(str[2]);
            addEdges(a, b, d);
        }
        dijkstra(1);
        out.println(dis2[N]);
        out.flush();
    }

    private void addEdges(int a, int b, int d) {
        head[a] = new Edge(b, d, head[a]);
        head[b] = new Edge(a, d, head[b]);
    }

    // 使用jdk优先队列，O(eloge)
    void dijkstra(int s){
        PriorityQueue<PairOfDisAndVec> que = new PriorityQueue<PairOfDisAndVec>();
        dis[s] = 0;
        que.offer(new PairOfDisAndVec(0l, s));
        while (!que.isEmpty()) {
            PairOfDisAndVec p = que.poll();
            if (dis2[p.vec] < p.dis) {
                continue;
            }
            for (Edge e = head[p.vec]; e != null; e = e.next) {
                long d2 = p.dis + e.dis;
                long temp = d2;
                if (d2 < dis[e.to]) {
                    // 最短路
                    temp = dis[e.to];
                    dis[e.to] = d2;
                    que.offer(new PairOfDisAndVec(dis[e.to], e.to));
                }
                if (temp < dis2[e.to]) {
                    // 次短路
                    dis2[e.to] = temp;
                    que.offer(new PairOfDisAndVec(dis2[e.to], e.to));
                }
            }
        }
    }

    class PairOfDisAndVec implements Comparable<PairOfDisAndVec>{
        long dis;
        int vec;
        PairOfDisAndVec(long dis, int vec) {
            this.dis = dis;
            this.vec = vec;
        }

        @Override
        public int compareTo(PairOfDisAndVec p) {
            return dis < p.dis ? -1 : (dis == p.dis ? 0 : 1);
        }
    }

    long[] reDis = new long[5005];
    long AStar(int s, int t, int k) {
        int cnt = 0;
        PriorityQueue<PairOfDisAndVec> que = new PriorityQueue<PairOfDisAndVec>();
        if (s == t) {
            k++;
        }
        if (reDis[s] == Long.MAX_VALUE){
            return Long.MAX_VALUE;
        }
        que.offer(new PairOfDisAndVec(reDis[s], s));
        while (!que.isEmpty()) {
            PairOfDisAndVec p = que.poll();
            if (p.vec == t) {
                cnt++;
            }
            if (cnt == k) {
                return p.dis;
            }
            for (Edge e = head[p.vec]; e != null; e = e.next) {
                long g = p.dis - reDis[p.vec] + e.dis;
                que.offer(new PairOfDisAndVec(g + reDis[e.to], e.to));
            }
        }
        return 0L;
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
