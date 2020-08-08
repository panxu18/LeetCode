package xp.tiaozhanchengxujingsai.poj.dp;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 动态规划，斜率优化
 *
 * 问题描述
 * 在长度为n的非递减数组中，通过将元素减1这种操作，使任意一个元素都有其他k-1各元素和它相同，计算最小的操作次数。
 *
 * 问题分析
 * 通过分析可以使用动态规划解决，dp[i]表示前i个元素操作完成，因此dp[i] = min{dp[j] + arr[i - 1] - arr[j] + arr[i - 2] - arr[j] +...+arr[i-k]- arr[j] | 0<=j<=i-k}。方程可以表示为前n项和：dp[i] = min{dp[j] + (sum[i] - sum[j]) - arr[j] * (i-j) | 0<=j<=i-k}。对于i来说，计算最优解的过程就是在i-k+1条直线方程中寻找最优解。随着i的变化，直线方程是固定的，只是越来越多了。因为要求直线方程的最小值，所以相当于一个凸包问题，求直线的所有交点构成的凸包，最小值一定是在凸多边形上。
 * 将所有直线根据斜率由大到小排序，然后依次计算它们的交点，就可以得到一个凸多边形的下边界。
 */
public class KAnonymousSequence {

    public static void main(String[] args) {
        new KAnonymousSequence().solve();
    }

    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    static long INF = Long.MAX_VALUE;
    private long getLeastCost(long[] arr, int k) {
        long[] dp = new long[arr.length + 1];
        long[] sum = new long[arr.length + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        sum[0] = 0;
        int[] queue = new int[arr.length + 2];
        int s = 0, t = -1;
        queue[0] = 0;
        // 构造直线集合，按与y轴的交点由大到小排序,然后从后向前删除一部分在边界上的的直线
        // 由于这里的斜率已经按照由大到小排序，所以在下面的循环中直接维护直线集合
        /*
            1、直线的加入需要从后向前删除不要的直线，
            2、随着x的增加，队首的直线也不会永远成为最小值
            通过画图可以得出如何判断直线的交点的x坐标大小很容易判断是否需要删除直线，(a2-a1)*(b3-b2)>=(a3-a2)*(b2-b1)
         */

        for (int i = 1; i <= arr.length; i++) {
            sum[i] =sum[i - 1] + arr[i - 1];
        }

        for (int i = k; i <= arr.length; i++) {
            if (i - k >= k) { // 从 i= 2k开始才需要入队，之前都只能向第一个元素对齐
                while (s < t && check(queue[t - 1], queue[t],i-k, dp, arr,sum)) t--;
                queue[++t] = i - k;
            }
            while (s < t && getY(queue[s], i, dp, arr, sum) >= getY(queue[s + 1], i, dp, arr, sum)) s++;
            dp[i] = sum[i] + getY(queue[s], i, dp, arr, sum);
        }
        return dp[arr.length];
    }

    /**
     * 判断是否删除第2条线
     */
    private boolean check(int f1, int f2, int f3, long[] dp, long[] arr, long[] sum) {
        BigInteger a1 = BigInteger.valueOf((dp[f3] + arr[f3] * f3 - sum[f3]) - (dp[f2] + arr[f2] * f2 - sum[f2]))
                .multiply(BigInteger.valueOf(arr[f1] - arr[f2]));
        BigInteger a2 = BigInteger.valueOf((dp[f2] + arr[f2] * f2 - sum[f2]) - (dp[f1] + arr[f1] * f1 - sum[f1]))
                .multiply(BigInteger.valueOf(arr[f2] - arr[f3]));
        return a1.compareTo(a2) >= 0;
    }

    // 计算第一条直线
    private long getY(int i, int x, long[] dp, long[] arr, long[] sum){
        return -arr[i] * x + dp[i] + arr[i] * i - sum[i];
    }

    public void solve() {
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            int n = in.nextInt();
            int k = in.nextInt();
            long[] arr = new long[n];
            for (int j = 0; j < n; j++) {
                arr[j] = in.nextInt();
            }
            out.println(getLeastCost(arr, k));
        }
        out.flush();
    }

    class InputReader {

        private BufferedReader bf;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            bf = new BufferedReader(new InputStreamReader(stream));
            tokenizer = new StringTokenizer("");
        }

        int  nextInt() {
            return Integer.parseInt(next());
        }

        private String next() {
            if (hashNext()) {
                return tokenizer.nextToken();
            }
            return null;
        }

        private boolean hashNext() {
            while (!tokenizer.hasMoreTokens()) {
                String s = null;
                try {
                    s = bf.readLine();
                } catch (IOException e) {
                    return false;
                }
                tokenizer = new StringTokenizer(s);
            }
            return true;
        }

    }
}
