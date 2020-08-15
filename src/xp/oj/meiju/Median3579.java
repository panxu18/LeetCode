package xp.oj.meiju;

import java.io.*;
import java.util.Arrays;

/**
 * 枚举，第K大值和
 *
 * 问题描述
 * 给N个数，计算它们两两的绝对值和的中位数。
 * 问题分析
 * 根据中位数算法可以在O(N^2)时间内计算中位数，这里选择使用枚举中位数，然后在原序列中判断中位数。根据第K大数的特征有
 * 1、小于等于Xk的数不少于K个
 * 2、小于Xk的数少于K个
 * 所以选择满足条件1最小的数就是中位数，计算最小值可以使用二分查找。
 */
public class Median3579 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int readInt() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new Median3579().solve();
    }

    int N, K, MAXN = 100005;
    int INF = 1000000007;
    long[] arr = new long[MAXN];

    private void solve() throws IOException {
        while (in.nextToken() != StreamTokenizer.TT_EOF){
            N = (int) in.nval;
            K = ((N-1)*N/2 + 1)/2;
            for (int i = 0; i < N; i++) {
                arr[i] = readInt();
            }
            Arrays.sort(arr, 0, N);
            int lb = 0, ub = INF;
            while (lb < ub){
                int mid = (lb + ub) / 2;
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

    // 小于等于m的数不少于K个
    private boolean check(int m){
        long cnt = 0;
        int head = 0, tail = 0;
        while (head < N - 1){
            while (tail < N && arr[tail] - arr[head] <= m) {
                tail++;
            }
            cnt += tail - head - 1;
            head++;
        }
        return cnt >= K;
    }
}
