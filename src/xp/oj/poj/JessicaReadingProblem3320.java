package xp.oj.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * 尺取法
 * 问题描述
 * 有本书，每一页一个知识点，知识点会重复出现，通过阅读最少的连续页学习到所有至少点。
 * 问题分析
 * 从第i页开始阅读，直到学习全部知识点。然后去掉i页的知识点，从i+1开始，直到学习所有知识点。
 */
public class JessicaReadingProblem3320 {

    public static void main(String[] args) throws IOException {
        new JessicaReadingProblem3320().solve();
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

    int T, N, S;
    int INF = 1000000007;
    int[] arr = new int[100005];
    Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
    private void solve() throws IOException {
        N = readInt();
        for (int i = 0; i < N; i++) {
            arr[i] = readInt();
            map.put(arr[i], 0);
        }
        int tot = map.size();
        int cnt = 0;
        int head = 0, tail = 0;
        int ans = INF;
        for (int i = 0; i < N; i++) {
            int c = map.get(arr[i]);
            if (c == 0) {
                cnt++;
            }
            map.put(arr[tail++], c + 1);

            while (head < tail && cnt >= tot) {
                ans = Math.min(ans, tail - head);
                c = map.get(arr[head]);
                if (c == 1) {
                    cnt--;
                }
                map.put(arr[head++], c - 1);
            }
        }
        out.println(ans);
        out.flush();
    }
}
