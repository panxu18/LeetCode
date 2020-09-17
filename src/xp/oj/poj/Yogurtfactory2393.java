package xp.oj.poj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 贪心
 *
 * 贪心策略是维持每周的最低单位成本，每周可能用上周剩下的，也可能生产新的。于是该周单位成本可能为上一周的单位成本加上储存费，
 * 也可能为该周的单位成本。
 */
public class Yogurtfactory2393 {

    public static void main(String[] args) throws IOException {
        new Yogurtfactory2393().solve();
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
        return in.readLine();
    }

    int N, S;
    void solve() throws IOException {
        N = readInt();
        S = readInt();
        long min = Long.MAX_VALUE - 500;
        long cost = 0;
        for (int i = 0; i < N; i++) {
            int c = readInt();
            int y = readInt();
            min = Math.min(c, min + S);
            cost += min * (long) y;
        }
        out.println(cost);
        out.flush();

    }
}
