package xp.oj.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * 深搜
 *
 * 问题描述
 * 一个人在房间的@处，房间里铺设着红色和黑色的瓷砖，这个人只准走黑色的瓷砖，不能走红色的瓷砖黑色的瓷砖用‘.’表示，
 * 红色的瓷砖用'#'表示，求这个人可以走多少个瓷砖。
 * 问题分析
 * 从开始点的4个方向进行dfs。
 */
public class RedandBlack1979 {
    public static void main(String[] args) throws IOException {
        new RedandBlack1979().solve();
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

    String next() throws IOException {
        while (st == null  || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine(), " ");
        }
        return st.nextToken();
    }

    long W, H;
    char[][] arr = new char[25][25];
    void solve() throws IOException {
        while (true) {
            W = readInt();
            H = readInt();
            if (W == 0 && H == 0) {
                break;
            }
            for (int i = 0; i < H; i++) {
                String str = next();
                str.getChars(0, str.length(), arr[i], 0);
            }
            out.println(getAns());

        }

        out.flush();
    }

    private int getAns() {
        int x = 0, y = 0;
        out: for (x = 0; x < H; x++) {
            for (y = 0; y < W; y++) {
                if (arr[x][y] == '@'){
                    break out;
                }
            }
        }
        return dfs(x, y);
    }

    private int dfs(int x, int y) {
        int res = 0;
        res++;
        arr[x][y] = '#';
        if (x > 0 && arr[x - 1][y] == '.'){
            res += dfs(x - 1, y);
        }
        if (y > 0 && arr[x][y - 1] == '.') {
            res += dfs(x, y - 1);
        }
        if (x < H - 1 && arr[x + 1][y] == '.') {
            res += dfs(x + 1, y);
        }
        if (y < W - 1 && arr[x][y + 1] == '.') {
            res += dfs(x, y + 1);
        }

        return res;
    }
}
