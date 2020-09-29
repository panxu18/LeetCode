package xp.pan.qihu360;

import java.util.Scanner;

public class Test0926_1 {
    private static int A, B, K, V;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            A = in.nextInt();
            B = in.nextInt();
            K = in.nextInt();
            V = in.nextInt();

            System.out.println(solve(A, B, K, V));
        }
    }

    private static int solve(int a, int b, int k, int v) {
        if (a <= 0) {
            return 0;
        }
        int cnt = Math.min(b, k - 1);
        a -= (cnt + 1) * v;
        b -= cnt;
        return solve(a, b, k, v) + 1;
    }
}
