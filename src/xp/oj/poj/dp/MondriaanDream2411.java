package xp.oj.poj.dp;

import java.io.*;
import java.util.Arrays;

import static java.io.StreamTokenizer.TT_EOF;

/**
 * 铺砖问题，状态压缩，动态规划
 *
 * 如果从上至下放置，保证只有下边缘的格子没有被覆盖。每个单元格的放置方式只和相邻的几个单元格有关系。和二维开关问题类似，前面的状态确定了之后，后面的选择就固定或者是有限个，从上到下从左到右遍历每个单元格。考虑一个单元格，放置小矩形只能选择覆盖当前单元格和左边的单元格，或者覆盖当前单元格和上面的单元格。
 */
public class MondriaanDream2411 {
    public static void main(String[] args) throws IOException {
        new MondriaanDream2411().solve();
    }
    PrintWriter out = new PrintWriter(System.out);
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    double readDouble() throws IOException {
        in.nextToken();
        return in.nval;
    }

    int MAXN = 15;
    int MAXM = 50;
    int INF = 1000000007;
    int H, W, P, A, B;
    double[] tikets = new double[MAXN];
    long ans;
    private void solve() throws IOException {
        while (in.nextToken() != TT_EOF) {
            H = (int) in.nval;
            W = (int) readDouble();
            if (H == 0 && W == 0) {
                break;
            }
            doSovle();
            out.println(ans);
        }
        out.flush();

    }

    long[][] dp = new long[2][1<<MAXN];
    private void doSovle() {
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 0);
        int cnt = 0;
        dp[cnt++][(1<<W)-1] = 1;
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j < W; j++,cnt++) {
                for (int S = 0; S < 1 << W; S++) {
                    if ((S>>j&1) == 0) {
                        dp[cnt&1][S] = dp[(cnt+1)&1][S|1<<j];
                    }else {
                        long res = 0;
                        if (j>0 && (S>>j-1&1)==1) {
                            res += dp[(cnt+1)&1][S&~(1<<j-1)];
                        }
                        if (i>0) {
                            res += dp[(cnt+1)&1][S&~(1<<j)];
                        }
                        dp[cnt&1][S] = res;
                    }
                }
            }
        }
        ans = dp[cnt-1&1][0];

    }
}
