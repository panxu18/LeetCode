package xp.tiaozhanchengxujingsai.poj.dp;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.min;

/**
 * 双调TSP，旅行者问题，动态规划
 *
 * 问题描述
 * 平面上N个点，从中找出一个最短回路，起点是最左端的点，先从起点到最右端的点，严格从左向右，然后在回到起点，严格从右向左。
 * 问题分析
 * 由于路径的单调性，所以从起点开始通过最优决策可以获得全局最优解。假设最优的回路可以视为两条路径A和B。Ai表示路径A的最后一个点是i点，Bj表示路径B的最后一个点是j点，且i比j大（实际上没有标记A和B，只是方便理解）。下一点是i+1，i+1可以添加到A路径上也可以添加到B路径上。
 */
public class Tour2677 {
    public static void main(String[] args) throws IOException {
        new Tour2677().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    int readString() throws IOException {
        in.nextToken();
        return (int)in.nval;
    }
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 200;
    int INF = 1000000007;
    int N;
    Point[] points = new Point[MAXN];
    private void solve() throws IOException {
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            N = (int)in.nval;
            for (int i = 0; i < N; i++) {
                points[i] = new Point(readDouble(), readDouble());
            }
            doSolve();
            double ans = Double.MAX_VALUE;
            for (int i = 0; i < N -1; i++) {
                ans = min(ans, dp[N-1][i] + dis[i][N-1]);
            }
            out.printf("%.2f\n", ans);
        }

        out.flush();
    }



    double[][] dp = new double[MAXN][MAXN];
    private void doSolve() {
        computeDis();
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], Double.MAX_VALUE);
        }
        dp[1][0] = dis[0][1];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                dp[i+1][j] = min(dp[i+1][j], dp[i][j] + dis[i][i+1]);
                dp[i+1][i] = min(dp[i+1][i], dp[i][j] + dis[j][i+1]);
            }
        }
    }

    double[][] dis = new double[MAXN][MAXN];
    private void computeDis() {
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                dis[i][j] = dis(points[i], points[j]);
            }
        }
    }

    private double dis(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    class Point implements Comparable<Point>{
        double x, y;
        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public int compareTo(Point o) {
            return x < o.x ? -1 : (x == o.x ? 0 : 1);
        }
    }
}
