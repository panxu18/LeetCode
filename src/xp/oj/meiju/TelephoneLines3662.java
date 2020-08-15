package xp.oj.meiju;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 枚举，01最短路径
 *
 * 问题描述
 * 有K根电线杆，其中有一些电线杆之间可以连接一条电线，现在要使第1根电线杆和第N根电线杆连接。假设供电公司免费提供K根电线
 * ，剩下的电线中最长的电线长度是多少。
 * 问题分析
 * 首先将两种特殊情况单独考虑，1、从电线杆1到电线杆N之间最多只需要K根电线，这样所有电线都是免费的；2、电线杆1到电线杆N之
 * 间无法连通。这两种情况可以使用bfs计算电线杆1到电线杆N的最短连接数。
 * 剩下的情况就需要枚举第K+1根电线的长度d，计算从电线杆1到电线杆N之间最少要多少条距离大于d的电线，把距离大于d的电线看
 * 做1，其他的看做0，就可以使用最短路径计算。对于01最短路径可以使用双端队列BFS优化。
 */
public class TelephoneLines3662 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new TelephoneLines3662().solve();
    }

    int  N, K, P;
    int MAXN = 1005;
    int INF = 1000000007;
    Edge[] head = new Edge[MAXN];
    int[] dis = new int[MAXN];

    private void solve() throws IOException {
        N = readInt();
        P = readInt();
        K = readInt();
        for (int i = 0; i < P; i++) {
            int u = readInt();
            int v = readInt();
            int d = readInt();
            addEdge(u, v, d);
        }
        int k = bfs();
        if (k <= K){
            out.println(0);
        } else if (k == INF){
            out.println(-1);
        } else {
            int lb = 1, ub = 1000007;
            while (lb < ub){
                int mid = (lb + ub) >> 1;
                if (check(mid)){
                    ub = mid;
                } else {
                    lb = mid + 1;
                }
            }
            out.println(ub);
        }
        out.flush();
    }

    // 计算到N经过的边数
    private int bfs() {
        Arrays.fill(dis, INF);
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(1);
        dis[1] = 0;
        while (!que.isEmpty()){
            int now = que.poll();
            for (Edge e = head[now]; e != null; e = e.next){
                if (dis[e.to] == INF){
                    dis[e.to] = dis[now] + 1;
                    que.offer(e.to);
                }
            }
        }
        return dis[N];
    }


    private void addEdge(int u, int v, int d) {
        head[u] = new Edge(u, v, d, head[u]);
        head[v] = new Edge(v, u, d, head[v]);
    }

    boolean[] used = new boolean[MAXN];
    // 判断到达N要经过条长度大于d的边不超过K条
    private boolean check(long d) {
        Arrays.fill(dis, INF);
        Arrays.fill(used, false);
        LinkedList<Integer> que = new LinkedList<Integer>();
        que.offer(1);
        dis[1] = 0;
        while (!que.isEmpty()){
            int now = que.poll();
            if (used[now]) {
                continue;
            }
            used[now] = true;
            for (Edge e = head[now]; e != null; e = e.next){
                int d2 = dis[now];
                if (e.dis > d) {
                    d2++;
                }
                if (d2 < dis[e.to]){
                    if (e.dis <= d){
                        dis[e.to] = d2;
                        que.offerFirst(e.to);
                    } else {
                        dis[e.to] = d2;
                        que.offerLast(e.to);
                    }
                }
            }
        }
        return dis[N] <= K;
    }


    class Edge{
        int from, to, dis;
        Edge next;

        public Edge(int from, int to, int dis, Edge next) {
            this.from = from;
            this.to = to;
            this.dis = dis;
            this.next = next;
        }

    }
}
