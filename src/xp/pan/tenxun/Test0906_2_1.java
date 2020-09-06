package xp.pan.tenxun;

import java.util.Scanner;

public class Test0906_2_1 {

    public static class E {
        int v;
        int u;
        E next;

        public E(int u, int v, E next) {
            this.u = u;
            this.v = v;
            this.next = next;
        }
    }

    private static final int MAXI = 100001;
    private static final E[] HEAD = new E[MAXI];
    private static final boolean[] USED = new boolean[MAXI];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            int next = -2;
            int first = sc.nextInt();
            for (int j = 1; j < k; j++) {
                next = sc.nextInt();
                HEAD[first] = new E(first, next, HEAD[first]);
                HEAD[next] = new E(next, first, HEAD[next]);
                first = next;
            }
        }
        System.out.println(dfs(0));
    }

    private static int dfs(int root) {
        USED[root] = true;
        int res = 1;
        for (E e = HEAD[root]; e != null; e = e.next) {
            if (!USED[e.v]) {
                res += dfs(e.v);
            }
        }
        return res;
    }
}
