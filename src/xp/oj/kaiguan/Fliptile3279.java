package xp.oj.kaiguan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * 二维开关问题
 *
 * 问题描述
 * 有一个n*m的棋盘，0表示白色，1表示黑色。每次可以翻转当前位置，它的上下左右四个位置也会被相应翻转。问最少翻转多少次会
 * 使所有棋面显示为白色，并给出需要翻转的位置，0表示不翻转，1表示翻转。
 * 问题分析
 * 开关问题通常是固定前面的状态，根据前面的状态调整后面的策略。这个题目中后面的翻转会影响之前的状态，所以需要解决这个
 * 问题。翻转一个格子时上下左右的格子会翻转，注意上面的格子，如果通过翻转下一行的格子，就可以对上一行的格子进行调整，
 * 同时这种调整不会影响之前的状态。将前N-1行格子调整好之后，如果最后一排没有好那么说明没有办法将所有格子翻面。
 * 第一排的格子的初始状态有2^N种，对每一种进行模拟。
 */
public class Fliptile3279 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        new Fliptile3279().solve();
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
    int T, N, M,S;
    boolean[] arr = new boolean[MAXN];
    boolean[][] map = new boolean[20][20];
    boolean[][] flip = new boolean[20][20];
    boolean[][] opt = new boolean[20][20];
    int ans;
    private void solve() throws IOException, NoSuchFieldException, IllegalAccessException {
        M = readInt();
        N = readInt();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = (readInt() == 0);
            }
        }
        ans = INF;
        for (int i = 0; i < (1 << N); i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(flip[j], false);
            }
            int num = 0;
            for (int j = 0; j < N; j++) {
                // 第一行初始状态
                if ((i >> j & 1) == 1) {
                    flip[0][N - j - 1] = true;
                    num++;
                }
            }
            num += check();
            if (num < ans) {
                ans = num;
                for (int j = 0; j < M; j++) {
                    System.arraycopy(flip[j], 0, opt[j], 0, 20);
                }
            }
        }
        if (ans == INF) {
            out.println("IMPOSSIBLE");
        } else {
            printans();
        }
        out.flush();
    }

    private void printans(){
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (j < N - 1) {
                    out.printf("%d ", opt[i][j]?1:0);
                } else {
                    out.printf("%d\n", opt[i][j]?1:0);
                }
            }
        }
    }

    int[][] dir = {{0,-1},{0,1},{0,0},{-1,0},{1,0}};
    private int check() {
        int sum = 0;
        // 根据前一行的状态翻转
        for (int i = 1; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (getFlip(i - 1,j) == map[i-1][j]) {
                    flip[i][j] = true;
                    sum++;
                }
            }
        }
        // 检测最后一行
        for (int i = 0; i < N; i++) {
            if (getFlip(M - 1, i) == map[M - 1][i]) {
                return INF; // 失败
            }
        }
        return sum;
    }

    // 查询是否被翻转
    private boolean getFlip(int x, int y) {
        boolean res = false;
        for (int i = 0; i < 5; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx >= 0 && nx < M && ny >= 0 && ny < N) {
                res ^= flip[nx][ny];
            }
        }
        return res;
    }
}
