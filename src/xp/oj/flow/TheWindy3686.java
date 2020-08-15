package xp.oj.flow;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;

/**
 * 指派问题, KM算法, 最大带权匹配
 *
 * 问题描述
 * 有N个玩具，M个工厂，j号工厂加工i号玩具的时间为Zij，同一个工厂同一时间只能加工一个玩具，计算加工所有玩具的最小时间。
 * 问题分析
 * 这个和逃出迷宫最短时间类似，只是这里每个玩具花费的时间不是单位时间，而是长短不一的时间段。这里考虑每个工厂里加工玩具
 * 的序列，即每个玩具可以选择在该工厂加工的次序，从1到N。如果选择第K个加工，那么还得考虑前面加工了哪些玩具才能计算当前
 * 选择的代价。换一个角度考虑，一个玩具会被多少个玩具等待？总的等待时间是k*Zij，对于一个工厂来说一共有系数N有种可能，
 * 把这N中可能作为顶点进行指派。
 */
public class TheWindy3686 {

    public static void main(String[] args) throws IOException {
        new TheWindy3686().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    String readString() throws IOException {
        in.nextToken();
        return in.sval;
    }

    int MAXN = 3000;
    int INF = 1000000007;
    int T, N, M;
    long ans;
    long[][] cost = new long[300][300];
    long[][] map = new long[60][MAXN];
    private void solve() throws IOException {
        T = (int) readDouble();
        for (int t = 0; t < T; t++) {
            N = (int) readDouble();
            M = (int) readDouble();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    cost[i][j] = (long)readDouble();
                }
            }
            for (int i = 0; i < N; i++) {
                Arrays.fill(map[i], INF);
            }
            for (int i = 0; i < M; i++) {
                for (int k = 0; k < N; k++) {
                    for (int j = 0; j < N; j++) {
                        map[j][(i*N + k)] = -(k+1)*cost[j][i];
                    }
                }
            }
            km();
            out.printf("%.6f\n",(double)-ans/N);
        }

        out.flush();

    }

    int[] match = new int[MAXN];
    boolean[] visy = new boolean[MAXN];
    long[] lablex = new long[MAXN];
    long[] labley = new long[MAXN];
    long[] slack = new long[MAXN];
    int[] pre = new int[MAXN];
    void km(){
        Arrays.fill(match, -1);
        Arrays.fill(labley, 0);
        for (int i = 0; i < N; i++) {
            lablex[i] = -INF;
            for (int j = 0; j < N * M; j++) {
                lablex[i] = max(lablex[i], map[i][j]);
            }
        }
        for (int i = 0; i < N; i++) {
            bfs(i);
        }
        ans  = 0;
        for (int i = 0; i < N * M; i++) {
            if (match[i] >= 0){
                ans += map[match[i]][i];
            }
        }
    }

    private void bfs(int x){
        Arrays.fill(slack, INF);
        Arrays.fill(visy, false);

        long d;
        int y = N*M; // 临时使用
        match[y] = x;
        int ny = -1;
        while (x!=-1){
            // 找x的匹配
            d = INF;
            visy[y] = true;
            for (int i = 0; i < N * M; i++) {
                if (!visy[i] && map[x][i] != INF){
                    if (lablex[x] + labley[i] - map[x][i] < slack[i]) {
                        slack[i] = lablex[x] + labley[i] - map[x][i];
                        pre[i] = y;
                    }
                    if (slack[i] < d) {
                        d = slack[i];
                        ny = i;
                    }
                }
            }
            for (int i = 0; i <= N * M; i++) {
                if (visy[i]){
                    lablex[match[i]] -= d;
                    labley[i] += d;
                } else {
                    slack[i] -= d;
                }
            }
            y = ny;
            x = match[ny];
        }
        while (y<N*M){
            match[y] = match[pre[y]];
            y = pre[y];
        }
    }
}
