package xp.oj.kaiguan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * 开关问题, FIFO队列
 *
 * 问题描述
 * N头牛排成了一列。每头牛头向前或向后。为了让所有的牛都面向前方，农夫约翰买了一台自动转向的机器。这个机器在购买时就必
 * 须设定一个数值K，机器每操作一次恰好使K头连续的牛转向。求让所有牛都能面向前方需要的最少操作次数M和对应的最小的K.
 * 问题分析
 * 开关问题，通常是固定开头的状态，后面的状态根据开头的状态进行调整。
 * 假设翻转长度为K，如何计算每个牛当前状态？需要快速模拟翻转，依次计算状态需要O(N*K）。对于每头牛i来说，前面的K-1头牛进
 * 行翻转时，才会让第i头牛翻转。所以我需要保存前K-1次操作，0为未执行翻转，1为将后面连续K头牛翻转，和为偶数时第i头牛就仍
 * 然是初始状态，否则被翻转了。
 * 注意点：最后K-1头牛能操作，只能作为判断，此时仍然要维护FIFO队列。
 */
public class FaceTheRightWay3276 {

    public static void main(String[] args) throws IOException {
        new FaceTheRightWay3276().solve();
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

    int INF = 1000000007;
    int MAXN = 50005;
    int T, N, S;
    boolean[] arr = new boolean[MAXN];
    int ans;
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            char c = readChar();
            arr[i] = c == 'F';
        }
        ans = INF;
        int k = INF;
        for (int i = 1; i <= N; i++) {
            int m = check(i);
            if (m < ans) {
                ans = m;
                k = i;
            }
        }
        out.printf("%d %d\n", k, ans);
        out.flush();
    }

    boolean[] flip = new boolean[MAXN];
    private int check(int k){
        Arrays.fill(flip, false);
        int head = 0, tail = 0;
        boolean cnt = false;
        for (int i = 0; i < N - k + 1; i++) {
            flip[tail] = cnt == arr[i];
            cnt ^= flip[tail++];
            if (tail - head == k) {
                cnt ^= flip[head++];
            }
        }
        for (int i = N - k + 1; i < N; i++) {
            if (cnt == arr[i]) {
                return INF;
            }
            if (++tail - head == k) {
                cnt ^= flip[head++];
            }
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (flip[i]) {
                sum++;
            }
        }
        return sum;
    }
}
