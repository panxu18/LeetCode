package xp.oj.meiju;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 二分枚举
 *
 * 问题描述
 * 给n条绳子，长度分别为Li，如果要获得K条长度一样的绳子，最大可以获得多长的绳子。
 * 问题分析
 * 枚举所有长度，判断是否可以获得K条。如果能获得长度为L的绳子，那么一定可以多的长度小于L的绳子，所有符合二分搜索条件。
 * 由于题目中的长度是浮点型的，所有有无穷多种长度，所以这里肯定取一个精度作为下界。简单来计算的2^30差不过千万左右，
 * 已经可以达到小数后三位，这里精度取大一点，小数点后24位。
 */
public class CableMaster1064 {
    public static void main(String[] args) throws IOException {
        new CableMaster1064().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    double readDouble() throws IOException {
        if(canRead()) {
            return Double.parseDouble(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    boolean canRead() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String s = in.readLine();
            if (s != null) {
                st = new StringTokenizer(s, " ");
            } else {
                return false;
            }
        }
        return true;
    }

    int MAXN = 10005;
    double[] arr = new double[MAXN];
    int N, K;
    private void solve() throws IOException {
        N = readInt();
        K = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = -readDouble();
        }
        Arrays.sort(arr, 0, N);

        double l = 0.0;
        double r = 100000.0;
        for (int i = 0; i < 100; i++) {
            double m = (l + r) / 2;
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] + m > 0) {
                    break;
                }
                sum += (int)(-arr[j] / m);
            }
            if (sum >= K) {
                l = m;
            } else {
                r = m;
            }
        }
        out.printf("%.2f\n", Math.floor(r*100)/100);
        out.flush();
    }
}
