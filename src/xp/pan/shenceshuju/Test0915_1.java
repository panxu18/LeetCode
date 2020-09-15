package xp.pan.shenceshuju;

import java.util.Scanner;

public class Test0915_1 {
    private static int N, M, K;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        for (int i = 0; i < M; i++) {
            union(in.nextInt(), in.nextInt());
        }
        K = in.nextInt();
        for (int i = 0; i < K; i++) {
            System.out.println(isSame(in.nextInt(), in.nextInt()) ? "Yes" : "Not Sure");
        }
    }

    private static int MAXN = 100010;
    private static int[] parent = new int[MAXN];

    static {
        for (int i = 0; i < MAXN; i++) {
            parent[i] = i;
        }
    }
    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) {
            return;
        }
        parent[px] = py;
    }

    private static int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    private static boolean isSame(int x, int y) {
        return find(x) == find(y);
    }
}
