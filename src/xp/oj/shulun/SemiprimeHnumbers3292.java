package xp.oj.shulun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 伪素数打表
 *
 * 问题描述
 * 定义一种数H满足4n+1，定义H-prime为H集合内的质数（即不考虑集合H之外的因子），定义H-SemiPrime为两个H-prime的乘积。
 * 计算H-SemiPrime的个数。
 * 问题分析
 * 题目给的数据域不是正整数，而是4n+1的正整数，因此首先打表筛选出数据域H内的数，然后在使用素数筛选法打表标记H数据域内的
 * 素数。筛选semiprime可以再次使用素数筛选法，再标记为非素数时，检测是否为semiprime。
 */
public class SemiprimeHnumbers3292 {

    public static void main(String[] args) throws IOException {
        new SemiprimeHnumbers3292().solve();
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter out = new PrintWriter(System.out);
    StringTokenizer st;

    int readInt() throws IOException {
        if (canRead()) {
            return Integer.parseInt(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    long readLong() throws IOException {
        if(canRead()) {
            return Long.parseLong(st.nextToken());
        }
        throw new NoSuchElementException();
    }

    String readLine() throws IOException {
        String s;
        if (st != null &&st.hasMoreTokens()) {
            s = st.nextToken("");
        } else {
            s = in.readLine();
        }
        if (s == null) {
            throw new NoSuchElementException();
        }
        st = null;
        return s;

    }

    char readChar() throws IOException {
        if (canRead()) {
            return st.nextToken().charAt(0);
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

    int H;
    int N = 1000001;
    int[] dp = new int[1000005];
    boolean[] isHPrime = new boolean[1000005];
    boolean[] isHSemiPrime = new boolean[1000005];
    private void solve() throws IOException {
        checkPrimes();
        while (true){
            H = readInt();
            if (H == 0) {
                break;
            }
            out.printf("%d %d\n", H, dp[H]);
        }
        out.flush();
    }

    void checkPrimes() {
        for (int i = 2; i < N; i++) {
            if ((i - 1) % 4 == 0) {
                isHPrime[i] = true;
            }
        }
        for (int i = 2; i * i < N; i++) {
            if (isHPrime[i]) {
                for (int j = i<<1; j < N; j += i) {
                    isHPrime[j] = false;
                }
            }
        }
        for (int i = 2; i * i < N; i++) {
            if (isHPrime[i]) {
                for (int j = 2; j *  i < N; j++) {
                    if (isHPrime[j]) {
                        isHSemiPrime[i*j] = true;
                    }
                }
            }
        }

        dp[1] = 0;
        for (int i = 2; i < N; i++) {
            if (isHSemiPrime[i]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i - 1];
            }
        }
    }

}
