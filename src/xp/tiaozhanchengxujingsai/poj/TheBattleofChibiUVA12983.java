package xp.tiaozhanchengxujingsai.poj;

import java.io.*;
import java.util.Arrays;

/**
 * 树状数组，动态规划
 *
 * dp[i][j]表示长度为i以a[j]结尾的上升子序列数量，转移方程：dp[i][j]=∑dp[i−1][k]，其中 k < j 且 a[k] < a[j]。很明显这是n^3的时间复杂度，有没有办法优化呢？这个方程和前N项和类似，但是有一个附加条件 a[k] < a[j]，因此，使用树状数组可以维护一个动态的区间和，状态转移的时间复杂优化为logn。
 */
public class TheBattleofChibiUVA12983 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new TheBattleofChibiUVA12983().solve();
    }

    int MAXN = 1005;
    int N, M, K;
    int[] arr = new int[MAXN];
    int[][] dp = new int[2][MAXN];
    int MOD = 1000000007;
    private void solve() throws IOException {
        int T = read();
        for (int t = 1; t <= T; t++) {
            caseInit();
            N = read();
            M = read();
            for (int i = 0; i < N; i++) {
                arr[i] = read();
            }
            out.printf("Case #%d: %d\n", t, doSovle());
        }
        out.flush();
    }

    private int doSovle() {
        discretize(arr, 0, N);
        Arrays.fill(dp[1&1], 1);
        Bit bit = new Bit(N);
        for (int i = 2; i <= M; i++) {
            bit.init();
            for (int j = 0; j < N; j++) {
                dp[i & 1][j] = bit.sumMod(arr[j]-1, MOD);
                bit.addMod(arr[j], dp[(i+1)&1][j], MOD);
            }
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            res += dp[M&1][i];
            res %= MOD;
        }
        return res;
    }

    int[] unique = new int[MAXN];
    private void discretize(int[] arr,int from, int to){
        System.arraycopy(arr, from, unique, 0, to - from);
        int len = unique(unique, 0, to-from);
        for (int i = from; i < to; i++) {
            arr[i] = Arrays.binarySearch(unique, 0, len, arr[i])+1;
        }
    }

    private int unique(int[] arr, int from, int to) {
        int k = 0;
        Arrays.sort(arr, from, to);
        for (int i = from; i < to; i++) {
            if (k == 0 || arr[i] != arr[k-1]){
                arr[k++] = arr[i];
            }
        }
        return k;
    }


    private void caseInit() {
        Arrays.fill(dp[0], 0);
        Arrays.fill(dp[1], 0);
    }

    class Bit{
        int[] bit;

        private Bit(int len){
            bit = new int[len+1];
        }

        public void init(){
            Arrays.fill(bit, 0);
        }

        public int sumMod(int x, int mod){
            int s = 0;
            while (x > 0) {
                s += bit[x];
                s %= mod;
                x -= x&-x;
            }
            return s;
        }

        public void addMod(int x, int val, int mod) {
            while (x < bit.length) {
                bit[x] += val;
                bit[x] %= mod;
                x += x & -x;
            }
        }

    }
}
