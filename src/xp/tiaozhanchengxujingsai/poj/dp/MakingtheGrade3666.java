package xp.tiaozhanchengxujingsai.poj.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 离散化，动态规划，单调序列调整代价
 *
 * 问题描述
 * 给一个序列，每次只能将一个数加或减1，求将序列修改为单调序列的最小操作次数。
 * 问题分析
 * 先考虑非降序列，如果前i个数已经调整为非降序列，那么比较第i+1和第i个数的大小，就可以确定前i+1个数调整为非降序列的操作次数。每个数调整后可取的值为原序列包含的数。
 * 前i个数调整为非降序列，且第i个数为第j个数时最小操作次数为dp[i][j]，dp[i][j] = min{dp[i - 1][0-j]} + abs(arr[i] - b[j])
 */
public class MakingtheGrade3666 {

    public static void main(String[] args) throws IOException {
        new MakingtheGrade3666().solve();
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

    String readLine() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), "");
        }

        return st.nextToken();
    }

    char readChar() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken().charAt(0);
    }

    int N, P;
    int[] arr = new int[2005];
    int[] b = new int[2005];
    int[][] dp = new int[2][2005];
    int ans = Integer.MAX_VALUE;
    private void solve() throws IOException {
        N = readInt();
        for (int i = 1; i <= N; i++) {
            arr[i] = readInt();
        }
        computeDP();
        reverse(arr, 1, N + 1);
        computeDP();
        out.println(ans);
        out.flush();
    }

    private void computeDP() {
        System.arraycopy(arr, 1, b, 0, N);
        int k = unique(b, 0, N);
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 0);
        for (int i = 0; i < N; i++) {
            int min = dp[i&1][0];
            for (int j = 0; j < k; j++) {
                min = Math.min(min, dp[i&1][j]);
                dp[(i+1)&1][j] = Math.abs(arr[i + 1] - b[j]) + min;
            }
        }
        for (int i = 0; i < k; i++) {
            ans = Math.min(dp[N&1][i], ans);
        }
    }

    private void reverse(int[] list, int s, int t) {
        t -= 1;
        while (s < t) {
            int temp = list[s];
            list[s++] = list[t];
            list[t--] = temp;
        }
    }

    private int unique(int[] list, int s, int t){
        Arrays.sort(list, s, t);
        int k = s;
        for (int i = s + 1; i < t; i++) {
            if (list[i] != list[k]) {
                list[++k] = list[i];
            }
        }
        return k + 1;
    }
}
