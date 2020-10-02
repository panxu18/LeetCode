package xp.pan.wangyi;

import java.util.Scanner;
import java.util.concurrent.BlockingDeque;

public class Test0912_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();
        int[] father = new int[N];
        for (int i = 1; i < N; i++) {
            father[i] = in.nextInt();
        }
        int maxDepth = 0;
        for (int i = 1; i < N; i++) {
            int dep = 0;
            int p = i;
            do {
                dep++;
            } while ((p = father[p]) != 0);
            maxDepth = Math.max(maxDepth, dep);
        }
        if (maxDepth >= K) {
            System.out.println(K + 1);
        } else {
            System.out.println(Math.min(N, 1 + maxDepth + ((K - maxDepth))>>1));
        }

    }

}
