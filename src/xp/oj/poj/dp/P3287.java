package xp.oj.poj.dp;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.max;

/**
 * 树状数组，动态规划
 *
 * 转态转移方程是真的想不到，看了题解之后竟然非常简洁，ｆ［ｉ］［ｊ］＝ｍａｘ｛ｆ［ｋ］［ｌ］｝＋１（１＜＝ｋ＜ｉ，０＜＝ｌ＜＝ｊ，ｈ［ｉ］＋ｊ＞＝ｈ［ｋ］＋ｌ）。这个转移方程有一个前提，就是每次更新的区间是一个后缀区间，也就是每次都是将最后一部分拔高。对于某一苗子，前面的苗子拔高次数一定不超过当前苗子的拔高次数。所以f[i][j]表示前i个苗子拔高j次时最长非降序列等于前i-1个苗子拔高次数不超过j次的最大值加1。
 * 因为多了一个限制条件ｈ［ｉ］＋ｊ＞＝ｈ［ｋ］＋ｌ，可以从遍历i的过程中可以将１＜＝ｋ＜ｉ从限制条件中去掉，然后就可以通过二维树状数组计算二维前缀最大值。
 */
public class P3287 {
    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new P3287().solve();
    }

    private int MAXN = 10005;
    private int MAXK = 505;
    private int N, M, K;
    private int[] arr = new int[MAXN];
    private void solve() throws IOException {
        N = read();
        K = read();
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }
        int ans = 0;
        MulBit bit = new MulBit(MAXN,MAXK);
        for (int i = 0; i < N; i++) {
            for (int j = K; j >= 0; j--) {
                int fij = bit.query(arr[i] + j, j + 1) + 1;
                bit.change(arr[i] + j, j+1, fij);
                ans = max(ans, fij);
            }
        }
        out.println(ans);
        out.flush();
    }

    private class MulBit{
        int[][] bit;

        public MulBit(int l1, int l2){
            this.bit = new int[l1 + 1][l2 + 1];
        }

        public void init(){
            for (int i = 0; i < bit.length; i++) {
                Arrays.fill(bit[i], 0);
            }
        }

        public int query(int row, int col){
            int res = 0;
            for (; row > 0; row -= row & -row) {
                for (int i = col; i > 0; i -= i & -i) {
                    res = max(res, bit[row][i]);
                }
            }
            return res;
        }

        public void change(int row, int col, int x) {
            for (; row < bit.length; row += row & -row) {
                for (int i = col; i < bit[0].length; i += i & -i) {
                    bit[row][i] = max(bit[row][i], x);
                }
            }
        }

    }
}
