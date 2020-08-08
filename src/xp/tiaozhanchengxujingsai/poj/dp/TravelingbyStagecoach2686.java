package xp.tiaozhanchengxujingsai.poj.dp;

import java.io.*;
import java.util.Arrays;

import static java.io.StreamTokenizer.TT_EOF;
import static java.lang.Math.min;

/**
 * DAG最短路径，旅行家问题，状态压缩，动态规划
 *
 * 问题描述
 * 在图中从起点a出发到终点b，每通过一条路需要使用一张车票，经过该路的时间为路的长度除以车票的面值，现有N张不同面值的车票，计算从a到b最短的时间。
 * 问题分析
 * 把剩余的车票当成状态，每次可以选择任意一张车票，去当前可以到达的点，剩余的车票会不断变少，所以可以使用dp求解
 */
public class TravelingbyStagecoach2686 {

    public static void main(String[] args) throws IOException {
        new TravelingbyStagecoach2686().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 10;
    int MAXM = 50;
    int INF = 1000000007;
    int N, M, P, A, B;
    Edge[] head = new Edge[MAXM];
    double[] tikets = new double[MAXN];
    private void solve() throws IOException {
        while (in.nextToken() != TT_EOF) {
            N = (int) in.nval;
            M = (int) readDouble();
            P = (int) readDouble();
            A = (int) readDouble();
            B = (int) readDouble();
            if (N == 0 && M == 0 && P == 0 && A == 0 && B == 0) {
                break;
            }
            for (int i = 0; i < N; i++) {
                tikets[i] = readDouble();
            }
            Arrays.fill(head, null);
            for (int i = 0; i < P; i++) {
                int u = (int) readDouble();
                int v = (int) readDouble();
                double d = readDouble();
                addEdges(u, v, d);
            }

            doSovle();
            double ans = Double.MAX_VALUE;
            for (int i = 0; i < 1 << N; i++) {
                ans = min(dp[i][B], ans);
            }
            if (ans == Double.MAX_VALUE) {
                out.println("Impossible");
            } else {
                out.printf("%.3f\n", ans);
            }
        }

        out.flush();
    }

    double[][] dp = new double[1<<MAXN][MAXM];
    private void doSovle() {
        for (int i = 0; i < 1 << MAXN; i++) {
            Arrays.fill(dp[i], Double.MAX_VALUE);
        }
        dp[0][A] = 0;
        for (int S = 0; S < 1 << N; S++) {
            for (int v = 1; v <= M; v++) {
                if (dp[S][v] != Double.MAX_VALUE) {
                    for (Edge e = head[v]; e != null; e = e.next) {
                        for (int k = 0; k < N; k++) {
                            if ((S>>k&1) == 0) {
                                dp[S|1<<k][e.to] = min(dp[S|1<<k][e.to], dp[S][v] + e.dis/tikets[k]);
                            }
                        }
                    }
                }
            }
        }

    }

    private void addEdges(int u, int v, double d) {
        head[u] = new Edge(v, d, head[u]);
        head[v] = new Edge(u, d, head[v]);
    }

    class Edge{
        int to;
        double dis;
        Edge next;
        Edge(int to, double dis, Edge next) {
            this.to = to;
            this.dis = dis;
            this.next = next;
        }
    }
}
