package xp.oj.chiqufa;

import java.io.*;
import java.util.Arrays;

import static java.lang.Math.*;

/**
 * 前缀和,chiqufa
 *
 * 问题描述
 * 给一个长度为N的序列，选择一个区间计算其和的绝对值，使其最接近给定的目标值t。
 * 问题分析
 * 需要表示区间的和，可以使用前缀和相减表示，所以先计算前缀和，对前缀和排序后，就可以得到单调的区间和。
 * 然后通过尺取法计算结果。
 * 注意：前0个元素的前缀和为0也要考虑。
 */
public class BoundFound2566 {

    StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    PrintWriter out = new PrintWriter(System.out);

    int read() throws IOException {
        in.nextToken();
        return (int) in.nval;
    }

    public static void main(String[] args) throws IOException {
        new BoundFound2566().solve();
    }

    int  N, K;
    int MAXN = 100005;
    long INF = Long.MAX_VALUE;
    long ans;
    SumOfKth[] arr = new SumOfKth[MAXN];
    private void solve() throws IOException {
        while (true){
            N = read();
            K = read();
            if (N == 0 && K == 0){
                break;
            }
            arr[0] = new SumOfKth(0, 0);
            long sum = 0;
            for (int i = 1; i <= N; i++) {
                sum+=read();
                arr[i] = new SumOfKth(i, sum);
            }
            Arrays.sort(arr, 0, N + 1);
            for (int i = 0; i < K; i++) {
                int t = read();
                int l = 0, r = 0;
                long min = INF, ans = 0;
                int head = 0, tail = 1;

                while (tail <= N){
                    long delta = arr[tail].sum - arr[head].sum;
                    if (abs(delta - t) < min){
                        min = abs(delta-t);
                        ans = arr[tail].sum - arr[head].sum;
                        l = min(arr[tail].i, arr[head].i);
                        r = max(arr[tail].i, arr[head].i);
                    }
                    if (delta < t) {
                        tail++;
                    } else if (delta > t){
                        head++;
                    } else {
                        break;
                    }
                    if (head == tail) {
                        tail++;
                    }
                }
                out.printf("%d %d %d\n", ans, l+1, r);
            }

        }

        out.flush();
    }
    class SumOfKth implements Comparable<SumOfKth>{
        int i;
        long sum;

        public SumOfKth(int i, long sum) {
            this.i = i;
            this.sum = sum;
        }

        @Override
        public int compareTo(SumOfKth o) {
            return sum < o.sum ? -1 : (sum == o.sum ? 0 : 1);
        }
    }
}
