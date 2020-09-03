package xp.pan.baidu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Test0903_2 {
    private static int T, N, M;
    private static int MAXN = 1010;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        T = in.nextInt();
        for (int t = 0; t < T; t++) {
            N = in.nextInt();
            M = in.nextInt();
            init();
            for (int i = 0; i < M; i++) {
                int k = in.nextInt();
                for (int j = 0; j < k; j++) {
                    int l = in.nextInt();
                    int r = in.nextInt();
                    addRange(l, r, 1);
                }
            }
            ArrayList<Integer> resultList = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if (query(i,i) == M) {
                    resultList.add(i);
                }
            }
            System.out.println(resultList.size());
            System.out.println(resultList.stream()
                    .map(String::valueOf).collect(Collectors.joining(" ")));
        }

    }

    private static long[] bit1 = new long[MAXN];
    private static long[] bit2 = new long[MAXN];
    private static void init() {
        Arrays.fill(bit1, 0L);
        Arrays.fill(bit2, 0L);
    }
    private static void addRange(int i, int j, long x) {
        add(bit1, i, -(i - 1) * x);
        add(bit2, i, x);
        add(bit1, j + 1, j * x);
        add(bit2, j + 1, -x);
    }

    private static long query(int i, int j) {
        long r1 = sum(bit1, j) - sum(bit1, i - 1);
        long r2 = sum(bit2, j) * j - sum(bit2, i - 1) * (i - 1);
        return r1 + r2;
    }

    private static void add(long[] bit, int i, long a) {
        while (i <= N) {
            bit[i] += a;
            i += i&-i;
        }
    }

    private static long sum(long[] bit, int i) {
        long res = 0;
        while (i > 0) {
            res += bit[i];
            i -= i&-i;
        }
        return res;
    }
}
